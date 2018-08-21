 #!/bin/sh

rm *.class

echo "Main-Class: BasicJTA
arjuna-properties-file: jbossts-properties.xml" > manifest.txt

javac BasicJTA.java
cp ../lib/*.jar .
cp -r ../jta-1_1-classes/javax .
jar -xvf narayana-jta-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest.txt BasicJTA.jar BasicJTA.class DummyXA.class com org javax

rm manifest.txt
