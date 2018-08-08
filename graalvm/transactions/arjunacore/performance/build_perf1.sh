 #!/bin/sh

javac Performance1.java
cp lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest_perf1.txt Performance1.jar Performance1.class com org