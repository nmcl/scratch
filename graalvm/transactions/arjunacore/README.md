Some basic ArjunaCore examples.

To run normally:

(i) add contents of lib to classpath. add etc directory to classpath.

(ii) javac BasicExample

(iii) java BasicExample

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

(i) explode the jars in the lib directory and add their contents, along with the two .mappings files, to the jar using jar -cmf manifest.txt BasicExample.jar BasicExample.class arjuna-properties.mappings txoj-properties.mappings com org

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
