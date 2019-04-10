To run normally:

(i) add contents of ../lib to classpath. add ../etc directory to classpath.

(ii) ./build.sh

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
