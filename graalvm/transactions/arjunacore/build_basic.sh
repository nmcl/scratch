 #!/bin/sh

javac BasicExample.java
cp lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest_basic.txt BasicExample.jar BasicExample.class com org