 #!/bin/sh

rm *.class

echo "Main-Class: AIT$1
arjuna-properties-file: jbossts-properties.xml" >> manifest.txt

javac AIT$1.java
cp ../lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest.txt AIT$1.jar *.class com org

rm manifest.txt
