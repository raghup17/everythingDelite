#include "cpphelperFuncs.h"
cppDeliteArraystring * kernel_x110(resourceInfo_t &resourceInfo,string  x109) {
cppDeliteArraystring *x110 = string_split(resourceInfo,x109,string("\\s+"),0);

return x110;
}
