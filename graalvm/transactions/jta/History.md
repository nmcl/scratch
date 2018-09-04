Represents a log of the problems found ...

----

Assume we've gone through the ArjunaCore examples and covered the
issues there.

Trying BasicJTA test and got ...

rorschach:basic marklittle$ ./BasicJTA
java.lang.RuntimeException: java.lang.InstantiationException: Type `com.arjuna.ats.jta.common.JTAEnvironmentBean` can not be instantiated reflectively as it does not have a no-parameter constructor or the no-parameter constructor has not been added explicitly to the native image.
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.RuntimeException.<init>(RuntimeException.java:96)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:90)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getDefaultInstance(BeanPopulator.java:53)
	at com.arjuna.ats.jta.common.jtaPropertyManager.getJTAEnvironmentBean(jtaPropertyManager.java:42)
	at com.arjuna.ats.jta.TransactionManager.transactionManager(TransactionManager.java:71)
	at BasicJTA.main(BasicJTA.java:45)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:177)
Caused by: java.lang.InstantiationException: Type `com.arjuna.ats.jta.common.JTAEnvironmentBean` can not be instantiated reflectively as it does not have a no-parameter constructor or the no-parameter constructor has not been added explicitly to the native image.
	at java.lang.Throwable.<init>(Throwable.java:265)
	at java.lang.Exception.<init>(Exception.java:66)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:56)
	at java.lang.InstantiationException.<init>(InstantiationException.java:63)
	at com.oracle.svm.core.hub.DynamicHub.newInstance(DynamicHub.java:598)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:82)
	... 5 more

So looks similar to other Bean configuration problems we saw with
ArjunaCore. Created an issue to track:
https://github.com/nmcl/scratch/issues/30

First try to add the default constructor it's complaining about ...

public JTAEnvironmentBean () {}

But that didn't work so hard code it is ...

public class jtaPropertyManager
{
    public static JTAEnvironmentBean getJTAEnvironmentBean()
    {
	//return BeanPopulator.getDefaultInstance(JTAEnvironmentBean.class);
	return new JTAEnvironmentBean();
    }
	}

However ...

rorschach:basic marklittle$ ./BasicJTA
java.lang.RuntimeException: java.lang.InstantiationException: Type `com.arjuna.ats.jta.common.JTAEnvironmentBean` can not be instantiated reflectively as it does not have a no-parameter constructor or the no-parameter constructor has not been added explicitly to the native image.
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.RuntimeException.<init>(RuntimeException.java:96)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:90)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getDefaultInstance(BeanPopulator.java:53)
	at com.arjuna.ats.jta.common.jtaPropertyManager.getJTAEnvironmentBean(jtaPropertyManager.java:42)
	at com.arjuna.ats.jta.TransactionManager.transactionManager(TransactionManager.java:71)
	at BasicJTA.main(BasicJTA.java:45)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:177)
Caused by: java.lang.InstantiationException: Type `com.arjuna.ats.jta.common.JTAEnvironmentBean` can not be instantiated reflectively as it does not have a no-parameter constructor or the no-parameter constructor has not been added explicitly to the native image.
	at java.lang.Throwable.<init>(Throwable.java:265)
	at java.lang.Exception.<init>(Exception.java:66)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:56)
	at java.lang.InstantiationException.<init>(InstantiationException.java:63)
	at com.oracle.svm.core.hub.DynamicHub.newInstance(DynamicHub.java:598)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:82)
	... 5 more

So something not quite right ... but checking code it seems to be a
build/refresh problem. Try again. And this time during the build ...

Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.165 sec <<< FAILURE! - in com.hp.mwtests.ts.jta.nested.SimpleNestedTest
testEnabled(com.hp.mwtests.ts.jta.nested.SimpleNestedTest)  Time elapsed: 0.126 sec  <<< ERROR!
javax.transaction.NotSupportedException: BaseTransaction.checkTransactionState - ARJUNA016051: thread is already associated with a transaction!
	at com.hp.mwtests.ts.jta.nested.SimpleNestedTest.testEnabled(SimpleNestedTest.java:53)
Caused by: java.lang.IllegalStateException: BaseTransaction.checkTransactionState - ARJUNA016051: thread is already associated with a transaction!
	at com.hp.mwtests.ts.jta.nested.SimpleNestedTest.testEnabled(SimpleNestedTest.java:53)

Running com.hp.mwtests.ts.jta.recovery.CrashAfterResourcesCommitTest
Tests run: 3, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 3.981 sec <<< FAILURE! - in com.hp.mwtests.ts.jta.recovery.CrashAfterResourcesCommitTest
testTwoXAResourceWrappers(com.hp.mwtests.ts.jta.recovery.CrashAfterResourcesCommitTest)  Time elapsed: 1.56 sec  <<< FAILURE!
java.lang.AssertionError: expected:<0> but was:<1>
	at com.hp.mwtests.ts.jta.recovery.CrashAfterResourcesCommitTest.testTwoXAResourceWrappers(CrashAfterResourcesCommitTest.java:138)

testXAResourceAndXAResourceWrapper(com.hp.mwtests.ts.jta.recovery.CrashAfterResourcesCommitTest)  Time elapsed: 1.155 sec  <<< FAILURE!
java.lang.AssertionError: expected:<1> but was:<0>
	at com.hp.mwtests.ts.jta.recovery.CrashAfterResourcesCommitTest.testXAResourceAndXAResourceWrapper(CrashAfterResourcesCommitTest.java:182)

Running com.hp.mwtests.ts.jta.recovery.CrashRecovery2
Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 1.67 sec <<< FAILURE! - in com.hp.mwtests.ts.jta.recovery.CrashRecovery2
test(com.hp.mwtests.ts.jta.recovery.CrashRecovery2)  Time elapsed: 1.272 sec  <<< FAILURE!
java.lang.AssertionError
	at com.hp.mwtests.ts.jta.recovery.CrashRecovery2.test(CrashRecovery2.java:124)

Running com.hp.mwtests.ts.jta.recovery.CrashRecoveryCommitReturnsXA_RETRY
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.632 sec - in com.hp.mwtests.ts.jta.recovery.CrashRecoveryCommitReturnsXA_RETRY
Running com.hp.mwtests.ts.jta.recovery.CrashRecoveryCommitReturnsXA_RETRYHeuristicRollback
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.66 sec - in com.hp.mwtests.ts.jta.recovery.CrashRecoveryCommitReturnsXA_RETRYHeuristicRollback
Running com.hp.mwtests.ts.jta.recovery.RecoveryManagerTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.102 sec - in com.hp.mwtests.ts.jta.recovery.RecoveryManagerTest
Running com.hp.mwtests.ts.jta.recovery.RecoveryTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.144 sec - in com.hp.mwtests.ts.jta.recovery.RecoveryTest
Running com.hp.mwtests.ts.jta.recovery.RecoveryXidsUnitTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 20.156 sec - in com.hp.mwtests.ts.jta.recovery.RecoveryXidsUnitTest
Running com.hp.mwtests.ts.jta.recovery.TestJDBCStoreOffline
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.182 sec - in com.hp.mwtests.ts.jta.recovery.TestJDBCStoreOffline
Running com.hp.mwtests.ts.jta.recovery.XARecoveryModuleHelpersUnitTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.914 sec - in com.hp.mwtests.ts.jta.recovery.XARecoveryModuleHelpersUnitTest
Running com.hp.mwtests.ts.jta.recovery.XARecoveryModuleUnitTest
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.193 sec - in com.hp.mwtests.ts.jta.recovery.XARecoveryModuleUnitTest
Running com.hp.mwtests.ts.jta.recovery.XARecoveryModuleUnitTest2
Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 20.179 sec <<< FAILURE! - in com.hp.mwtests.ts.jta.recovery.XARecoveryModuleUnitTest2
testDelayedPhase2(com.hp.mwtests.ts.jta.recovery.XARecoveryModuleUnitTest2)  Time elapsed: 20.138 sec  <<< FAILURE!
java.lang.AssertionError
	at com.hp.mwtests.ts.jta.recovery.XARecoveryModuleUnitTest2.testDelayedPhase2(XARecoveryModuleUnitTest2.java:124)

Running com.hp.mwtests.ts.jta.recovery.XARecoveryResourceUnitTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 sec - in com.hp.mwtests.ts.jta.recovery.XARecoveryResourceUnitTest
Running com.hp.mwtests.ts.jta.recovery.XAResourceOrphanFilterTest
Tests run: 3, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.164 sec <<< FAILURE! - in com.hp.mwtests.ts.jta.recovery.XAResourceOrphanFilterTest
testJTANodeNameXAResourceOrphanFilter(com.hp.mwtests.ts.jta.recovery.XAResourceOrphanFilterTest)  Time elapsed: 0.112 sec  <<< FAILURE!
java.lang.AssertionError: expected:<ROLLBACK> but was:<ABSTAIN>
	at com.hp.mwtests.ts.jta.recovery.XAResourceOrphanFilterTest.testJTANodeNameXAResourceOrphanFilter(XAResourceOrphanFilterTest.java:72)

testJTAActionStatusServiceXAResourceOrphanFilter(com.hp.mwtests.ts.jta.recovery.XAResourceOrphanFilterTest)  Time elapsed: 0.001 sec  <<< FAILURE!
java.lang.AssertionError: expected:<ROLLBACK> but was:<ABSTAIN>
	at com.hp.mwtests.ts.jta.recovery.XAResourceOrphanFilterTest.testJTAActionStatusServiceXAResourceOrphanFilter(XAResourceOrphanFilterTest.java:106)

Running com.hp.mwtests.ts.jta.subordinate.SubordinateTestCase
Tests run: 32, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.29 sec - in com.hp.mwtests.ts.jta.subordinate.SubordinateTestCase
Running com.hp.mwtests.ts.jta.subordinate.SubordinateTxUnitTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.171 sec - in com.hp.mwtests.ts.jta.subordinate.SubordinateTxUnitTest
Running com.hp.mwtests.ts.jta.timeout.RollbackTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.165 sec - in com.hp.mwtests.ts.jta.timeout.RollbackTest
Running com.hp.mwtests.ts.jta.timeout.SimpleTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.165 sec - in com.hp.mwtests.ts.jta.timeout.SimpleTest
Running com.hp.mwtests.ts.jta.tools.ForgetTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.144 sec - in com.hp.mwtests.ts.jta.tools.ForgetTest
Running com.hp.mwtests.ts.jta.tools.ObjStoreBrowserTest
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.57 sec - in com.hp.mwtests.ts.jta.tools.ObjStoreBrowserTest
Running com.hp.mwtests.ts.jta.twophase.SimpleTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.166 sec - in com.hp.mwtests.ts.jta.twophase.SimpleTest
Running com.hp.mwtests.ts.jta.twophase.TransactionImpleUnitTest
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.176 sec - in com.hp.mwtests.ts.jta.twophase.TransactionImpleUnitTest
Running com.hp.mwtests.ts.jta.twophase.TransactionManagerImpleUnitTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.089 sec - in com.hp.mwtests.ts.jta.twophase.TransactionManagerImpleUnitTest
Running com.hp.mwtests.ts.jta.twophase.UserTransactionUnitTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.166 sec - in com.hp.mwtests.ts.jta.twophase.UserTransactionUnitTest
Running com.hp.mwtests.ts.jta.twophase.XAResourceRecordUnitTest
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.213 sec - in com.hp.mwtests.ts.jta.twophase.XAResourceRecordUnitTest
Running com.hp.mwtests.ts.jta.utils.JNDIManagerUnitTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.066 sec - in com.hp.mwtests.ts.jta.utils.JNDIManagerUnitTest
Running com.hp.mwtests.ts.jta.utils.UtilsUnitTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.145 sec - in com.hp.mwtests.ts.jta.utils.UtilsUnitTest
Running com.hp.mwtests.ts.jta.utxextension.AsyncCommit
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.166 sec - in com.hp.mwtests.ts.jta.utxextension.AsyncCommit
Running com.hp.mwtests.ts.jta.xa.JTARMErrorCommitTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.167 sec - in com.hp.mwtests.ts.jta.xa.JTARMErrorCommitTest
Running com.hp.mwtests.ts.jta.xa.JTATest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.181 sec - in com.hp.mwtests.ts.jta.xa.JTATest
Running com.hp.mwtests.ts.jta.xa.OnePhaseUnitTest
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.18 sec - in com.hp.mwtests.ts.jta.xa.OnePhaseUnitTest
Running com.hp.mwtests.ts.jta.xa.SynchronizationUnitTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.12 sec - in com.hp.mwtests.ts.jta.xa.SynchronizationUnitTest
Running com.hp.mwtests.ts.jta.xa.TxInfoUnitTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.163 sec - in com.hp.mwtests.ts.jta.xa.TxInfoUnitTest
Running com.hp.mwtests.ts.jta.xa.XAUtilsUnitTest
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.143 sec <<< FAILURE! - in com.hp.mwtests.ts.jta.xa.XAUtilsUnitTest
testXAResourceErrorHandler(com.hp.mwtests.ts.jta.xa.XAUtilsUnitTest)  Time elapsed: 0.007 sec  <<< FAILURE!
java.lang.AssertionError
	at com.hp.mwtests.ts.jta.xa.XAUtilsUnitTest.testXAResourceErrorHandler(XAUtilsUnitTest.java:90)

Running com.hp.mwtests.ts.jta.xa.xidcheck
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.146 sec - in com.hp.mwtests.ts.jta.xa.xidcheck

Results :

Failed tests: 
  CrashAfterResourcesCommitTest.testTwoXAResourceWrappers:138 expected:<0> but was:<1>
  CrashAfterResourcesCommitTest.testXAResourceAndXAResourceWrapper:182 expected:<1> but was:<0>
  CrashRecovery2.test:124
  XARecoveryModuleUnitTest2.testDelayedPhase2:124
  XAResourceOrphanFilterTest.testJTAActionStatusServiceXAResourceOrphanFilter:106 expected:<ROLLBACK> but was:<ABSTAIN>
  XAResourceOrphanFilterTest.testJTANodeNameXAResourceOrphanFilter:72 expected:<ROLLBACK> but was:<ABSTAIN>
  XAUtilsUnitTest.testXAResourceErrorHandler:90
Tests in error: 
  SimpleNestedTest.testEnabled:53 » NotSupported BaseTransaction.checkTransactio...


