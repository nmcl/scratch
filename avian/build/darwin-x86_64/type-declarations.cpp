const unsigned TypeCount = 118;

const unsigned ClassFlags = 8;

#define HAVE_ClassFlags 1

inline uint16_t&
classFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[ClassFlags]);
}

const unsigned ClassVmFlags = 2 + 8;

#define HAVE_ClassVmFlags 1

inline uint16_t&
classVmFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[ClassVmFlags]);
}

const unsigned ClassFixedSize = 2 + 2 + 8;

#define HAVE_ClassFixedSize 1

inline uint16_t&
classFixedSize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[ClassFixedSize]);
}

const unsigned ClassArrayElementSize = 2 + 2 + 2 + 8;

#define HAVE_ClassArrayElementSize 1

inline uint8_t&
classArrayElementSize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ClassArrayElementSize]);
}

const unsigned ClassArrayDimensions = 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassArrayDimensions 1

inline uint8_t&
classArrayDimensions(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ClassArrayDimensions]);
}

const unsigned ClassRuntimeDataIndex = 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassRuntimeDataIndex 1

inline uint32_t&
classRuntimeDataIndex(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[ClassRuntimeDataIndex]);
}

const unsigned ClassObjectMask = 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassObjectMask 1

inline object&
classObjectMask(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassObjectMask]);
}

const unsigned ClassName = 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassName 1

inline object&
className(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassName]);
}

const unsigned ClassSourceFile = 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassSourceFile 1

inline object&
classSourceFile(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassSourceFile]);
}

const unsigned ClassSuper = 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassSuper 1

inline object&
classSuper(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassSuper]);
}

const unsigned ClassInterfaceTable = 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassInterfaceTable 1

inline object&
classInterfaceTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassInterfaceTable]);
}

const unsigned ClassVirtualTable = 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassVirtualTable 1

inline object&
classVirtualTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassVirtualTable]);
}

const unsigned ClassFieldTable = 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassFieldTable 1

inline object&
classFieldTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassFieldTable]);
}

const unsigned ClassMethodTable = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassMethodTable 1

inline object&
classMethodTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassMethodTable]);
}

const unsigned ClassAddendum = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassAddendum 1

inline object&
classAddendum(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassAddendum]);
}

const unsigned ClassStaticTable = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassStaticTable 1

inline object&
classStaticTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassStaticTable]);
}

const unsigned ClassLoader = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassLoader 1

inline object&
classLoader(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassLoader]);
}

const unsigned ClassSource = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassSource 1

inline object&
classSource(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassSource]);
}

const unsigned ClassLength = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassLength 1

inline uintptr_t&
classLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ClassLength]);
}

const unsigned ClassVtable = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 2 + 2 + 2 + 8;

#define HAVE_ClassVtable 1

inline void*&
classVtable(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassType), o));
  assert(t, i < classLength(t, o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[ClassVtable + (i * 8)]);
}

const unsigned JclassVmClass = 8;

#define HAVE_JclassVmClass 1

inline object&
jclassVmClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JclassType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JclassVmClass]);
}

const unsigned JfieldVmField = 8;

#define HAVE_JfieldVmField 1

inline object&
jfieldVmField(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JfieldType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JfieldVmField]);
}

const unsigned JfieldAccessible = 8 + 8;

#define HAVE_JfieldAccessible 1

inline uint8_t&
jfieldAccessible(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JfieldType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[JfieldAccessible]);
}

const unsigned JmethodVmMethod = 8;

#define HAVE_JmethodVmMethod 1

inline object&
jmethodVmMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JmethodType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JmethodVmMethod]);
}

const unsigned JmethodAccessible = 8 + 8;

#define HAVE_JmethodAccessible 1

inline uint8_t&
jmethodAccessible(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JmethodType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[JmethodAccessible]);
}

const unsigned JconstructorMethod = 8;

#define HAVE_JconstructorMethod 1

inline object&
jconstructorMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JconstructorType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JconstructorMethod]);
}

const unsigned SingletonLength = 8;

#define HAVE_SingletonLength 1

inline uintptr_t&
singletonLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::SingletonType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[SingletonLength]);
}

const unsigned SingletonBody = 8 + 8;

#define HAVE_SingletonBody 1

inline uintptr_t&
singletonBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::SingletonType), o));
  assert(t, i < singletonLength(t, o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[SingletonBody + (i * 8)]);
}

const unsigned ClassLoaderParent = 8;

#define HAVE_ClassLoaderParent 1

inline object&
classLoaderParent(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassLoaderType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassLoaderParent]);
}

const unsigned ClassLoaderPackages = 8 + 8;

#define HAVE_ClassLoaderPackages 1

inline object&
classLoaderPackages(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassLoaderType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassLoaderPackages]);
}

const unsigned ClassLoaderMap = 8 + 8 + 8;

#define HAVE_ClassLoaderMap 1

inline object&
classLoaderMap(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassLoaderType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassLoaderMap]);
}

const unsigned SystemClassLoaderFinder = 8 + 8 + 8 + 8;

#define HAVE_SystemClassLoaderFinder 1

inline void*&
systemClassLoaderFinder(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::SystemClassLoaderType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[SystemClassLoaderFinder]);
}

const unsigned FieldVmFlags = 8;

#define HAVE_FieldVmFlags 1

inline uint8_t&
fieldVmFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[FieldVmFlags]);
}

const unsigned FieldCode = 1 + 8;

#define HAVE_FieldCode 1

inline uint8_t&
fieldCode(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[FieldCode]);
}

const unsigned FieldFlags = 1 + 1 + 8;

#define HAVE_FieldFlags 1

inline uint16_t&
fieldFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[FieldFlags]);
}

const unsigned FieldOffset = 2 + 1 + 1 + 8;

#define HAVE_FieldOffset 1

inline uint16_t&
fieldOffset(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[FieldOffset]);
}

const unsigned FieldNativeID = 2 + 2 + 2 + 1 + 1 + 8;

#define HAVE_FieldNativeID 1

inline uint32_t&
fieldNativeID(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[FieldNativeID]);
}

const unsigned FieldName = 4 + 4 + 2 + 2 + 2 + 1 + 1 + 8;

#define HAVE_FieldName 1

inline object&
fieldName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FieldName]);
}

const unsigned FieldSpec = 8 + 4 + 4 + 2 + 2 + 2 + 1 + 1 + 8;

#define HAVE_FieldSpec 1

inline object&
fieldSpec(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FieldSpec]);
}

const unsigned FieldAddendum = 8 + 8 + 4 + 4 + 2 + 2 + 2 + 1 + 1 + 8;

#define HAVE_FieldAddendum 1

inline object&
fieldAddendum(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FieldAddendum]);
}

const unsigned FieldClass = 8 + 8 + 8 + 4 + 4 + 2 + 2 + 2 + 1 + 1 + 8;

#define HAVE_FieldClass 1

inline object&
fieldClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FieldType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FieldClass]);
}

const unsigned MethodVmFlags = 8;

#define HAVE_MethodVmFlags 1

inline uint8_t&
methodVmFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[MethodVmFlags]);
}

const unsigned MethodReturnCode = 1 + 8;

#define HAVE_MethodReturnCode 1

inline uint8_t&
methodReturnCode(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[MethodReturnCode]);
}

const unsigned MethodParameterCount = 1 + 1 + 8;

#define HAVE_MethodParameterCount 1

inline uint8_t&
methodParameterCount(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[MethodParameterCount]);
}

const unsigned MethodParameterFootprint = 1 + 1 + 1 + 8;

#define HAVE_MethodParameterFootprint 1

inline uint8_t&
methodParameterFootprint(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[MethodParameterFootprint]);
}

const unsigned MethodFlags = 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodFlags 1

inline uint16_t&
methodFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[MethodFlags]);
}

const unsigned MethodOffset = 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodOffset 1

inline uint16_t&
methodOffset(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[MethodOffset]);
}

const unsigned MethodNativeID = 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodNativeID 1

inline uint32_t&
methodNativeID(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[MethodNativeID]);
}

const unsigned MethodRuntimeDataIndex = 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodRuntimeDataIndex 1

inline uint32_t&
methodRuntimeDataIndex(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[MethodRuntimeDataIndex]);
}

const unsigned MethodName = 4 + 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodName 1

inline object&
methodName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodName]);
}

const unsigned MethodSpec = 8 + 4 + 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodSpec 1

inline object&
methodSpec(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodSpec]);
}

const unsigned MethodAddendum = 8 + 8 + 4 + 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodAddendum 1

inline object&
methodAddendum(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodAddendum]);
}

const unsigned MethodClass = 8 + 8 + 8 + 4 + 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodClass 1

inline object&
methodClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodClass]);
}

const unsigned MethodCode = 8 + 8 + 8 + 8 + 4 + 4 + 2 + 2 + 1 + 1 + 1 + 1 + 8;

#define HAVE_MethodCode 1

inline object&
methodCode(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodCode]);
}

const unsigned AddendumPool = 8;

#define HAVE_AddendumPool 1

inline object&
addendumPool(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::AddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[AddendumPool]);
}

const unsigned AddendumAnnotationTable = 8 + 8;

#define HAVE_AddendumAnnotationTable 1

inline object&
addendumAnnotationTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::AddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[AddendumAnnotationTable]);
}

const unsigned AddendumSignature = 8 + 8 + 8;

#define HAVE_AddendumSignature 1

inline object&
addendumSignature(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::AddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[AddendumSignature]);
}

const unsigned ClassAddendumInterfaceTable = 8 + 8 + 8 + 8;

#define HAVE_ClassAddendumInterfaceTable 1

inline object&
classAddendumInterfaceTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassAddendumInterfaceTable]);
}

const unsigned ClassAddendumInnerClassTable = 8 + 8 + 8 + 8 + 8;

#define HAVE_ClassAddendumInnerClassTable 1

inline object&
classAddendumInnerClassTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassAddendumInnerClassTable]);
}

const unsigned ClassAddendumDeclaredMethodCount = 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_ClassAddendumDeclaredMethodCount 1

inline uint32_t&
classAddendumDeclaredMethodCount(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassAddendumType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[ClassAddendumDeclaredMethodCount]);
}

const unsigned ClassAddendumEnclosingClass = 4 + 4 + 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_ClassAddendumEnclosingClass 1

inline object&
classAddendumEnclosingClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassAddendumEnclosingClass]);
}

const unsigned ClassAddendumEnclosingMethod = 8 + 4 + 4 + 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_ClassAddendumEnclosingMethod 1

inline object&
classAddendumEnclosingMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassAddendumEnclosingMethod]);
}

const unsigned MethodAddendumExceptionTable = 8 + 8 + 8 + 8;

#define HAVE_MethodAddendumExceptionTable 1

inline object&
methodAddendumExceptionTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodAddendumExceptionTable]);
}

const unsigned MethodAddendumAnnotationDefault = 8 + 8 + 8 + 8 + 8;

#define HAVE_MethodAddendumAnnotationDefault 1

inline object&
methodAddendumAnnotationDefault(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodAddendumAnnotationDefault]);
}

const unsigned MethodAddendumParameterAnnotationTable = 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_MethodAddendumParameterAnnotationTable 1

inline object&
methodAddendumParameterAnnotationTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodAddendumType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodAddendumParameterAnnotationTable]);
}

const unsigned ClassRuntimeDataArrayClass = 8;

#define HAVE_ClassRuntimeDataArrayClass 1

inline object&
classRuntimeDataArrayClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassRuntimeDataType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassRuntimeDataArrayClass]);
}

const unsigned ClassRuntimeDataJclass = 8 + 8;

#define HAVE_ClassRuntimeDataJclass 1

inline object&
classRuntimeDataJclass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassRuntimeDataType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassRuntimeDataJclass]);
}

const unsigned ClassRuntimeDataPool = 8 + 8 + 8;

#define HAVE_ClassRuntimeDataPool 1

inline object&
classRuntimeDataPool(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassRuntimeDataType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassRuntimeDataPool]);
}

const unsigned ClassRuntimeDataSigners = 8 + 8 + 8 + 8;

#define HAVE_ClassRuntimeDataSigners 1

inline object&
classRuntimeDataSigners(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassRuntimeDataType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassRuntimeDataSigners]);
}

const unsigned MethodRuntimeDataNative = 8;

#define HAVE_MethodRuntimeDataNative 1

inline object&
methodRuntimeDataNative(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MethodRuntimeDataType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MethodRuntimeDataNative]);
}

const unsigned PointerValue = 8;

#define HAVE_PointerValue 1

inline void*&
pointerValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::PointerType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[PointerValue]);
}

const unsigned NativeFunction = 8;

#define HAVE_NativeFunction 1

inline void*&
nativeFunction(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::NativeType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[NativeFunction]);
}

const unsigned NativeFast = 8 + 8;

#define HAVE_NativeFast 1

inline uint8_t&
nativeFast(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::NativeType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[NativeFast]);
}

const unsigned NativeInterceptOriginal = 7 + 1 + 8 + 8;

#define HAVE_NativeInterceptOriginal 1

inline object&
nativeInterceptOriginal(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::NativeInterceptType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[NativeInterceptOriginal]);
}

const unsigned FinderFinder = 8;

#define HAVE_FinderFinder 1

inline void*&
finderFinder(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinderType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[FinderFinder]);
}

const unsigned FinderName = 8 + 8;

#define HAVE_FinderName 1

inline object&
finderName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinderType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FinderName]);
}

const unsigned FinderNext = 8 + 8 + 8;

#define HAVE_FinderNext 1

inline object&
finderNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinderType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FinderNext]);
}

const unsigned RegionRegion = 8;

#define HAVE_RegionRegion 1

inline void*&
regionRegion(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::RegionType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[RegionRegion]);
}

const unsigned RegionPosition = 8 + 8;

#define HAVE_RegionPosition 1

inline uint32_t&
regionPosition(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::RegionType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[RegionPosition]);
}

const unsigned ExceptionHandlerTableLength = 8;

#define HAVE_ExceptionHandlerTableLength 1

inline uintptr_t&
exceptionHandlerTableLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ExceptionHandlerTableType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ExceptionHandlerTableLength]);
}

const unsigned ExceptionHandlerTableBody = 8 + 8;

#define HAVE_ExceptionHandlerTableBody 1

inline uint64_t&
exceptionHandlerTableBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ExceptionHandlerTableType), o));
  assert(t, i < exceptionHandlerTableLength(t, o));
  return reinterpret_cast<uint64_t&>(reinterpret_cast<uint8_t*>(o)[ExceptionHandlerTableBody + (i * 8)]);
}

const unsigned LineNumberTableLength = 8;

#define HAVE_LineNumberTableLength 1

inline uintptr_t&
lineNumberTableLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::LineNumberTableType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[LineNumberTableLength]);
}

const unsigned LineNumberTableBody = 8 + 8;

#define HAVE_LineNumberTableBody 1

inline uint64_t&
lineNumberTableBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::LineNumberTableType), o));
  assert(t, i < lineNumberTableLength(t, o));
  return reinterpret_cast<uint64_t&>(reinterpret_cast<uint8_t*>(o)[LineNumberTableBody + (i * 8)]);
}

const unsigned CodePool = 8;

#define HAVE_CodePool 1

inline object&
codePool(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[CodePool]);
}

const unsigned CodeExceptionHandlerTable = 8 + 8;

#define HAVE_CodeExceptionHandlerTable 1

inline object&
codeExceptionHandlerTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[CodeExceptionHandlerTable]);
}

const unsigned CodeLineNumberTable = 8 + 8 + 8;

#define HAVE_CodeLineNumberTable 1

inline object&
codeLineNumberTable(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[CodeLineNumberTable]);
}

const unsigned CodeCompiled = 8 + 8 + 8 + 8;

#define HAVE_CodeCompiled 1

inline intptr_t&
codeCompiled(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<intptr_t&>(reinterpret_cast<uint8_t*>(o)[CodeCompiled]);
}

const unsigned CodeCompiledSize = 8 + 8 + 8 + 8 + 8;

#define HAVE_CodeCompiledSize 1

inline uint32_t&
codeCompiledSize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[CodeCompiledSize]);
}

const unsigned CodeMaxStack = 4 + 8 + 8 + 8 + 8 + 8;

#define HAVE_CodeMaxStack 1

inline uint16_t&
codeMaxStack(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[CodeMaxStack]);
}

const unsigned CodeMaxLocals = 2 + 4 + 8 + 8 + 8 + 8 + 8;

#define HAVE_CodeMaxLocals 1

inline uint16_t&
codeMaxLocals(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[CodeMaxLocals]);
}

const unsigned CodeLength = 2 + 2 + 4 + 8 + 8 + 8 + 8 + 8;

#define HAVE_CodeLength 1

inline uintptr_t&
codeLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[CodeLength]);
}

const unsigned CodeBody = 8 + 2 + 2 + 4 + 8 + 8 + 8 + 8 + 8;

#define HAVE_CodeBody 1

inline uint8_t&
codeBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CodeType), o));
  assert(t, i < codeLength(t, o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[CodeBody + (i * 1)]);
}

const unsigned ReferenceKind = 8;

#define HAVE_ReferenceKind 1

inline uint8_t&
referenceKind(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ReferenceType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ReferenceKind]);
}

const unsigned ReferenceClass = 7 + 1 + 8;

#define HAVE_ReferenceClass 1

inline object&
referenceClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ReferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ReferenceClass]);
}

const unsigned ReferenceName = 8 + 7 + 1 + 8;

#define HAVE_ReferenceName 1

inline object&
referenceName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ReferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ReferenceName]);
}

const unsigned ReferenceSpec = 8 + 8 + 7 + 1 + 8;

#define HAVE_ReferenceSpec 1

inline object&
referenceSpec(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ReferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ReferenceSpec]);
}

const unsigned InvocationBootstrap = 8;

#define HAVE_InvocationBootstrap 1

inline uint16_t&
invocationBootstrap(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[InvocationBootstrap]);
}

const unsigned InvocationIndex = 2 + 2 + 8;

#define HAVE_InvocationIndex 1

inline int32_t&
invocationIndex(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationType), o));
  return reinterpret_cast<int32_t&>(reinterpret_cast<uint8_t*>(o)[InvocationIndex]);
}

const unsigned InvocationClass = 4 + 2 + 2 + 8;

#define HAVE_InvocationClass 1

inline object&
invocationClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InvocationClass]);
}

const unsigned InvocationPool = 8 + 4 + 2 + 2 + 8;

#define HAVE_InvocationPool 1

inline object&
invocationPool(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InvocationPool]);
}

const unsigned InvocationTemplate = 8 + 8 + 4 + 2 + 2 + 8;

#define HAVE_InvocationTemplate 1

inline object&
invocationTemplate(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InvocationTemplate]);
}

const unsigned InvocationSite = 8 + 8 + 8 + 4 + 2 + 2 + 8;

#define HAVE_InvocationSite 1

inline object&
invocationSite(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InvocationSite]);
}

const unsigned TripleFirst = 8;

#define HAVE_TripleFirst 1

inline object&
tripleFirst(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TripleType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TripleFirst]);
}

const unsigned TripleSecond = 8 + 8;

#define HAVE_TripleSecond 1

inline object&
tripleSecond(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TripleType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TripleSecond]);
}

const unsigned TripleThird = 8 + 8 + 8;

#define HAVE_TripleThird 1

inline object&
tripleThird(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TripleType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TripleThird]);
}

const unsigned FinalizerTarget = 8;

#define HAVE_FinalizerTarget 1

inline object&
finalizerTarget(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinalizerType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FinalizerTarget]);
}

const unsigned FinalizerFinalize = 8 + 8;

#define HAVE_FinalizerFinalize 1

inline void*&
finalizerFinalize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinalizerType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[FinalizerFinalize]);
}

const unsigned FinalizerNext = 8 + 8 + 8;

#define HAVE_FinalizerNext 1

inline object&
finalizerNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinalizerType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FinalizerNext]);
}

const unsigned FinalizerQueueTarget = 8 + 8 + 8 + 8;

#define HAVE_FinalizerQueueTarget 1

inline object&
finalizerQueueTarget(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinalizerType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FinalizerQueueTarget]);
}

const unsigned FinalizerQueueNext = 8 + 8 + 8 + 8 + 8;

#define HAVE_FinalizerQueueNext 1

inline object&
finalizerQueueNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FinalizerType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[FinalizerQueueNext]);
}

const unsigned HashMapSize = 8;

#define HAVE_HashMapSize 1

inline uint32_t&
hashMapSize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::HashMapType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[HashMapSize]);
}

const unsigned HashMapArray = 4 + 4 + 8;

#define HAVE_HashMapArray 1

inline object&
hashMapArray(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::HashMapType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[HashMapArray]);
}

const unsigned ListSize = 8;

#define HAVE_ListSize 1

inline uint32_t&
listSize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ListType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[ListSize]);
}

const unsigned ListFront = 4 + 4 + 8;

#define HAVE_ListFront 1

inline object&
listFront(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ListType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ListFront]);
}

const unsigned ListRear = 8 + 4 + 4 + 8;

#define HAVE_ListRear 1

inline object&
listRear(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ListType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ListRear]);
}

const unsigned VectorSize = 8;

#define HAVE_VectorSize 1

inline uint32_t&
vectorSize(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::VectorType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[VectorSize]);
}

const unsigned VectorLength = 4 + 4 + 8;

#define HAVE_VectorLength 1

inline uintptr_t&
vectorLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::VectorType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[VectorLength]);
}

const unsigned VectorBody = 8 + 4 + 4 + 8;

#define HAVE_VectorBody 1

inline object&
vectorBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::VectorType), o));
  assert(t, i < vectorLength(t, o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[VectorBody + (i * 8)]);
}

const unsigned TraceElementMethod = 8;

#define HAVE_TraceElementMethod 1

inline object&
traceElementMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TraceElementType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TraceElementMethod]);
}

const unsigned TraceElementIp = 8 + 8;

#define HAVE_TraceElementIp 1

inline int32_t&
traceElementIp(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TraceElementType), o));
  return reinterpret_cast<int32_t&>(reinterpret_cast<uint8_t*>(o)[TraceElementIp]);
}

const unsigned TreeNodeValue = 8;

#define HAVE_TreeNodeValue 1

inline object&
treeNodeValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TreeNodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TreeNodeValue]);
}

const unsigned TreeNodeLeft = 8 + 8;

#define HAVE_TreeNodeLeft 1

inline object&
treeNodeLeft(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TreeNodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TreeNodeLeft]);
}

const unsigned TreeNodeRight = 8 + 8 + 8;

#define HAVE_TreeNodeRight 1

inline object&
treeNodeRight(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::TreeNodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[TreeNodeRight]);
}

const unsigned CallNodeAddress = 8;

#define HAVE_CallNodeAddress 1

inline intptr_t&
callNodeAddress(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CallNodeType), o));
  return reinterpret_cast<intptr_t&>(reinterpret_cast<uint8_t*>(o)[CallNodeAddress]);
}

const unsigned CallNodeTarget = 8 + 8;

#define HAVE_CallNodeTarget 1

inline object&
callNodeTarget(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CallNodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[CallNodeTarget]);
}

const unsigned CallNodeFlags = 8 + 8 + 8;

#define HAVE_CallNodeFlags 1

inline uintptr_t&
callNodeFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CallNodeType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[CallNodeFlags]);
}

const unsigned CallNodeNext = 8 + 8 + 8 + 8;

#define HAVE_CallNodeNext 1

inline object&
callNodeNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CallNodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[CallNodeNext]);
}

const unsigned WordArrayLength = 8;

#define HAVE_WordArrayLength 1

inline uintptr_t&
wordArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::WordArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[WordArrayLength]);
}

const unsigned WordArrayBody = 8 + 8;

#define HAVE_WordArrayBody 1

inline uintptr_t&
wordArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::WordArrayType), o));
  assert(t, i < wordArrayLength(t, o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[WordArrayBody + (i * 8)]);
}

const unsigned ArrayLength = 8;

#define HAVE_ArrayLength 1

inline uintptr_t&
arrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ArrayLength]);
}

const unsigned ArrayBody = 8 + 8;

#define HAVE_ArrayBody 1

inline object&
arrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ArrayType), o));
  assert(t, i < arrayLength(t, o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ArrayBody + (i * 8)]);
}

inline object&
arrayBodyUnsafe(Thread* t UNUSED, object o, unsigned i) {
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ArrayBody + (i * 8)]);
}

const unsigned PairFirst = 8;

#define HAVE_PairFirst 1

inline object&
pairFirst(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::PairType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[PairFirst]);
}

const unsigned PairSecond = 8 + 8;

#define HAVE_PairSecond 1

inline object&
pairSecond(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::PairType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[PairSecond]);
}

const unsigned MonitorOwner = 8;

#define HAVE_MonitorOwner 1

inline void*&
monitorOwner(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[MonitorOwner]);
}

const unsigned MonitorWaitHead = 8 + 8;

#define HAVE_MonitorWaitHead 1

inline void*&
monitorWaitHead(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[MonitorWaitHead]);
}

const unsigned MonitorWaitTail = 8 + 8 + 8;

#define HAVE_MonitorWaitTail 1

inline void*&
monitorWaitTail(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[MonitorWaitTail]);
}

const unsigned MonitorAcquireHead = 8 + 8 + 8 + 8;

#define HAVE_MonitorAcquireHead 1

inline object&
monitorAcquireHead(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MonitorAcquireHead]);
}

const unsigned MonitorAcquireTail = 8 + 8 + 8 + 8 + 8;

#define HAVE_MonitorAcquireTail 1

inline object&
monitorAcquireTail(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MonitorAcquireTail]);
}

const unsigned MonitorDepth = 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_MonitorDepth 1

inline uint32_t&
monitorDepth(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[MonitorDepth]);
}

const unsigned MonitorNodeValue = 8;

#define HAVE_MonitorNodeValue 1

inline void*&
monitorNodeValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorNodeType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[MonitorNodeValue]);
}

const unsigned MonitorNodeNext = 8 + 8;

#define HAVE_MonitorNodeNext 1

inline object&
monitorNodeNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::MonitorNodeType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[MonitorNodeNext]);
}

const unsigned InnerClassReferenceInner = 8;

#define HAVE_InnerClassReferenceInner 1

inline object&
innerClassReferenceInner(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InnerClassReferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InnerClassReferenceInner]);
}

const unsigned InnerClassReferenceOuter = 8 + 8;

#define HAVE_InnerClassReferenceOuter 1

inline object&
innerClassReferenceOuter(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InnerClassReferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InnerClassReferenceOuter]);
}

const unsigned InnerClassReferenceName = 8 + 8 + 8;

#define HAVE_InnerClassReferenceName 1

inline object&
innerClassReferenceName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InnerClassReferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InnerClassReferenceName]);
}

const unsigned InnerClassReferenceFlags = 8 + 8 + 8 + 8;

#define HAVE_InnerClassReferenceFlags 1

inline uint16_t&
innerClassReferenceFlags(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InnerClassReferenceType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[InnerClassReferenceFlags]);
}

const unsigned ContinuationContextNext = 8;

#define HAVE_ContinuationContextNext 1

inline object&
continuationContextNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationContextType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationContextNext]);
}

const unsigned ContinuationContextBefore = 8 + 8;

#define HAVE_ContinuationContextBefore 1

inline object&
continuationContextBefore(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationContextType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationContextBefore]);
}

const unsigned ContinuationContextAfter = 8 + 8 + 8;

#define HAVE_ContinuationContextAfter 1

inline object&
continuationContextAfter(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationContextType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationContextAfter]);
}

const unsigned ContinuationContextContinuation = 8 + 8 + 8 + 8;

#define HAVE_ContinuationContextContinuation 1

inline object&
continuationContextContinuation(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationContextType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationContextContinuation]);
}

const unsigned ContinuationContextMethod = 8 + 8 + 8 + 8 + 8;

#define HAVE_ContinuationContextMethod 1

inline object&
continuationContextMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationContextType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationContextMethod]);
}

const unsigned ContinuationNext = 8;

#define HAVE_ContinuationNext 1

inline object&
continuationNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationNext]);
}

const unsigned ContinuationContext = 8 + 8;

#define HAVE_ContinuationContext 1

inline object&
continuationContext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationContext]);
}

const unsigned ContinuationMethod = 8 + 8 + 8;

#define HAVE_ContinuationMethod 1

inline object&
continuationMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ContinuationMethod]);
}

const unsigned ContinuationAddress = 8 + 8 + 8 + 8;

#define HAVE_ContinuationAddress 1

inline void*&
continuationAddress(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<void*&>(reinterpret_cast<uint8_t*>(o)[ContinuationAddress]);
}

const unsigned ContinuationReturnAddressOffset = 8 + 8 + 8 + 8 + 8;

#define HAVE_ContinuationReturnAddressOffset 1

inline uintptr_t&
continuationReturnAddressOffset(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ContinuationReturnAddressOffset]);
}

const unsigned ContinuationFramePointerOffset = 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_ContinuationFramePointerOffset 1

inline uintptr_t&
continuationFramePointerOffset(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ContinuationFramePointerOffset]);
}

const unsigned ContinuationLength = 8 + 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_ContinuationLength 1

inline uintptr_t&
continuationLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ContinuationLength]);
}

const unsigned ContinuationBody = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 8;

#define HAVE_ContinuationBody 1

inline uintptr_t&
continuationBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ContinuationType), o));
  assert(t, i < continuationLength(t, o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ContinuationBody + (i * 8)]);
}

const unsigned UnwindResultContinuation = 8;

#define HAVE_UnwindResultContinuation 1

inline object&
unwindResultContinuation(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::UnwindResultType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[UnwindResultContinuation]);
}

const unsigned UnwindResultResult = 8 + 8;

#define HAVE_UnwindResultResult 1

inline object&
unwindResultResult(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::UnwindResultType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[UnwindResultResult]);
}

const unsigned UnwindResultException = 8 + 8 + 8;

#define HAVE_UnwindResultException 1

inline object&
unwindResultException(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::UnwindResultType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[UnwindResultException]);
}

const unsigned StringData = 8;

#define HAVE_StringData 1

inline object&
stringData(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StringType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[StringData]);
}

const unsigned StringOffset = 8 + 8;

#define HAVE_StringOffset 1

inline uint32_t&
stringOffset(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StringType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[StringOffset]);
}

const unsigned StringLength = 4 + 8 + 8;

#define HAVE_StringLength 1

inline uint32_t&
stringLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StringType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[StringLength]);
}

const unsigned StringHashCode = 4 + 4 + 8 + 8;

#define HAVE_StringHashCode 1

inline uint32_t&
stringHashCode(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StringType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[StringHashCode]);
}

const unsigned ThreadParkBlocker = 8;

#define HAVE_ThreadParkBlocker 1

inline object&
threadParkBlocker(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadParkBlocker]);
}

const unsigned ThreadPeer = 8 + 8;

#define HAVE_ThreadPeer 1

inline uint64_t&
threadPeer(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<uint64_t&>(reinterpret_cast<uint8_t*>(o)[ThreadPeer]);
}

const unsigned ThreadInterrupted = 8 + 8 + 8;

#define HAVE_ThreadInterrupted 1

inline uint8_t&
threadInterrupted(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ThreadInterrupted]);
}

const unsigned ThreadUnparked = 1 + 8 + 8 + 8;

#define HAVE_ThreadUnparked 1

inline uint8_t&
threadUnparked(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ThreadUnparked]);
}

const unsigned ThreadDaemon = 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadDaemon 1

inline uint8_t&
threadDaemon(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ThreadDaemon]);
}

const unsigned ThreadState = 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadState 1

inline uint8_t&
threadState(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ThreadState]);
}

const unsigned ThreadPriority = 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadPriority 1

inline uint8_t&
threadPriority(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ThreadPriority]);
}

const unsigned ThreadTask = 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadTask 1

inline object&
threadTask(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadTask]);
}

const unsigned ThreadLocals = 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadLocals 1

inline object&
threadLocals(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadLocals]);
}

const unsigned ThreadSleepLock = 8 + 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadSleepLock 1

inline object&
threadSleepLock(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadSleepLock]);
}

const unsigned ThreadClassLoader = 8 + 8 + 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadClassLoader 1

inline object&
threadClassLoader(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadClassLoader]);
}

const unsigned ThreadExceptionHandler = 8 + 8 + 8 + 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadExceptionHandler 1

inline object&
threadExceptionHandler(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadExceptionHandler]);
}

const unsigned ThreadName = 8 + 8 + 8 + 8 + 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadName 1

inline object&
threadName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadName]);
}

const unsigned ThreadGroup = 8 + 8 + 8 + 8 + 8 + 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadGroup 1

inline object&
threadGroup(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadGroup]);
}

const unsigned ThreadInterruptLock = 8 + 8 + 8 + 8 + 8 + 8 + 8 + 3 + 1 + 1 + 1 + 1 + 1 + 8 + 8 + 8;

#define HAVE_ThreadInterruptLock 1

inline object&
threadInterruptLock(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadInterruptLock]);
}

const unsigned ThreadGroupParent = 8;

#define HAVE_ThreadGroupParent 1

inline object&
threadGroupParent(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadGroupType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadGroupParent]);
}

const unsigned ThreadGroupName = 8 + 8;

#define HAVE_ThreadGroupName 1

inline object&
threadGroupName(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadGroupType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadGroupName]);
}

const unsigned ThreadGroupSubgroups = 8 + 8 + 8;

#define HAVE_ThreadGroupSubgroups 1

inline object&
threadGroupSubgroups(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThreadGroupType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThreadGroupSubgroups]);
}

const unsigned StackTraceElementClass = 8;

#define HAVE_StackTraceElementClass 1

inline object&
stackTraceElementClass(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StackTraceElementType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[StackTraceElementClass]);
}

const unsigned StackTraceElementMethod = 8 + 8;

#define HAVE_StackTraceElementMethod 1

inline object&
stackTraceElementMethod(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StackTraceElementType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[StackTraceElementMethod]);
}

const unsigned StackTraceElementFile = 8 + 8 + 8;

#define HAVE_StackTraceElementFile 1

inline object&
stackTraceElementFile(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StackTraceElementType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[StackTraceElementFile]);
}

const unsigned StackTraceElementLine = 8 + 8 + 8 + 8;

#define HAVE_StackTraceElementLine 1

inline uint32_t&
stackTraceElementLine(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::StackTraceElementType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[StackTraceElementLine]);
}

const unsigned ThrowableMessage = 8;

#define HAVE_ThrowableMessage 1

inline object&
throwableMessage(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThrowableType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThrowableMessage]);
}

const unsigned ThrowableTrace = 8 + 8;

#define HAVE_ThrowableTrace 1

inline object&
throwableTrace(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThrowableType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThrowableTrace]);
}

const unsigned ThrowableCause = 8 + 8 + 8;

#define HAVE_ThrowableCause 1

inline object&
throwableCause(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ThrowableType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ThrowableCause]);
}

const unsigned ClassNotFoundExceptionCause2 = 8 + 8 + 8 + 8;

#define HAVE_ClassNotFoundExceptionCause2 1

inline object&
classNotFoundExceptionCause2(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ClassNotFoundExceptionType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ClassNotFoundExceptionCause2]);
}

const unsigned InvocationTargetExceptionTarget = 8 + 8 + 8 + 8;

#define HAVE_InvocationTargetExceptionTarget 1

inline object&
invocationTargetExceptionTarget(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::InvocationTargetExceptionType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[InvocationTargetExceptionTarget]);
}

const unsigned ExceptionInInitializerErrorException = 8 + 8 + 8 + 8;

#define HAVE_ExceptionInInitializerErrorException 1

inline object&
exceptionInInitializerErrorException(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ExceptionInInitializerErrorType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ExceptionInInitializerErrorException]);
}

const unsigned ByteValue = 8;

#define HAVE_ByteValue 1

inline uint8_t&
byteValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ByteType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[ByteValue]);
}

const unsigned BooleanValue = 8;

#define HAVE_BooleanValue 1

inline uint8_t&
booleanValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::BooleanType), o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[BooleanValue]);
}

const unsigned ShortValue = 8;

#define HAVE_ShortValue 1

inline uint16_t&
shortValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ShortType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[ShortValue]);
}

const unsigned CharValue = 8;

#define HAVE_CharValue 1

inline uint16_t&
charValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CharType), o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[CharValue]);
}

const unsigned IntValue = 8;

#define HAVE_IntValue 1

inline uint32_t&
intValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::IntType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[IntValue]);
}

const unsigned LongValue = 8;

#define HAVE_LongValue 1

inline uint64_t&
longValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::LongType), o));
  return reinterpret_cast<uint64_t&>(reinterpret_cast<uint8_t*>(o)[LongValue]);
}

const unsigned FloatValue = 8;

#define HAVE_FloatValue 1

inline uint32_t&
floatValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FloatType), o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[FloatValue]);
}

const unsigned DoubleValue = 8;

#define HAVE_DoubleValue 1

inline uint64_t&
doubleValue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::DoubleType), o));
  return reinterpret_cast<uint64_t&>(reinterpret_cast<uint8_t*>(o)[DoubleValue]);
}

const unsigned ReferenceQueueFront = 8;

#define HAVE_ReferenceQueueFront 1

inline object&
referenceQueueFront(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ReferenceQueueType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[ReferenceQueueFront]);
}

const unsigned JreferenceVmNext = 8;

#define HAVE_JreferenceVmNext 1

inline object&
jreferenceVmNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JreferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JreferenceVmNext]);
}

const unsigned JreferenceTarget = 8 + 8;

#define HAVE_JreferenceTarget 1

inline object&
jreferenceTarget(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JreferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JreferenceTarget]);
}

const unsigned JreferenceQueue = 8 + 8 + 8;

#define HAVE_JreferenceQueue 1

inline object&
jreferenceQueue(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JreferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JreferenceQueue]);
}

const unsigned JreferenceJNext = 8 + 8 + 8 + 8;

#define HAVE_JreferenceJNext 1

inline object&
jreferenceJNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::JreferenceType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[JreferenceJNext]);
}

const unsigned CleanerQueueNext = 8;

#define HAVE_CleanerQueueNext 1

inline object&
cleanerQueueNext(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CleanerType), o));
  return reinterpret_cast<object&>(reinterpret_cast<uint8_t*>(o)[CleanerQueueNext]);
}

const unsigned ByteArrayLength = 8;

#define HAVE_ByteArrayLength 1

inline uintptr_t&
byteArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ByteArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ByteArrayLength]);
}

const unsigned ByteArrayBody = 8 + 8;

#define HAVE_ByteArrayBody 1

inline int8_t&
byteArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ByteArrayType), o));
  assert(t, i < byteArrayLength(t, o));
  return reinterpret_cast<int8_t&>(reinterpret_cast<uint8_t*>(o)[ByteArrayBody + (i * 1)]);
}

const unsigned BooleanArrayLength = 8;

#define HAVE_BooleanArrayLength 1

inline uintptr_t&
booleanArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::BooleanArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[BooleanArrayLength]);
}

const unsigned BooleanArrayBody = 8 + 8;

#define HAVE_BooleanArrayBody 1

inline uint8_t&
booleanArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::BooleanArrayType), o));
  assert(t, i < booleanArrayLength(t, o));
  return reinterpret_cast<uint8_t&>(reinterpret_cast<uint8_t*>(o)[BooleanArrayBody + (i * 1)]);
}

const unsigned ShortArrayLength = 8;

#define HAVE_ShortArrayLength 1

inline uintptr_t&
shortArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ShortArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[ShortArrayLength]);
}

const unsigned ShortArrayBody = 8 + 8;

#define HAVE_ShortArrayBody 1

inline int16_t&
shortArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::ShortArrayType), o));
  assert(t, i < shortArrayLength(t, o));
  return reinterpret_cast<int16_t&>(reinterpret_cast<uint8_t*>(o)[ShortArrayBody + (i * 2)]);
}

const unsigned CharArrayLength = 8;

#define HAVE_CharArrayLength 1

inline uintptr_t&
charArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CharArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[CharArrayLength]);
}

const unsigned CharArrayBody = 8 + 8;

#define HAVE_CharArrayBody 1

inline uint16_t&
charArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::CharArrayType), o));
  assert(t, i < charArrayLength(t, o));
  return reinterpret_cast<uint16_t&>(reinterpret_cast<uint8_t*>(o)[CharArrayBody + (i * 2)]);
}

const unsigned IntArrayLength = 8;

#define HAVE_IntArrayLength 1

inline uintptr_t&
intArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::IntArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[IntArrayLength]);
}

const unsigned IntArrayBody = 8 + 8;

#define HAVE_IntArrayBody 1

inline int32_t&
intArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::IntArrayType), o));
  assert(t, i < intArrayLength(t, o));
  return reinterpret_cast<int32_t&>(reinterpret_cast<uint8_t*>(o)[IntArrayBody + (i * 4)]);
}

const unsigned LongArrayLength = 8;

#define HAVE_LongArrayLength 1

inline uintptr_t&
longArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::LongArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[LongArrayLength]);
}

const unsigned LongArrayBody = 8 + 8;

#define HAVE_LongArrayBody 1

inline int64_t&
longArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::LongArrayType), o));
  assert(t, i < longArrayLength(t, o));
  return reinterpret_cast<int64_t&>(reinterpret_cast<uint8_t*>(o)[LongArrayBody + (i * 8)]);
}

const unsigned FloatArrayLength = 8;

#define HAVE_FloatArrayLength 1

inline uintptr_t&
floatArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FloatArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[FloatArrayLength]);
}

const unsigned FloatArrayBody = 8 + 8;

#define HAVE_FloatArrayBody 1

inline uint32_t&
floatArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::FloatArrayType), o));
  assert(t, i < floatArrayLength(t, o));
  return reinterpret_cast<uint32_t&>(reinterpret_cast<uint8_t*>(o)[FloatArrayBody + (i * 4)]);
}

const unsigned DoubleArrayLength = 8;

#define HAVE_DoubleArrayLength 1

inline uintptr_t&
doubleArrayLength(Thread* t UNUSED, object o) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::DoubleArrayType), o));
  return reinterpret_cast<uintptr_t&>(reinterpret_cast<uint8_t*>(o)[DoubleArrayLength]);
}

const unsigned DoubleArrayBody = 8 + 8;

#define HAVE_DoubleArrayBody 1

inline uint64_t&
doubleArrayBody(Thread* t UNUSED, object o, unsigned i) {
  assert(t, t->m->unsafe or instanceOf(t, arrayBodyUnsafe(t, t->m->types, Machine::DoubleArrayType), o));
  assert(t, i < doubleArrayLength(t, o));
  return reinterpret_cast<uint64_t&>(reinterpret_cast<uint8_t*>(o)[DoubleArrayBody + (i * 8)]);
}

const unsigned FixedSizeOfJobject = 8;

const unsigned FixedSizeOfClass = 128;

const unsigned ArrayElementSizeOfClass = 8;

const unsigned FixedSizeOfJclass = 16;

const unsigned FixedSizeOfJaccessibleObject = 8;

const unsigned FixedSizeOfJfield = 24;

const unsigned FixedSizeOfJmethod = 24;

const unsigned FixedSizeOfJconstructor = 16;

const unsigned FixedSizeOfConstantPool = 8;

const unsigned FixedSizeOfSerializable = 8;

const unsigned FixedSizeOfCloneable = 8;

const unsigned FixedSizeOfSingleton = 16;

const unsigned ArrayElementSizeOfSingleton = 8;

const unsigned FixedSizeOfClassLoader = 32;

const unsigned FixedSizeOfSystemClassLoader = 40;

const unsigned FixedSizeOfField = 56;

const unsigned FixedSizeOfMethod = 64;

const unsigned FixedSizeOfAddendum = 32;

const unsigned FixedSizeOfClassAddendum = 72;

const unsigned FixedSizeOfMethodAddendum = 56;

const unsigned FixedSizeOfFieldAddendum = 32;

const unsigned FixedSizeOfClassRuntimeData = 40;

const unsigned FixedSizeOfMethodRuntimeData = 16;

const unsigned FixedSizeOfPointer = 16;

const unsigned FixedSizeOfNative = 24;

const unsigned FixedSizeOfNativeIntercept = 32;

const unsigned FixedSizeOfFinder = 32;

const unsigned FixedSizeOfRegion = 24;

const unsigned FixedSizeOfExceptionHandlerTable = 16;

const unsigned ArrayElementSizeOfExceptionHandlerTable = 8;

const unsigned FixedSizeOfLineNumberTable = 16;

const unsigned ArrayElementSizeOfLineNumberTable = 8;

const unsigned FixedSizeOfCode = 56;

const unsigned ArrayElementSizeOfCode = 1;

const unsigned FixedSizeOfReference = 40;

const unsigned FixedSizeOfInvocation = 48;

const unsigned FixedSizeOfTriple = 32;

const unsigned FixedSizeOfFinalizer = 48;

const unsigned FixedSizeOfHashMap = 24;

const unsigned FixedSizeOfWeakHashMap = 24;

const unsigned FixedSizeOfList = 32;

const unsigned FixedSizeOfVector = 24;

const unsigned ArrayElementSizeOfVector = 8;

const unsigned FixedSizeOfTraceElement = 24;

const unsigned FixedSizeOfTreeNode = 32;

const unsigned FixedSizeOfCallNode = 40;

const unsigned FixedSizeOfWordArray = 16;

const unsigned ArrayElementSizeOfWordArray = 8;

const unsigned FixedSizeOfArray = 16;

const unsigned ArrayElementSizeOfArray = 8;

const unsigned FixedSizeOfPair = 24;

const unsigned FixedSizeOfMonitor = 56;

const unsigned FixedSizeOfMonitorNode = 24;

const unsigned FixedSizeOfInnerClassReference = 40;

const unsigned FixedSizeOfContinuationContext = 48;

const unsigned FixedSizeOfContinuation = 64;

const unsigned ArrayElementSizeOfContinuation = 8;

const unsigned FixedSizeOfUnwindResult = 32;

const unsigned FixedSizeOfString = 32;

const unsigned FixedSizeOfThread = 96;

const unsigned FixedSizeOfThreadGroup = 32;

const unsigned FixedSizeOfStackTraceElement = 40;

const unsigned FixedSizeOfThrowable = 32;

const unsigned FixedSizeOfException = 32;

const unsigned FixedSizeOfRuntimeException = 32;

const unsigned FixedSizeOfNullPointerException = 32;

const unsigned FixedSizeOfArithmeticException = 32;

const unsigned FixedSizeOfIllegalStateException = 32;

const unsigned FixedSizeOfIllegalArgumentException = 32;

const unsigned FixedSizeOfIllegalMonitorStateException = 32;

const unsigned FixedSizeOfIndexOutOfBoundsException = 32;

const unsigned FixedSizeOfArrayIndexOutOfBoundsException = 32;

const unsigned FixedSizeOfArrayStoreException = 32;

const unsigned FixedSizeOfNegativeArraySizeException = 32;

const unsigned FixedSizeOfCloneNotSupportedException = 32;

const unsigned FixedSizeOfReflectiveOperationException = 32;

const unsigned FixedSizeOfClassCastException = 32;

const unsigned FixedSizeOfClassNotFoundException = 40;

const unsigned FixedSizeOfInvocationTargetException = 40;

const unsigned FixedSizeOfInterruptedException = 32;

const unsigned FixedSizeOfError = 32;

const unsigned FixedSizeOfVirtualMachineError = 32;

const unsigned FixedSizeOfOutOfMemoryError = 32;

const unsigned FixedSizeOfStackOverflowError = 32;

const unsigned FixedSizeOfLinkageError = 32;

const unsigned FixedSizeOfIncompatibleClassChangeError = 32;

const unsigned FixedSizeOfAbstractMethodError = 32;

const unsigned FixedSizeOfNoSuchFieldError = 32;

const unsigned FixedSizeOfNoSuchMethodError = 32;

const unsigned FixedSizeOfNoClassDefFoundError = 32;

const unsigned FixedSizeOfUnsatisfiedLinkError = 32;

const unsigned FixedSizeOfExceptionInInitializerError = 40;

const unsigned FixedSizeOfIoException = 32;

const unsigned FixedSizeOfFileNotFoundException = 32;

const unsigned FixedSizeOfIncompatibleContinuationException = 32;

const unsigned FixedSizeOfNumber = 8;

const unsigned FixedSizeOfByte = 16;

const unsigned FixedSizeOfBoolean = 16;

const unsigned FixedSizeOfShort = 16;

const unsigned FixedSizeOfChar = 16;

const unsigned FixedSizeOfInt = 16;

const unsigned FixedSizeOfLong = 16;

const unsigned FixedSizeOfFloat = 16;

const unsigned FixedSizeOfDouble = 16;

const unsigned FixedSizeOfReferenceQueue = 16;

const unsigned FixedSizeOfJreference = 40;

const unsigned FixedSizeOfWeakReference = 40;

const unsigned FixedSizeOfSoftReference = 40;

const unsigned FixedSizeOfPhantomReference = 40;

const unsigned FixedSizeOfCleaner = 16;

const unsigned FixedSizeOfByteArray = 16;

const unsigned ArrayElementSizeOfByteArray = 1;

const unsigned FixedSizeOfBooleanArray = 16;

const unsigned ArrayElementSizeOfBooleanArray = 1;

const unsigned FixedSizeOfShortArray = 16;

const unsigned ArrayElementSizeOfShortArray = 2;

const unsigned FixedSizeOfCharArray = 16;

const unsigned ArrayElementSizeOfCharArray = 2;

const unsigned FixedSizeOfIntArray = 16;

const unsigned ArrayElementSizeOfIntArray = 4;

const unsigned FixedSizeOfLongArray = 16;

const unsigned ArrayElementSizeOfLongArray = 8;

const unsigned FixedSizeOfFloatArray = 16;

const unsigned ArrayElementSizeOfFloatArray = 4;

const unsigned FixedSizeOfDoubleArray = 16;

const unsigned ArrayElementSizeOfDoubleArray = 8;

const unsigned FixedSizeOfJbyte = 8;

const unsigned FixedSizeOfJboolean = 8;

const unsigned FixedSizeOfJshort = 8;

const unsigned FixedSizeOfJchar = 8;

const unsigned FixedSizeOfJint = 8;

const unsigned FixedSizeOfJlong = 8;

const unsigned FixedSizeOfJfloat = 8;

const unsigned FixedSizeOfJdouble = 8;

const unsigned FixedSizeOfJvoid = 8;

void initJobject(Thread* t, object o);

void initClass(Thread* t, object o, uint16_t flags, uint16_t vmFlags, uint16_t fixedSize, uint8_t arrayElementSize, uint8_t arrayDimensions, uint32_t runtimeDataIndex, object objectMask, object name, object sourceFile, object super, object interfaceTable, object virtualTable, object fieldTable, object methodTable, object addendum, object staticTable, object loader, object source, uintptr_t length);

void initJclass(Thread* t, object o, object vmClass);

void initJaccessibleObject(Thread* t, object o);

void initJfield(Thread* t, object o, object vmField, uint8_t accessible);

void initJmethod(Thread* t, object o, object vmMethod, uint8_t accessible);

void initJconstructor(Thread* t, object o, object method);

void initConstantPool(Thread* t, object o);

void initSerializable(Thread* t, object o);

void initCloneable(Thread* t, object o);

void initSingleton(Thread* t, object o, uintptr_t length);

void initClassLoader(Thread* t, object o, object parent, object packages, object map);

void initSystemClassLoader(Thread* t, object o, object parent, object packages, object map, void* finder);

void initField(Thread* t, object o, uint8_t vmFlags, uint8_t code, uint16_t flags, uint16_t offset, uint32_t nativeID, object name, object spec, object addendum, object class_);

void initMethod(Thread* t, object o, uint8_t vmFlags, uint8_t returnCode, uint8_t parameterCount, uint8_t parameterFootprint, uint16_t flags, uint16_t offset, uint32_t nativeID, uint32_t runtimeDataIndex, object name, object spec, object addendum, object class_, object code);

void initAddendum(Thread* t, object o, object pool, object annotationTable, object signature);

void initClassAddendum(Thread* t, object o, object pool, object annotationTable, object signature, object interfaceTable, object innerClassTable, uint32_t declaredMethodCount, object enclosingClass, object enclosingMethod);

void initMethodAddendum(Thread* t, object o, object pool, object annotationTable, object signature, object exceptionTable, object annotationDefault, object parameterAnnotationTable);

void initFieldAddendum(Thread* t, object o, object pool, object annotationTable, object signature);

void initClassRuntimeData(Thread* t, object o, object arrayClass, object jclass, object pool, object signers);

void initMethodRuntimeData(Thread* t, object o, object native);

void initPointer(Thread* t, object o, void* value);

void initNative(Thread* t, object o, void* function, uint8_t fast);

void initNativeIntercept(Thread* t, object o, void* function, uint8_t fast, object original);

void initFinder(Thread* t, object o, void* finder, object name, object next);

void initRegion(Thread* t, object o, void* region, uint32_t position);

void initExceptionHandlerTable(Thread* t, object o, uintptr_t length);

void initLineNumberTable(Thread* t, object o, uintptr_t length);

void initCode(Thread* t, object o, object pool, object exceptionHandlerTable, object lineNumberTable, intptr_t compiled, uint32_t compiledSize, uint16_t maxStack, uint16_t maxLocals, uintptr_t length);

void initReference(Thread* t, object o, uint8_t kind, object class_, object name, object spec);

void initInvocation(Thread* t, object o, uint16_t bootstrap, int32_t index, object class_, object pool, object template_, object site);

void initTriple(Thread* t, object o, object first, object second, object third);

void initFinalizer(Thread* t, object o, object target, void* finalize, object next, object queueTarget, object queueNext);

void initHashMap(Thread* t, object o, uint32_t size, object array);

void initWeakHashMap(Thread* t, object o, uint32_t size, object array);

void initList(Thread* t, object o, uint32_t size, object front, object rear);

void initVector(Thread* t, object o, uint32_t size, uintptr_t length);

void initTraceElement(Thread* t, object o, object method, int32_t ip);

void initTreeNode(Thread* t, object o, object value, object left, object right);

void initCallNode(Thread* t, object o, intptr_t address, object target, uintptr_t flags, object next);

void initWordArray(Thread* t, object o, uintptr_t length);

void initArray(Thread* t, object o, uintptr_t length);

void initPair(Thread* t, object o, object first, object second);

void initMonitor(Thread* t, object o, void* owner, void* waitHead, void* waitTail, object acquireHead, object acquireTail, uint32_t depth);

void initMonitorNode(Thread* t, object o, void* value, object next);

void initInnerClassReference(Thread* t, object o, object inner, object outer, object name, uint16_t flags);

void initContinuationContext(Thread* t, object o, object next, object before, object after, object continuation, object method);

void initContinuation(Thread* t, object o, object next, object context, object method, void* address, uintptr_t returnAddressOffset, uintptr_t framePointerOffset, uintptr_t length);

void initUnwindResult(Thread* t, object o, object continuation, object result, object exception);

void initString(Thread* t, object o, object data, uint32_t offset, uint32_t length, uint32_t hashCode);

void initThread(Thread* t, object o, object parkBlocker, uint64_t peer, uint8_t interrupted, uint8_t unparked, uint8_t daemon, uint8_t state, uint8_t priority, object task, object locals, object sleepLock, object classLoader, object exceptionHandler, object name, object group, object interruptLock);

void initThreadGroup(Thread* t, object o, object parent, object name, object subgroups);

void initStackTraceElement(Thread* t, object o, object class_, object method, object file, uint32_t line);

void initThrowable(Thread* t, object o, object message, object trace, object cause);

void initException(Thread* t, object o, object message, object trace, object cause);

void initRuntimeException(Thread* t, object o, object message, object trace, object cause);

void initNullPointerException(Thread* t, object o, object message, object trace, object cause);

void initArithmeticException(Thread* t, object o, object message, object trace, object cause);

void initIllegalStateException(Thread* t, object o, object message, object trace, object cause);

void initIllegalArgumentException(Thread* t, object o, object message, object trace, object cause);

void initIllegalMonitorStateException(Thread* t, object o, object message, object trace, object cause);

void initIndexOutOfBoundsException(Thread* t, object o, object message, object trace, object cause);

void initArrayIndexOutOfBoundsException(Thread* t, object o, object message, object trace, object cause);

void initArrayStoreException(Thread* t, object o, object message, object trace, object cause);

void initNegativeArraySizeException(Thread* t, object o, object message, object trace, object cause);

void initCloneNotSupportedException(Thread* t, object o, object message, object trace, object cause);

void initReflectiveOperationException(Thread* t, object o, object message, object trace, object cause);

void initClassCastException(Thread* t, object o, object message, object trace, object cause);

void initClassNotFoundException(Thread* t, object o, object message, object trace, object cause, object cause2);

void initInvocationTargetException(Thread* t, object o, object message, object trace, object cause, object target);

void initInterruptedException(Thread* t, object o, object message, object trace, object cause);

void initError(Thread* t, object o, object message, object trace, object cause);

void initVirtualMachineError(Thread* t, object o, object message, object trace, object cause);

void initOutOfMemoryError(Thread* t, object o, object message, object trace, object cause);

void initStackOverflowError(Thread* t, object o, object message, object trace, object cause);

void initLinkageError(Thread* t, object o, object message, object trace, object cause);

void initIncompatibleClassChangeError(Thread* t, object o, object message, object trace, object cause);

void initAbstractMethodError(Thread* t, object o, object message, object trace, object cause);

void initNoSuchFieldError(Thread* t, object o, object message, object trace, object cause);

void initNoSuchMethodError(Thread* t, object o, object message, object trace, object cause);

void initNoClassDefFoundError(Thread* t, object o, object message, object trace, object cause);

void initUnsatisfiedLinkError(Thread* t, object o, object message, object trace, object cause);

void initExceptionInInitializerError(Thread* t, object o, object message, object trace, object cause, object exception);

void initIoException(Thread* t, object o, object message, object trace, object cause);

void initFileNotFoundException(Thread* t, object o, object message, object trace, object cause);

void initIncompatibleContinuationException(Thread* t, object o, object message, object trace, object cause);

void initNumber(Thread* t, object o);

void initByte(Thread* t, object o, uint8_t value);

void initBoolean(Thread* t, object o, uint8_t value);

void initShort(Thread* t, object o, uint16_t value);

void initChar(Thread* t, object o, uint16_t value);

void initInt(Thread* t, object o, uint32_t value);

void initLong(Thread* t, object o, uint64_t value);

void initFloat(Thread* t, object o, uint32_t value);

void initDouble(Thread* t, object o, uint64_t value);

void initReferenceQueue(Thread* t, object o, object front);

void initJreference(Thread* t, object o, object vmNext, object target, object queue, object jNext);

void initWeakReference(Thread* t, object o, object vmNext, object target, object queue, object jNext);

void initSoftReference(Thread* t, object o, object vmNext, object target, object queue, object jNext);

void initPhantomReference(Thread* t, object o, object vmNext, object target, object queue, object jNext);

void initCleaner(Thread* t, object o, object queueNext);

void initByteArray(Thread* t, object o, uintptr_t length);

void initBooleanArray(Thread* t, object o, uintptr_t length);

void initShortArray(Thread* t, object o, uintptr_t length);

void initCharArray(Thread* t, object o, uintptr_t length);

void initIntArray(Thread* t, object o, uintptr_t length);

void initLongArray(Thread* t, object o, uintptr_t length);

void initFloatArray(Thread* t, object o, uintptr_t length);

void initDoubleArray(Thread* t, object o, uintptr_t length);

void initJbyte(Thread* t, object o);

void initJboolean(Thread* t, object o);

void initJshort(Thread* t, object o);

void initJchar(Thread* t, object o);

void initJint(Thread* t, object o);

void initJlong(Thread* t, object o);

void initJfloat(Thread* t, object o);

void initJdouble(Thread* t, object o);

void initJvoid(Thread* t, object o);

object makeJobject(Thread* t);

object makeClass(Thread* t, uint16_t flags, uint16_t vmFlags, uint16_t fixedSize, uint8_t arrayElementSize, uint8_t arrayDimensions, uint32_t runtimeDataIndex, object objectMask, object name, object sourceFile, object super, object interfaceTable, object virtualTable, object fieldTable, object methodTable, object addendum, object staticTable, object loader, object source, uintptr_t length);

object makeJclass(Thread* t, object vmClass);

object makeJaccessibleObject(Thread* t);

object makeJfield(Thread* t, object vmField, uint8_t accessible);

object makeJmethod(Thread* t, object vmMethod, uint8_t accessible);

object makeJconstructor(Thread* t, object method);

object makeConstantPool(Thread* t);

object makeSerializable(Thread* t);

object makeCloneable(Thread* t);

object makeSingleton(Thread* t, uintptr_t length);

object makeClassLoader(Thread* t, object parent, object packages, object map);

object makeSystemClassLoader(Thread* t, object parent, object packages, object map, void* finder);

object makeField(Thread* t, uint8_t vmFlags, uint8_t code, uint16_t flags, uint16_t offset, uint32_t nativeID, object name, object spec, object addendum, object class_);

object makeMethod(Thread* t, uint8_t vmFlags, uint8_t returnCode, uint8_t parameterCount, uint8_t parameterFootprint, uint16_t flags, uint16_t offset, uint32_t nativeID, uint32_t runtimeDataIndex, object name, object spec, object addendum, object class_, object code);

object makeAddendum(Thread* t, object pool, object annotationTable, object signature);

object makeClassAddendum(Thread* t, object pool, object annotationTable, object signature, object interfaceTable, object innerClassTable, uint32_t declaredMethodCount, object enclosingClass, object enclosingMethod);

object makeMethodAddendum(Thread* t, object pool, object annotationTable, object signature, object exceptionTable, object annotationDefault, object parameterAnnotationTable);

object makeFieldAddendum(Thread* t, object pool, object annotationTable, object signature);

object makeClassRuntimeData(Thread* t, object arrayClass, object jclass, object pool, object signers);

object makeMethodRuntimeData(Thread* t, object native);

object makePointer(Thread* t, void* value);

object makeNative(Thread* t, void* function, uint8_t fast);

object makeNativeIntercept(Thread* t, void* function, uint8_t fast, object original);

object makeFinder(Thread* t, void* finder, object name, object next);

object makeRegion(Thread* t, void* region, uint32_t position);

object makeExceptionHandlerTable(Thread* t, uintptr_t length);

object makeLineNumberTable(Thread* t, uintptr_t length);

object makeCode(Thread* t, object pool, object exceptionHandlerTable, object lineNumberTable, intptr_t compiled, uint32_t compiledSize, uint16_t maxStack, uint16_t maxLocals, uintptr_t length);

object makeReference(Thread* t, uint8_t kind, object class_, object name, object spec);

object makeInvocation(Thread* t, uint16_t bootstrap, int32_t index, object class_, object pool, object template_, object site);

object makeTriple(Thread* t, object first, object second, object third);

object makeFinalizer(Thread* t, object target, void* finalize, object next, object queueTarget, object queueNext);

object makeHashMap(Thread* t, uint32_t size, object array);

object makeWeakHashMap(Thread* t, uint32_t size, object array);

object makeList(Thread* t, uint32_t size, object front, object rear);

object makeVector(Thread* t, uint32_t size, uintptr_t length);

object makeTraceElement(Thread* t, object method, int32_t ip);

object makeTreeNode(Thread* t, object value, object left, object right);

object makeCallNode(Thread* t, intptr_t address, object target, uintptr_t flags, object next);

object makeWordArray(Thread* t, uintptr_t length);

object makeArray(Thread* t, uintptr_t length);

object makePair(Thread* t, object first, object second);

object makeMonitor(Thread* t, void* owner, void* waitHead, void* waitTail, object acquireHead, object acquireTail, uint32_t depth);

object makeMonitorNode(Thread* t, void* value, object next);

object makeInnerClassReference(Thread* t, object inner, object outer, object name, uint16_t flags);

object makeContinuationContext(Thread* t, object next, object before, object after, object continuation, object method);

object makeContinuation(Thread* t, object next, object context, object method, void* address, uintptr_t returnAddressOffset, uintptr_t framePointerOffset, uintptr_t length);

object makeUnwindResult(Thread* t, object continuation, object result, object exception);

object makeString(Thread* t, object data, uint32_t offset, uint32_t length, uint32_t hashCode);

object makeThread(Thread* t, object parkBlocker, uint64_t peer, uint8_t interrupted, uint8_t unparked, uint8_t daemon, uint8_t state, uint8_t priority, object task, object locals, object sleepLock, object classLoader, object exceptionHandler, object name, object group, object interruptLock);

object makeThreadGroup(Thread* t, object parent, object name, object subgroups);

object makeStackTraceElement(Thread* t, object class_, object method, object file, uint32_t line);

object makeThrowable(Thread* t, object message, object trace, object cause);

object makeException(Thread* t, object message, object trace, object cause);

object makeRuntimeException(Thread* t, object message, object trace, object cause);

object makeNullPointerException(Thread* t, object message, object trace, object cause);

object makeArithmeticException(Thread* t, object message, object trace, object cause);

object makeIllegalStateException(Thread* t, object message, object trace, object cause);

object makeIllegalArgumentException(Thread* t, object message, object trace, object cause);

object makeIllegalMonitorStateException(Thread* t, object message, object trace, object cause);

object makeIndexOutOfBoundsException(Thread* t, object message, object trace, object cause);

object makeArrayIndexOutOfBoundsException(Thread* t, object message, object trace, object cause);

object makeArrayStoreException(Thread* t, object message, object trace, object cause);

object makeNegativeArraySizeException(Thread* t, object message, object trace, object cause);

object makeCloneNotSupportedException(Thread* t, object message, object trace, object cause);

object makeReflectiveOperationException(Thread* t, object message, object trace, object cause);

object makeClassCastException(Thread* t, object message, object trace, object cause);

object makeClassNotFoundException(Thread* t, object message, object trace, object cause, object cause2);

object makeInvocationTargetException(Thread* t, object message, object trace, object cause, object target);

object makeInterruptedException(Thread* t, object message, object trace, object cause);

object makeError(Thread* t, object message, object trace, object cause);

object makeVirtualMachineError(Thread* t, object message, object trace, object cause);

object makeOutOfMemoryError(Thread* t, object message, object trace, object cause);

object makeStackOverflowError(Thread* t, object message, object trace, object cause);

object makeLinkageError(Thread* t, object message, object trace, object cause);

object makeIncompatibleClassChangeError(Thread* t, object message, object trace, object cause);

object makeAbstractMethodError(Thread* t, object message, object trace, object cause);

object makeNoSuchFieldError(Thread* t, object message, object trace, object cause);

object makeNoSuchMethodError(Thread* t, object message, object trace, object cause);

object makeNoClassDefFoundError(Thread* t, object message, object trace, object cause);

object makeUnsatisfiedLinkError(Thread* t, object message, object trace, object cause);

object makeExceptionInInitializerError(Thread* t, object message, object trace, object cause, object exception);

object makeIoException(Thread* t, object message, object trace, object cause);

object makeFileNotFoundException(Thread* t, object message, object trace, object cause);

object makeIncompatibleContinuationException(Thread* t, object message, object trace, object cause);

object makeNumber(Thread* t);

object makeByte(Thread* t, uint8_t value);

object makeBoolean(Thread* t, uint8_t value);

object makeShort(Thread* t, uint16_t value);

object makeChar(Thread* t, uint16_t value);

object makeInt(Thread* t, uint32_t value);

object makeLong(Thread* t, uint64_t value);

object makeFloat(Thread* t, uint32_t value);

object makeDouble(Thread* t, uint64_t value);

object makeReferenceQueue(Thread* t, object front);

object makeJreference(Thread* t, object vmNext, object target, object queue, object jNext);

object makeWeakReference(Thread* t, object vmNext, object target, object queue, object jNext);

object makeSoftReference(Thread* t, object vmNext, object target, object queue, object jNext);

object makePhantomReference(Thread* t, object vmNext, object target, object queue, object jNext);

object makeCleaner(Thread* t, object queueNext);

object makeByteArray(Thread* t, uintptr_t length);

object makeBooleanArray(Thread* t, uintptr_t length);

object makeShortArray(Thread* t, uintptr_t length);

object makeCharArray(Thread* t, uintptr_t length);

object makeIntArray(Thread* t, uintptr_t length);

object makeLongArray(Thread* t, uintptr_t length);

object makeFloatArray(Thread* t, uintptr_t length);

object makeDoubleArray(Thread* t, uintptr_t length);

object makeJbyte(Thread* t);

object makeJboolean(Thread* t);

object makeJshort(Thread* t);

object makeJchar(Thread* t);

object makeJint(Thread* t);

object makeJlong(Thread* t);

object makeJfloat(Thread* t);

object makeJdouble(Thread* t);

object makeJvoid(Thread* t);


