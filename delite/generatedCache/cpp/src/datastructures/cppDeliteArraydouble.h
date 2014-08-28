#ifndef __cppDeliteArraydouble__
#define __cppDeliteArraydouble__

#include "DeliteNamespaces.h"
#include "DeliteMemory.h"
class cppDeliteArraydouble : public DeliteMemory {
public:
  double  *data;
  int length;

  cppDeliteArraydouble(int _length, int heapIdx): data((double  *)(DeliteHeapAlloc(sizeof(double )*_length,heapIdx))), length(_length) { }

  cppDeliteArraydouble(int _length): data((double  *)(new double [_length])), length(_length) { }

  cppDeliteArraydouble(double  *_data, int _length) {
    data = _data;
    length = _length;
  }

  double  apply(int idx) {
    return data[idx];
  }

  void update(int idx, double  val) {
    data[idx] = val;
  }

  void print(void) {
    printf("length is %d\n", length);
  }

  bool equals(cppDeliteArraydouble *to) {
    return this == this;
  }

  uint32_t hashcode(void) {
    return (uintptr_t)this;
  }
};

struct cppDeliteArraydoubleD {
  void operator()(cppDeliteArraydouble *p) {
    //printf("cppDeliteArraydouble: deleting %p\n",p);
    delete[] p->data;
  }
};


#endif
