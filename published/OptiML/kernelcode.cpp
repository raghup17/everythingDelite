int32_t x241 = x239->_numRows;
int32_t  x244 = x241 % 1;
int32_t x245 = x241 - x244;
int32_t x243 = x240->_numCols;
int32_t  x249 = x243 % 1;
int32_t x250 = x243 - x249;
int32_t x242 = x239->_numCols;
int32_t  x254 = x242 % 1;
int32_t x255 = x242 - x254;
cppDeliteArraydouble *x273 = x239->_data;
cppDeliteArraydouble *x287 = x240->_data;
for(int i=0 ; i<x245 ; i+=4) {
    { int32_t x248 = i;
  int32_t x259 = x248 + 4;
  int32_t x260 = x259 - x248;
  int32_t  x261 = x260 % 1;
  int32_t x262 = x259 - x261;
    for(int i=0 ; i<x250 ; i+=4) {
      { int32_t x253 = i;
    int32_t x266 = x253 + 4;
    int32_t x267 = x266 - x253;
    int32_t  x268 = x267 % 1;
    int32_t x269 = x266 - x268;
      for(int i=0 ; i<x255 ; i+=4) {
        { int32_t x258 = i;
      int32_t x278 = x258 + 4;
      int32_t x279 = x278 - x258;
      int32_t  x280 = x279 % 4;
      int32_t x281 = x278 - x280;
      for(int i=x248 ; i<x262 ; i+=1) {
          { int32_t x265 = i;
        int32_t x274 = x265 * x242;
        for(int i=x253 ; i<x269 ; i+=1) {
            { int32_t x272 = i;
          int32_t x275 = x274 + x272;
          double x276 = x273->apply(x275);
          double x277 = x276;
          for(int i=x258 ; i<x281 ; i+=4) {
              { int32_t x284 = i;
            int32_t x285 = x274 + x284;
            double x286 = x273->apply(x285);
            int32_t x288 = x284 * x243;
            int32_t x289 = x288 + x272;
            double x290 = x287->apply(x289);
            double  x291 = x286 * x290;
            x277 += x291;
            int32_t x293 = x284 + 1;
            int32_t x294 = x274 + x293;
            double x295 = x273->apply(x294);
            int32_t x296 = x293 * x243;
            int32_t x297 = x296 + x272;
            double x298 = x287->apply(x297);
            double  x299 = x295 * x298;
            x277 += x299;
            int32_t x301 = x284 + 2;
            int32_t x302 = x274 + x301;
            double x303 = x273->apply(x302);
            int32_t x304 = x301 * x243;
            int32_t x305 = x304 + x272;
            double x306 = x287->apply(x305);
            double  x307 = x303 * x306;
            x277 += x307;
            int32_t x309 = x284 + 3;
            int32_t x310 = x274 + x309;
            double x311 = x273->apply(x310);
            int32_t x312 = x309 * x243;
            int32_t x313 = x312 + x272;
            double x314 = x287->apply(x313);
            double  x315 = x311 * x314;
            x277 += x315;
             }
           
          };
            for(int i=x281 ; i<x278 ; i+=1) {
              { int32_t x321 = i;
            int32_t x322 = x274 + x321;
            double x323 = x273->apply(x322);
            int32_t x324 = x321 * x243;
            int32_t x325 = x324 + x272;
            double x326 = x287->apply(x325);
            double  x327 = x323 * x326;
            x277 += x327;
             }
             
            };
          double x331 = x277;
          x239->_data->update(x275,x331);
           }
         
        };
         }
       
      };
       }
       
      };
     }
     
    };
   }
 
};
cppDenseMatrixDouble *x343 = x239; // unsafe immutable;
cppDenseMatrixDouble *x345 = x343;
