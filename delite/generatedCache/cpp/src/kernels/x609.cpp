//activation record for fat loop
#ifndef __activation_x609__
#define __activation_x609__
#include "cpphelperFuncs.h"
class activation_x609 {
public:
activation_x609 *left_act;
cppDeliteArraydouble *x609;
cppDeliteArraydouble *x609_data;
};
#endif
#include "cpphelperFuncs.h"
#ifndef __kernel_x609__
#define __kernel_x609__
class kernel_x609{
public:
int32_t x557;
cppDeliteArraydouble *x558;
cppDeliteArraydouble *x559;

kernel_x609(int32_t  _x557,cppDeliteArraydouble * _x558,cppDeliteArraydouble * _x559) {
x557 = _x557;
x558 = _x558;
x559 = _x559;
}

int32_t size(resourceInfo_t &resourceInfo) {
return x557;}

int32_t loopStart;
int32_t loopSize;
activation_x609 *alloc(resourceInfo_t &resourceInfo) {
activation_x609 * __act = new activation_x609();;
int32_t x571 = loopSize;
cppDeliteArraydouble *x575 = new (resourceInfo.thread_id) cppDeliteArraydouble(x571, resourceInfo.thread_id);
__act->x609_data = x575;
return __act;}

activation_x609 *processRange(resourceInfo_t &resourceInfo,activation_x609 *__act,int32_t start,int32_t end) {
bool  isEmpty = end-start <= 0;
activation_x609 * __act2 = init(resourceInfo,__act,start,isEmpty);
int32_t  idx = start + 1;
while (idx < end) {
process(resourceInfo,__act2,idx);
idx = idx + 1;
}
return __act2;}

activation_x609 *init(resourceInfo_t &resourceInfo,activation_x609 *__act,int32_t x242,bool isEmpty) {
if (!isEmpty) {
process(resourceInfo,__act,x242);
}
return __act;}

void process(resourceInfo_t &resourceInfo,activation_x609 *__act,int32_t x242) {
double x566 = x558->apply(x242);
double x567 = x559->apply(x242);
double  x568 = x566 * x567;
double x570 = x568;
cppDeliteArraydouble *x569 = __act->x609_data;
x569->update(x242, x570);
}

void combine(resourceInfo_t &resourceInfo,activation_x609 *__act,activation_x609 *rhs) {
}

void postCombine(resourceInfo_t &resourceInfo,activation_x609 *__act,activation_x609 *lhs) {
__act->left_act = lhs;
}

void postProcInit(resourceInfo_t &resourceInfo,activation_x609 *__act) {
}

void postProcess(resourceInfo_t &resourceInfo,activation_x609 *__act) {
}

void finalize(resourceInfo_t &resourceInfo,activation_x609 *__act) {
cppDeliteArraydouble * x569 = __act->x609_data;
__act->x609 = x569;
}

activation_x609 *initAct(resourceInfo_t &resourceInfo) {
activation_x609 * act = new activation_x609;
return act;}

};
#endif

