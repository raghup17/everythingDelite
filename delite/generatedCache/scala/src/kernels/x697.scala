package generated.scala
object kernel_x697 {
def apply(x610:generated.scala.DenseMatrixDouble,x556:Int,x609:Array[Double],x611:Boolean): java.lang.String = {
def x697_block = { 
var x612: java.lang.String = ""
val x694 = {
def x694thenb(): Unit = {
val x613 = x612 = "null"
()
}
def x694elseb(): Unit = {
val x615 = x610._numRows
val x616 = {
x615 == 0}
val x692 = {
def x692thenb(): Unit = {
val x617 = x612 = "[ ]"
()
}
def x692elseb(): Unit = {
val x619 = x610._numRows
val x620 = x619 - 1
val x621 = {
x556 == 0}
val x654 = {
var i = 0
while (i < x620) {
  { val x287 = i
var x622: java.lang.String = ""
val x647 = {
def x647thenb(): Unit = {
val x623 = x622 = "[ ]"
()
}
def x647elseb(): Unit = {
val x625 = x556 - 1
val x626 = x287 * x556
val x635 = {
var i = 0
while (i < x625) {
  { val x299 = i
val x627 = x622
val x628 = x626 + x299
val x629 = x609(x628)
val x630 = {

def numericStr[A](x: A) = {
  val s = ("% ."+Global.numericPrecision+"g").format(x)
  val padPrefix = (Global.numericPrecision+6) - s.length
  if (padPrefix > 0) " "*padPrefix + s else s
}
if (x629.isInstanceOf[Double] || x629.isInstanceOf[Float]) numericStr(x629) else ("" + x629)
}
val x631 = {
"  ".toString + x630.toString}
val x632 = {
x627.toString + x631.toString}
val x633 = x622 = x632
 }
 
  i += 1
}}
val x636 = x622
val x637 = x556 - 1
val x638 = x626 + x637
val x639 = x610._data
val x640 = x639(x638)
val x641 = {

def numericStr[A](x: A) = {
  val s = ("% ."+Global.numericPrecision+"g").format(x)
  val padPrefix = (Global.numericPrecision+6) - s.length
  if (padPrefix > 0) " "*padPrefix + s else s
}
if (x640.isInstanceOf[Double] || x640.isInstanceOf[Float]) numericStr(x640) else ("" + x640)
}
val x642 = {
"  ".toString + x641.toString}
val x643 = {
x636.toString + x642.toString}
val x644 = x622 = x643
()
}
if (x621) {
x647thenb()
} else { 
x647elseb()
}
}
val x648 = x622
val x649 = x612
val x650 = {
x649.toString + x648.toString}
val x651 = {
x650.toString + "\n".toString}
val x652 = x612 = x651
 }
 
  i += 1
}}
var x655: java.lang.String = ""
val x656 = x610._numCols
val x657 = {
x656 == 0}
val x685 = {
def x685thenb(): Unit = {
val x658 = x655 = "[ ]"
()
}
def x685elseb(): Unit = {
val x660 = x656 - 1
val x661 = x610._numRows
val x662 = x661 - 1
val x663 = x610._numCols
val x664 = x662 * x663
val x673 = {
var i = 0
while (i < x660) {
  { val x340 = i
val x665 = x655
val x666 = x664 + x340
val x667 = x609(x666)
val x668 = {

def numericStr[A](x: A) = {
  val s = ("% ."+Global.numericPrecision+"g").format(x)
  val padPrefix = (Global.numericPrecision+6) - s.length
  if (padPrefix > 0) " "*padPrefix + s else s
}
if (x667.isInstanceOf[Double] || x667.isInstanceOf[Float]) numericStr(x667) else ("" + x667)
}
val x669 = {
"  ".toString + x668.toString}
val x670 = {
x665.toString + x669.toString}
val x671 = x655 = x670
 }
 
  i += 1
}}
val x674 = x655
val x675 = x656 - 1
val x676 = x664 + x675
val x677 = x610._data
val x678 = x677(x676)
val x679 = {

def numericStr[A](x: A) = {
  val s = ("% ."+Global.numericPrecision+"g").format(x)
  val padPrefix = (Global.numericPrecision+6) - s.length
  if (padPrefix > 0) " "*padPrefix + s else s
}
if (x678.isInstanceOf[Double] || x678.isInstanceOf[Float]) numericStr(x678) else ("" + x678)
}
val x680 = {
"  ".toString + x679.toString}
val x681 = {
x674.toString + x680.toString}
val x682 = x655 = x681
()
}
if (x657) {
x685thenb()
} else { 
x685elseb()
}
}
val x686 = x655
val x687 = x612
val x688 = {
x687.toString + x686.toString}
val x689 = x612 = x688
()
}
if (x616) {
x692thenb()
} else { 
x692elseb()
}
}
x692
}
if (x611) {
x694thenb()
} else { 
x694elseb()
}
}
val x695 = x612
x695
}
val x697 = x697_block

x697
}}
