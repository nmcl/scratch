Some basic ArjunaCore examples. You can look through History.md if you
want to see the journey taken to get here.

Note, things in this directory are rough and not intended for others to use. Really a workspace for hacking around some issues with GraalVM and/or Narayana implementation. Will eventually create a stable repository for end results.

----

For AITBasic:

(i) add contents of ../lib to classpath. add ../etc directory to 
classpath. 

(ii) ./build.sh Basic

(iii) native-image -jar AITBasic.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml

watchmen:arjunacore marklittle$ java -jar AITBasic.jar
Aug 03, 2018 3:28:33 PM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 03, 2018 3:28:33 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 50070 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Time to create, enlist and commit 1000 transactions is 1533 milliseconds.

And ...

watchmen:arjunacore marklittle$ ./AITBasic 
Time to create, enlist and commit 1000 transactions is 842 milliseconds.

----

For AITNested:

(i) add contents of ../lib to classpath. add ../etc directory to 
classpath. 

(ii) ./build.sh Nested

(iii) native-image -jar AITNested.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml -H:ReflectionConfigurationFiles=reflectconfig.json

rorschach:arjunacore marklittle$ java -jar AITNested.jar
Aug 07, 2018 3:41:40 PM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 07, 2018 3:41:40 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 58674 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Time to create, enlist and commit 1000 transactions is 2332 milliseconds.

And ...

rorschach:arjunacore marklittle$ ./AITNested 
Time to create, enlist and commit 1000 transactions is 1353 milliseconds.
