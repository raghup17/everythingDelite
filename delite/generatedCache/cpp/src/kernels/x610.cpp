#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x610(resourceInfo_t &resourceInfo,int32_t  x555, int32_t  x556, cppDeliteArraydouble  *x609) {
cppDenseMatrixDouble *x610 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x555,x556,x609);

return x610;
}
