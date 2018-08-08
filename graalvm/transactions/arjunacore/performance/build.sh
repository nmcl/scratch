 #!/bin/sh

echo "Main-Class: Performance$1
arjuna-properties-file: jbossts-properties.xml" >> manifest.txt

javac Performance$1.java
cp ../lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest_perf1.txt Performance$1.jar Performance$1.class com org
