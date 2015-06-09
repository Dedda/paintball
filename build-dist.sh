#!/usr/bin/env bash
if [ ! -d "./distribution/" ]; then
    mkdir "./distribution/"
fi
cp "./dist/paintball.jar" "./distribution/"
cp "./sql/projekt.sql" "./distribution/tables.sql"
cp "./sql/views.sql" "./distribution/view.sql"
cp "./sql/data.sql" "./distribution/data.sql"
cp -r "./lib" "./distribution/"