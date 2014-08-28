package generated.scala
//activation record for fat loop
final class activation_x609 {
var left_act: activation_x609 = _
var x609: Array[Double] = _
var x609_data: Array[Double] = _
}
object activation_x609 {
}
object kernel_x609 {
def apply(x557:Int,x558:Array[Double],x559:Array[Double]): generated.scala.DeliteOpMultiLoop[activation_x609] = {
val x609 = new generated.scala.DeliteOpMultiLoop[activation_x609] {
def size(): Int = {
x557}

var loopStart: Int = _
var loopSize: Int = _
def alloc(): activation_x609 = {
val __act: activation_x609 = new activation_x609()
val x571 = loopSize
val x575 = new Array[Double](x571)
__act.x609_data = x575
__act}

def processRange(__act:activation_x609,start:Int,end:Int): activation_x609 = {
val isEmpty: Boolean = end-start <= 0
val __act2: activation_x609 = init(__act,start,isEmpty)
var idx: Int = start + 1
while (idx < end) {
process(__act2,idx)
idx = idx + 1
}
__act2}

def init(__act:activation_x609,x242:Int,isEmpty:Boolean): activation_x609 = {
if (!isEmpty) {
process(__act,x242)
}
__act}

def process(__act:activation_x609,x242:Int): Unit = {
val x566 = x558(x242)
val x567 = x559(x242)
val x568 = {
x566 * x567}
val x570 = x568
val x569 = __act.x609_data
val x579 = x569(x242) = x570
}

def combine(__act:activation_x609,rhs:activation_x609): Unit = {
}

def postCombine(__act:activation_x609,lhs:activation_x609): Unit = {
__act.left_act = lhs
}

def postProcInit(__act:activation_x609): Unit = {
}

def postProcess(__act:activation_x609): Unit = {
}

def finalize(__act:activation_x609): Unit = {
var x569: Array[Double] = __act.x609_data
__act.x609_data = null
__act.x609 = x569
}

def initAct(): activation_x609 = {
val act: activation_x609 = new activation_x609
act}

}

x609
}}
