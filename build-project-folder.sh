#!/usr/bin/env bash

function usage {
    echo "Usage:"
    echo "./build-project-folder.sh [installation path]"
    echo ""
    echo "triggers:"
    echo "--install                      installs the database"
    echo "--desktop-link [link name]     creates a soft link on"
    echo "                               the desktop with the given name"
    exit
}

if [ -z $1 ]; then
    usage
fi

PROJECT_FOLDER=$1
INSTALL=0
DESKTOP_LINK=0
DESKTOP_LINK_NAME=""
shift

while [ "$1" != "" ]; do
    if [ "$1" = "--install" ]; then
        INSTALL=1
        shift
        continue
    elif [ "$1" = "--desktop-link" ]; then
        DESKTOP_LINK=1
        shift
        if [ "$1" = "" ]; then
            usage
        fi
        DESKTOP_LINK_NAME=$1
        shift
        continue
    fi
done

if [ -d $PROJECT_FOLDER ]; then
    echo "removing existing project folder"
    rm -rf $PROJECT_FOLDER
fi

if [ -d ./dist/ ]; then
    rm -rf ./dist/
fi

mkdir $PROJECT_FOLDER
mkdir $PROJECT_FOLDER/distribution
mkdir $PROJECT_FOLDER/source
echo "copying project folder to $PROJECT_FOLDER/source..."
cp -r ./* $PROJECT_FOLDER/source/

cd $PROJECT_FOLDER
ABSOLUTE_PROJECT_PATH=$(pwd)
cd - > /dev/null

echo "compiling and generating javadoc..."
ant jar javadoc > /dev/null
cp ./dist/paintball.jar $PROJECT_FOLDER/distribution/
echo -e "#!/usr/bin/env bash\njava -jar $ABSOLUTE_PROJECT_PATH/distribution/paintball.jar" > $PROJECT_FOLDER/distribution/paintball.sh
chmod +x $PROJECT_FOLDER/distribution/paintball.sh

echo "copying libs and sql scripts..."
cp -r       "./lib"                     "$PROJECT_FOLDER/distribution/"
cp          ./sql/*                     "$PROJECT_FOLDER/"
chmod +x    "$PROJECT_FOLDER/install.sh"
cp -r       "./documentation"           "$PROJECT_FOLDER/dok"
rm -f       $PROJECT_FOLDER/dok/erm/*.png &> /dev/null
rm -f       $PROJECT_FOLDER/dok/uml/class/*.png &> /dev/null
echo "generating diagrams from puml"
java -jar   $PROJECT_FOLDER/distribution/lib/plantuml.jar $PROJECT_FOLDER/dok/erm/**.puml
java -jar   $PROJECT_FOLDER/distribution/lib/plantuml.jar $PROJECT_FOLDER/dok/uml/class/**.puml
java -jar   $PROJECT_FOLDER/distribution/lib/plantuml.jar $PROJECT_FOLDER/dok/uml/activity/**.puml
cp          ./documentation/index.html  "$PROJECT_FOLDER/dok/"
cp -r       "./dist/javadoc"            "$PROJECT_FOLDER/dok/"
cp          "./README.md"               "$PROJECT_FOLDER/dok/"

echo "converting odt files to pdf... make sure all instances of libre office are closed!"
cd $PROJECT_FOLDER/dok
libreoffice --headless --convert-to pdf *.odt > /dev/null
cd - > /dev/null


echo "cleaning up..."
rm -rf ./dist/
rm $PROJECT_FOLDER/source/build-project-folder.sh
rm -rf $PROJECT_FOLDER/source/documentation/

if [ $INSTALL == 1 ]; then
    echo "installation triggered"
    cd $PROJECT_FOLDER
    ./install.sh
    cd - > /dev/null
fi

if [ $DESKTOP_LINK == 1 ]; then
    echo "create link on desktop"
    cd ~/Desktop
    ln -fs $ABSOLUTE_PROJECT_PATH/distribution/paintball.sh ./$DESKTOP_LINK_NAME
fi

echo "project directory created at $ABSOLUTE_PROJECT_PATH"