#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

#define M 512
#define N 512
#define P 512

// 2, 64, 16, 256, 8, 2, 1, 
// 4, 8, 4, 4, 2, 1, 
// 1, 1, 1, 1, 8, 2
// Level 0 blocking params
#define bm0 128
#define bn0 128
#define bp0 128

// Level 1 blocking params
#define bm1 16
#define bn1 16
#define bp1 16



void printMat(double *buf, int rdim, int cdim) {
  int i=0, j=0;

  for (i=0; i<rdim; i++) {
    for (j=0; j<cdim; j++) {
      printf("%3.2lf ", buf[i*cdim+j]);
    }
    printf("\n");
  }
}

// m1: M x P
// m2: P x N
// out: M x N
void matMult(double *m1, double *m2, double *out) {

  int i=0, j=0, k=0;
  int i1=0, j1=0, k1=0;
  int i2=0, j2=0, k2=0;

  // Allocate transpose buffer
  double *transposeBuffer = (double*) malloc(bp0*bn0*sizeof(double));
  int trow=0, tcol=0;


  // Multiply m1(0,0)..m1(M,P) and m2(0,0)..m2(P,N)
  for (i=0; i<M; i+=bm0) {
  for (j=0; j<N; j+=bn0) {
  for (k=0; k<P; k+=bp0) {

    // Transpose here
//        for (trow=0; trow<bn0; trow++) {
//          for (tcol=0; tcol<bp0; tcol++) {
//              transposeBuffer[trow*bp0+tcol] = m2[(tcol+k)*N+trow+j];
//          }
//        }
    for (i1=i; i1<i+bm0; i1+=bm1) {
    for (j1=j; j1<j+bn0; j1+=bn1) {
    for (k1=k; k1<k+bp0; k1+=bp1) {

      for (trow=0; trow<bn1; trow++) {
          for (tcol=0; tcol<bp1; tcol++) {
              transposeBuffer[trow*bp1+tcol] = m2[(tcol+k1)*N+trow+j1];
          }
        }

              for (i2=i1; i2<i1+bm1; i2++) {
              for (j2=j1; j2<j1+bn1; j2++) {
              for (k2=k1; k2<k1+bp1; k2++) {
//                out[i2*N+j2] += m1[i2*P+k2] * m2[k2*N+j2];
//                  printf("Would've multiplied %3.2lf and %3.2lf\n", m1[i2*P+k2], m2[k2*N+j2]);
//                  printf("Actually multiplying %3.2lf and %3.2lf\n", m1[i2*P+k2], transposeBuffer[(j2-j1)*(bp1)+(k2-k1)]);
//                  out[i2*N+j2] += m1[i2*P+k2] * transposeBuffer[(j2-j)*(bp0)+(k2-k)];
                  out[i2*N+j2] += m1[i2*P+k2] * transposeBuffer[(j2-j1)*(bp1)+(k2-k1)];
              }
              }
              }

    }
    }
    }
  }
  }
  }
}

// m1: M x P
// m2: P x N
// out: M x N
void matMult_gold(double *m1, double *m2, double *out) {

  int i=0, j=0, k=0;

  for (i=0; i<M; i++) {
    for (j=0; j<N; j++) {
      double acc = out[i*N+j];
      for (k=0; k<P; k++) {
        acc += m1[i*P+k] * m2[k*N+j];
      }
      out[i*N+j] = acc;
    }
  }
}

// Compare two MxN matrices element-by-element
// Returns 0 if matrices match, else returns the number of mismatches
bool matrixCmp(double *m1, double *m2) {
  int i=0, j=0;
  int errorCount = 0;
  for (i=0; i<M*N; i++) {
    if (m1[i] != m2[i]) {
      errorCount++;
    }
  }
  return errorCount;
}

double getTimeInMicroSecs() {
  double tmp = 0.0;
  struct timeval t;
  gettimeofday(&t, NULL);
  tmp = t.tv_sec * 1000000.0 + t.tv_usec;
  return tmp;
}

int main()
{
  // Allocate
  double *m1 = (double*) malloc (M*P*sizeof(double));
  double *m2 = (double*) malloc (P*N*sizeof(double));
  double *out = (double*) malloc (M*N*sizeof(double));
  double *out_gold = (double*) malloc (M*N*sizeof(double));
  int mismatchCount = 0;
  int i=0;
  volatile double uStart = 0.0;
  volatile double uEnd = 0.0;

  // Initialize - already initialized to random values
  for (i=0; i<M*P; i++) {
    m1[i] = i;
  }

  for (i=0; i<P*N; i++) {
    m2[i] = i;
  }

  for (i=0; i<M*N; i++) {
    out[i] = 0.0;
    out_gold[i] = 0.0;
  }

  // Invoke
  __builtin___clear_cache((char*)m1, (char*)(&m1[M*P-1]));
  __builtin___clear_cache((char*)m2, (char*)(&m2[P*N-1]));
  __builtin___clear_cache((char*)out, (char*)(&m1[M*N-1]));
  uStart = getTimeInMicroSecs();
  matMult(m1, m2, out);
  uEnd = getTimeInMicroSecs();
  printf("Matrix multiply took %lf us\n", (uEnd-uStart));

  // Get Golden copy
  __builtin___clear_cache((char*)m1, (char*)(&m1[M*P-1]));
  __builtin___clear_cache((char*)m2, (char*)(&m2[P*N-1]));
  __builtin___clear_cache((char*)out, (char*)(&m1[M*N-1]));
  uStart = getTimeInMicroSecs();
  matMult_gold(m1, m2, out_gold);
  uEnd = getTimeInMicroSecs();
  printf("Vanilla multiply took %lf us\n", (uEnd-uStart));

  // Check
  mismatchCount = matrixCmp(out, out_gold);
  if (mismatchCount > 0) {
    printf("Matrix multiply failed, %d mismatches!\n", mismatchCount);
  }
  else {
    printf("Matrix multiply suceeded\n", mismatchCount);
  }

  return 0;

}
