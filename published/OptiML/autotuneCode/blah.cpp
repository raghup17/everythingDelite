#include <iostream>
using namespace std;

typedef struct {
  int thread_id;
  int socket_id;
} resourceInfo_t;


int main()
{
	resourceInfo_t r;
	int i = 0;
	cout << "Hello world" << endl;
	int *x = kernel_x364(r, 10);
	for (i=0; i<10; i++) {
		x[i] = i;
	}

	for (i=0; i<10; i++) {
		cout << "i = " << x[i] << endl;
	}
	return 0;
}
