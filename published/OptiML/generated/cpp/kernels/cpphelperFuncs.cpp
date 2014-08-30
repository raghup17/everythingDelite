#include <jni.h>
#include "cpphelperFuncs.h"
bool recvCPPfromJVM_bool(JNIEnv *env, jboolean sym) {
	return (bool)sym;
}

bool recvViewCPPfromJVM_bool(JNIEnv *env, jboolean sym) {
	assert(false);
	return (bool)sym;
}

void sendUpdateCPPtoJVM_bool(JNIEnv *env, bool sym) {
	assert(false);
}

void recvUpdateCPPfromJVM_bool(JNIEnv *env, bool sym) {
	assert(false);
}

jboolean sendCPPtoJVM_bool(JNIEnv *env, bool sym) {
	return (jboolean)sym;
}

jboolean sendViewCPPtoJVM_bool(JNIEnv *env, bool sym) {
	assert(false);
	return (jboolean)sym;
}

jobject makeManifest_bool(JNIEnv *env){
jclass cls = env->FindClass("Lscala/reflect/ManifestFactory;");
jmethodID mid = env->GetStaticMethodID(cls,"Boolean","()Lscala/reflect/AnyValManifest;");
jobject mobj = env->CallStaticObjectMethod(cls,mid);
return mobj;
}

cppDenseMatrixDouble  *recvCPPfromJVM_cppDenseMatrixDouble(JNIEnv *env, jobject obj) {
	cppDenseMatrixDouble  *sym = new cppDenseMatrixDouble();
	jclass cls = env->GetObjectClass(obj);
	jmethodID mid_get__numRows = env->GetMethodID(cls,"_numRows","()I");
	jint j__numRows = env->CallIntMethod(obj,mid_get__numRows);
	int32_t _numRows = recvCPPfromJVM_int32_t(env,j__numRows);
	sym->_numRows = _numRows;
	jmethodID mid_get__numCols = env->GetMethodID(cls,"_numCols","()I");
	jint j__numCols = env->CallIntMethod(obj,mid_get__numCols);
	int32_t _numCols = recvCPPfromJVM_int32_t(env,j__numCols);
	sym->_numCols = _numCols;
	jmethodID mid_get__data = env->GetMethodID(cls,"_data","()[D");
	jobject j__data = env->CallObjectMethod(obj,mid_get__data);
	cppDeliteArraydouble  *_data = recvCPPfromJVM_cppDeliteArraydouble(env,j__data);
	sym->_data = _data;
	env->DeleteLocalRef(j__data);
	return sym;
}

cppDenseMatrixDouble  *recvViewCPPfromJVM_cppDenseMatrixDouble(JNIEnv *env, jobject obj) {
assert(false);
}

void sendUpdateCPPtoJVM_cppDenseMatrixDouble(JNIEnv *env, jobject &obj, cppDenseMatrixDouble  *sym) {
	jclass cls = env->GetObjectClass(obj);
	jmethodID mid__numRows = env->GetMethodID(cls,"_numRows_$eq","(I)V");
	jint obj__numRows = sendCPPtoJVM_int32_t(env,sym->_numRows);
	env->CallVoidMethod(obj,mid__numRows,obj__numRows);
	jmethodID mid__numCols = env->GetMethodID(cls,"_numCols_$eq","(I)V");
	jint obj__numCols = sendCPPtoJVM_int32_t(env,sym->_numCols);
	env->CallVoidMethod(obj,mid__numCols,obj__numCols);
	jmethodID mid__data = env->GetMethodID(cls,"_data","()[D");
	jmethodID mid__data_setter = env->GetMethodID(cls,"_data_$eq","([D)V");
	jobject obj__data = env->CallObjectMethod(obj,mid__data);
	sendUpdateCPPtoJVM_cppDeliteArraydouble(env,obj__data,sym->_data);
	env->CallVoidMethod(obj,mid__data_setter,obj__data);
	env->DeleteLocalRef(obj__data);
}

void recvUpdateCPPfromJVM_cppDenseMatrixDouble(JNIEnv *env, jobject obj, cppDenseMatrixDouble  *sym) {
	jclass cls = env->GetObjectClass(obj);
	jmethodID mid__numRows = env->GetMethodID(cls,"_numRows","()I");
	jint obj__numRows = env->CallIntMethod(obj, mid__numRows);
	sym->_numRows = recvCPPfromJVM_int32_t(env,obj__numRows);
	jmethodID mid__numCols = env->GetMethodID(cls,"_numCols","()I");
	jint obj__numCols = env->CallIntMethod(obj, mid__numCols);
	sym->_numCols = recvCPPfromJVM_int32_t(env,obj__numCols);
	jmethodID mid__data = env->GetMethodID(cls,"_data","()[D");
	jobject obj__data = env->CallObjectMethod(obj,mid__data);
	recvUpdateCPPfromJVM_cppDeliteArraydouble(env,obj__data,sym->_data);
	env->DeleteLocalRef(obj__data);
}

jobject sendCPPtoJVM_cppDenseMatrixDouble(JNIEnv *env, cppDenseMatrixDouble  *sym) {
	if(sym == NULL) return NULL;
	jint _numRows = sendCPPtoJVM_int32_t(env,sym->_numRows);
	jint _numCols = sendCPPtoJVM_int32_t(env,sym->_numCols);
	jobject _data = sendCPPtoJVM_cppDeliteArraydouble(env,sym->_data);
	jclass cls = env->FindClass("generated/scala/DenseMatrixDouble");
	jmethodID mid = env->GetMethodID(cls,"<init>","(II[D)V");
	jobject obj = env->NewObject(cls,mid,_numRows,_numCols,_data);
	env->DeleteLocalRef(_data);
	return obj;
}

jobject sendViewCPPtoJVM_cppDenseMatrixDouble(JNIEnv *env, cppDenseMatrixDouble  *sym) {
assert(false);
}

double recvCPPfromJVM_double(JNIEnv *env, jdouble sym) {
	return (double)sym;
}

double recvViewCPPfromJVM_double(JNIEnv *env, jdouble sym) {
	assert(false);
	return (double)sym;
}

void sendUpdateCPPtoJVM_double(JNIEnv *env, double sym) {
	assert(false);
}

void recvUpdateCPPfromJVM_double(JNIEnv *env, double sym) {
	assert(false);
}

jdouble sendCPPtoJVM_double(JNIEnv *env, double sym) {
	return (jdouble)sym;
}

jdouble sendViewCPPtoJVM_double(JNIEnv *env, double sym) {
	assert(false);
	return (jdouble)sym;
}

jobject makeManifest_double(JNIEnv *env){
jclass cls = env->FindClass("Lscala/reflect/ManifestFactory;");
jmethodID mid = env->GetStaticMethodID(cls,"Double","()Lscala/reflect/AnyValManifest;");
jobject mobj = env->CallStaticObjectMethod(cls,mid);
return mobj;
}

cppDeliteArraystring  *recvCPPfromJVM_cppDeliteArraystring(JNIEnv *env, jobject obj) {
	int length = env->GetArrayLength((jobjectArray)obj);
	cppDeliteArraystring  *sym = new cppDeliteArraystring(length);
	for(int i=0; i<length; i++) {
		jobject o = env->GetObjectArrayElement((jobjectArray)obj,i);
		sym->data[i] = (o == NULL)? string("") : recvCPPfromJVM_string(env, o);
		env->DeleteLocalRef(o);
	}
	return sym;
}

cppDeliteArraystring  *recvViewCPPfromJVM_cppDeliteArraystring(JNIEnv *env, jobject obj) {
	assert(false);
}

void sendUpdateCPPtoJVM_cppDeliteArraystring(JNIEnv *env, jobject &obj, cppDeliteArraystring  *sym) {
	assert(false);
}

void recvUpdateCPPfromJVM_cppDeliteArraystring(JNIEnv *env, jobject obj, cppDeliteArraystring  *sym) {
	assert(false);
}

jobject sendCPPtoJVM_cppDeliteArraystring(JNIEnv *env, cppDeliteArraystring  *sym) {
	jclass cls = env->FindClass("Ljava/lang/String;");
	jobjectArray arr = env->NewObjectArray(sym->length,cls,0);
	for(int i=0; i<sym->length; i++) {
		jobject obj = (sym->data[i]==string("")) ? NULL : sendCPPtoJVM_string(env, sym->data[i]);
		env->SetObjectArrayElement(arr,i,obj);
		env->DeleteLocalRef(obj);
	}
	return arr;
}

jobject sendViewCPPtoJVM_cppDeliteArraystring(JNIEnv *env, cppDeliteArraystring  *sym) {
	assert(false);
}

cppDeliteArraydouble  *recvCPPfromJVM_cppDeliteArraydouble(JNIEnv *env, jobject obj) {
	int length = env->GetArrayLength((jdoubleArray)obj);
	jdouble *dataPtr = (jdouble *)env->GetPrimitiveArrayCritical((jdoubleArray)obj,0);
	cppDeliteArraydouble  *sym = new cppDeliteArraydouble(length);
	memcpy(sym->data, dataPtr, length*sizeof(double));
	env->ReleasePrimitiveArrayCritical((jdoubleArray)obj, dataPtr, 0);
	return sym;
}

cppDeliteArraydouble  *recvViewCPPfromJVM_cppDeliteArraydouble(JNIEnv *env, jobject obj) {
	int length = env->GetArrayLength((jdoubleArray)obj);
	jdouble *dataPtr = (jdouble *)env->GetPrimitiveArrayCritical((jdoubleArray)obj,0);
	cppDeliteArraydouble  *sym = new cppDeliteArraydouble((double *)dataPtr,length);
	return sym;
}

void sendUpdateCPPtoJVM_cppDeliteArraydouble(JNIEnv *env, jobject &obj, cppDeliteArraydouble  *sym) {
	int length = env->GetArrayLength((jdoubleArray)obj);
	if(length != sym->length) {
		jdoubleArray arr = env->NewDoubleArray(sym->length);
		env->DeleteLocalRef(obj);
		obj = (jobject)arr;
	}
		jdouble *dataPtr = (jdouble *)env->GetPrimitiveArrayCritical((jdoubleArray)obj,0);
	memcpy(dataPtr, sym->data, sym->length*sizeof(double));
	env->ReleasePrimitiveArrayCritical((jdoubleArray)obj, dataPtr, 0);
}

void recvUpdateCPPfromJVM_cppDeliteArraydouble(JNIEnv *env, jobject obj, cppDeliteArraydouble  *sym) {
	int length = env->GetArrayLength((jdoubleArray)obj);
	if(length != sym->length)
		sym->data = (double*)realloc((void*)(sym->data),sizeof(double)*length);
	sym->length = length;
	jdouble *dataPtr = (jdouble*)env->GetPrimitiveArrayCritical((jdoubleArray)obj,0);
	memcpy(sym->data, dataPtr, length*sizeof(double));
	env->ReleasePrimitiveArrayCritical((jdoubleArray)obj, dataPtr, 0);
}

jobject sendCPPtoJVM_cppDeliteArraydouble(JNIEnv *env, cppDeliteArraydouble  *sym) {
	jdoubleArray arr = env->NewDoubleArray(sym->length);
	jdouble *dataPtr = (jdouble *)env->GetPrimitiveArrayCritical((jdoubleArray)arr,0);
	memcpy(dataPtr, sym->data, sym->length*sizeof(double));
	env->ReleasePrimitiveArrayCritical((jdoubleArray)arr, dataPtr, 0);
	return arr;
}

jobject sendViewCPPtoJVM_cppDeliteArraydouble(JNIEnv *env, cppDeliteArraydouble  *sym) {
	assert(false);
}

int32_t recvCPPfromJVM_int32_t(JNIEnv *env, jint sym) {
	return (int32_t)sym;
}

int32_t recvViewCPPfromJVM_int32_t(JNIEnv *env, jint sym) {
	assert(false);
	return (int32_t)sym;
}

void sendUpdateCPPtoJVM_int32_t(JNIEnv *env, int32_t sym) {
	assert(false);
}

void recvUpdateCPPfromJVM_int32_t(JNIEnv *env, int32_t sym) {
	assert(false);
}

jint sendCPPtoJVM_int32_t(JNIEnv *env, int32_t sym) {
	return (jint)sym;
}

jint sendViewCPPtoJVM_int32_t(JNIEnv *env, int32_t sym) {
	assert(false);
	return (jint)sym;
}

jobject makeManifest_int32_t(JNIEnv *env){
jclass cls = env->FindClass("Lscala/reflect/ManifestFactory;");
jmethodID mid = env->GetStaticMethodID(cls,"Int","()Lscala/reflect/AnyValManifest;");
jobject mobj = env->CallStaticObjectMethod(cls,mid);
return mobj;
}

string recvCPPfromJVM_string(JNIEnv *env, jobject obj) {
	const char *str = env->GetStringUTFChars((jstring)obj,NULL);
	char *str2 = (char *)malloc((1+strlen(str))*sizeof(char));
	strcpy(str2,str);
	string sym(str2);
	env->ReleaseStringUTFChars((jstring)obj,str);	return sym;
}

string recvViewCPPfromJVM_string(JNIEnv *env, jobject *obj) {
	assert(false);
	return NULL;
}

void sendUpdateCPPtoJVM_string(JNIEnv *env, string sym) {
	assert(false);
}

void recvUpdateCPPfromJVM_string(JNIEnv *env, string sym) {
	assert(false);
}

jobject sendCPPtoJVM_string(JNIEnv *env, string sym) {
return env->NewStringUTF(sym.c_str());
}

jobject sendViewCPPtoJVM_string(JNIEnv *env, string sym) {
	assert(false);
	return NULL;
}

jobject makeManifest_string(JNIEnv *env){
jclass cls = env->FindClass("Lscala/reflect/ManifestFactory;");
jmethodID mid = env->GetStaticMethodID(cls,"classType","(Ljava/lang/Class;)Lscala/reflect/Manifest;");
jclass clsString = env->FindClass("Ljava/lang/string;");
jobject mobj = env->CallStaticObjectMethod(cls,mid,clsString);
return mobj;
}

