# JWT Authentication with Local Reosurce Server.

## Execute Liquibase Diff
1. make the change to the database entity.
2. run mvn clean install to compile.
3. run mvn liquibase:diff to generate the diff change set.
4. check if the generated code is acceptable.
5. add id to a new change file in changes folder.
6. add the entry to the master-changelog.yaml file.
7. run the app
8. check the log for the execution of the change sets.