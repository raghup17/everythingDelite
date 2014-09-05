#ifndef CPP_HELPERFUNCS_H
#define CPP_HELPERFUNCS_H
#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <sys/time.h>
#include <iostream>

class cppDeliteArraydouble {
public:
  double  *data;
  int length;

  cppDeliteArraydouble(int _length): data((double  *)(new double [_length])), length(_length) { }
  cppDeliteArraydouble(int _length, int heapIdx): data((double  *)(new double [_length])), length(_length) { }

  cppDeliteArraydouble(double  *_data, int _length) {
    data = _data;
    length = _length;
  }

  double  apply(int idx) {
    return data[idx];
  }

  void update(int idx, double  val) {
 //   std::cout << "Updating " << idx << std::endl;
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

// Helper class to time kernels
class Timer {
  double uStart;
  double uEnd;
  double totalElapsed;
  int count;
  struct timeval t;

public:
  Timer() {
    uStart = 0.0;
    uEnd = 0.0;
    count = 0;
    totalElapsed = 0.0;
  }

  double getTimeInMicroSecs() {
    double tmp = 0.0;
    gettimeofday(&t, NULL);
    tmp = t.tv_sec * 1000000.0 + t.tv_usec;
    return tmp;
  }
  void start() {
    count++;
    uStart = getTimeInMicroSecs(); 
  }

  double stop() {
    uEnd = getTimeInMicroSecs();
    double elapsed = uEnd - uStart;
    totalElapsed += elapsed;
    if (elapsed < 0) {
      std::cout << "TIMER ERROR: Overflow!" << std::endl;
    }
    return elapsed;
  }

  double getAvgElapsed() {
    return totalElapsed / count;
  }
};

#endif
