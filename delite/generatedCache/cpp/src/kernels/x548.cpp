//activation record for fat loop
#ifndef __activation_x548__
#define __activation_x548__
#include "cpphelperFuncs.h"
class activation_x548 {
public:
activation_x548 *left_act;
cppDeliteArraydouble *x548;
cppDeliteArraydouble *x548_data;
cppDeliteArraydouble *x548_buf;
int32_t x548_size;
int32_t x548_offset;
int32_t x548_conditionals;
void x548_data_set(resourceInfo_t &resourceInfo,cppDeliteArraydouble *xs,int32_t cs) {
x548_data = xs;
x548_conditionals = cs;
if (left_act != NULL)
left_act->x548_data_set(resourceInfo,xs,cs);
}

};
#endif
#include "cpphelperFuncs.h"
#ifndef __kernel_x548__
#define __kernel_x548__
class kernel_x548{
public:
int32_t x122;
cppFileStream *x121;

kernel_x548(int32_t  _x122,cppFileStream * _x121) {
x122 = _x122;
x121 = _x121;
}

int32_t size(resourceInfo_t &resourceInfo) {
return x122;}

int32_t loopStart;
int32_t loopSize;
activation_x548 *alloc(resourceInfo_t &resourceInfo) {
activation_x548 * __act = new activation_x548();;
// __act->x548 stays null for now
return __act;}

activation_x548 *processRange(resourceInfo_t &resourceInfo,activation_x548 *__act,int32_t start,int32_t end) {
x121->openAtNewLine(resourceInfo,start);
bool  isEmpty = x121->end(start) - x121->pos(start) <= 0;
activation_x548 * __act2 = init(resourceInfo,__act,start,isEmpty);
while (x121->pos(start) < x121->end(start)) {
process(resourceInfo,__act2,start);
}
x121->close(resourceInfo,start);
return __act2;}

activation_x548 *init(resourceInfo_t &resourceInfo,activation_x548 *__act,int32_t x123,bool isEmpty) {
activation_x548 * __act2 = new activation_x548();;
int32_t x188 = 0;
cppDeliteArraydouble *x467 = new (resourceInfo.thread_id) cppDeliteArraydouble(x188, resourceInfo.thread_id);
__act2->x548_buf = x467;
if (!isEmpty) {
string x124 = x121->readLine(resourceInfo,x123);
string  x125 = string_trim(x124);
cppDeliteArraystring *x126 = string_split(resourceInfo,x125,string("\\s+"),0);
int32_t x127 = x126->length;
int32_t  x143 = x127;
cppDeliteArraydouble *x513 = new (resourceInfo.thread_id) cppDeliteArraydouble(x143, resourceInfo.thread_id);
cppDeliteArraydouble * x547_data = x513;
int32_t  x547_size = 0;
int32_t  x547_conditionals = 0;
int32_t  x128 = 0;
while (x128 < x127) {  // begin fat loop x547
string x129 = x126->apply(x128);
bool  x130 = x129 == string("Inf");
double  x512;
if (x130) {
double  x503 = std::numeric_limits<double>::max();
x512 = x503;
} else {
bool  x505 = x129 == string("-Inf");
double  x510;
if (x505) {
double  x506 = std::numeric_limits<double>::min();
x510 = x506;
} else {
double  x508 = string_toDouble(x129);
x510 = x508;
}
x512 = x510;
}
double x142 = x512;
cppDeliteArraydouble *x141 = x547_data;
x141->update(x128, x142);
x128 = x128 + 1;
} // end fat loop x547
cppDeliteArraydouble *x141 = x547_data;
cppDeliteArraydouble *x547 = x141;
cppDeliteArraydouble *x183 = x547;
int32_t x184 = x183->length;
int32_t  x182 = 0;
while (x182 < x184) { //flatMap loop
double x185 = x183->apply(x182);
cppDeliteArraydouble *x186 = __act2->x548_buf;
double x187 = x185;
if (true) {
int32_t x473 = __act2->x548_size;
int32_t x474 = x186->length;
bool x475 = x473 >= x474;
if (x475) {
bool x476 = x474 < 16;
int32_t  x479;
if (x476) {
x479 = 16;
} else {
int32_t x477 = x474 * 2;
x479 = x477;
}
cppDeliteArraydouble *x480 = new (resourceInfo.thread_id) cppDeliteArraydouble(x479, resourceInfo.thread_id);
if((x186->data==x480->data) && (0<0))
std::copy_backward(x186->data+0,x186->data+0+x474,x480->data+0+x474);
else 
std::copy(x186->data+0,x186->data+0+x474,x480->data+0);
x480->update(x473, x187);
__act2->x548_buf = x480;
} else {
x186->update(x473, x187);
}
__act2->x548_size = __act2->x548_size + 1;
}
__act2->x548_conditionals = __act2->x548_conditionals + 1;
x182 = x182 + 1;
}
}
return __act2;}

void process(resourceInfo_t &resourceInfo,activation_x548 *__act,int32_t x123) {
string x124 = x121->readLine(resourceInfo,x123);
string  x125 = string_trim(x124);
cppDeliteArraystring *x126 = string_split(resourceInfo,x125,string("\\s+"),0);
int32_t x127 = x126->length;
int32_t  x143 = x127;
cppDeliteArraydouble *x513 = new (resourceInfo.thread_id) cppDeliteArraydouble(x143, resourceInfo.thread_id);
cppDeliteArraydouble * x547_data = x513;
int32_t  x547_size = 0;
int32_t  x547_conditionals = 0;
int32_t  x128 = 0;
while (x128 < x127) {  // begin fat loop x547
string x129 = x126->apply(x128);
bool  x130 = x129 == string("Inf");
double  x512;
if (x130) {
double  x503 = std::numeric_limits<double>::max();
x512 = x503;
} else {
bool  x505 = x129 == string("-Inf");
double  x510;
if (x505) {
double  x506 = std::numeric_limits<double>::min();
x510 = x506;
} else {
double  x508 = string_toDouble(x129);
x510 = x508;
}
x512 = x510;
}
double x142 = x512;
cppDeliteArraydouble *x141 = x547_data;
x141->update(x128, x142);
x128 = x128 + 1;
} // end fat loop x547
cppDeliteArraydouble *x141 = x547_data;
cppDeliteArraydouble *x547 = x141;
cppDeliteArraydouble *x183 = x547;
int32_t x184 = x183->length;
int32_t  x182 = 0;
while (x182 < x184) { //flatMap loop
double x185 = x183->apply(x182);
cppDeliteArraydouble *x186 = __act->x548_buf;
double x187 = x185;
if (true) {
int32_t x473 = __act->x548_size;
int32_t x474 = x186->length;
bool x475 = x473 >= x474;
if (x475) {
bool x476 = x474 < 16;
int32_t  x479;
if (x476) {
x479 = 16;
} else {
int32_t x477 = x474 * 2;
x479 = x477;
}
cppDeliteArraydouble *x480 = new (resourceInfo.thread_id) cppDeliteArraydouble(x479, resourceInfo.thread_id);
if((x186->data==x480->data) && (0<0))
std::copy_backward(x186->data+0,x186->data+0+x474,x480->data+0+x474);
else 
std::copy(x186->data+0,x186->data+0+x474,x480->data+0);
x480->update(x473, x187);
__act->x548_buf = x480;
} else {
x186->update(x473, x187);
}
__act->x548_size = __act->x548_size + 1;
}
__act->x548_conditionals = __act->x548_conditionals + 1;
x182 = x182 + 1;
}
}

void combine(resourceInfo_t &resourceInfo,activation_x548 *__act,activation_x548 *rhs) {
}

void postCombine(resourceInfo_t &resourceInfo,activation_x548 *__act,activation_x548 *lhs) {
__act->x548_offset = lhs->x548_offset+lhs->x548_size;
__act->x548_conditionals = __act->x548_conditionals+lhs->x548_conditionals;
__act->left_act = lhs;
}

void postProcInit(resourceInfo_t &resourceInfo,activation_x548 *__act) {
if (__act->x548_offset > 0) {
int32_t x188 = __act->x548_offset + __act->x548_size;
cppDeliteArraydouble *x186 = __act->x548_buf;
cppDeliteArraydouble *x497 = new (resourceInfo.thread_id) cppDeliteArraydouble(x188, resourceInfo.thread_id);
__act->x548_data_set(resourceInfo,x497,__act->x548_conditionals);
} else {
__act->x548_data_set(resourceInfo,__act->x548_buf,__act->x548_conditionals);
}
}

void postProcess(resourceInfo_t &resourceInfo,activation_x548 *__act) {
if (__act->x548_data != __act->x548_buf) {
int32_t x188 = __act->x548_size;
cppDeliteArraydouble *x191 = __act->x548_buf;
cppDeliteArraydouble *x186 = __act->x548_data;
int32_t x189 = 0;
int32_t x190 = __act->x548_offset;
if((x191->data==x186->data) && (x189<x190))
std::copy_backward(x191->data+x189,x191->data+x189+x188,x186->data+x190+x188);
else 
std::copy(x191->data+x189,x191->data+x189+x188,x186->data+x190);
}
}

void finalize(resourceInfo_t &resourceInfo,activation_x548 *__act) {
cppDeliteArraydouble * x186 = __act->x548_data;
int32_t x188 = __act->x548_conditionals;
int32_t x489 = x186->length;
bool x490 = x489 > x188;
if (x490) {
cppDeliteArraydouble *x491 = new (resourceInfo.thread_id) cppDeliteArraydouble(x188, resourceInfo.thread_id);
if((x186->data==x491->data) && (0<0))
std::copy_backward(x186->data+0,x186->data+0+x188,x491->data+0+x188);
else 
std::copy(x186->data+0,x186->data+0+x188,x491->data+0);
x186 = x491;
} else {
}
__act->x548 = x186;
}

activation_x548 *initAct(resourceInfo_t &resourceInfo) {
activation_x548 * act = new activation_x548;
return act;}

};
#endif

