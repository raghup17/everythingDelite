#ifndef __cppRefdouble__
#define __cppRefdouble__

#include "DeliteNamespaces.h"
class cppRefdouble {
public:
  double  data;

  cppRefdouble(double  _data) {
    data = _data;
  }

  double  get(void) {
    return data;
  }

  void set(double  newVal) {
      data = newVal;
  }
};

struct cppRefdoubleD {
  void operator()(cppRefdouble *p) {
    //printf("cppRefdouble: deleting %p\n",p);
  }
};

#endif
