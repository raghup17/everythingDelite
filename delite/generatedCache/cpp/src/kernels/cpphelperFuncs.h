#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <stdlib.h>
#include <memory>
#include <float.h>
#include <jni.h>
#include <assert.h>
#include <math.h>
#include <iostream>
#include <limits>
#include <algorithm>
#include "cpptypes.h"
#include "cppDeliteStructs.h"
#include "cppDeliteArrays.h"
#include "cppHashMap.h"
#include "cppFileStream.h"
#include "library.h"
#include "cppDeliteVariables.h"

#include "DeliteCpp.h"
string  kernel_x1(resourceInfo_t &resourceInfo,cppDeliteArraystring  *x0);
string  kernel_x108(resourceInfo_t &resourceInfo,string  x1);
string  kernel_x109(resourceInfo_t &resourceInfo,string  x108);
cppDeliteArraystring * kernel_x110(resourceInfo_t &resourceInfo,string  x109);
int32_t  kernel_x111(resourceInfo_t &resourceInfo,cppDeliteArraystring  *x110);
int32_t  kernel_x116(resourceInfo_t &resourceInfo,int32_t  x111);
cppFileStream * kernel_x2(resourceInfo_t &resourceInfo,string  x1);
int32_t  kernel_x3(resourceInfo_t &resourceInfo,cppFileStream  *x2);
int32_t  kernel_x459(resourceInfo_t &resourceInfo,cppDeliteArraydouble  *x458);
int32_t  kernel_x460(resourceInfo_t &resourceInfo,int32_t  x459, int32_t  x111);
int32_t  kernel_x461(resourceInfo_t &resourceInfo,int32_t  x460);
cppDeliteArraydouble * kernel_x462(resourceInfo_t &resourceInfo,cppDeliteArraydouble  *x458);
cppDenseMatrixDouble * kernel_x463(resourceInfo_t &resourceInfo,int32_t  x461, int32_t  x116, cppDeliteArraydouble  *x462);
cppDenseMatrixDouble * kernel_x464(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x463);
string  kernel_x120(resourceInfo_t &resourceInfo,cppDeliteArraystring  *x0);
string  kernel_x227(resourceInfo_t &resourceInfo,string  x120);
string  kernel_x228(resourceInfo_t &resourceInfo,string  x227);
cppDeliteArraystring * kernel_x229(resourceInfo_t &resourceInfo,string  x228);
int32_t  kernel_x230(resourceInfo_t &resourceInfo,cppDeliteArraystring  *x229);
int32_t  kernel_x235(resourceInfo_t &resourceInfo,int32_t  x230);
cppFileStream * kernel_x121(resourceInfo_t &resourceInfo,string  x120);
int32_t  kernel_x122(resourceInfo_t &resourceInfo,cppFileStream  *x121);
int32_t  kernel_x549(resourceInfo_t &resourceInfo,cppDeliteArraydouble  *x548);
int32_t  kernel_x550(resourceInfo_t &resourceInfo,int32_t  x549, int32_t  x230);
int32_t  kernel_x551(resourceInfo_t &resourceInfo,int32_t  x550);
cppDeliteArraydouble * kernel_x552(resourceInfo_t &resourceInfo,cppDeliteArraydouble  *x548);
cppDenseMatrixDouble * kernel_x553(resourceInfo_t &resourceInfo,int32_t  x551, int32_t  x235, cppDeliteArraydouble  *x552);
cppDenseMatrixDouble * kernel_x554(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x553);
int32_t  kernel_x555(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x464);
int32_t  kernel_x556(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x464);
int32_t  kernel_x557(resourceInfo_t &resourceInfo,int32_t  x555, int32_t  x556);
cppDeliteArraydouble * kernel_x558(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x464);
cppDeliteArraydouble * kernel_x559(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x554);
cppDenseMatrixDouble * kernel_x610(resourceInfo_t &resourceInfo,int32_t  x555, int32_t  x556, cppDeliteArraydouble  *x609);
bool  kernel_x611(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x610);
string  kernel_x697(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x610, int32_t  x556, cppDeliteArraydouble  *x609, bool  x611);
string  kernel_x698(resourceInfo_t &resourceInfo,string  x697);
void  kernel_x699(resourceInfo_t &resourceInfo,string  x698);
cppDeliteArraydouble  *recvCPPfromJVM_cppDeliteArraydouble(JNIEnv *env, jobject obj);

cppDeliteArraydouble  *recvViewCPPfromJVM_cppDeliteArraydouble(JNIEnv *env, jobject obj);

void sendUpdateCPPtoJVM_cppDeliteArraydouble(JNIEnv *env, jobject &obj, cppDeliteArraydouble  *sym);

void recvUpdateCPPfromJVM_cppDeliteArraydouble(JNIEnv *env, jobject obj, cppDeliteArraydouble  *sym);

jobject sendCPPtoJVM_cppDeliteArraydouble(JNIEnv *env, cppDeliteArraydouble  *sym);

jobject sendViewCPPtoJVM_cppDeliteArraydouble(JNIEnv *env, cppDeliteArraydouble  *sym);

int32_t recvCPPfromJVM_int32_t(JNIEnv *env, jint sym);

int32_t recvViewCPPfromJVM_int32_t(JNIEnv *env, jint sym);

void sendUpdateCPPtoJVM_int32_t(JNIEnv *env, int32_t sym);

void recvUpdateCPPfromJVM_int32_t(JNIEnv *env, int32_t sym);

jint sendCPPtoJVM_int32_t(JNIEnv *env, int32_t sym);

jint sendViewCPPtoJVM_int32_t(JNIEnv *env, int32_t sym);

jobject makeManifest_int32_t(JNIEnv *env);

cppDeliteArraystring  *recvCPPfromJVM_cppDeliteArraystring(JNIEnv *env, jobject obj);

cppDeliteArraystring  *recvViewCPPfromJVM_cppDeliteArraystring(JNIEnv *env, jobject obj);

void sendUpdateCPPtoJVM_cppDeliteArraystring(JNIEnv *env, jobject &obj, cppDeliteArraystring  *sym);

void recvUpdateCPPfromJVM_cppDeliteArraystring(JNIEnv *env, jobject obj, cppDeliteArraystring  *sym);

jobject sendCPPtoJVM_cppDeliteArraystring(JNIEnv *env, cppDeliteArraystring  *sym);

jobject sendViewCPPtoJVM_cppDeliteArraystring(JNIEnv *env, cppDeliteArraystring  *sym);

double recvCPPfromJVM_double(JNIEnv *env, jdouble sym);

double recvViewCPPfromJVM_double(JNIEnv *env, jdouble sym);

void sendUpdateCPPtoJVM_double(JNIEnv *env, double sym);

void recvUpdateCPPfromJVM_double(JNIEnv *env, double sym);

jdouble sendCPPtoJVM_double(JNIEnv *env, double sym);

jdouble sendViewCPPtoJVM_double(JNIEnv *env, double sym);

jobject makeManifest_double(JNIEnv *env);

bool recvCPPfromJVM_bool(JNIEnv *env, jboolean sym);

bool recvViewCPPfromJVM_bool(JNIEnv *env, jboolean sym);

void sendUpdateCPPtoJVM_bool(JNIEnv *env, bool sym);

void recvUpdateCPPfromJVM_bool(JNIEnv *env, bool sym);

jboolean sendCPPtoJVM_bool(JNIEnv *env, bool sym);

jboolean sendViewCPPtoJVM_bool(JNIEnv *env, bool sym);

jobject makeManifest_bool(JNIEnv *env);

string recvCPPfromJVM_string(JNIEnv *env, jobject obj);

string recvViewCPPfromJVM_string(JNIEnv *env, jobject *obj);

void sendUpdateCPPtoJVM_string(JNIEnv *env, string sym);

void recvUpdateCPPfromJVM_string(JNIEnv *env, string sym);

jobject sendCPPtoJVM_string(JNIEnv *env, string sym);

jobject sendViewCPPtoJVM_string(JNIEnv *env, string sym);

jobject makeManifest_string(JNIEnv *env);

cppDenseMatrixDouble  *recvCPPfromJVM_cppDenseMatrixDouble(JNIEnv *env, jobject obj);

cppDenseMatrixDouble  *recvViewCPPfromJVM_cppDenseMatrixDouble(JNIEnv *env, jobject obj);

void sendUpdateCPPtoJVM_cppDenseMatrixDouble(JNIEnv *env, jobject &obj, cppDenseMatrixDouble  *sym);

void recvUpdateCPPfromJVM_cppDenseMatrixDouble(JNIEnv *env, jobject obj, cppDenseMatrixDouble  *sym);

jobject sendCPPtoJVM_cppDenseMatrixDouble(JNIEnv *env, cppDenseMatrixDouble  *sym);

jobject sendViewCPPtoJVM_cppDenseMatrixDouble(JNIEnv *env, cppDenseMatrixDouble  *sym);

