#ifndef __cppRefcppDenseMatrixDouble__
#define __cppRefcppDenseMatrixDouble__

#include "DeliteNamespaces.h"
class cppRefcppDenseMatrixDouble {
public:
  cppDenseMatrixDouble * data;

  cppRefcppDenseMatrixDouble(cppDenseMatrixDouble * _data) {
    data = _data;
  }

  cppDenseMatrixDouble * get(void) {
    return data;
  }

  void set(cppDenseMatrixDouble * newVal) {
      data = newVal;
  }
};

struct cppRefcppDenseMatrixDoubleD {
  void operator()(cppRefcppDenseMatrixDouble *p) {
    //printf("cppRefcppDenseMatrixDouble: deleting %p\n",p);
  }
};

#endif
