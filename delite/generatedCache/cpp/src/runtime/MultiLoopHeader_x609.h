#include <pthread.h>
#include "x609.cpp"
#ifndef HEADER_x609
#define HEADER_x609
class MultiLoopHeader_x609 {
public: 
kernel_x609* closure;
activation_x609* out;
MultiLoopHeader_x609(resourceInfo_t &resourceInfo, int32_t  in0, cppDeliteArraydouble *  in1, cppDeliteArraydouble *  in2) {
closure = new kernel_x609(in0, in1, in2);
closure->loopStart = 0;
closure->loopSize = closure->size(resourceInfo);
out = closure->alloc(resourceInfo);
initSync();
}
void initSync() {
}
~MultiLoopHeader_x609() {
delete closure;
//out will be released by the caller
}
};
#endif
