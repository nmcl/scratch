#!/bin/sh

export CLASSPATH=.:arjuna-5.6.0.Final-SNAPSHOT.jar:common-5.6.0.Final-SNAPSHOT.jar:txoj-5.6.0.Final-SNAPSHOT.jar:jboss-logging.jar:$CLASSPATH

rm *.class

javac -classpath $CLASSPATH AtomicTest.java