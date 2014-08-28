#ifndef __cppDeliteArraystring__
#define __cppDeliteArraystring__

#include "DeliteNamespaces.h"
#include "DeliteMemory.h"
class cppDeliteArraystring : public DeliteMemory {
public:
  string  *data;
  int length;

  cppDeliteArraystring(int _length, int heapIdx): data((string  *)(DeliteHeapAlloc(sizeof(string )*_length,heapIdx))), length(_length) { }

  cppDeliteArraystring(int _length): data((string  *)(new string [_length])), length(_length) { }

  cppDeliteArraystring(string  *_data, int _length) {
    data = _data;
    length = _length;
  }

  string  apply(int idx) {
    return data[idx];
  }

  void update(int idx, string  val) {
    data[idx] = val;
  }

  void print(void) {
    printf("length is %d\n", length);
  }

  bool equals(cppDeliteArraystring *to) {
    return this == this;
  }

  uint32_t hashcode(void) {
    return (uintptr_t)this;
  }
};

struct cppDeliteArraystringD {
  void operator()(cppDeliteArraystring *p) {
    //printf("cppDeliteArraystring: deleting %p\n",p);
    delete[] p->data;
  }
};


#endif
