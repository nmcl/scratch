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

So something not quite right ...
