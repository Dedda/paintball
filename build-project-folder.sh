#!/usr/bin/env bash

if [ -z $1 ]; then
    echo "no path given!"
    exit
fi

INSTALL=0

for arg in "$@"; do
    if [ $arg == "--install" ]; then
        INSTALL=1
    fi
done

PROJECT_FOLDER=$1

if [ -d $PROJECT_FOLDER ]; then
    echo "removing existing project folder"
    rm -rf $PROJECT_FOLDER
fi

if [ -d "./dist/" ]; then
    rm -rf "./dist/"
fi

mkdir "$PROJECT_FOLDER"
mkdir "$PROJECT_FOLDER/distribution"
mkdir "$PROJECT_FOLDER/source"
echo "copying project folder to $PROJECT_FOLDER/source..."
cp -r ./* "$PROJECT_FOLDER/source/"

echo "compiling and generating javadoc..."
ant jar javadoc > /dev/null
cp "./dist/paintball.jar" "$PROJECT_FOLDER/distribution/"

echo "copying libs and sql scripts..."
cp -r       "./lib"                     "$PROJECT_FOLDER/distribution/"
cp          ./sql/*                     "$PROJECT_FOLDER/"
chmod +x    "$PROJECT_FOLDER/install.sh"
cp -r       "./documentation"           "$PROJECT_FOLDER/dok"
cp          ./documentation/index.html  "$PROJECT_FOLDER/dok/"
cp -r       "./dist/javadoc"            "$PROJECT_FOLDER/dok/"
cp          "./README.md"               "$PROJECT_FOLDER/dok/"

echo "converting odt files to pdf... make sure all instances of libre office are closed!"
cd "$PROJECT_FOLDER/dok"
libreoffice --headless --convert-to pdf *.odt > /dev/null
cd - > /dev/null


echo "cleaning up..."
rm -rf "./dist/"
rm "$PROJECT_FOLDER/source/build-project-folder.sh"

if [ $INSTALL == 1 ]; then
    echo "installation triggered"
    cd "$PROJECT_FOLDER"
    ./install.sh
    cd - > /dev/null
fi


cd $PROJECT_FOLDER
echo "project directory created at $(pwd)"

cd - > /dev/null