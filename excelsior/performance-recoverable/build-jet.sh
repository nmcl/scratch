#!/bin/sh

export JET_HOME=/usr/local/jet11.3-eval-amd64/profile1.8.0_101/jre/bin

export CLASSPATH=.:arjuna-5.6.0.Final-SNAPSHOT.jar:common-5.2.12.Final-SNAPSHOT.jar:txoj-5.6.0.Final-SNAPSHOT.jar:jboss-logging.jar:$CLASSPATH

javac -classpath $CLASSPATH AtomicTest.java

$JET_HOME/java -classpath $CLASSPATH AtomicTest