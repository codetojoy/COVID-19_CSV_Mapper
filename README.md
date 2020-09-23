## Summary

* basic parser to explore [this dataset](https://www150.statcan.gc.ca/t1/tbl1/en/tv.action?pid=1310078101) as reported in [this CBC article](https://www.cbc.ca/news/canada/public-health-agency-of-canada-covid-19-statistics-1.5733069)

### To Run

* place full dataset in `./original/data.csv`
* execute: `./run.sh`
* test: `./test.sh`

### Layout

* `net.codetojoy.custom` contains code specific to the CSV format
* `net.codetojoy.system` contains semi-generic parsing, and `Runner`
* `net.codetojoy.utils` contains cleaning utilities

### Notes

* the data has multiple rows for various attributes
    - e.g. when `Case information` is `Region`, then `Value` is the region  
    - e.g. when `Case information` is `Age group`, then `Value` is the age group  
    - these attributes are linked via `Case identifier number`
* this code is not efficient, but it does the following:
    - read each attribute as an individual object (with case-id)
    - group all objects by case-id
    - unify info by case-id and write to console
