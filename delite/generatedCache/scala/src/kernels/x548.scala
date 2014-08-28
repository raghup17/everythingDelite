package generated.scala
//activation record for fat loop
final class activation_x548 {
var left_act: activation_x548 = _
var x548: Array[Double] = _
var x548_data: Array[Double] = _
var x548_buf: Array[Double] = _
var x548_size: Int = _
var x548_offset: Int = _
var x548_conditionals: Int = _
def x548_data_set(xs:Array[Double],cs:Int): Unit = {
x548_data = xs
x548_conditionals = cs
if (left_act ne null)
left_act.x548_data_set(xs,cs)
}

}
object activation_x548 {
}
object kernel_x548 {
def apply(x122:Int,x121:generated.scala.io.FileStreamImpl): generated.scala.DeliteOpMultiLoop[activation_x548] = {
val x548 = new generated.scala.DeliteOpMultiLoop[activation_x548] {
def size(): Int = {
x122}

var loopStart: Int = _
var loopSize: Int = _
def alloc(): activation_x548 = {
val __act: activation_x548 = new activation_x548()
// __act.x548 stays null for now
__act}

def processRange(__act:activation_x548,start:Int,end:Int): activation_x548 = {
x121.openAtNewLine(start)
val isEmpty: Boolean = x121.end(start) - x121.pos(start) <= 0
val __act2: activation_x548 = init(__act,start,isEmpty)
while (x121.pos(start) < x121.end(start)) {
process(__act2,start)
}
x121.close(start)
__act2}

def init(__act:activation_x548,x123:Int,isEmpty:Boolean): activation_x548 = {
val __act2: activation_x548 = new activation_x548()
val x188 = 0
val x467 = new Array[Double](x188)
__act2.x548_buf = x467
if (!isEmpty) {
val x124 = x121.readLine(x123)
val x125 = {
x124.trim}
val x126 = x125.split("\\s+", 0)
val x127 = x126.length //MatMult.scala:9//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;MatMult.scala:9//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x143: Int = x127
val x513 = new Array[Double](x143)
val x547_data: Array[Double] = x513
var x547_size: Int = 0
var x547_conditionals: Int = 0
var x128: Int = 0
while (x128 < x127) {  // begin fat loop x547
val x129 = x126(x128)
val x130 = {
x129 == "Inf"}
val x512 = {
def x512thenb(): Double = {
val x503 = {
Double.PositiveInfinity}
x503
}
def x512elseb(): Double = {
val x505 = {
x129 == "-Inf"}
val x510 = {
def x510thenb(): Double = {
val x506 = {
Double.NegativeInfinity}
x506
}
def x510elseb(): Double = {
val x508 = {
x129.toDouble}
x508
}
if (x505) {
x510thenb()
} else { 
x510elseb()
}
}
x510
}
if (x130) {
x512thenb()
} else { 
x512elseb()
}
}
val x142 = x512
val x141 = x547_data
val x517 = x141(x128) = x142
x128 = x128 + 1
} // end fat loop x547
val x141 = x547_data
val x547 = x141
val x183 = x547
val x184 = x183.length //DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x182: Int = 0
while (x182 < x184) { //flatMap loop
val x185 = x183(x182)
val x186 = __act2.x548_buf
val x187 = x185
if (true) {
val x473 = __act2.x548_size
val x474 = x186.length //DeliteFileReader.scala:160//DeliteOps.scala:387//DeliteArrayBuffer.scala:300//DenseVectorOpsExp.scala:941//DeliteArray.scala:214//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteOps.scala:1193
val x475 = x473 >= x474
val x487 = {
def x487thenb(): Unit = {
val x476 = x474 < 16
val x479 = {
def x479thenb(): Int = {
16
}
def x479elseb(): Int = {
val x477 = x474 * 2
x477
}
if (x476) {
x479thenb()
} else { 
x479elseb()
}
}
val x480 = new Array[Double](x479)
val x481 = System.arraycopy(x186,0,x480,0,x474)
val x482 = x480(x473) = x187
val x483 = __act2.x548_buf = x480
x483
}
def x487elseb(): Unit = {
val x485 = x186(x473) = x187
x485
}
if (x475) {
x487thenb()
} else { 
x487elseb()
}
}
__act2.x548_size = __act2.x548_size + 1
}
__act2.x548_conditionals = __act2.x548_conditionals + 1
x182 = x182 + 1
}
}
__act2}

def process(__act:activation_x548,x123:Int): Unit = {
val x124 = x121.readLine(x123)
val x125 = {
x124.trim}
val x126 = x125.split("\\s+", 0)
val x127 = x126.length //MatMult.scala:9//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;MatMult.scala:9//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x143: Int = x127
val x513 = new Array[Double](x143)
val x547_data: Array[Double] = x513
var x547_size: Int = 0
var x547_conditionals: Int = 0
var x128: Int = 0
while (x128 < x127) {  // begin fat loop x547
val x129 = x126(x128)
val x130 = {
x129 == "Inf"}
val x512 = {
def x512thenb(): Double = {
val x503 = {
Double.PositiveInfinity}
x503
}
def x512elseb(): Double = {
val x505 = {
x129 == "-Inf"}
val x510 = {
def x510thenb(): Double = {
val x506 = {
Double.NegativeInfinity}
x506
}
def x510elseb(): Double = {
val x508 = {
x129.toDouble}
x508
}
if (x505) {
x510thenb()
} else { 
x510elseb()
}
}
x510
}
if (x130) {
x512thenb()
} else { 
x512elseb()
}
}
val x142 = x512
val x141 = x547_data
val x517 = x141(x128) = x142
x128 = x128 + 1
} // end fat loop x547
val x141 = x547_data
val x547 = x141
val x183 = x547
val x184 = x183.length //DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x182: Int = 0
while (x182 < x184) { //flatMap loop
val x185 = x183(x182)
val x186 = __act.x548_buf
val x187 = x185
if (true) {
val x473 = __act.x548_size
val x474 = x186.length //DeliteFileReader.scala:160//DeliteOps.scala:387//DeliteArrayBuffer.scala:300//DenseVectorOpsExp.scala:941//DeliteArray.scala:214//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteOps.scala:1193
val x475 = x473 >= x474
val x487 = {
def x487thenb(): Unit = {
val x476 = x474 < 16
val x479 = {
def x479thenb(): Int = {
16
}
def x479elseb(): Int = {
val x477 = x474 * 2
x477
}
if (x476) {
x479thenb()
} else { 
x479elseb()
}
}
val x480 = new Array[Double](x479)
val x481 = System.arraycopy(x186,0,x480,0,x474)
val x482 = x480(x473) = x187
val x483 = __act.x548_buf = x480
x483
}
def x487elseb(): Unit = {
val x485 = x186(x473) = x187
x485
}
if (x475) {
x487thenb()
} else { 
x487elseb()
}
}
__act.x548_size = __act.x548_size + 1
}
__act.x548_conditionals = __act.x548_conditionals + 1
x182 = x182 + 1
}
}

def combine(__act:activation_x548,rhs:activation_x548): Unit = {
}

def postCombine(__act:activation_x548,lhs:activation_x548): Unit = {
__act.x548_offset = lhs.x548_offset+lhs.x548_size
__act.x548_conditionals = __act.x548_conditionals+lhs.x548_conditionals
__act.left_act = lhs
}

def postProcInit(__act:activation_x548): Unit = {
if (__act.x548_offset > 0) {
val x188 = __act.x548_offset + __act.x548_size
val x186 = __act.x548_buf
val x497 = new Array[Double](x188)
__act.x548_data_set(x497,__act.x548_conditionals)
} else {
__act.x548_data_set(__act.x548_buf,__act.x548_conditionals)
}
}

def postProcess(__act:activation_x548): Unit = {
if (__act.x548_data ne __act.x548_buf) {
val x188 = __act.x548_size
val x191 = __act.x548_buf
val x186 = __act.x548_data
val x189 = 0
val x190 = __act.x548_offset
val x499 = System.arraycopy(x191,x189,x186,x190,x188)
}
__act.x548_buf = null
}

def finalize(__act:activation_x548): Unit = {
var x186: Array[Double] = __act.x548_data
__act.x548_data = null
val x188 = __act.x548_conditionals
val x489 = x186.length //DeliteFileReader.scala:160//DeliteOps.scala:387//DeliteArrayBuffer.scala:283//DenseVectorOpsExp.scala:931//DeliteArray.scala:189//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteOps.scala:1193
val x490 = x489 > x188
val x495 = {
def x495thenb(): Unit = {
val x491 = new Array[Double](x188)
val x492 = System.arraycopy(x186,0,x491,0,x188)
val x493 = x186 = x491
x493
}
if (x490) {
x495thenb()
}
}
__act.x548 = x186
}

def initAct(): activation_x548 = {
val act: activation_x548 = new activation_x548
act}

}

x548
}}
