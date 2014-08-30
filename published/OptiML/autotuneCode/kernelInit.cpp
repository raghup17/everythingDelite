#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x364(resourceInfo_t &resourceInfo,int32_t  x350, int32_t  x351) {
int32_t x353 = x350;
int32_t x354 = x351;
int32_t x355 = x350 * x351;
cppDeliteArraydouble *x356 = new (resourceInfo.thread_id) cppDeliteArraydouble(x355, resourceInfo.thread_id);
cppDeliteArraydouble *x357 = x356;
cppDenseMatrixDouble *x358 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x353,x354,x357);
int32_t x359 = x358->_numRows;
int32_t x360 = x358->_numCols;
cppDeliteArraydouble *x361 = x358->_data;
cppDenseMatrixDouble *x362 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x359,x360,x361);
cppDenseMatrixDouble *x364 = x362;
return x364;
}
#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x376(resourceInfo_t &resourceInfo,int32_t  x351, int32_t  x352) {
int32_t x365 = x351;
int32_t x366 = x352;
int32_t x367 = x351 * x352;
cppDeliteArraydouble *x368 = new (resourceInfo.thread_id) cppDeliteArraydouble(x367, resourceInfo.thread_id);
cppDeliteArraydouble *x369 = x368;
cppDenseMatrixDouble *x370 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x365,x366,x369);
int32_t x371 = x370->_numRows;
int32_t x372 = x370->_numCols;
cppDeliteArraydouble *x373 = x370->_data;
cppDenseMatrixDouble *x374 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x371,x372,x373);
cppDenseMatrixDouble *x376 = x374;
return x376;
}
#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x388(resourceInfo_t &resourceInfo,int32_t  x350, int32_t  x352) {
int32_t x377 = x350;
int32_t x378 = x352;
int32_t x379 = x350 * x352;
cppDeliteArraydouble *x380 = new (resourceInfo.thread_id) cppDeliteArraydouble(x379, resourceInfo.thread_id);
cppDeliteArraydouble *x381 = x380;
cppDenseMatrixDouble *x382 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x377,x378,x381);
int32_t x383 = x382->_numRows;
int32_t x384 = x382->_numCols;
cppDeliteArraydouble *x385 = x382->_data;
cppDenseMatrixDouble *x386 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x383,x384,x385);
cppDenseMatrixDouble *x388 = x386;
return x388;
}
