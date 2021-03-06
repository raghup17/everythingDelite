#ifndef __DELITE_MEMORY_H__
#define __DELITE_MEMORY_H__

#include <memory>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>
#include <sched.h>
#include "DeliteDatastructures.h"

//#define DHEAP_DEBUG(...) fprintf(stderr, "[DEBUG-DeliteHeap] "); fprintf(stderr, __VA_ARGS__)
#define DHEAP_DEBUG(...)

// Delite Custom Memory APIs
void DeliteHeapInit(int idx, int numThreads, int numLiveThreads, int initializer, size_t heapSize);
void DeliteHeapClear(int idx, int numThreads, int numLiveThreads, int finalizer);
char *DeliteHeapAlloc(size_t sz, int idx);

// globally overloaded new operators
void *operator new(size_t sz, const resourceInfo_t &resourceInfo);
void *operator new[](size_t sz, const resourceInfo_t &resourceInfo);

class DeliteMemory {
public:
  void* operator new(size_t sz) {
    DHEAP_DEBUG("Non-local allocation with size %d\n", sz);
    return malloc(sz);
  }
  
  void* operator new(size_t sz, int heapIdx) {
    DHEAP_DEBUG("Allocation from Idx %d with size %d\n", heapIdx, sz);
    return DeliteHeapAlloc(sz, heapIdx);
  }

  /*
  void operator delete(void*) {
  }
  */
};

#endif
