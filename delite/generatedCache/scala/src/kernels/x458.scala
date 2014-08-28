package generated.scala
//activation record for fat loop
final class activation_x458 {
var left_act: activation_x458 = _
var x458: Array[Double] = _
var x458_data: Array[Double] = _
var x458_buf: Array[Double] = _
var x458_size: Int = _
var x458_offset: Int = _
var x458_conditionals: Int = _
def x458_data_set(xs:Array[Double],cs:Int): Unit = {
x458_data = xs
x458_conditionals = cs
if (left_act ne null)
left_act.x458_data_set(xs,cs)
}

}
object activation_x458 {
}
object kernel_x458 {
def apply(x3:Int,x2:generated.scala.io.FileStreamImpl): generated.scala.DeliteOpMultiLoop[activation_x458] = {
val x458 = new generated.scala.DeliteOpMultiLoop[activation_x458] {
def size(): Int = {
x3}

var loopStart: Int = _
var loopSize: Int = _
def alloc(): activation_x458 = {
val __act: activation_x458 = new activation_x458()
// __act.x458 stays null for now
__act}

def processRange(__act:activation_x458,start:Int,end:Int): activation_x458 = {
x2.openAtNewLine(start)
val isEmpty: Boolean = x2.end(start) - x2.pos(start) <= 0
val __act2: activation_x458 = init(__act,start,isEmpty)
while (x2.pos(start) < x2.end(start)) {
process(__act2,start)
}
x2.close(start)
__act2}

def init(__act:activation_x458,x4:Int,isEmpty:Boolean): activation_x458 = {
val __act2: activation_x458 = new activation_x458()
val x69 = 0
val x377 = new Array[Double](x69)
__act2.x458_buf = x377
if (!isEmpty) {
val x5 = x2.readLine(x4)
val x6 = {
x5.trim}
val x7 = x6.split("\\s+", 0)
val x8 = x7.length //MatMult.scala:8//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;MatMult.scala:8//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x24: Int = x8
val x423 = new Array[Double](x24)
val x457_data: Array[Double] = x423
var x457_size: Int = 0
var x457_conditionals: Int = 0
var x9: Int = 0
while (x9 < x8) {  // begin fat loop x457
val x10 = x7(x9)
val x11 = {
x10 == "Inf"}
val x422 = {
def x422thenb(): Double = {
val x413 = {
Double.PositiveInfinity}
x413
}
def x422elseb(): Double = {
val x415 = {
x10 == "-Inf"}
val x420 = {
def x420thenb(): Double = {
val x416 = {
Double.NegativeInfinity}
x416
}
def x420elseb(): Double = {
val x418 = {
x10.toDouble}
x418
}
if (x415) {
x420thenb()
} else { 
x420elseb()
}
}
x420
}
if (x11) {
x422thenb()
} else { 
x422elseb()
}
}
val x23 = x422
val x22 = x457_data
val x427 = x22(x9) = x23
x9 = x9 + 1
} // end fat loop x457
val x22 = x457_data
val x457 = x22
val x64 = x457
val x65 = x64.length //DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x63: Int = 0
while (x63 < x65) { //flatMap loop
val x66 = x64(x63)
val x67 = __act2.x458_buf
val x68 = x66
if (true) {
val x383 = __act2.x458_size
val x384 = x67.length //DeliteFileReader.scala:160//DeliteOps.scala:387//DeliteArrayBuffer.scala:300//DenseVectorOpsExp.scala:941//DeliteArray.scala:214//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteOps.scala:1193
val x385 = x383 >= x384
val x397 = {
def x397thenb(): Unit = {
val x386 = x384 < 16
val x389 = {
def x389thenb(): Int = {
16
}
def x389elseb(): Int = {
val x387 = x384 * 2
x387
}
if (x386) {
x389thenb()
} else { 
x389elseb()
}
}
val x390 = new Array[Double](x389)
val x391 = System.arraycopy(x67,0,x390,0,x384)
val x392 = x390(x383) = x68
val x393 = __act2.x458_buf = x390
x393
}
def x397elseb(): Unit = {
val x395 = x67(x383) = x68
x395
}
if (x385) {
x397thenb()
} else { 
x397elseb()
}
}
__act2.x458_size = __act2.x458_size + 1
}
__act2.x458_conditionals = __act2.x458_conditionals + 1
x63 = x63 + 1
}
}
__act2}

def process(__act:activation_x458,x4:Int): Unit = {
val x5 = x2.readLine(x4)
val x6 = {
x5.trim}
val x7 = x6.split("\\s+", 0)
val x8 = x7.length //MatMult.scala:8//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;MatMult.scala:8//LAioOpsImpl.scala:28//LAioOpsImpl.scala:47//Arrays.scala:31//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x24: Int = x8
val x423 = new Array[Double](x24)
val x457_data: Array[Double] = x423
var x457_size: Int = 0
var x457_conditionals: Int = 0
var x9: Int = 0
while (x9 < x8) {  // begin fat loop x457
val x10 = x7(x9)
val x11 = {
x10 == "Inf"}
val x422 = {
def x422thenb(): Double = {
val x413 = {
Double.PositiveInfinity}
x413
}
def x422elseb(): Double = {
val x415 = {
x10 == "-Inf"}
val x420 = {
def x420thenb(): Double = {
val x416 = {
Double.NegativeInfinity}
x416
}
def x420elseb(): Double = {
val x418 = {
x10.toDouble}
x418
}
if (x415) {
x420thenb()
} else { 
x420elseb()
}
}
x420
}
if (x11) {
x422thenb()
} else { 
x422elseb()
}
}
val x23 = x422
val x22 = x457_data
val x427 = x22(x9) = x23
x9 = x9 + 1
} // end fat loop x457
val x22 = x457_data
val x457 = x22
val x64 = x457
val x65 = x64.length //DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteArray.scala:324//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434;DeliteFileReader.scala:174//DeliteArrayBuffer.scala:265//DenseVectorOpsExp.scala:911//DenseMatrixOpsExp.scala:1066//DenseVectorViewOpsExp.scala:474//IndexVectorOpsExp.scala:520//DeliteArray.scala:172//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//Effects.scala:434
var x63: Int = 0
while (x63 < x65) { //flatMap loop
val x66 = x64(x63)
val x67 = __act.x458_buf
val x68 = x66
if (true) {
val x383 = __act.x458_size
val x384 = x67.length //DeliteFileReader.scala:160//DeliteOps.scala:387//DeliteArrayBuffer.scala:300//DenseVectorOpsExp.scala:941//DeliteArray.scala:214//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteOps.scala:1193
val x385 = x383 >= x384
val x397 = {
def x397thenb(): Unit = {
val x386 = x384 < 16
val x389 = {
def x389thenb(): Int = {
16
}
def x389elseb(): Int = {
val x387 = x384 * 2
x387
}
if (x386) {
x389thenb()
} else { 
x389elseb()
}
}
val x390 = new Array[Double](x389)
val x391 = System.arraycopy(x67,0,x390,0,x384)
val x392 = x390(x383) = x68
val x393 = __act.x458_buf = x390
x393
}
def x397elseb(): Unit = {
val x395 = x67(x383) = x68
x395
}
if (x385) {
x397thenb()
} else { 
x397elseb()
}
}
__act.x458_size = __act.x458_size + 1
}
__act.x458_conditionals = __act.x458_conditionals + 1
x63 = x63 + 1
}
}

def combine(__act:activation_x458,rhs:activation_x458): Unit = {
}

def postCombine(__act:activation_x458,lhs:activation_x458): Unit = {
__act.x458_offset = lhs.x458_offset+lhs.x458_size
__act.x458_conditionals = __act.x458_conditionals+lhs.x458_conditionals
__act.left_act = lhs
}

def postProcInit(__act:activation_x458): Unit = {
if (__act.x458_offset > 0) {
val x69 = __act.x458_offset + __act.x458_size
val x67 = __act.x458_buf
val x407 = new Array[Double](x69)
__act.x458_data_set(x407,__act.x458_conditionals)
} else {
__act.x458_data_set(__act.x458_buf,__act.x458_conditionals)
}
}

def postProcess(__act:activation_x458): Unit = {
if (__act.x458_data ne __act.x458_buf) {
val x69 = __act.x458_size
val x72 = __act.x458_buf
val x67 = __act.x458_data
val x70 = 0
val x71 = __act.x458_offset
val x409 = System.arraycopy(x72,x70,x67,x71,x69)
}
__act.x458_buf = null
}

def finalize(__act:activation_x458): Unit = {
var x67: Array[Double] = __act.x458_data
__act.x458_data = null
val x69 = __act.x458_conditionals
val x399 = x67.length //DeliteFileReader.scala:160//DeliteOps.scala:387//DeliteArrayBuffer.scala:283//DenseVectorOpsExp.scala:931//DeliteArray.scala:189//DeliteArray.scala:24//DeliteArray.scala:27//DeliteArray.scala:448//DeliteArray.scala:247//DeliteOps.scala:1161//StaticData.scala:24//Effects.scala:367//DeliteOps.scala:1178//DeliteStruct.scala:39//Effects.scala:430//DeliteRestage.scala:122//SumOpsExp.scala:98//RewriteOpsExp.scala:186//LAPACKOpsExp.scala:94//BLASOpsExp.scala:86//Asserts.scala:16//IOOps.scala:113//Profiling.scala:19//DeliteFileReader.scala:107//Functions.scala:105//HashMap.scala:59//DeliteArrayBuffer.scala:349//Arrays.scala:91//BasicMathOpsExp.scala:96//FileStreamOpsExp.scala:120//LAioOpsExp.scala:108//Ordering2OpsExp.scala:114//ControlOpsExp.scala:52//GrayscaleImageOpsExp.scala:70//SparseMatrixOpsExp.scala:458//DenseVectorOpsExp.scala:900//DenseMatrixOpsExp.scala:1055//Tup5OpsExp.scala:72//RandomVariableOpsExp.scala:58//Tup4OpsExp.scala:68//DenseVectorViewOpsExp.scala:463//Tup3OpsExp.scala:64//CastOpsExp.scala:54//FactorVariableOpsExp.scala:55//RangeOpsExp.scala:129//MiscOpsExp.scala:161//SparseMatrixBuildableOpsExp.scala:261//LinAlgOpsExp.scala:55//TrainingSetOpsExp.scala:63//SHashMapOpsExp.scala:110//FStringOpsExp.scala:187//ComputeStreamOpsExp.scala:74//FunctionFactorOpsExp.scala:82//MLioOpsExp.scala:151//Tup6OpsExp.scala:76//RandOpsExp.scala:114//UTriangleOpsExp.scala:61//SparseVectorOpsExp.scala:532//FactorGraphOpsExp.scala:81//BinaryFeatureOpsExp.scala:46//WeightOpsExp.scala:52//Tup7OpsExp.scala:80//MathOpsExp.scala:226//IndexVectorOpsExp.scala:509//CHashMapOpsExp.scala:110//Primitive2OpsExp.scala:361//DiscreteFeatureOpsExp.scala:60//Tup8OpsExp.scala:84//Tup9OpsExp.scala:88//ContinuousFeatureOpsExp.scala:52//ComplexOpsExp.scala:76//Tup2OpsExp.scala:60//SparseVectorViewOpsExp.scala:144//TableFactorOpsExp.scala:55//DeliteWhile.scala:61//DeliteIfThenElse.scala:56//BooleanOps.scala:40//StaticData.scala:34//CastingOps.scala:36//Structs.scala:322//DeliteOps.scala:1193
val x400 = x399 > x69
val x405 = {
def x405thenb(): Unit = {
val x401 = new Array[Double](x69)
val x402 = System.arraycopy(x67,0,x401,0,x69)
val x403 = x67 = x401
x403
}
if (x400) {
x405thenb()
}
}
__act.x458 = x67
}

def initAct(): activation_x458 = {
val act: activation_x458 = new activation_x458
act}

}

x458
}}
