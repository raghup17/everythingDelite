#include "MultiLoopHeader_x458.h"
#include "MultiLoopHeader_x548.h"
#include "MultiLoopHeader_x609.h"
#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include "DeliteCppProfiler.h"
#include "cppSyncObjects.h"
#include "cpphelperFuncs.h"
#include "multiLoopHeaders.h"
extern JNIEnv* env1;
JNIEnv* env1;
extern "C" JNIEXPORT void JNICALL Java_Executable1_00024_hostExecutable1(JNIEnv* jnienv, jobject object);
JNIEXPORT void JNICALL Java_Executable1_00024_hostExecutable1(JNIEnv* jnienv, jobject object) {
env1 = jnienv;
resourceInfo_t resourceInfo;
resourceInfo.thread_id = 0;
DeliteHeapInit(0,1,1,0,0ULL);
jclass cls0 = env1->FindClass("Sync_Executable0");
jclass cls1 = env1->FindClass("Sync_Executable1");
jclass clsBU = env1->FindClass("scala/runtime/BoxedUnit");
jobject boxedUnit = env1->GetStaticObjectField(clsBU, env1->GetStaticFieldID(clsBU, "UNIT", "Lscala/runtime/BoxedUnit;"));
cppDeliteArraystring *xHx0 = cppArgsGet(0);
string xHx1 = kernel_x1(resourceInfo,xHx0);
cppFileStream *xHx2 = kernel_x2(resourceInfo,xHx1);
string xHx108 = kernel_x108(resourceInfo,xHx1);
int32_t xHx3 = kernel_x3(resourceInfo,xHx2);
string xHx109 = kernel_x109(resourceInfo,xHx108);
MultiLoopHeader_x458 *xHx458_h = kernel_MultiLoopHeader_x458(resourceInfo,xHx3,xHx2);
activation_x458 *xop_x458 = MultiLoop_x458_Chunk_0(resourceInfo,xHx458_h);
cppDeliteArraydouble * xHx458 = xop_x458->x458;
cppDeliteArraystring *xHx110 = kernel_x110(resourceInfo,xHx109);
cppDeliteArraydouble *xHx462 = kernel_x462(resourceInfo,xHx458);
int32_t xHx459 = kernel_x459(resourceInfo,xHx458);
int32_t xHx111 = kernel_x111(resourceInfo,xHx110);
int32_t xHx460 = kernel_x460(resourceInfo,xHx459,xHx111);
int32_t xHx116 = kernel_x116(resourceInfo,xHx111);
int32_t xHx461 = kernel_x461(resourceInfo,xHx460);
cppDenseMatrixDouble *xHx463 = kernel_x463(resourceInfo,xHx461,xHx116,xHx462);
cppDenseMatrixDouble *xHx464 = kernel_x464(resourceInfo,xHx463);
string xHx120 = kernel_x120(resourceInfo,xHx0);
string xHx227 = kernel_x227(resourceInfo,xHx120);
cppFileStream *xHx121 = kernel_x121(resourceInfo,xHx120);
string xHx228 = kernel_x228(resourceInfo,xHx227);
int32_t xHx122 = kernel_x122(resourceInfo,xHx121);
cppDeliteArraystring *xHx229 = kernel_x229(resourceInfo,xHx228);
MultiLoopHeader_x548 *xHx548_h = kernel_MultiLoopHeader_x548(resourceInfo,xHx122,xHx121);
activation_x548 *xop_x548 = MultiLoop_x548_Chunk_0(resourceInfo,xHx548_h);
cppDeliteArraydouble * xHx548 = xop_x548->x548;
int32_t xHx230 = kernel_x230(resourceInfo,xHx229);
cppDeliteArraydouble *xHx552 = kernel_x552(resourceInfo,xHx548);
int32_t xHx549 = kernel_x549(resourceInfo,xHx548);
int32_t xHx235 = kernel_x235(resourceInfo,xHx230);
int32_t xHx550 = kernel_x550(resourceInfo,xHx549,xHx230);
int32_t xHx551 = kernel_x551(resourceInfo,xHx550);
cppDenseMatrixDouble *xHx553 = kernel_x553(resourceInfo,xHx551,xHx235,xHx552);
cppDenseMatrixDouble *xHx554 = kernel_x554(resourceInfo,xHx553);
int32_t xHx555 = kernel_x555(resourceInfo,xHx464);
int32_t xHx556 = kernel_x556(resourceInfo,xHx464);
cppDeliteArraydouble *xHx559 = kernel_x559(resourceInfo,xHx554);
cppDeliteArraydouble *xHx558 = kernel_x558(resourceInfo,xHx464);
int32_t xHx557 = kernel_x557(resourceInfo,xHx555,xHx556);
MultiLoopHeader_x609 *xHx609_h = kernel_MultiLoopHeader_x609(resourceInfo,xHx557,xHx558,xHx559);
activation_x609 *xop_x609 = MultiLoop_x609_Chunk_0(resourceInfo,xHx609_h);
cppDeliteArraydouble * xHx609 = xop_x609->x609;
cppDenseMatrixDouble *xHx610 = kernel_x610(resourceInfo,xHx555,xHx556,xHx609);
bool xHx611 = kernel_x611(resourceInfo,xHx610);
string xHx697 = kernel_x697(resourceInfo,xHx610,xHx556,xHx609,xHx611);
string xHx698 = kernel_x698(resourceInfo,xHx697);
kernel_x699(resourceInfo,xHx698);
env1->CallStaticVoidMethod(cls1,env1->GetStaticMethodID(cls1,"set_xop_x699","(Lscala/runtime/BoxedUnit;)V"),boxedUnit);
DeliteHeapClear(0,1,1,0);
}
