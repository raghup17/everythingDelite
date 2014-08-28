#ifndef __cppDenseMatrixDouble__
#define __cppDenseMatrixDouble__
#include "DeliteCpp.h"
#include "DeliteMemory.h"
#include "cppDeliteArraydouble.h"
class cppDenseMatrixDouble : public DeliteMemory {
public:
	int32_t  _numRows;
	int32_t  _numCols;
	cppDeliteArraydouble  *_data;
	cppDenseMatrixDouble(void) { }
	cppDenseMatrixDouble(int32_t  arg__numRows,int32_t  arg__numCols,cppDeliteArraydouble * arg__data) {
		_numRows = arg__numRows;
		_numCols = arg__numCols;
		_data = arg__data;
	}
	bool equals(cppDenseMatrixDouble * to) {
		return (_numRows == to->_numRows) && (_numCols == to->_numCols) && (_data == to->_data);
	}
	uint32_t hashcode(void) {
		int32_t hc;
		int32_t ret = 0;
		hc = delite_hashcode(_numRows);
		ret = ret * 41 + hc;
		hc = delite_hashcode(_numCols);
		ret = ret * 41 + hc;
		hc = _data->hashcode();
		ret = ret * 41 + hc;
		return ret;
	}
};

struct cppDenseMatrixDoubleD {
  void operator()(cppDenseMatrixDouble *p) {
    //printf("cppDenseMatrixDouble: deleting %p\n",p);
    
  }
};

#endif
