#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x463(resourceInfo_t &resourceInfo,int32_t  x461, int32_t  x116, cppDeliteArraydouble  *x462) {
cppDenseMatrixDouble *x463 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x461,x116,x462);

return x463;
}
