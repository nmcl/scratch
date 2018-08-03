 #!/bin/sh

javac Performance3.java
cp lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest_perf3.txt Performance3.jar Performance3.class SimpleAbstractRecord.class SyncRecord.class com org