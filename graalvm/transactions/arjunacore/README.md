Some basic ArjunaCore examples. You can look through History.md if you
want to see the journey taken to get here.

Note, things in this directory are rough and not intended for others to use. Really a workspace for hacking around some issues with GraalVM and/or Narayana implementation. Will eventually create a stable repository for end results.

----

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

----

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

----

For AITBasic, use 'native-image -jar AITBasic.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml'

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

For AITNested the only change is 'native-image -jar AITNested.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml -H:ReflectionConfigurationFiles=reflectconfig.json'

rorschach:arjunacore marklittle$ java -jar AITNested.jar
Aug 07, 2018 3:41:40 PM com.arjuna.common.util.propertyservice.AbstractPropertiesFactory getPropertiesFromFile
WARN: ARJUNA048002: Could not find configuration file, URL was: null
Aug 07, 2018 3:41:40 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 58674 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
Time to create, enlist and commit 1000 transactions is 2332 milliseconds.

And ...

rorschach:arjunacore marklittle$ ./AITNested 
Time to create, enlist and commit 1000 transactions is 1353 milliseconds.
