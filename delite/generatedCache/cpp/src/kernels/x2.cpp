#include "cpphelperFuncs.h"
cppFileStream * kernel_x2(resourceInfo_t &resourceInfo,string  x1) {
cppFileStream *x2 = new cppFileStream(1,x1.c_str());

return x2;
}
