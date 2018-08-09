 #!/bin/sh

javac AITBasic.java
cp lib/*.jar .
jar -xvf arjunacore-5.5.1.Final-SNAPSHOT.jar
jar -xvf common-5.5.1.Final-SNAPSHOT.jar
jar -xvf jboss-logging.jar
rm -rf *.jar META-INF *mappings
jar -cmf manifest_aitbasic.txt AITBasic.jar AITBasic.class BasicObject.class com org