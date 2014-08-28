#include "MultiLoopHeader_x609.h"
#include "DeliteCppProfiler.h"
activation_x609* MultiLoop_x609_Chunk_0(resourceInfo_t &resourceInfo,MultiLoopHeader_x609* head) {
activation_x609* out = head->out;
int64_t startOffset = head->closure->loopStart;
int64_t size = head->closure->loopSize;
int64_t start = startOffset + size*0/1;
int64_t end = startOffset + size*1/1;
activation_x609* acc = head->closure->processRange(resourceInfo,out,start,end);
head->closure->finalize(resourceInfo,acc);
return acc;
}
