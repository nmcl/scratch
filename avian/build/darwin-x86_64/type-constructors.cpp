void
initJobject(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JobjectType));
}

void
initClass(Thread* t, object o, uint16_t flags, uint16_t vmFlags, uint16_t fixedSize, uint8_t arrayElementSize, uint8_t arrayDimensions, uint32_t runtimeDataIndex, object objectMask, object name, object sourceFile, object super, object interfaceTable, object virtualTable, object fieldTable, object methodTable, object addendum, object staticTable, object loader, object source, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ClassType));
  classFlags(t, o) = flags;
  classVmFlags(t, o) = vmFlags;
  classFixedSize(t, o) = fixedSize;
  classArrayElementSize(t, o) = arrayElementSize;
  classArrayDimensions(t, o) = arrayDimensions;
  classRuntimeDataIndex(t, o) = runtimeDataIndex;
  classObjectMask(t, o) = objectMask;
  className(t, o) = name;
  classSourceFile(t, o) = sourceFile;
  classSuper(t, o) = super;
  classInterfaceTable(t, o) = interfaceTable;
  classVirtualTable(t, o) = virtualTable;
  classFieldTable(t, o) = fieldTable;
  classMethodTable(t, o) = methodTable;
  classAddendum(t, o) = addendum;
  classStaticTable(t, o) = staticTable;
  classLoader(t, o) = loader;
  classSource(t, o) = source;
  classLength(t, o) = length;
}

void
initJclass(Thread* t, object o, object vmClass)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JclassType));
  jclassVmClass(t, o) = vmClass;
}

void
initJaccessibleObject(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JaccessibleObjectType));
}

void
initJfield(Thread* t, object o, object vmField, uint8_t accessible)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JfieldType));
  jfieldVmField(t, o) = vmField;
  jfieldAccessible(t, o) = accessible;
}

void
initJmethod(Thread* t, object o, object vmMethod, uint8_t accessible)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JmethodType));
  jmethodVmMethod(t, o) = vmMethod;
  jmethodAccessible(t, o) = accessible;
}

void
initJconstructor(Thread* t, object o, object method)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JconstructorType));
  jconstructorMethod(t, o) = method;
}

void
initConstantPool(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ConstantPoolType));
}

void
initSerializable(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::SerializableType));
}

void
initCloneable(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CloneableType));
}

void
initSingleton(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::SingletonType));
  singletonLength(t, o) = length;
}

void
initClassLoader(Thread* t, object o, object parent, object packages, object map)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ClassLoaderType));
  classLoaderParent(t, o) = parent;
  classLoaderPackages(t, o) = packages;
  classLoaderMap(t, o) = map;
}

void
initSystemClassLoader(Thread* t, object o, object parent, object packages, object map, void* finder)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::SystemClassLoaderType));
  classLoaderParent(t, o) = parent;
  classLoaderPackages(t, o) = packages;
  classLoaderMap(t, o) = map;
  systemClassLoaderFinder(t, o) = finder;
}

void
initField(Thread* t, object o, uint8_t vmFlags, uint8_t code, uint16_t flags, uint16_t offset, uint32_t nativeID, object name, object spec, object addendum, object class_)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FieldType));
  fieldVmFlags(t, o) = vmFlags;
  fieldCode(t, o) = code;
  fieldFlags(t, o) = flags;
  fieldOffset(t, o) = offset;
  fieldNativeID(t, o) = nativeID;
  fieldName(t, o) = name;
  fieldSpec(t, o) = spec;
  fieldAddendum(t, o) = addendum;
  fieldClass(t, o) = class_;
}

void
initMethod(Thread* t, object o, uint8_t vmFlags, uint8_t returnCode, uint8_t parameterCount, uint8_t parameterFootprint, uint16_t flags, uint16_t offset, uint32_t nativeID, uint32_t runtimeDataIndex, object name, object spec, object addendum, object class_, object code)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::MethodType));
  methodVmFlags(t, o) = vmFlags;
  methodReturnCode(t, o) = returnCode;
  methodParameterCount(t, o) = parameterCount;
  methodParameterFootprint(t, o) = parameterFootprint;
  methodFlags(t, o) = flags;
  methodOffset(t, o) = offset;
  methodNativeID(t, o) = nativeID;
  methodRuntimeDataIndex(t, o) = runtimeDataIndex;
  methodName(t, o) = name;
  methodSpec(t, o) = spec;
  methodAddendum(t, o) = addendum;
  methodClass(t, o) = class_;
  methodCode(t, o) = code;
}

void
initAddendum(Thread* t, object o, object pool, object annotationTable, object signature)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::AddendumType));
  addendumPool(t, o) = pool;
  addendumAnnotationTable(t, o) = annotationTable;
  addendumSignature(t, o) = signature;
}

void
initClassAddendum(Thread* t, object o, object pool, object annotationTable, object signature, object interfaceTable, object innerClassTable, uint32_t declaredMethodCount, object enclosingClass, object enclosingMethod)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ClassAddendumType));
  addendumPool(t, o) = pool;
  addendumAnnotationTable(t, o) = annotationTable;
  addendumSignature(t, o) = signature;
  classAddendumInterfaceTable(t, o) = interfaceTable;
  classAddendumInnerClassTable(t, o) = innerClassTable;
  classAddendumDeclaredMethodCount(t, o) = declaredMethodCount;
  classAddendumEnclosingClass(t, o) = enclosingClass;
  classAddendumEnclosingMethod(t, o) = enclosingMethod;
}

void
initMethodAddendum(Thread* t, object o, object pool, object annotationTable, object signature, object exceptionTable, object annotationDefault, object parameterAnnotationTable)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::MethodAddendumType));
  addendumPool(t, o) = pool;
  addendumAnnotationTable(t, o) = annotationTable;
  addendumSignature(t, o) = signature;
  methodAddendumExceptionTable(t, o) = exceptionTable;
  methodAddendumAnnotationDefault(t, o) = annotationDefault;
  methodAddendumParameterAnnotationTable(t, o) = parameterAnnotationTable;
}

void
initFieldAddendum(Thread* t, object o, object pool, object annotationTable, object signature)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FieldAddendumType));
  addendumPool(t, o) = pool;
  addendumAnnotationTable(t, o) = annotationTable;
  addendumSignature(t, o) = signature;
}

void
initClassRuntimeData(Thread* t, object o, object arrayClass, object jclass, object pool, object signers)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ClassRuntimeDataType));
  classRuntimeDataArrayClass(t, o) = arrayClass;
  classRuntimeDataJclass(t, o) = jclass;
  classRuntimeDataPool(t, o) = pool;
  classRuntimeDataSigners(t, o) = signers;
}

void
initMethodRuntimeData(Thread* t, object o, object native)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::MethodRuntimeDataType));
  methodRuntimeDataNative(t, o) = native;
}

void
initPointer(Thread* t, object o, void* value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::PointerType));
  pointerValue(t, o) = value;
}

void
initNative(Thread* t, object o, void* function, uint8_t fast)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NativeType));
  nativeFunction(t, o) = function;
  nativeFast(t, o) = fast;
}

void
initNativeIntercept(Thread* t, object o, void* function, uint8_t fast, object original)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NativeInterceptType));
  nativeFunction(t, o) = function;
  nativeFast(t, o) = fast;
  nativeInterceptOriginal(t, o) = original;
}

void
initFinder(Thread* t, object o, void* finder, object name, object next)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FinderType));
  finderFinder(t, o) = finder;
  finderName(t, o) = name;
  finderNext(t, o) = next;
}

void
initRegion(Thread* t, object o, void* region, uint32_t position)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::RegionType));
  regionRegion(t, o) = region;
  regionPosition(t, o) = position;
}

void
initExceptionHandlerTable(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ExceptionHandlerTableType));
  exceptionHandlerTableLength(t, o) = length;
}

void
initLineNumberTable(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::LineNumberTableType));
  lineNumberTableLength(t, o) = length;
}

void
initCode(Thread* t, object o, object pool, object exceptionHandlerTable, object lineNumberTable, intptr_t compiled, uint32_t compiledSize, uint16_t maxStack, uint16_t maxLocals, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CodeType));
  codePool(t, o) = pool;
  codeExceptionHandlerTable(t, o) = exceptionHandlerTable;
  codeLineNumberTable(t, o) = lineNumberTable;
  codeCompiled(t, o) = compiled;
  codeCompiledSize(t, o) = compiledSize;
  codeMaxStack(t, o) = maxStack;
  codeMaxLocals(t, o) = maxLocals;
  codeLength(t, o) = length;
}

void
initReference(Thread* t, object o, uint8_t kind, object class_, object name, object spec)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ReferenceType));
  referenceKind(t, o) = kind;
  referenceClass(t, o) = class_;
  referenceName(t, o) = name;
  referenceSpec(t, o) = spec;
}

void
initInvocation(Thread* t, object o, uint16_t bootstrap, int32_t index, object class_, object pool, object template_, object site)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::InvocationType));
  invocationBootstrap(t, o) = bootstrap;
  invocationIndex(t, o) = index;
  invocationClass(t, o) = class_;
  invocationPool(t, o) = pool;
  invocationTemplate(t, o) = template_;
  invocationSite(t, o) = site;
}

void
initTriple(Thread* t, object o, object first, object second, object third)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::TripleType));
  tripleFirst(t, o) = first;
  tripleSecond(t, o) = second;
  tripleThird(t, o) = third;
}

void
initFinalizer(Thread* t, object o, object target, void* finalize, object next, object queueTarget, object queueNext)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FinalizerType));
  finalizerTarget(t, o) = target;
  finalizerFinalize(t, o) = finalize;
  finalizerNext(t, o) = next;
  finalizerQueueTarget(t, o) = queueTarget;
  finalizerQueueNext(t, o) = queueNext;
}

void
initHashMap(Thread* t, object o, uint32_t size, object array)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::HashMapType));
  hashMapSize(t, o) = size;
  hashMapArray(t, o) = array;
}

void
initWeakHashMap(Thread* t, object o, uint32_t size, object array)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::WeakHashMapType));
  hashMapSize(t, o) = size;
  hashMapArray(t, o) = array;
}

void
initList(Thread* t, object o, uint32_t size, object front, object rear)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ListType));
  listSize(t, o) = size;
  listFront(t, o) = front;
  listRear(t, o) = rear;
}

void
initVector(Thread* t, object o, uint32_t size, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::VectorType));
  vectorSize(t, o) = size;
  vectorLength(t, o) = length;
}

void
initTraceElement(Thread* t, object o, object method, int32_t ip)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::TraceElementType));
  traceElementMethod(t, o) = method;
  traceElementIp(t, o) = ip;
}

void
initTreeNode(Thread* t, object o, object value, object left, object right)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::TreeNodeType));
  treeNodeValue(t, o) = value;
  treeNodeLeft(t, o) = left;
  treeNodeRight(t, o) = right;
}

void
initCallNode(Thread* t, object o, intptr_t address, object target, uintptr_t flags, object next)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CallNodeType));
  callNodeAddress(t, o) = address;
  callNodeTarget(t, o) = target;
  callNodeFlags(t, o) = flags;
  callNodeNext(t, o) = next;
}

void
initWordArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::WordArrayType));
  wordArrayLength(t, o) = length;
}

void
initArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ArrayType));
  arrayLength(t, o) = length;
}

void
initPair(Thread* t, object o, object first, object second)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::PairType));
  pairFirst(t, o) = first;
  pairSecond(t, o) = second;
}

void
initMonitor(Thread* t, object o, void* owner, void* waitHead, void* waitTail, object acquireHead, object acquireTail, uint32_t depth)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::MonitorType));
  monitorOwner(t, o) = owner;
  monitorWaitHead(t, o) = waitHead;
  monitorWaitTail(t, o) = waitTail;
  monitorAcquireHead(t, o) = acquireHead;
  monitorAcquireTail(t, o) = acquireTail;
  monitorDepth(t, o) = depth;
}

void
initMonitorNode(Thread* t, object o, void* value, object next)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::MonitorNodeType));
  monitorNodeValue(t, o) = value;
  monitorNodeNext(t, o) = next;
}

void
initInnerClassReference(Thread* t, object o, object inner, object outer, object name, uint16_t flags)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::InnerClassReferenceType));
  innerClassReferenceInner(t, o) = inner;
  innerClassReferenceOuter(t, o) = outer;
  innerClassReferenceName(t, o) = name;
  innerClassReferenceFlags(t, o) = flags;
}

void
initContinuationContext(Thread* t, object o, object next, object before, object after, object continuation, object method)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ContinuationContextType));
  continuationContextNext(t, o) = next;
  continuationContextBefore(t, o) = before;
  continuationContextAfter(t, o) = after;
  continuationContextContinuation(t, o) = continuation;
  continuationContextMethod(t, o) = method;
}

void
initContinuation(Thread* t, object o, object next, object context, object method, void* address, uintptr_t returnAddressOffset, uintptr_t framePointerOffset, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ContinuationType));
  continuationNext(t, o) = next;
  continuationContext(t, o) = context;
  continuationMethod(t, o) = method;
  continuationAddress(t, o) = address;
  continuationReturnAddressOffset(t, o) = returnAddressOffset;
  continuationFramePointerOffset(t, o) = framePointerOffset;
  continuationLength(t, o) = length;
}

void
initUnwindResult(Thread* t, object o, object continuation, object result, object exception)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::UnwindResultType));
  unwindResultContinuation(t, o) = continuation;
  unwindResultResult(t, o) = result;
  unwindResultException(t, o) = exception;
}

void
initString(Thread* t, object o, object data, uint32_t offset, uint32_t length, uint32_t hashCode)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::StringType));
  stringData(t, o) = data;
  stringOffset(t, o) = offset;
  stringLength(t, o) = length;
  stringHashCode(t, o) = hashCode;
}

void
initThread(Thread* t, object o, object parkBlocker, uint64_t peer, uint8_t interrupted, uint8_t unparked, uint8_t daemon, uint8_t state, uint8_t priority, object task, object locals, object sleepLock, object classLoader, object exceptionHandler, object name, object group, object interruptLock)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ThreadType));
  threadParkBlocker(t, o) = parkBlocker;
  threadPeer(t, o) = peer;
  threadInterrupted(t, o) = interrupted;
  threadUnparked(t, o) = unparked;
  threadDaemon(t, o) = daemon;
  threadState(t, o) = state;
  threadPriority(t, o) = priority;
  threadTask(t, o) = task;
  threadLocals(t, o) = locals;
  threadSleepLock(t, o) = sleepLock;
  threadClassLoader(t, o) = classLoader;
  threadExceptionHandler(t, o) = exceptionHandler;
  threadName(t, o) = name;
  threadGroup(t, o) = group;
  threadInterruptLock(t, o) = interruptLock;
}

void
initThreadGroup(Thread* t, object o, object parent, object name, object subgroups)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ThreadGroupType));
  threadGroupParent(t, o) = parent;
  threadGroupName(t, o) = name;
  threadGroupSubgroups(t, o) = subgroups;
}

void
initStackTraceElement(Thread* t, object o, object class_, object method, object file, uint32_t line)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::StackTraceElementType));
  stackTraceElementClass(t, o) = class_;
  stackTraceElementMethod(t, o) = method;
  stackTraceElementFile(t, o) = file;
  stackTraceElementLine(t, o) = line;
}

void
initThrowable(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ThrowableType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initRuntimeException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::RuntimeExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initNullPointerException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NullPointerExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initArithmeticException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ArithmeticExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initIllegalStateException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IllegalStateExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initIllegalArgumentException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IllegalArgumentExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initIllegalMonitorStateException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IllegalMonitorStateExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initIndexOutOfBoundsException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IndexOutOfBoundsExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initArrayIndexOutOfBoundsException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ArrayIndexOutOfBoundsExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initArrayStoreException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ArrayStoreExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initNegativeArraySizeException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NegativeArraySizeExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initCloneNotSupportedException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CloneNotSupportedExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initReflectiveOperationException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ReflectiveOperationExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initClassCastException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ClassCastExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initClassNotFoundException(Thread* t, object o, object message, object trace, object cause, object cause2)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ClassNotFoundExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
  classNotFoundExceptionCause2(t, o) = cause2;
}

void
initInvocationTargetException(Thread* t, object o, object message, object trace, object cause, object target)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::InvocationTargetExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
  invocationTargetExceptionTarget(t, o) = target;
}

void
initInterruptedException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::InterruptedExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initVirtualMachineError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::VirtualMachineErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initOutOfMemoryError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::OutOfMemoryErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initStackOverflowError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::StackOverflowErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initLinkageError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::LinkageErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initIncompatibleClassChangeError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IncompatibleClassChangeErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initAbstractMethodError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::AbstractMethodErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initNoSuchFieldError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NoSuchFieldErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initNoSuchMethodError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NoSuchMethodErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initNoClassDefFoundError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NoClassDefFoundErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initUnsatisfiedLinkError(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::UnsatisfiedLinkErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initExceptionInInitializerError(Thread* t, object o, object message, object trace, object cause, object exception)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ExceptionInInitializerErrorType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
  exceptionInInitializerErrorException(t, o) = exception;
}

void
initIoException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IoExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initFileNotFoundException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FileNotFoundExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initIncompatibleContinuationException(Thread* t, object o, object message, object trace, object cause)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IncompatibleContinuationExceptionType));
  throwableMessage(t, o) = message;
  throwableTrace(t, o) = trace;
  throwableCause(t, o) = cause;
}

void
initNumber(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::NumberType));
}

void
initByte(Thread* t, object o, uint8_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ByteType));
  byteValue(t, o) = value;
}

void
initBoolean(Thread* t, object o, uint8_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::BooleanType));
  booleanValue(t, o) = value;
}

void
initShort(Thread* t, object o, uint16_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ShortType));
  shortValue(t, o) = value;
}

void
initChar(Thread* t, object o, uint16_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CharType));
  charValue(t, o) = value;
}

void
initInt(Thread* t, object o, uint32_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IntType));
  intValue(t, o) = value;
}

void
initLong(Thread* t, object o, uint64_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::LongType));
  longValue(t, o) = value;
}

void
initFloat(Thread* t, object o, uint32_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FloatType));
  floatValue(t, o) = value;
}

void
initDouble(Thread* t, object o, uint64_t value)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::DoubleType));
  doubleValue(t, o) = value;
}

void
initReferenceQueue(Thread* t, object o, object front)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ReferenceQueueType));
  referenceQueueFront(t, o) = front;
}

void
initJreference(Thread* t, object o, object vmNext, object target, object queue, object jNext)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JreferenceType));
  jreferenceVmNext(t, o) = vmNext;
  jreferenceTarget(t, o) = target;
  jreferenceQueue(t, o) = queue;
  jreferenceJNext(t, o) = jNext;
}

void
initWeakReference(Thread* t, object o, object vmNext, object target, object queue, object jNext)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::WeakReferenceType));
  jreferenceVmNext(t, o) = vmNext;
  jreferenceTarget(t, o) = target;
  jreferenceQueue(t, o) = queue;
  jreferenceJNext(t, o) = jNext;
}

void
initSoftReference(Thread* t, object o, object vmNext, object target, object queue, object jNext)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::SoftReferenceType));
  jreferenceVmNext(t, o) = vmNext;
  jreferenceTarget(t, o) = target;
  jreferenceQueue(t, o) = queue;
  jreferenceJNext(t, o) = jNext;
}

void
initPhantomReference(Thread* t, object o, object vmNext, object target, object queue, object jNext)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::PhantomReferenceType));
  jreferenceVmNext(t, o) = vmNext;
  jreferenceTarget(t, o) = target;
  jreferenceQueue(t, o) = queue;
  jreferenceJNext(t, o) = jNext;
}

void
initCleaner(Thread* t, object o, object queueNext)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CleanerType));
  cleanerQueueNext(t, o) = queueNext;
}

void
initByteArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ByteArrayType));
  byteArrayLength(t, o) = length;
}

void
initBooleanArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::BooleanArrayType));
  booleanArrayLength(t, o) = length;
}

void
initShortArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::ShortArrayType));
  shortArrayLength(t, o) = length;
}

void
initCharArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::CharArrayType));
  charArrayLength(t, o) = length;
}

void
initIntArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::IntArrayType));
  intArrayLength(t, o) = length;
}

void
initLongArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::LongArrayType));
  longArrayLength(t, o) = length;
}

void
initFloatArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::FloatArrayType));
  floatArrayLength(t, o) = length;
}

void
initDoubleArray(Thread* t, object o, uintptr_t length)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::DoubleArrayType));
  doubleArrayLength(t, o) = length;
}

void
initJbyte(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JbyteType));
}

void
initJboolean(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JbooleanType));
}

void
initJshort(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JshortType));
}

void
initJchar(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JcharType));
}

void
initJint(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JintType));
}

void
initJlong(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JlongType));
}

void
initJfloat(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JfloatType));
}

void
initJdouble(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JdoubleType));
}

void
initJvoid(Thread* t, object o)
{
  setObjectClass(t, o, arrayBody(t, t->m->types, Machine::JvoidType));
}

object makeJobject(Thread* t)
{
  object o = allocate(t, 8, false);
  initJobject(t, o);
  return o;
}

object makeClass(Thread* t, uint16_t flags, uint16_t vmFlags, uint16_t fixedSize, uint8_t arrayElementSize, uint8_t arrayDimensions, uint32_t runtimeDataIndex, object objectMask, object name, object sourceFile, object super, object interfaceTable, object virtualTable, object fieldTable, object methodTable, object addendum, object staticTable, object loader, object source, uintptr_t length)
{
  PROTECT(t, objectMask);
  PROTECT(t, name);
  PROTECT(t, sourceFile);
  PROTECT(t, super);
  PROTECT(t, interfaceTable);
  PROTECT(t, virtualTable);
  PROTECT(t, fieldTable);
  PROTECT(t, methodTable);
  PROTECT(t, addendum);
  PROTECT(t, staticTable);
  PROTECT(t, loader);
  PROTECT(t, source);
  object o = allocate(t, pad((length * 8) + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 12 + 4 + 1 + 1 + 2 + 2 + 2 + 8), true);
  initClass(t, o, flags, vmFlags, fixedSize, arrayElementSize, arrayDimensions, runtimeDataIndex, objectMask, name, sourceFile, super, interfaceTable, virtualTable, fieldTable, methodTable, addendum, staticTable, loader, source, length);
  return o;
}

object makeJclass(Thread* t, object vmClass)
{
  PROTECT(t, vmClass);
  object o = allocate(t, 8 + 8, true);
  initJclass(t, o, vmClass);
  return o;
}

object makeJaccessibleObject(Thread* t)
{
  object o = allocate(t, 8, false);
  initJaccessibleObject(t, o);
  return o;
}

object makeJfield(Thread* t, object vmField, uint8_t accessible)
{
  PROTECT(t, vmField);
  object o = allocate(t, 7 + 1 + 8 + 8, true);
  initJfield(t, o, vmField, accessible);
  return o;
}

object makeJmethod(Thread* t, object vmMethod, uint8_t accessible)
{
  PROTECT(t, vmMethod);
  object o = allocate(t, 7 + 1 + 8 + 8, true);
  initJmethod(t, o, vmMethod, accessible);
  return o;
}

object makeJconstructor(Thread* t, object method)
{
  PROTECT(t, method);
  object o = allocate(t, 8 + 8, true);
  initJconstructor(t, o, method);
  return o;
}

object makeConstantPool(Thread* t)
{
  object o = allocate(t, 8, false);
  initConstantPool(t, o);
  return o;
}

object makeSerializable(Thread* t)
{
  object o = allocate(t, 8, false);
  initSerializable(t, o);
  return o;
}

object makeCloneable(Thread* t)
{
  object o = allocate(t, 8, false);
  initCloneable(t, o);
  return o;
}

object makeSingleton(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), true);
  initSingleton(t, o, length);
  return o;
}

object makeClassLoader(Thread* t, object parent, object packages, object map)
{
  PROTECT(t, parent);
  PROTECT(t, packages);
  PROTECT(t, map);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initClassLoader(t, o, parent, packages, map);
  return o;
}

object makeSystemClassLoader(Thread* t, object parent, object packages, object map, void* finder)
{
  PROTECT(t, parent);
  PROTECT(t, packages);
  PROTECT(t, map);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initSystemClassLoader(t, o, parent, packages, map, finder);
  return o;
}

object makeField(Thread* t, uint8_t vmFlags, uint8_t code, uint16_t flags, uint16_t offset, uint32_t nativeID, object name, object spec, object addendum, object class_)
{
  PROTECT(t, name);
  PROTECT(t, spec);
  PROTECT(t, addendum);
  PROTECT(t, class_);
  object o = allocate(t, 8 + 8 + 8 + 12 + 6 + 2 + 2 + 1 + 1 + 8, true);
  initField(t, o, vmFlags, code, flags, offset, nativeID, name, spec, addendum, class_);
  return o;
}

object makeMethod(Thread* t, uint8_t vmFlags, uint8_t returnCode, uint8_t parameterCount, uint8_t parameterFootprint, uint16_t flags, uint16_t offset, uint32_t nativeID, uint32_t runtimeDataIndex, object name, object spec, object addendum, object class_, object code)
{
  PROTECT(t, name);
  PROTECT(t, spec);
  PROTECT(t, addendum);
  PROTECT(t, class_);
  PROTECT(t, code);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8 + 4 + 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8, true);
  initMethod(t, o, vmFlags, returnCode, parameterCount, parameterFootprint, flags, offset, nativeID, runtimeDataIndex, name, spec, addendum, class_, code);
  return o;
}

object makeAddendum(Thread* t, object pool, object annotationTable, object signature)
{
  PROTECT(t, pool);
  PROTECT(t, annotationTable);
  PROTECT(t, signature);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initAddendum(t, o, pool, annotationTable, signature);
  return o;
}

object makeClassAddendum(Thread* t, object pool, object annotationTable, object signature, object interfaceTable, object innerClassTable, uint32_t declaredMethodCount, object enclosingClass, object enclosingMethod)
{
  PROTECT(t, pool);
  PROTECT(t, annotationTable);
  PROTECT(t, signature);
  PROTECT(t, interfaceTable);
  PROTECT(t, innerClassTable);
  PROTECT(t, enclosingClass);
  PROTECT(t, enclosingMethod);
  object o = allocate(t, 8 + 12 + 4 + 8 + 8 + 8 + 8 + 8 + 8, true);
  initClassAddendum(t, o, pool, annotationTable, signature, interfaceTable, innerClassTable, declaredMethodCount, enclosingClass, enclosingMethod);
  return o;
}

object makeMethodAddendum(Thread* t, object pool, object annotationTable, object signature, object exceptionTable, object annotationDefault, object parameterAnnotationTable)
{
  PROTECT(t, pool);
  PROTECT(t, annotationTable);
  PROTECT(t, signature);
  PROTECT(t, exceptionTable);
  PROTECT(t, annotationDefault);
  PROTECT(t, parameterAnnotationTable);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8 + 8 + 8, true);
  initMethodAddendum(t, o, pool, annotationTable, signature, exceptionTable, annotationDefault, parameterAnnotationTable);
  return o;
}

object makeFieldAddendum(Thread* t, object pool, object annotationTable, object signature)
{
  PROTECT(t, pool);
  PROTECT(t, annotationTable);
  PROTECT(t, signature);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initFieldAddendum(t, o, pool, annotationTable, signature);
  return o;
}

object makeClassRuntimeData(Thread* t, object arrayClass, object jclass, object pool, object signers)
{
  PROTECT(t, arrayClass);
  PROTECT(t, jclass);
  PROTECT(t, pool);
  PROTECT(t, signers);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initClassRuntimeData(t, o, arrayClass, jclass, pool, signers);
  return o;
}

object makeMethodRuntimeData(Thread* t, object native)
{
  PROTECT(t, native);
  object o = allocate(t, 8 + 8, true);
  initMethodRuntimeData(t, o, native);
  return o;
}

object makePointer(Thread* t, void* value)
{
  object o = allocate(t, 8 + 8, false);
  initPointer(t, o, value);
  return o;
}

object makeNative(Thread* t, void* function, uint8_t fast)
{
  object o = allocate(t, 7 + 1 + 8 + 8, false);
  initNative(t, o, function, fast);
  return o;
}

object makeNativeIntercept(Thread* t, void* function, uint8_t fast, object original)
{
  PROTECT(t, original);
  object o = allocate(t, 8 + 7 + 1 + 8 + 8, true);
  initNativeIntercept(t, o, function, fast, original);
  return o;
}

object makeFinder(Thread* t, void* finder, object name, object next)
{
  PROTECT(t, name);
  PROTECT(t, next);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initFinder(t, o, finder, name, next);
  return o;
}

object makeRegion(Thread* t, void* region, uint32_t position)
{
  object o = allocate(t, 4 + 4 + 8 + 8, false);
  initRegion(t, o, region, position);
  return o;
}

object makeExceptionHandlerTable(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), false);
  initExceptionHandlerTable(t, o, length);
  return o;
}

object makeLineNumberTable(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), false);
  initLineNumberTable(t, o, length);
  return o;
}

object makeCode(Thread* t, object pool, object exceptionHandlerTable, object lineNumberTable, intptr_t compiled, uint32_t compiledSize, uint16_t maxStack, uint16_t maxLocals, uintptr_t length)
{
  PROTECT(t, pool);
  PROTECT(t, exceptionHandlerTable);
  PROTECT(t, lineNumberTable);
  object o = allocate(t, pad((length * 1) + 8 + 2 + 2 + 4 + 8 + 8 + 8 + 8 + 8), true);
  initCode(t, o, pool, exceptionHandlerTable, lineNumberTable, compiled, compiledSize, maxStack, maxLocals, length);
  return o;
}

object makeReference(Thread* t, uint8_t kind, object class_, object name, object spec)
{
  PROTECT(t, class_);
  PROTECT(t, name);
  PROTECT(t, spec);
  object o = allocate(t, 8 + 8 + 15 + 1 + 8, true);
  initReference(t, o, kind, class_, name, spec);
  return o;
}

object makeInvocation(Thread* t, uint16_t bootstrap, int32_t index, object class_, object pool, object template_, object site)
{
  PROTECT(t, class_);
  PROTECT(t, pool);
  PROTECT(t, template_);
  PROTECT(t, site);
  object o = allocate(t, 8 + 8 + 8 + 8 + 6 + 2 + 8, true);
  initInvocation(t, o, bootstrap, index, class_, pool, template_, site);
  return o;
}

object makeTriple(Thread* t, object first, object second, object third)
{
  PROTECT(t, first);
  PROTECT(t, second);
  PROTECT(t, third);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initTriple(t, o, first, second, third);
  return o;
}

object makeFinalizer(Thread* t, object target, void* finalize, object next, object queueTarget, object queueNext)
{
  PROTECT(t, queueTarget);
  PROTECT(t, queueNext);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8 + 8, true);
  initFinalizer(t, o, target, finalize, next, queueTarget, queueNext);
  return o;
}

object makeHashMap(Thread* t, uint32_t size, object array)
{
  PROTECT(t, array);
  object o = allocate(t, 12 + 4 + 8, true);
  initHashMap(t, o, size, array);
  return o;
}

object makeWeakHashMap(Thread* t, uint32_t size, object array)
{
  PROTECT(t, array);
  object o = allocate(t, 12 + 4 + 8, true);
  initWeakHashMap(t, o, size, array);
  return o;
}

object makeList(Thread* t, uint32_t size, object front, object rear)
{
  PROTECT(t, front);
  PROTECT(t, rear);
  object o = allocate(t, 8 + 12 + 4 + 8, true);
  initList(t, o, size, front, rear);
  return o;
}

object makeVector(Thread* t, uint32_t size, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 12 + 4 + 8), true);
  initVector(t, o, size, length);
  return o;
}

object makeTraceElement(Thread* t, object method, int32_t ip)
{
  PROTECT(t, method);
  object o = allocate(t, 4 + 4 + 8 + 8, true);
  initTraceElement(t, o, method, ip);
  return o;
}

object makeTreeNode(Thread* t, object value, object left, object right)
{
  PROTECT(t, value);
  PROTECT(t, left);
  PROTECT(t, right);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initTreeNode(t, o, value, left, right);
  return o;
}

object makeCallNode(Thread* t, intptr_t address, object target, uintptr_t flags, object next)
{
  PROTECT(t, target);
  PROTECT(t, next);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initCallNode(t, o, address, target, flags, next);
  return o;
}

object makeWordArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), false);
  initWordArray(t, o, length);
  return o;
}

object makeArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), true);
  initArray(t, o, length);
  return o;
}

object makePair(Thread* t, object first, object second)
{
  PROTECT(t, first);
  PROTECT(t, second);
  object o = allocate(t, 8 + 8 + 8, true);
  initPair(t, o, first, second);
  return o;
}

object makeMonitor(Thread* t, void* owner, void* waitHead, void* waitTail, object acquireHead, object acquireTail, uint32_t depth)
{
  PROTECT(t, acquireHead);
  PROTECT(t, acquireTail);
  object o = allocate(t, 4 + 4 + 8 + 8 + 8 + 8 + 8 + 8, true);
  initMonitor(t, o, owner, waitHead, waitTail, acquireHead, acquireTail, depth);
  return o;
}

object makeMonitorNode(Thread* t, void* value, object next)
{
  PROTECT(t, next);
  object o = allocate(t, 8 + 8 + 8, true);
  initMonitorNode(t, o, value, next);
  return o;
}

object makeInnerClassReference(Thread* t, object inner, object outer, object name, uint16_t flags)
{
  PROTECT(t, inner);
  PROTECT(t, outer);
  PROTECT(t, name);
  object o = allocate(t, 6 + 2 + 8 + 8 + 8 + 8, true);
  initInnerClassReference(t, o, inner, outer, name, flags);
  return o;
}

object makeContinuationContext(Thread* t, object next, object before, object after, object continuation, object method)
{
  PROTECT(t, next);
  PROTECT(t, before);
  PROTECT(t, after);
  PROTECT(t, continuation);
  PROTECT(t, method);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8 + 8, true);
  initContinuationContext(t, o, next, before, after, continuation, method);
  return o;
}

object makeContinuation(Thread* t, object next, object context, object method, void* address, uintptr_t returnAddressOffset, uintptr_t framePointerOffset, uintptr_t length)
{
  PROTECT(t, next);
  PROTECT(t, context);
  PROTECT(t, method);
  object o = allocate(t, pad((length * 8) + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8), true);
  initContinuation(t, o, next, context, method, address, returnAddressOffset, framePointerOffset, length);
  return o;
}

object makeUnwindResult(Thread* t, object continuation, object result, object exception)
{
  PROTECT(t, continuation);
  PROTECT(t, result);
  PROTECT(t, exception);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initUnwindResult(t, o, continuation, result, exception);
  return o;
}

object makeString(Thread* t, object data, uint32_t offset, uint32_t length, uint32_t hashCode)
{
  PROTECT(t, data);
  object o = allocate(t, 4 + 4 + 4 + 4 + 8 + 8, true);
  initString(t, o, data, offset, length, hashCode);
  return o;
}

object makeThread(Thread* t, object parkBlocker, uint64_t peer, uint8_t interrupted, uint8_t unparked, uint8_t daemon, uint8_t state, uint8_t priority, object task, object locals, object sleepLock, object classLoader, object exceptionHandler, object name, object group, object interruptLock)
{
  PROTECT(t, parkBlocker);
  PROTECT(t, task);
  PROTECT(t, locals);
  PROTECT(t, sleepLock);
  PROTECT(t, classLoader);
  PROTECT(t, exceptionHandler);
  PROTECT(t, name);
  PROTECT(t, group);
  PROTECT(t, interruptLock);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8 + 8 + 8 + 11 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8, true);
  initThread(t, o, parkBlocker, peer, interrupted, unparked, daemon, state, priority, task, locals, sleepLock, classLoader, exceptionHandler, name, group, interruptLock);
  return o;
}

object makeThreadGroup(Thread* t, object parent, object name, object subgroups)
{
  PROTECT(t, parent);
  PROTECT(t, name);
  PROTECT(t, subgroups);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initThreadGroup(t, o, parent, name, subgroups);
  return o;
}

object makeStackTraceElement(Thread* t, object class_, object method, object file, uint32_t line)
{
  PROTECT(t, class_);
  PROTECT(t, method);
  PROTECT(t, file);
  object o = allocate(t, 4 + 4 + 8 + 8 + 8 + 8, true);
  initStackTraceElement(t, o, class_, method, file, line);
  return o;
}

object makeThrowable(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initThrowable(t, o, message, trace, cause);
  return o;
}

object makeException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initException(t, o, message, trace, cause);
  return o;
}

object makeRuntimeException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initRuntimeException(t, o, message, trace, cause);
  return o;
}

object makeNullPointerException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initNullPointerException(t, o, message, trace, cause);
  return o;
}

object makeArithmeticException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initArithmeticException(t, o, message, trace, cause);
  return o;
}

object makeIllegalStateException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIllegalStateException(t, o, message, trace, cause);
  return o;
}

object makeIllegalArgumentException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIllegalArgumentException(t, o, message, trace, cause);
  return o;
}

object makeIllegalMonitorStateException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIllegalMonitorStateException(t, o, message, trace, cause);
  return o;
}

object makeIndexOutOfBoundsException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIndexOutOfBoundsException(t, o, message, trace, cause);
  return o;
}

object makeArrayIndexOutOfBoundsException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initArrayIndexOutOfBoundsException(t, o, message, trace, cause);
  return o;
}

object makeArrayStoreException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initArrayStoreException(t, o, message, trace, cause);
  return o;
}

object makeNegativeArraySizeException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initNegativeArraySizeException(t, o, message, trace, cause);
  return o;
}

object makeCloneNotSupportedException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initCloneNotSupportedException(t, o, message, trace, cause);
  return o;
}

object makeReflectiveOperationException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initReflectiveOperationException(t, o, message, trace, cause);
  return o;
}

object makeClassCastException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initClassCastException(t, o, message, trace, cause);
  return o;
}

object makeClassNotFoundException(Thread* t, object message, object trace, object cause, object cause2)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  PROTECT(t, cause2);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initClassNotFoundException(t, o, message, trace, cause, cause2);
  return o;
}

object makeInvocationTargetException(Thread* t, object message, object trace, object cause, object target)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  PROTECT(t, target);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initInvocationTargetException(t, o, message, trace, cause, target);
  return o;
}

object makeInterruptedException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initInterruptedException(t, o, message, trace, cause);
  return o;
}

object makeError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initError(t, o, message, trace, cause);
  return o;
}

object makeVirtualMachineError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initVirtualMachineError(t, o, message, trace, cause);
  return o;
}

object makeOutOfMemoryError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initOutOfMemoryError(t, o, message, trace, cause);
  return o;
}

object makeStackOverflowError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initStackOverflowError(t, o, message, trace, cause);
  return o;
}

object makeLinkageError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initLinkageError(t, o, message, trace, cause);
  return o;
}

object makeIncompatibleClassChangeError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIncompatibleClassChangeError(t, o, message, trace, cause);
  return o;
}

object makeAbstractMethodError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initAbstractMethodError(t, o, message, trace, cause);
  return o;
}

object makeNoSuchFieldError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initNoSuchFieldError(t, o, message, trace, cause);
  return o;
}

object makeNoSuchMethodError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initNoSuchMethodError(t, o, message, trace, cause);
  return o;
}

object makeNoClassDefFoundError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initNoClassDefFoundError(t, o, message, trace, cause);
  return o;
}

object makeUnsatisfiedLinkError(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initUnsatisfiedLinkError(t, o, message, trace, cause);
  return o;
}

object makeExceptionInInitializerError(Thread* t, object message, object trace, object cause, object exception)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  PROTECT(t, exception);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initExceptionInInitializerError(t, o, message, trace, cause, exception);
  return o;
}

object makeIoException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIoException(t, o, message, trace, cause);
  return o;
}

object makeFileNotFoundException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initFileNotFoundException(t, o, message, trace, cause);
  return o;
}

object makeIncompatibleContinuationException(Thread* t, object message, object trace, object cause)
{
  PROTECT(t, message);
  PROTECT(t, trace);
  PROTECT(t, cause);
  object o = allocate(t, 8 + 8 + 8 + 8, true);
  initIncompatibleContinuationException(t, o, message, trace, cause);
  return o;
}

object makeNumber(Thread* t)
{
  object o = allocate(t, 8, false);
  initNumber(t, o);
  return o;
}

object makeByte(Thread* t, uint8_t value)
{
  object o = allocate(t, 7 + 1 + 8, false);
  initByte(t, o, value);
  return o;
}

object makeBoolean(Thread* t, uint8_t value)
{
  object o = allocate(t, 7 + 1 + 8, false);
  initBoolean(t, o, value);
  return o;
}

object makeShort(Thread* t, uint16_t value)
{
  object o = allocate(t, 6 + 2 + 8, false);
  initShort(t, o, value);
  return o;
}

object makeChar(Thread* t, uint16_t value)
{
  object o = allocate(t, 6 + 2 + 8, false);
  initChar(t, o, value);
  return o;
}

object makeInt(Thread* t, uint32_t value)
{
  object o = allocate(t, 4 + 4 + 8, false);
  initInt(t, o, value);
  return o;
}

object makeLong(Thread* t, uint64_t value)
{
  object o = allocate(t, 8 + 8, false);
  initLong(t, o, value);
  return o;
}

object makeFloat(Thread* t, uint32_t value)
{
  object o = allocate(t, 4 + 4 + 8, false);
  initFloat(t, o, value);
  return o;
}

object makeDouble(Thread* t, uint64_t value)
{
  object o = allocate(t, 8 + 8, false);
  initDouble(t, o, value);
  return o;
}

object makeReferenceQueue(Thread* t, object front)
{
  PROTECT(t, front);
  object o = allocate(t, 8 + 8, true);
  initReferenceQueue(t, o, front);
  return o;
}

object makeJreference(Thread* t, object vmNext, object target, object queue, object jNext)
{
  PROTECT(t, jNext);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initJreference(t, o, vmNext, target, queue, jNext);
  return o;
}

object makeWeakReference(Thread* t, object vmNext, object target, object queue, object jNext)
{
  PROTECT(t, jNext);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initWeakReference(t, o, vmNext, target, queue, jNext);
  return o;
}

object makeSoftReference(Thread* t, object vmNext, object target, object queue, object jNext)
{
  PROTECT(t, jNext);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initSoftReference(t, o, vmNext, target, queue, jNext);
  return o;
}

object makePhantomReference(Thread* t, object vmNext, object target, object queue, object jNext)
{
  PROTECT(t, jNext);
  object o = allocate(t, 8 + 8 + 8 + 8 + 8, true);
  initPhantomReference(t, o, vmNext, target, queue, jNext);
  return o;
}

object makeCleaner(Thread* t, object queueNext)
{
  PROTECT(t, queueNext);
  object o = allocate(t, 8 + 8, true);
  initCleaner(t, o, queueNext);
  return o;
}

object makeByteArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 1) + 8 + 8), false);
  initByteArray(t, o, length);
  return o;
}

object makeBooleanArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 1) + 8 + 8), false);
  initBooleanArray(t, o, length);
  return o;
}

object makeShortArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 2) + 8 + 8), false);
  initShortArray(t, o, length);
  return o;
}

object makeCharArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 2) + 8 + 8), false);
  initCharArray(t, o, length);
  return o;
}

object makeIntArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 4) + 8 + 8), false);
  initIntArray(t, o, length);
  return o;
}

object makeLongArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), false);
  initLongArray(t, o, length);
  return o;
}

object makeFloatArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 4) + 8 + 8), false);
  initFloatArray(t, o, length);
  return o;
}

object makeDoubleArray(Thread* t, uintptr_t length)
{
  object o = allocate(t, pad((length * 8) + 8 + 8), false);
  initDoubleArray(t, o, length);
  return o;
}

object makeJbyte(Thread* t)
{
  object o = allocate(t, 8, false);
  initJbyte(t, o);
  return o;
}

object makeJboolean(Thread* t)
{
  object o = allocate(t, 8, false);
  initJboolean(t, o);
  return o;
}

object makeJshort(Thread* t)
{
  object o = allocate(t, 8, false);
  initJshort(t, o);
  return o;
}

object makeJchar(Thread* t)
{
  object o = allocate(t, 8, false);
  initJchar(t, o);
  return o;
}

object makeJint(Thread* t)
{
  object o = allocate(t, 8, false);
  initJint(t, o);
  return o;
}

object makeJlong(Thread* t)
{
  object o = allocate(t, 8, false);
  initJlong(t, o);
  return o;
}

object makeJfloat(Thread* t)
{
  object o = allocate(t, 8, false);
  initJfloat(t, o);
  return o;
}

object makeJdouble(Thread* t)
{
  object o = allocate(t, 8, false);
  initJdouble(t, o);
  return o;
}

object makeJvoid(Thread* t)
{
  object o = allocate(t, 8, false);
  initJvoid(t, o);
  return o;
}


