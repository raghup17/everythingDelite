#include "cpphelperFuncs.h"
cppDenseMatrixDouble * kernel_x553(resourceInfo_t &resourceInfo,int32_t  x551, int32_t  x235, cppDeliteArraydouble  *x552) {
cppDenseMatrixDouble *x553 = new (resourceInfo.thread_id) cppDenseMatrixDouble(x551,x235,x552);

return x553;
}
