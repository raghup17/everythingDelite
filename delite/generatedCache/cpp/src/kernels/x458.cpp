//activation record for fat loop
#ifndef __activation_x458__
#define __activation_x458__
#include "cpphelperFuncs.h"
class activation_x458 {
public:
activation_x458 *left_act;
cppDeliteArraydouble *x458;
cppDeliteArraydouble *x458_data;
cppDeliteArraydouble *x458_buf;
int32_t x458_size;
int32_t x458_offset;
int32_t x458_conditionals;
void x458_data_set(resourceInfo_t &resourceInfo,cppDeliteArraydouble *xs,int32_t cs) {
x458_data = xs;
x458_conditionals = cs;
if (left_act != NULL)
left_act->x458_data_set(resourceInfo,xs,cs);
}

};
#endif
#include "cpphelperFuncs.h"
#ifndef __kernel_x458__
#define __kernel_x458__
class kernel_x458{
public:
int32_t x3;
cppFileStream *x2;

kernel_x458(int32_t  _x3,cppFileStream * _x2) {
x3 = _x3;
x2 = _x2;
}

int32_t size(resourceInfo_t &resourceInfo) {
return x3;}

int32_t loopStart;
int32_t loopSize;
activation_x458 *alloc(resourceInfo_t &resourceInfo) {
activation_x458 * __act = new activation_x458();;
// __act->x458 stays null for now
return __act;}

activation_x458 *processRange(resourceInfo_t &resourceInfo,activation_x458 *__act,int32_t start,int32_t end) {
x2->openAtNewLine(resourceInfo,start);
bool  isEmpty = x2->end(start) - x2->pos(start) <= 0;
activation_x458 * __act2 = init(resourceInfo,__act,start,isEmpty);
while (x2->pos(start) < x2->end(start)) {
process(resourceInfo,__act2,start);
}
x2->close(resourceInfo,start);
return __act2;}

activation_x458 *init(resourceInfo_t &resourceInfo,activation_x458 *__act,int32_t x4,bool isEmpty) {
activation_x458 * __act2 = new activation_x458();;
int32_t x69 = 0;
cppDeliteArraydouble *x377 = new (resourceInfo.thread_id) cppDeliteArraydouble(x69, resourceInfo.thread_id);
__act2->x458_buf = x377;
if (!isEmpty) {
string x5 = x2->readLine(resourceInfo,x4);
string  x6 = string_trim(x5);
cppDeliteArraystring *x7 = string_split(resourceInfo,x6,string("\\s+"),0);
int32_t x8 = x7->length;
int32_t  x24 = x8;
cppDeliteArraydouble *x423 = new (resourceInfo.thread_id) cppDeliteArraydouble(x24, resourceInfo.thread_id);
cppDeliteArraydouble * x457_data = x423;
int32_t  x457_size = 0;
int32_t  x457_conditionals = 0;
int32_t  x9 = 0;
while (x9 < x8) {  // begin fat loop x457
string x10 = x7->apply(x9);
bool  x11 = x10 == string("Inf");
double  x422;
if (x11) {
double  x413 = std::numeric_limits<double>::max();
x422 = x413;
} else {
bool  x415 = x10 == string("-Inf");
double  x420;
if (x415) {
double  x416 = std::numeric_limits<double>::min();
x420 = x416;
} else {
double  x418 = string_toDouble(x10);
x420 = x418;
}
x422 = x420;
}
double x23 = x422;
cppDeliteArraydouble *x22 = x457_data;
x22->update(x9, x23);
x9 = x9 + 1;
} // end fat loop x457
cppDeliteArraydouble *x22 = x457_data;
cppDeliteArraydouble *x457 = x22;
cppDeliteArraydouble *x64 = x457;
int32_t x65 = x64->length;
int32_t  x63 = 0;
while (x63 < x65) { //flatMap loop
double x66 = x64->apply(x63);
cppDeliteArraydouble *x67 = __act2->x458_buf;
double x68 = x66;
if (true) {
int32_t x383 = __act2->x458_size;
int32_t x384 = x67->length;
bool x385 = x383 >= x384;
if (x385) {
bool x386 = x384 < 16;
int32_t  x389;
if (x386) {
x389 = 16;
} else {
int32_t x387 = x384 * 2;
x389 = x387;
}
cppDeliteArraydouble *x390 = new (resourceInfo.thread_id) cppDeliteArraydouble(x389, resourceInfo.thread_id);
if((x67->data==x390->data) && (0<0))
std::copy_backward(x67->data+0,x67->data+0+x384,x390->data+0+x384);
else 
std::copy(x67->data+0,x67->data+0+x384,x390->data+0);
x390->update(x383, x68);
__act2->x458_buf = x390;
} else {
x67->update(x383, x68);
}
__act2->x458_size = __act2->x458_size + 1;
}
__act2->x458_conditionals = __act2->x458_conditionals + 1;
x63 = x63 + 1;
}
}
return __act2;}

void process(resourceInfo_t &resourceInfo,activation_x458 *__act,int32_t x4) {
string x5 = x2->readLine(resourceInfo,x4);
string  x6 = string_trim(x5);
cppDeliteArraystring *x7 = string_split(resourceInfo,x6,string("\\s+"),0);
int32_t x8 = x7->length;
int32_t  x24 = x8;
cppDeliteArraydouble *x423 = new (resourceInfo.thread_id) cppDeliteArraydouble(x24, resourceInfo.thread_id);
cppDeliteArraydouble * x457_data = x423;
int32_t  x457_size = 0;
int32_t  x457_conditionals = 0;
int32_t  x9 = 0;
while (x9 < x8) {  // begin fat loop x457
string x10 = x7->apply(x9);
bool  x11 = x10 == string("Inf");
double  x422;
if (x11) {
double  x413 = std::numeric_limits<double>::max();
x422 = x413;
} else {
bool  x415 = x10 == string("-Inf");
double  x420;
if (x415) {
double  x416 = std::numeric_limits<double>::min();
x420 = x416;
} else {
double  x418 = string_toDouble(x10);
x420 = x418;
}
x422 = x420;
}
double x23 = x422;
cppDeliteArraydouble *x22 = x457_data;
x22->update(x9, x23);
x9 = x9 + 1;
} // end fat loop x457
cppDeliteArraydouble *x22 = x457_data;
cppDeliteArraydouble *x457 = x22;
cppDeliteArraydouble *x64 = x457;
int32_t x65 = x64->length;
int32_t  x63 = 0;
while (x63 < x65) { //flatMap loop
double x66 = x64->apply(x63);
cppDeliteArraydouble *x67 = __act->x458_buf;
double x68 = x66;
if (true) {
int32_t x383 = __act->x458_size;
int32_t x384 = x67->length;
bool x385 = x383 >= x384;
if (x385) {
bool x386 = x384 < 16;
int32_t  x389;
if (x386) {
x389 = 16;
} else {
int32_t x387 = x384 * 2;
x389 = x387;
}
cppDeliteArraydouble *x390 = new (resourceInfo.thread_id) cppDeliteArraydouble(x389, resourceInfo.thread_id);
if((x67->data==x390->data) && (0<0))
std::copy_backward(x67->data+0,x67->data+0+x384,x390->data+0+x384);
else 
std::copy(x67->data+0,x67->data+0+x384,x390->data+0);
x390->update(x383, x68);
__act->x458_buf = x390;
} else {
x67->update(x383, x68);
}
__act->x458_size = __act->x458_size + 1;
}
__act->x458_conditionals = __act->x458_conditionals + 1;
x63 = x63 + 1;
}
}

void combine(resourceInfo_t &resourceInfo,activation_x458 *__act,activation_x458 *rhs) {
}

void postCombine(resourceInfo_t &resourceInfo,activation_x458 *__act,activation_x458 *lhs) {
__act->x458_offset = lhs->x458_offset+lhs->x458_size;
__act->x458_conditionals = __act->x458_conditionals+lhs->x458_conditionals;
__act->left_act = lhs;
}

void postProcInit(resourceInfo_t &resourceInfo,activation_x458 *__act) {
if (__act->x458_offset > 0) {
int32_t x69 = __act->x458_offset + __act->x458_size;
cppDeliteArraydouble *x67 = __act->x458_buf;
cppDeliteArraydouble *x407 = new (resourceInfo.thread_id) cppDeliteArraydouble(x69, resourceInfo.thread_id);
__act->x458_data_set(resourceInfo,x407,__act->x458_conditionals);
} else {
__act->x458_data_set(resourceInfo,__act->x458_buf,__act->x458_conditionals);
}
}

void postProcess(resourceInfo_t &resourceInfo,activation_x458 *__act) {
if (__act->x458_data != __act->x458_buf) {
int32_t x69 = __act->x458_size;
cppDeliteArraydouble *x72 = __act->x458_buf;
cppDeliteArraydouble *x67 = __act->x458_data;
int32_t x70 = 0;
int32_t x71 = __act->x458_offset;
if((x72->data==x67->data) && (x70<x71))
std::copy_backward(x72->data+x70,x72->data+x70+x69,x67->data+x71+x69);
else 
std::copy(x72->data+x70,x72->data+x70+x69,x67->data+x71);
}
}

void finalize(resourceInfo_t &resourceInfo,activation_x458 *__act) {
cppDeliteArraydouble * x67 = __act->x458_data;
int32_t x69 = __act->x458_conditionals;
int32_t x399 = x67->length;
bool x400 = x399 > x69;
if (x400) {
cppDeliteArraydouble *x401 = new (resourceInfo.thread_id) cppDeliteArraydouble(x69, resourceInfo.thread_id);
if((x67->data==x401->data) && (0<0))
std::copy_backward(x67->data+0,x67->data+0+x69,x401->data+0+x69);
else 
std::copy(x67->data+0,x67->data+0+x69,x401->data+0);
x67 = x401;
} else {
}
__act->x458 = x67;
}

activation_x458 *initAct(resourceInfo_t &resourceInfo) {
activation_x458 * act = new activation_x458;
return act;}

};
#endif

