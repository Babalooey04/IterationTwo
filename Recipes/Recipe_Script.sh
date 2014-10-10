#!/bin/bash

for f in *.txt
do
        mysql -e "LOAD DATA LOCAL INFILE '"$f"' INTO TABLE recipes_tbl FIELDS TERMINATED BY '*'" --local-infile -u root --password=PASSWORD Recipe
done
