#include <stdio.h>
#include <stdint.h>
#include <iostream>

class cppDeliteArraydouble {
public:
  double  *data;
  int length;

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
};


class cppDenseMatrixDouble {
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
};

typedef struct {
  int thread_id;
  int socket_id;
} resourceInfo_t;


