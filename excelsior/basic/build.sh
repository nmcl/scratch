#!/bin/sh

export CLASSPATH=.:arjuna-5.6.0.Final-SNAPSHOT.jar:common-5.2.12.Final-SNAPSHOT.jar:txoj-5.6.0.Final-SNAPSHOT.jar:jboss-logging.jar:$CLASSPATH

javac -classpath $CLASSPATH AtomicTest.java