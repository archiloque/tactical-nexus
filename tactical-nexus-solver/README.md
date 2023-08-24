A WIP solver in Kotlin.

# Requirements

- PostgreSQL 15
- A `tactical-nexus-solver` database 
- A PostgreSQL user with login `tactical-nexus-solver` that owns this database

```console
mvn clean install exec:java
```

```console
mvn install -Dmaven.test.skip=true assembly:single
java -Xmx2048m -jar target/tactical-nexus-solver-1.0-SNAPSHOT-jar-with-dependencies.jar  
```