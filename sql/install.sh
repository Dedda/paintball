#!/usr/bin/env bash

export PGPASSWORD="geheim"
echo "creating tables..."
psql -U projekt -h localhost dbprojekt < ./tables.sql &> /dev/null
echo "creating views..."
psql -U projekt -h localhost dbprojekt < ./view.sql &> /dev/null
echo "inserting data..."
psql -U projekt -h localhost dbprojekt < ./data.sql &> /dev/null
echo "database set up!"