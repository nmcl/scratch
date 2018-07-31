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

Still not working but at least we're not seeing the issues with the manifest file as previously. The user.dir is still a bit funky (https://github.com/oracle/graal/issues/577) though.