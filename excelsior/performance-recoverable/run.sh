#!/bin/sh

export CLASSPATH=.:arjuna-5.6.0.Final-SNAPSHOT.jar:common-5.6.0.Final-SNAPSHOT.jar:txoj-5.6.0.Final-SNAPSHOT.jar:jboss-logging.jar:../etc:$CLASSPATH

java -classpath $CLASSPATH AtomicTest