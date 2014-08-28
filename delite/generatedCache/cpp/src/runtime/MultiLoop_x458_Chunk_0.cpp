#include "MultiLoopHeader_x458.h"
#include "DeliteCppProfiler.h"
activation_x458* MultiLoop_x458_Chunk_0(resourceInfo_t &resourceInfo,MultiLoopHeader_x458* head) {
activation_x458* out = head->out;
int64_t startOffset = head->closure->loopStart;
int64_t size = head->closure->loopSize;
int64_t start = startOffset + size*0/1;
int64_t end = startOffset + size*1/1;
activation_x458* acc = head->closure->processRange(resourceInfo,out,start,end);
head->closure->postProcInit(resourceInfo,acc);
head->closure->postProcess(resourceInfo,acc);
head->closure->finalize(resourceInfo,acc);
return acc;
}
