mvn install -Dmaven.test.skip=true assembly:single                     
MOVES=$1 java -Xmx4096m -jar target/tactical-nexus-solver-1.0-SNAPSHOT-jar-with-dependencies.jar
