Represents a log of the problems found ...

----

To run with GraalVM let's first try a jar of jars ...

(i) jar cmf manifest.txt BasicExample.jar BasicExample.class lib/*

(ii) native-image -jar BasicExample.jar

And ... FAILURE

Build on Server(pid: 18126, port: 56056)
   classlist:     188.06 ms
       (cap):   1,200.05 ms
       setup:   1,538.92 ms
    analysis:   2,857.84 ms
error: Error loading a referenced type: java.lang.NoClassDefFoundError: com/arjuna/ats/arjuna/AtomicAction
Detailed message:
Error: Error loading a referenced type: java.lang.NoClassDefFoundError: com/arjuna/ats/arjuna/AtomicAction
Trace: 
	at parsing BasicExample.main(BasicExample.java:40)
Call path from entry point to BasicExample.main(String[]): 
	at BasicExample.main(BasicExample.java:40)
	at com.oracle.svm.reflect.proxies.Proxy_5_BasicExample_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eJavaMainWrapper_002erun_0028int_002corg_002egraalvm_002enativeimage_002ec_002etype_002eCCharPointerPointer_0029(generated:0)
Original exception that caused the problem: java.lang.NoClassDefFoundError: com/arjuna/ats/arjuna/AtomicAction

OK so now let's expand the jars from ArjunaCore and try again ...

(i) explode the jars in the lib directory and add their contents, along with the two .mappings files, to the jar using 'jar -cmf manifest.txt BasicExample.jar BasicExample.class arjuna-properties.mappings txoj-properties.mappings com org'

(ii) native-image -jar BasicExample.jar

And ... FAILURE

Build on Server(pid: 18126, port: 56056)
   classlist:     222.50 ms
       (cap):     811.66 ms
       setup:   1,141.28 ms
  (typeflow):   3,069.71 ms
   (objects):   1,091.65 ms
  (features):      33.02 ms
    analysis:   4,283.57 ms
    universe:     302.94 ms
error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Detailed message:
Error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Call path from entry point to java.io.FileDescriptor.sync(): 
	at java.io.FileDescriptor.sync(FileDescriptor.java)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.write_state(ShadowingStore.java:602)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_state_internal(FileSystemStore.java:365)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_committed(FileSystemStore.java:139)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.updateState(BasicAction.java:3272)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.forgetHeuristics(BasicAction.java:1348)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Abort(BasicAction.java:1680)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.cancel(TwoPhaseCoordinator.java:124)
	at com.arjuna.ats.arjuna.AtomicAction.cancel(AtomicAction.java:215)
	at com.arjuna.ats.arjuna.coordinator.TransactionReaper.doCancellations(TransactionReaper.java:381)
	at com.arjuna.ats.internal.arjuna.coordinator.ReaperWorkerThread.run(ReaperWorkerThread.java:78)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:238)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_002epthreadStartRoutine_0028com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_0024ThreadStartData_0029(generated:0)

This time due to an issue in the GraalVM: https://github.com/oracle/graal/issues/573

OK so let's add a suitable property to the ArjunaCore property file to disable the use of sync for now ...

<entry key="ObjectStoreEnvironmentBean.objectStoreSync">false</entry>

Try again ...

Build on Server(pid: 20845, port: 59505)*
   classlist:   1,607.25 ms
       (cap):   1,871.16 ms
       setup:   3,682.49 ms
Jul 27, 2018 8:31:57 PM com.arjuna.common.util.ConfigurationInfo getBuildTimeProperties
WARN: ARJUNA048001: Could not find manifest file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/META-INF/MANIFEST.MF
java.io.FileNotFoundException: /Users/marklittle/github/scratch/graalvm/transactions/arjunacore/META-INF/MANIFEST.MF (No such file or directory)
	at java.io.FileInputStream.open0(Native Method)
	at java.io.FileInputStream.open(FileInputStream.java:195)
	at java.io.FileInputStream.<init>(FileInputStream.java:138)
	at java.io.FileInputStream.<init>(FileInputStream.java:93)
	at sun.net.www.protocol.file.FileURLConnection.connect(FileURLConnection.java:90)
	at sun.net.www.protocol.file.FileURLConnection.getInputStream(FileURLConnection.java:188)
	at java.net.URL.openStream(URL.java:1045)
	at com.arjuna.common.util.ConfigurationInfo.getBuildTimeProperties(ConfigurationInfo.java:117)
	at com.arjuna.common.util.ConfigurationInfo.getPropertiesFile(ConfigurationInfo.java:62)
	at com.arjuna.common.util.propertyservice.AbstractPropertiesFactory.initDefaultProperties(AbstractPropertiesFactory.java:187)
	at com.arjuna.common.util.propertyservice.AbstractPropertiesFactory.getDefaultProperties(AbstractPropertiesFactory.java:62)
	at com.arjuna.common.util.propertyservice.PropertiesFactory.getDefaultProperties(PropertiesFactory.java:37)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:86)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getDefaultInstance(BeanPopulator.java:53)
	at com.arjuna.ats.arjuna.common.arjPropertyManager.getCoordinatorEnvironmentBean(arjPropertyManager.java:51)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.<clinit>(BasicAction.java:3749)

Looks like an issue with picking up the property file (through build info in the manifest).

Add some judicious debugging code (aka print statements) and view how the system works with just the normal JVM ...

java -jar BasicExample.jar
**classFileName ConfigurationInfo.class
**pathToThisClass jar:file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/BasicExample.jar!/com/arjuna/common/util/ConfigurationInfo.class
**basePath jar:file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/BasicExample.jar!
**propertyFileName jbossts-properties.xml
**classLoader sun.misc.Launcher$AppClassLoader@5c647e05
**findFile jbossts-properties.xml
**testAbsolutePath /Users/marklittle/github/scratch/graalvm/transactions/arjunacore/jbossts-properties.xml
**trying locateByProperty
**user.dir /Users/marklittle/github/scratch/graalvm/transactions/arjunacore
Jul 29, 2018 4:53:04 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 54787 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService

So we find the property file in the user.dir (aka cwd). Now let's look at what happens when we use GraalVM ...

Build on Server(pid: 32388, port: 55094)*
   classlist:   1,633.88 ms
       (cap):   1,598.41 ms
       setup:   3,228.17 ms
**classFileName ConfigurationInfo.class
**pathToThisClass file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/com/arjuna/common/util/ConfigurationInfo.class
**basePath file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore
Jul 29, 2018 5:03:19 PM com.arjuna.common.util.ConfigurationInfo getBuildTimeProperties
WARN: ARJUNA048001: Could not find manifest file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/META-INF/MANIFEST.MF
java.io.FileNotFoundException: /Users/marklittle/github/scratch/graalvm/transactions/arjunacore/META-INF/MANIFEST.MF (No such file or directory)

There are other errors later but let's start with the root (cause). Looks like it all goes terribly wrong quickly as the pathToThisClass is no longer being resolved as within a jar, which clearly isn't going to be good for us as we would then look for the manifest data!

Let's raise another issue: https://github.com/oracle/graal/issues/576

So seems from the answer to that issue we need to include the ConfigurationInfo class Narayana uses explicitly in the native-image call. So something like 'native-image -jar BasicExample.jar -H:IncludeResources='./com/arjuna/common/util/*ConfigurationInfo.class''

Build on Server(pid: 35253, port: 56921)
   classlist:     335.84 ms
       (cap):     850.85 ms
       setup:   1,535.20 ms
**classFileName ConfigurationInfo.class
**pathToThisClass file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/com/arjuna/common/util/ConfigurationInfo.class
**basePath file:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore
**propertyFileName arjuna-properties.xml
**classLoader java.net.URLClassLoader@49650587
**findFile arjuna-properties.xml
**testAbsolutePath /Users/marklittle/.native-image/machine-id-hostid-0/session-id-2f92/server-id-52c4416f9d4428f66dde9dc05de2016ff99431b8062bf69a2313e1966848d913d8156e0fb35286eb94c98280a3717e3c3f96ae98a566536295ae0a268fc15790/arjuna-properties.xml
**trying locateByProperty
**user.dir /Users/marklittle/.native-image/machine-id-hostid-0/session-id-2f92/server-id-52c4416f9d4428f66dde9dc05de2016ff99431b8062bf69a2313e1966848d913d8156e0fb35286eb94c98280a3717e3c3f96ae98a566536295ae0a268fc15790
**user.home /Users/marklittle
**java.dir /usr/local/graalvm-ee-1.0.0-rc4/Contents/Home/jre
**trying locateByResource
  (typeflow):   2,986.63 ms
   (objects):   1,501.82 ms
  (features):      44.70 ms
    analysis:   4,655.01 ms
    universe:     432.04 ms
error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Detailed message:
Error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Call path from entry point to java.io.FileDescriptor.sync(): 
	at java.io.FileDescriptor.sync(FileDescriptor.java)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.write_state(ShadowingStore.java:602)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_state_internal(FileSystemStore.java:365)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_committed(FileSystemStore.java:139)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.updateState(BasicAction.java:3272)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.forgetHeuristics(BasicAction.java:1348)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Abort(BasicAction.java:1680)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.cancel(TwoPhaseCoordinator.java:124)
	at com.arjuna.ats.arjuna.AtomicAction.cancel(AtomicAction.java:215)
	at com.arjuna.ats.arjuna.coordinator.TransactionReaper.doCancellations(TransactionReaper.java:381)
	at com.arjuna.ats.internal.arjuna.coordinator.ReaperWorkerThread.run(ReaperWorkerThread.java:78)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:238)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_002epthreadStartRoutine_0028com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_0024ThreadStartData_0029(generated:0)

Still not working but at least we're not seeing the issues with the manifest file as previously. The user.dir is still a bit funky (https://github.com/oracle/graal/issues/577) though. Probably Narayana doing property file initialisation through statics. Let's go straight to the property file and define it on the command line:

native-image -jar BasicExample.jar -H:IncludeResources='./com/arjuna/common/util/*ConfigurationInfo.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml

hutdown Server(pid: 35625, port: 58466)
Build on Server(pid: 36297, port: 62721)*
   classlist:   1,880.62 ms
       (cap):   2,459.22 ms
       setup:   4,960.12 ms
**propertyFileName abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml
**classLoader java.net.URLClassLoader@6a63012c
**findFile abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml
**testAbsolutePath /Users/marklittle/.native-image/machine-id-hostid-0/session-id-2f92/server-id-a6c5d9417b9496c0ca404d6052b3bcca117b64aff5ad1a7a080eeda8847657bf408463c3a5a075fceb22f09d3ed60782dcab00156283cc1b8e9d0358d91e7d4d/abs:/Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml
**findFile starts with
Jul 31, 2018 4:04:51 PM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 62736 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService
  (typeflow):   6,859.96 ms
   (objects):   7,738.28 ms
  (features):     111.04 ms
    analysis:  15,176.01 ms
    universe:     822.76 ms
error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Detailed message:
Error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Call path from entry point to java.io.FileDescriptor.sync(): 
	at java.io.FileDescriptor.sync(FileDescriptor.java)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.write_state(ShadowingStore.java:602)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_state_internal(FileSystemStore.java:365)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_committed(FileSystemStore.java:139)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.updateState(BasicAction.java:3272)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.forgetHeuristics(BasicAction.java:1348)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Abort(BasicAction.java:1680)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.cancel(TwoPhaseCoordinator.java:124)
	at com.arjuna.ats.arjuna.AtomicAction.cancel(AtomicAction.java:215)
	at com.arjuna.ats.arjuna.coordinator.TransactionReaper.doCancellations(TransactionReaper.java:381)
	at com.arjuna.ats.internal.arjuna.coordinator.ReaperWorkerThread.run(ReaperWorkerThread.java:78)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:238)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_002epthreadStartRoutine_0028com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_0024ThreadStartData_0029(generated:0)

OK so some further investigation shows that the Narayana object store initialisation isn't behaving as I would expect:

**doSync false com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@1a6c5a9e com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean@37bba400
java.lang.Exception: Stack trace
	at java.lang.Thread.dumpStack(Thread.java:1336)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.<init>(FileSystemStore.java:631)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.<init>(ShadowingStore.java:653)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore.<init>(ShadowNoFileLockStore.java:53)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:129)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.initStore(StoreManager.java:152)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.getActionStore(StoreManager.java:111)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.getRecoveryStore(StoreManager.java:68)
	at com.arjuna.ats.arjuna.recovery.ActionStatusService.<init>(ActionStatusService.java:65)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.lang.Class.newInstance(Class.java:442)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:135)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.start(TransactionStatusManager.java:125)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.<init>(TransactionStatusManager.java:58)
	at com.arjuna.ats.arjuna.coordinator.TxControl.createTransactionStatusManager(TxControl.java:188)
	at com.arjuna.ats.arjuna.coordinator.TxControl.<clinit>(TxControl.java:264)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Begin(BasicAction.java:1375)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:76)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:65)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:116)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:98)
	at BasicExample.main(BasicExample.java:47)
**doSync true com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@31221be2 com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean@377dca04
java.lang.Exception: Stack trace
	at java.lang.Thread.dumpStack(Thread.java:1336)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.<init>(FileSystemStore.java:631)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.<init>(ShadowingStore.java:653)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore.<init>(ShadowNoFileLockStore.java:53)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:129)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.initStore(StoreManager.java:152)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.getCommunicationStore(StoreManager.java:94)
	at com.arjuna.ats.internal.arjuna.recovery.TransactionStatusManagerItem.getStore(TransactionStatusManagerItem.java:87)
	at com.arjuna.ats.internal.arjuna.recovery.TransactionStatusManagerItem.saveThis(TransactionStatusManagerItem.java:280)
	at com.arjuna.ats.internal.arjuna.recovery.TransactionStatusManagerItem.createAndSave(TransactionStatusManagerItem.java:77)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.start(TransactionStatusManager.java:135)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.<init>(TransactionStatusManager.java:58)
	at com.arjuna.ats.arjuna.coordinator.TxControl.createTransactionStatusManager(TxControl.java:188)
	at com.arjuna.ats.arjuna.coordinator.TxControl.<clinit>(TxControl.java:264)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Begin(BasicAction.java:1375)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:76)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:65)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:116)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:98)
	at BasicExample.main(BasicExample.java:47)
**calling synchronousWrites! true com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@31221be2
**synchronousWrites true true and com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@31221be2

Summary: the configuration MBean for the transaction log is picking up the "no sync" data but the participant/communication store creation uses a new instance of the MBean which doesn't see the override. Strange. In running the jar normally (java -jar BasicExample) this doesn't cause an issue but with native-image it still allows sync to be called and that's not right (yet).

Let's add a little hack in FileSystemStore constructor while in parallel work with the Narayana team to figure out what's happening:

//        doSync = objectStoreEnvironmentBean.isObjectStoreSync();
        doSync = arjPropertyManager.getObjectStoreEnvironmentBean().isObjectStoreSync();

Running java -jar BasicExample.jar now gives:

**doSync false com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@1a6c5a9e com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean@37bba400
java.lang.Exception: Stack trace
	at java.lang.Thread.dumpStack(Thread.java:1336)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.<init>(FileSystemStore.java:632)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.<init>(ShadowingStore.java:653)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore.<init>(ShadowNoFileLockStore.java:53)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:129)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.initStore(StoreManager.java:152)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.getActionStore(StoreManager.java:111)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.getRecoveryStore(StoreManager.java:68)
	at com.arjuna.ats.arjuna.recovery.ActionStatusService.<init>(ActionStatusService.java:65)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.lang.Class.newInstance(Class.java:442)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:135)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.start(TransactionStatusManager.java:125)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.<init>(TransactionStatusManager.java:58)
	at com.arjuna.ats.arjuna.coordinator.TxControl.createTransactionStatusManager(TxControl.java:188)
	at com.arjuna.ats.arjuna.coordinator.TxControl.<clinit>(TxControl.java:264)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Begin(BasicAction.java:1375)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:76)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:65)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:116)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:98)
	at BasicExample.main(BasicExample.java:47)
**doSync false com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@31221be2 com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean@377dca04
java.lang.Exception: Stack trace
	at java.lang.Thread.dumpStack(Thread.java:1336)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.<init>(FileSystemStore.java:632)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.<init>(ShadowingStore.java:653)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore.<init>(ShadowNoFileLockStore.java:53)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:129)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.initStore(StoreManager.java:152)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.getCommunicationStore(StoreManager.java:94)
	at com.arjuna.ats.internal.arjuna.recovery.TransactionStatusManagerItem.getStore(TransactionStatusManagerItem.java:87)
	at com.arjuna.ats.internal.arjuna.recovery.TransactionStatusManagerItem.saveThis(TransactionStatusManagerItem.java:280)
	at com.arjuna.ats.internal.arjuna.recovery.TransactionStatusManagerItem.createAndSave(TransactionStatusManagerItem.java:77)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.start(TransactionStatusManager.java:135)
	at com.arjuna.ats.arjuna.recovery.TransactionStatusManager.<init>(TransactionStatusManager.java:58)
	at com.arjuna.ats.arjuna.coordinator.TxControl.createTransactionStatusManager(TxControl.java:188)
	at com.arjuna.ats.arjuna.coordinator.TxControl.<clinit>(TxControl.java:264)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Begin(BasicAction.java:1375)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:76)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.start(TwoPhaseCoordinator.java:65)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:116)
	at com.arjuna.ats.arjuna.AtomicAction.begin(AtomicAction.java:98)
	at BasicExample.main(BasicExample.java:47)
**calling synchronousWrites! false com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@31221be2
**synchronousWrites false true and com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@31221be2
Aug 01, 2018 10:59:47 AM com.arjuna.ats.arjuna.recovery.TransactionStatusManager start
INFO: ARJUNA012170: TransactionStatusManager started on port 60372 and host 127.0.0.1 with service com.arjuna.ats.arjuna.recovery.ActionStatusService

But with native-image -jar BasicExample.jar -H:IncludeResources='./com/arjuna/common/util/*ConfigurationInfo.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml 

**synchronousWrites false true and com.arjuna.ats.internal.arjuna.objectstore.ShadowNoFileLockStore@49d977ee
  (typeflow):   3,378.66 ms
   (objects):   1,403.82 ms
  (features):      36.99 ms
    analysis:   4,943.90 ms
    universe:     282.36 ms
error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Detailed message:
Error: Unsupported method java.io.FileDescriptor.sync() is reachable: Native method. If you intend to use the Java Native Interface (JNI), specify -H:+JNI and see also -H:JNIConfigurationFiles=<path> (use -H:+PrintFlags for details)
To diagnose the issue, you can add the option -H:+ReportUnsupportedElementsAtRuntime. The unsupported element is then reported at run time when it is accessed the first time.
Call path from entry point to java.io.FileDescriptor.sync(): 
	at java.io.FileDescriptor.sync(FileDescriptor.java)
	at com.arjuna.ats.internal.arjuna.objectstore.ShadowingStore.write_state(ShadowingStore.java:603)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_state_internal(FileSystemStore.java:365)
	at com.arjuna.ats.internal.arjuna.objectstore.FileSystemStore.write_committed(FileSystemStore.java:139)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.updateState(BasicAction.java:3272)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.forgetHeuristics(BasicAction.java:1348)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.Abort(BasicAction.java:1680)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.cancel(TwoPhaseCoordinator.java:124)
	at com.arjuna.ats.arjuna.AtomicAction.cancel(AtomicAction.java:215)
	at com.arjuna.ats.arjuna.coordinator.TransactionReaper.doCancellations(TransactionReaper.java:381)
	at com.arjuna.ats.internal.arjuna.coordinator.ReaperWorkerThread.run(ReaperWorkerThread.java:78)
	at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:238)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_002epthreadStartRoutine_0028com_002eoracle_002esvm_002ecore_002eposix_002ethread_002ePosixJavaThreads_0024ThreadStartData_0029(generated:0)

So this looks like it is purely a "is reachable" aspect rather than "is going to happen". So let's add another hack to ShadowingStore to ignore sync ...

 //                    if (synchronousWrites())
                    if (synchronousWrites() && false)

Re-run ...

  (typeflow):   2,959.68 ms
   (objects):   1,161.06 ms
  (features):      33.20 ms
    analysis:   4,235.07 ms
    universe:     259.71 ms
     (parse):   1,063.14 ms
    (inline):     779.18 ms
   (compile):   6,056.73 ms
     compile:   8,403.37 ms
       image:   2,085.77 ms
       write:   1,541.89 ms
     [total]:  18,568.67 ms

----

After backing out debugging, let's try the native image ...

Exception in thread "main" java.lang.reflect.InvocationTargetException
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:89)
	at java.lang.reflect.InvocationTargetException.<init>(InvocationTargetException.java:72)
	at com.oracle.svm.reflect.proxies.Proxy_4_BasicExample_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)
Caused by: java.lang.NullPointerException
	at com.arjuna.ats.arjuna.coordinator.BasicAction.<init>(BasicAction.java:97)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.<init>(TwoPhaseCoordinator.java:55)
	at com.arjuna.ats.arjuna.AtomicAction.<init>(AtomicAction.java:74)
	at BasicExample.main(BasicExample.java:44)
	... 3 more

Hmmm ... let's add more debug to see what's happening ...

Aug 02, 2018 10:50:15 AM com.arjuna.common.internal.util.ClassloadingUtility loadClass
WARN: ARJUNA048004: attempt to load com.arjuna.ats.internal.arjuna.coordinator.CheckedActionFactoryImple threw ClassNotFound. Wrong classloader?
java.lang.ClassNotFoundException: com.arjuna.ats.internal.arjuna.coordinator.CheckedActionFactoryImple
	at java.lang.Throwable.<init>(Throwable.java:287)
	at java.lang.Exception.<init>(Exception.java:84)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:75)
	at java.lang.ClassNotFoundException.<init>(ClassNotFoundException.java:82)
	at com.oracle.svm.core.hub.ClassForNameSupport.forName(ClassForNameSupport.java:51)
	at com.oracle.svm.core.hub.DynamicHub.forName(DynamicHub.java:906)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadClass(ClassloadingUtility.java:57)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadClass(ClassloadingUtility.java:85)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:117)
	at com.arjuna.ats.arjuna.common.CoordinatorEnvironmentBean.getCheckedActionFactory(CoordinatorEnvironmentBean.java:643)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.<init>(BasicAction.java:95)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.<init>(TwoPhaseCoordinator.java:55)
	at com.arjuna.ats.arjuna.AtomicAction.<init>(AtomicAction.java:74)
	at BasicExample.main(BasicExample.java:44)
	at com.oracle.svm.reflect.proxies.Proxy_1_BasicExample_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)

**checkedActionFactory: null
Aug 02, 2018 10:50:15 AM com.arjuna.common.internal.util.ClassloadingUtility loadClass
WARN: ARJUNA048004: attempt to load com.arjuna.ats.internal.arjuna.coordinator.CheckedActionFactoryImple threw ClassNotFound. Wrong classloader?
java.lang.ClassNotFoundException: com.arjuna.ats.internal.arjuna.coordinator.CheckedActionFactoryImple
	at java.lang.Throwable.<init>(Throwable.java:287)
	at java.lang.Exception.<init>(Exception.java:84)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:75)
	at java.lang.ClassNotFoundException.<init>(ClassNotFoundException.java:82)
	at com.oracle.svm.core.hub.ClassForNameSupport.forName(ClassForNameSupport.java:51)
	at com.oracle.svm.core.hub.DynamicHub.forName(DynamicHub.java:906)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadClass(ClassloadingUtility.java:57)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadClass(ClassloadingUtility.java:85)
	at com.arjuna.common.internal.util.ClassloadingUtility.loadAndInstantiateClass(ClassloadingUtility.java:117)
	at com.arjuna.ats.arjuna.common.CoordinatorEnvironmentBean.getCheckedActionFactory(CoordinatorEnvironmentBean.java:643)
	at com.arjuna.ats.arjuna.coordinator.BasicAction.<init>(BasicAction.java:99)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.<init>(TwoPhaseCoordinator.java:55)
	at com.arjuna.ats.arjuna.AtomicAction.<init>(AtomicAction.java:74)
	at BasicExample.main(BasicExample.java:44)
	at com.oracle.svm.reflect.proxies.Proxy_1_BasicExample_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)

Exception in thread "main" java.lang.reflect.InvocationTargetException
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:89)
	at java.lang.reflect.InvocationTargetException.<init>(InvocationTargetException.java:72)
	at com.oracle.svm.reflect.proxies.Proxy_1_BasicExample_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)
Caused by: java.lang.NullPointerException
	at com.arjuna.ats.arjuna.coordinator.BasicAction.<init>(BasicAction.java:100)
	at com.arjuna.ats.arjuna.coordinator.TwoPhaseCoordinator.<init>(TwoPhaseCoordinator.java:55)
	at com.arjuna.ats.arjuna.AtomicAction.<init>(AtomicAction.java:74)
	at BasicExample.main(BasicExample.java:44)

Must be a GraalVM resource "thing" and the fact we dynamically load a lot of classes at runtime. Rather than struggle with Narayana classloading complexities and GraalVM resource strangeness (this doesn't seem to work: 'native-image -jar BasicExample.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml 
'), let's hardwire this into BasicAction for now through the arjPropertyManager:

//		_checkedAction = arjPropertyManager
	//				.getCoordinatorEnvironmentBean().getCheckedActionFactory()
	//				.getCheckedAction(get_uid(), type());
	_checkedAction = new CheckedActionFactory();

Also have to disable CheckedActionTest during build:

//        assertTrue(DummyCheckedAction.factoryCalled());
	//        assertTrue(DummyCheckedAction.called());

And running again ...

Created BasicAction: 0:ffffc0a8561c:f383:5b634e41:2 status: ActionStatus.RUNNING
Created BasicAction: 0:ffffc0a8561c:f383:5b634e41:3 status: ActionStatus.RUNNING

... success!

----

Moving on eventually to StateManager and friends, with AITBasic, we get the following when running the executable created after using 'native-image -jar AITBasic.jar -H:IncludeResources='./com/arjuna/ats/internal/arjuna/coordinator/CheckedActionFactoryImple.class' -Dcom.arjuna.ats.arjuna.common.propertiesFile=abs:///Users/marklittle/github/scratch/graalvm/transactions/arjunacore/etc/jbossts-properties.xml'

Exception in thread "main" java.lang.reflect.InvocationTargetException
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:89)
	at java.lang.reflect.InvocationTargetException.<init>(InvocationTargetException.java:72)
	at com.oracle.svm.reflect.proxies.Proxy_3_AITBasic_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)
Caused by: java.lang.RuntimeException: java.lang.InstantiationException: Type `com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean` can not be instantiated reflectively as it does not have a no-parameter constructor or the no-parameter constructor has not been added explicitly to the native image.
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.RuntimeException.<init>(RuntimeException.java:96)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:90)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:66)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.initStore(StoreManager.java:146)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.setupStore(StoreManager.java:136)
	at com.arjuna.ats.arjuna.StateManager.setupStore(StateManager.java:1144)
	at com.arjuna.ats.arjuna.StateManager.setupStore(StateManager.java:1078)
	at com.arjuna.ats.arjuna.StateManager.activate(StateManager.java:258)
	at com.arjuna.ats.arjuna.StateManager.activate(StateManager.java:168)
	at BasicObject.activate(AITBasic.java:129)
	at BasicObject.<init>(AITBasic.java:72)
	at AITBasic.main(AITBasic.java:51)
	... 3 more
Caused by: java.lang.InstantiationException: Type `com.arjuna.ats.arjuna.common.ObjectStoreEnvironmentBean` can not be instantiated reflectively as it does not have a no-parameter constructor or the no-parameter constructor has not been added explicitly to the native image.
	at java.lang.Throwable.<init>(Throwable.java:265)
	at java.lang.Exception.<init>(Exception.java:66)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:56)
	at java.lang.InstantiationException.<init>(InstantiationException.java:63)
	at com.oracle.svm.core.hub.DynamicHub.newInstance(DynamicHub.java:592)
	at com.arjuna.common.internal.util.propertyservice.BeanPopulator.getNamedInstance(BeanPopulator.java:82)
	... 13 more

Some Graal magic needed to include it because https://github.com/nmcl/scratch/issues/23

Hack around this for now (details in the linked issue).

But ...

Exception in thread "main" java.lang.reflect.InvocationTargetException
	at java.lang.Throwable.<init>(Throwable.java:310)
	at java.lang.Exception.<init>(Exception.java:102)
	at java.lang.ReflectiveOperationException.<init>(ReflectiveOperationException.java:89)
	at java.lang.reflect.InvocationTargetException.<init>(InvocationTargetException.java:72)
	at com.oracle.svm.reflect.proxies.Proxy_8_AITBasic_main.invoke(Unknown Source)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:173)
Caused by: java.lang.NullPointerException
	at com.arjuna.ats.arjuna.objectstore.StoreManager.initStore(StoreManager.java:160)
	at com.arjuna.ats.arjuna.objectstore.StoreManager.setupStore(StoreManager.java:136)
	at com.arjuna.ats.arjuna.StateManager.setupStore(StateManager.java:1144)
	at com.arjuna.ats.arjuna.StateManager.setupStore(StateManager.java:1078)
	at com.arjuna.ats.arjuna.StateManager.activate(StateManager.java:258)
	at com.arjuna.ats.arjuna.StateManager.activate(StateManager.java:168)
	at BasicObject.activate(AITBasic.java:129)
	at BasicObject.<init>(AITBasic.java:72)
	at AITBasic.main(AITBasic.java:51)
	... 3 more
