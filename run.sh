#!/bin/bash 

set -e 

stat ./original/data.csv > /dev/null 
stat ./staging/bin/COVID-19_CSV_Mapper > /dev/null 

./gradlew -q clean test
./gradlew -q installDist
./staging/bin/COVID-19_CSV_Mapper ./original/data.csv > out.csv 

echo "Ready... see out.csv in this folder"
