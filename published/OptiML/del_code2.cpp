#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x278(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x239, cppDenseMatrixDouble  *x240) {
int32_t x244 = x239->_numRows;
int32_t x247 = x240->_numCols;
cppDeliteArraydouble *x250 = x239->_data;
int32_t x241 = x239->_numCols;
cppDeliteArraydouble *x253 = x240->_data;
for(int i=0 ; i<x244 ; i+=1) {
  { int32_t x246 = i;
int32_t x251 = x246 * x241;
double x252 = x250->apply(x251);
int32_t x269 = x246 * x247;
for(int i=0 ; i<x247 ; i+=1) {
  { int32_t x249 = i;
int32_t x254 = x249 * x247;
double x255 = x253->apply(x254);
double  x256 = x252 * x255;
double x257 = x256;
for(int i=1 ; i<x247 ; i+=1) {
  { int32_t x259 = i;
int32_t x260 = x251 + x259;
double x261 = x250->apply(x260);
int32_t x262 = x254 + x259;
double x263 = x253->apply(x262);
double  x264 = x261 * x263;
x257 += x264;
 }
 
};
double x268 = x257;
int32_t x270 = x269 + x249;
x240->_data->update(x270,x268);
 }
 
};
 }
 
};
cppDenseMatrixDouble *x276 = x240; // unsafe immutable;
cppDenseMatrixDouble *x278 = x276;
return x278;
}
