#include "kernelcode.cpp"

#include "kernelInit.cpp"

int main()
{
  resourceInfo_t r;
  int M = 512;
  int N = 512;
  int P = 512;

  cppDenseMatrixDouble *m1 = kernel_x364(r, M, P);
  cppDenseMatrixDouble *m2 = kernel_x376(r, P, N);
  cppDenseMatrixDouble *m3 = kernel_x388(r, M, N);

  m3 = kernel_x349(r, m1, m2, m3);

  return 0;
}

