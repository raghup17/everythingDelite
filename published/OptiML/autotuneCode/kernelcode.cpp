#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x349(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x239, cppDenseMatrixDouble  *x240, cppDenseMatrixDouble  *x241) {
int32_t x242 = x239->_numRows;
int32_t  x245 = x242 % 1;
int32_t x246 = x242 - x245;
int32_t x244 = x240->_numCols;
int32_t  x250 = x244 % 1;
int32_t x251 = x244 - x250;
int32_t x243 = x239->_numCols;
int32_t  x255 = x243 % 1;
int32_t x256 = x243 - x255;
cppDeliteArraydouble *x274 = x241->_data;
int32_t x275 = x241->_numCols;
cppDeliteArraydouble *x287 = x239->_data;
cppDeliteArraydouble *x291 = x240->_data;
for(int i=0 ; i<x246 ; i+=4) {
  { int32_t x249 = i;
int32_t x260 = x249 + 4;
int32_t x261 = x260 - x249;
int32_t  x262 = x261 % 1;
int32_t x263 = x260 - x262;
for(int i=0 ; i<x251 ; i+=4) {
  { int32_t x254 = i;
int32_t x267 = x254 + 4;
int32_t x268 = x267 - x254;
int32_t  x269 = x268 % 1;
int32_t x270 = x267 - x269;
for(int i=0 ; i<x256 ; i+=4) {
  { int32_t x259 = i;
int32_t x280 = x259 + 4;
int32_t x281 = x280 - x259;
int32_t  x282 = x281 % 4;
int32_t x283 = x280 - x282;
for(int i=x249 ; i<x263 ; i+=1) {
  { int32_t x266 = i;
int32_t x276 = x266 * x275;
int32_t x288 = x266 * x243;
for(int i=x254 ; i<x270 ; i+=1) {
  { int32_t x273 = i;
int32_t x277 = x276 + x273;
double x278 = x274->apply(x277);
double x279 = x278;
for(int i=x259 ; i<x283 ; i+=4) {
  { int32_t x286 = i;
int32_t x289 = x288 + x286;
double x290 = x287->apply(x289);
int32_t x292 = x286 * x244;
int32_t x293 = x292 + x273;
double x294 = x291->apply(x293);
double  x295 = x290 * x294;
x279 += x295;
int32_t x297 = x286 + 1;
int32_t x298 = x288 + x297;
double x299 = x287->apply(x298);
int32_t x300 = x297 * x244;
int32_t x301 = x300 + x273;
double x302 = x291->apply(x301);
double  x303 = x299 * x302;
x279 += x303;
int32_t x305 = x286 + 2;
int32_t x306 = x288 + x305;
double x307 = x287->apply(x306);
int32_t x308 = x305 * x244;
int32_t x309 = x308 + x273;
double x310 = x291->apply(x309);
double  x311 = x307 * x310;
x279 += x311;
int32_t x313 = x286 + 3;
int32_t x314 = x288 + x313;
double x315 = x287->apply(x314);
int32_t x316 = x313 * x244;
int32_t x317 = x316 + x273;
double x318 = x291->apply(x317);
double  x319 = x315 * x318;
x279 += x319;
 }
 
};
for(int i=x283 ; i<x280 ; i+=1) {
  { int32_t x325 = i;
std::cout << "Residue" << std::endl;
int32_t x326 = x288 + x325;
double x327 = x287->apply(x326);
int32_t x328 = x325 * x244;
int32_t x329 = x328 + x273;
double x330 = x291->apply(x329);
double  x331 = x327 * x330;
x279 += x331;
 }
 
};
double x335 = x279;
x241->_data->update(x277,x335);
 }
 
};
 }
 
};
 }
 
};
 }
 
};
 }
 
};
cppDenseMatrixDouble *x347 = x241; // unsafe immutable;
cppDenseMatrixDouble *x349 = x347;
return x349;
}
