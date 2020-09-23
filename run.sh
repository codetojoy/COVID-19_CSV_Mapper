#!/bin/bash 

set -e 

stat ./original/data.csv > /dev/null 2>&1 

./gradlew -q clean test
./gradlew -q installDist
./staging/bin/mapper ./original/data.csv 

echo "Ready."
