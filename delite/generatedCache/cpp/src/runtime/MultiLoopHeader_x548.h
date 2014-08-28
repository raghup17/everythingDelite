#include <pthread.h>
#include "x548.cpp"
#ifndef HEADER_x548
#define HEADER_x548
class MultiLoopHeader_x548 {
public: 
kernel_x548* closure;
activation_x548* out;
MultiLoopHeader_x548(resourceInfo_t &resourceInfo, int32_t  in0, cppFileStream *  in1) {
closure = new kernel_x548(in0, in1);
closure->loopStart = 0;
closure->loopSize = closure->size(resourceInfo);
out = closure->alloc(resourceInfo);
initSync();
}
void initSync() {
}
~MultiLoopHeader_x548() {
delete closure;
//out will be released by the caller
}
};
#endif
