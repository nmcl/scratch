Some basic ArjunaCore examples.

To run normally:

(i) add contents of lib to classpath. add etc directory to classpath.

(ii) javac BasicExample

(iii) java BasicExample

To run with GraalVM:

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