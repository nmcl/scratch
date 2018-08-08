 #!/bin/sh

echo "Main-Class: BasicExample
arjuna-properties-file: jbossts-properties.xml" > manifest.txt

javac BasicExample.java
cp ../lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest.txt BasicExample.jar BasicExample.class com org

rm manifest.txt
