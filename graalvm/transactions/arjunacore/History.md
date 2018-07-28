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