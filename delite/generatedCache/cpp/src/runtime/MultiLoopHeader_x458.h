#include <pthread.h>
#include "x458.cpp"
#ifndef HEADER_x458
#define HEADER_x458
class MultiLoopHeader_x458 {
public: 
kernel_x458* closure;
activation_x458* out;
MultiLoopHeader_x458(resourceInfo_t &resourceInfo, int32_t  in0, cppFileStream *  in1) {
closure = new kernel_x458(in0, in1);
closure->loopStart = 0;
closure->loopSize = closure->size(resourceInfo);
out = closure->alloc(resourceInfo);
initSync();
}
void initSync() {
}
~MultiLoopHeader_x458() {
delete closure;
//out will be released by the caller
}
};
#endif
