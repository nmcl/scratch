To run normally:

(i) add contents of ../lib to classpath. add ../etc directory to
classpath. Also add ../jta-1_1-classes

(ii) ./build.sh

(iii) native-image -jar BasicJTA.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/jta/etc/jbossts-properties.xml

rorschach:basic marklittle$ !java
java -jar BasicJTA.jar
Aug 21, 2018 4:56:32 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 59185 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService

Trying to start another transaction - should fail!
Transaction did not begin: javax.transaction.NotSupportedException: BaseTransaction.checkTransactionState - ARJUNA016051: thread is already associated with a transaction!

Committing transaction.

And ...

