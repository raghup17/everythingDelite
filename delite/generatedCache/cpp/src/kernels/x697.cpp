#include "cpphelperFuncs.h"
string  kernel_x697(resourceInfo_t &resourceInfo,cppDenseMatrixDouble  *x610, int32_t  x556, cppDeliteArraydouble  *x609, bool  x611) {
string x612 = string("");
if (x611) {
x612 = string("null");
} else {
int32_t x615 = x610->_numRows;
bool  x616 = x615 == 0;
if (x616) {
x612 = string("[ ]");
} else {
int32_t x619 = x610->_numRows;
int32_t x620 = x619 - 1;
bool  x621 = x556 == 0;
for(int i=0 ; i<x620 ; i+=1) {
  { int32_t x287 = i;
string x622 = string("");
if (x621) {
x622 = string("[ ]");
} else {
int32_t x625 = x556 - 1;
int32_t x626 = x287 * x556;
for(int i=0 ; i<x625 ; i+=1) {
  { int32_t x299 = i;
string x627 = x622;
int32_t x628 = x626 + x299;
double x629 = x609->apply(x628);
string  x630 = convert_to_string<double  >(x629);
string  x631 = string_plus( convert_to_string< string >(string("  ")), convert_to_string< string >(x630));
string  x632 = string_plus( convert_to_string< string >(x627), convert_to_string< string >(x631));
x622 = x632;
 }
 
};
string x636 = x622;
int32_t x637 = x556 - 1;
int32_t x638 = x626 + x637;
cppDeliteArraydouble *x639 = x610->_data;
double x640 = x639->apply(x638);
string  x641 = convert_to_string<double  >(x640);
string  x642 = string_plus( convert_to_string< string >(string("  ")), convert_to_string< string >(x641));
string  x643 = string_plus( convert_to_string< string >(x636), convert_to_string< string >(x642));
x622 = x643;
}
string x648 = x622;
string x649 = x612;
string  x650 = string_plus( convert_to_string< string >(x649), convert_to_string< string >(x648));
string  x651 = string_plus( convert_to_string< string >(x650), convert_to_string< string >(string("\n")));
x612 = x651;
 }
 
};
string x655 = string("");
int32_t x656 = x610->_numCols;
bool  x657 = x656 == 0;
if (x657) {
x655 = string("[ ]");
} else {
int32_t x660 = x656 - 1;
int32_t x661 = x610->_numRows;
int32_t x662 = x661 - 1;
int32_t x663 = x610->_numCols;
int32_t x664 = x662 * x663;
for(int i=0 ; i<x660 ; i+=1) {
  { int32_t x340 = i;
string x665 = x655;
int32_t x666 = x664 + x340;
double x667 = x609->apply(x666);
string  x668 = convert_to_string<double  >(x667);
string  x669 = string_plus( convert_to_string< string >(string("  ")), convert_to_string< string >(x668));
string  x670 = string_plus( convert_to_string< string >(x665), convert_to_string< string >(x669));
x655 = x670;
 }
 
};
string x674 = x655;
int32_t x675 = x656 - 1;
int32_t x676 = x664 + x675;
cppDeliteArraydouble *x677 = x610->_data;
double x678 = x677->apply(x676);
string  x679 = convert_to_string<double  >(x678);
string  x680 = string_plus( convert_to_string< string >(string("  ")), convert_to_string< string >(x679));
string  x681 = string_plus( convert_to_string< string >(x674), convert_to_string< string >(x680));
x655 = x681;
}
string x686 = x655;
string x687 = x612;
string  x688 = string_plus( convert_to_string< string >(x687), convert_to_string< string >(x686));
x612 = x688;
}
}
string x695 = x612;
string x697 = x695;

return x697;
}
