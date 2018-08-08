 #!/bin/sh

javac Performance2.java
cp lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest_perf2.txt Performance2.jar Performance2.class SimpleAbstractRecord.class com org