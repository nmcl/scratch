Some basic ArjunaCore examples.

Note, things in this directory are rough and not intended for others to use. Really a workspace for hacking around some issues with GraalVM and/or Narayana implementation. Will eventually create a stable repository for end results.

To run normally:

(i) add contents of lib to classpath. add etc directory to classpath.

(ii) ./build_basic.sh

(iii) native-image -jar BasicExample.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml

watchmen:arjunacore marklittle$ java -jar BasicExample.jar 
Aug 02, 2018 2:58:56 PM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 02, 2018 2:58:57 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 62751 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService

And ...

watchmen:arjunacore marklittle$ ./BasicExample 
Created BasicAction: 0:ffffc0a8561c:f524:5b63549a:2 status: ActionStatus.RUNNING
Created BasicAction: 0:ffffc0a8561c:f524:5b63549a:3 status: ActionStatus.RUNNING

For Performance1 same (i) as above but with:

(ii) ./build_perf1.sh

(iii) native-image -jar Performance1.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml

watchmen:arjunacore marklittle$ java -jar Performance1.jar
Aug 02, 2018 3:00:39 PM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 02, 2018 3:00:39 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 62759 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Time to create 1000 transactions is 113 milliseconds.

And ...

watchmen:arjunacore marklittle$ ./Performance1 
Time to create 1000 is 2 milliseconds.

For Performance 2 just do the same as Performance 1 except (iii) becomes 'native-image -jar Performance2.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml'

watchmen:arjunacore marklittle$ java -jar Performance2.jar
Aug 03, 2018 10:45:00 AM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 03, 2018 10:45:01 AM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 64130 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Time to create, enlist and commit 1000 transactions is 125 milliseconds.

And ...

watchmen:arjunacore marklittle$ ./Performance2
Time to create, enlist and commit 1000 transactions is 6 milliseconds.

Performance3 is the same except for 'native-image -jar Performance3.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml'

watchmen:arjunacore marklittle$ java -jar Performance3.jar
Aug 03, 2018 11:02:33 AM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 03, 2018 11:02:34 AM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 64211 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Time to create, enlist participants and synchronization and commit 1000 transactions is 623 milliseconds.

And ...

watchmen:arjunacore marklittle$ ./Performance3
Time to create, enlist participants and synchronization and commit 1000 transactions is 7 milliseconds.