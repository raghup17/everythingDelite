package optiml.compiler.ops

import scala.reflect.{Manifest,SourceContext}
import scala.virtualization.lms.common.{Base,BaseExp,EffectExp,BaseFatExp}
import scala.virtualization.lms.common.{ScalaGenBase,ScalaGenEffect,ScalaGenFat,CudaGenFat,CGenFat}
import scala.virtualization.lms.util._
import scala.virtualization.lms.internal._
import ppl.delite.framework.ops.DeliteCollection
import ppl.delite.framework.datastructures._
import ppl.delite.framework.ops.{DeliteOpsExp, DeliteCollectionOpsExp}
import ppl.delite.framework.Util._
import optiml.shared._
import optiml.shared.ops._
import optiml.shared.typeclass._
import optiml.compiler._
import optiml.compiler.ops._
import ppl.delite.framework.Config

import ppl.delite.framework.codegen.delite.DeliteCodegen
import java.io.{File, FileWriter, PrintWriter}
import scala.collection.mutable.ListBuffer
/**
 * IR Definitions
 */

trait DenseMatrixOpsExp extends DenseMatrixCompilerOps with DeliteCollectionOpsExp with DeliteStructsExp {
  this: OptiMLExp => 

  case class DenseMatrix20Object_Apply[T:Manifest](__arg0: Rep[Int],__arg1: Rep[Int])(implicit val __pos: SourceContext) extends DeliteStruct[DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]
    val elems = copyTransformedElems(collection.Seq(("_numRows",var_new(__arg0).e),("_numCols",var_new(__arg1).e),("_data",var_new(array_empty[T](__arg0*__arg1)).e)))
  }

  case class DenseMatrix23Object_Apply[T:Manifest](__arg0: Seq[Rep[DenseVector[T]]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_object_apply_impl23[T](__arg0)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrixObject_Block[T:Manifest](__arg0: Seq[Rep[DenseVector[DenseMatrix[T]]]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_object_block_impl[T](__arg0)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class Densematrix_fromarray[T:Manifest](__arg0: Rep[ForgeArray[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit val __pos: SourceContext) extends DeliteStruct[DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]
    val elems = copyTransformedElems(collection.Seq(("_numRows",__arg1),("_numCols",__arg2),("_data",__arg0)))
  }

  case class DenseMatrix4_ToBoolean[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val conv: (Rep[T]) => Rep[Boolean]) extends DeliteOpMap[T,Boolean,DenseMatrix[Boolean]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_toboolean_impl4_map[T](self)(implicitly[Manifest[T]],__pos,conv)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,Boolean](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix5_ToDouble[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val conv: (Rep[T]) => Rep[Double]) extends DeliteOpMap[T,Double,DenseMatrix[Double]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_todouble_impl5_map[T](self)(implicitly[Manifest[T]],__pos,conv)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,Double](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix5_ToFloat[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val conv: (Rep[T]) => Rep[Float]) extends DeliteOpMap[T,Float,DenseMatrix[Float]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_tofloat_impl5_map[T](self)(implicitly[Manifest[T]],__pos,conv)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,Float](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix5_ToInt[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val conv: (Rep[T]) => Rep[Int]) extends DeliteOpMap[T,Int,DenseMatrix[Int]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_toint_impl5_map[T](self)(implicitly[Manifest[T]],__pos,conv)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,Int](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix_Vview[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int],__arg3: Rep[Int],__arg4: Rep[Boolean])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[DenseVectorView[T]](reifyEffectsHere(densematrix_vview_impl[T](self,__arg1,__arg2,__arg3,__arg4)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix5_MakeString[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Stringable[T]) extends DeliteOpSingleTask[String](reifyEffectsHere(densematrix_makestring_impl5[T](self)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix14_ToString[T:Manifest](self: Rep[DenseMatrix[T]]) extends DeliteOpSingleTask[String](reifyEffectsHere(densematrix_tostring_impl14[T](self))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix5_Clone[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_clone_impl5_map[T](self)(implicitly[Manifest[T]],__pos)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix14_Mutable[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_mutable_impl14[T](self)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix2_InsertRow[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],y: Rep[DenseVector[T]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_insertrow_impl2[T](self,pos,y)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix_InsertAllRows[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],xs: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_insertallrows_impl[T](self,pos,xs)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix2_InsertCol[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],y: Rep[DenseVector[T]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_insertcol_impl2[T](self,pos,y)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix_InsertAllCols[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],xs: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_insertallcols_impl[T](self,pos,xs)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix3_Trim[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_trim_impl3[T](self)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix2_RemoveRows[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],num: Rep[Int])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_removerows_impl2[T](self,pos,num)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix2_RemoveCols[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],num: Rep[Int])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_removecols_impl2[T](self,pos,num)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class Densematrix_insertspace[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],len: Rep[Int])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_insertspace_impl[T](self,pos,len)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class Densematrix_ensureextra[T:Manifest](self: Rep[DenseMatrix[T]],extra: Rep[Int])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_ensureextra_impl[T](self,extra)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class Densematrix_realloc[T:Manifest](self: Rep[DenseMatrix[T]],minLen: Rep[Int])(implicit val __pos: SourceContext) extends DeliteOpSingleTask[Unit](reifyEffectsHere(densematrix_realloc_impl[T](self,minLen)(implicitly[Manifest[T]],__pos))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix74_Pl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpZipWith[T,T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val inA = self
    val inB = __arg1
    def func = densematrix_pl_impl74_zip[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix75_Pl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_pl_impl75_map[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix58_Sub[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpZipWith[T,T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val inA = self
    val inB = __arg1
    def func = densematrix_sub_impl58_zip[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix59_Sub[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_sub_impl59_map[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix11_Mulclnmul[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpZipWith[T,T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val inA = self
    val inB = __arg1
    def func = densematrix_mulclnmul_impl11_zip[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix61_Mul[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_mul_impl61_map[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class Densematrix_matmult[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_matmult_impl62[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]
    override def autotune = Config.autotuneEnabled
  }

  case class Densematrix_matmult_autotune3[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_matmult_impl62[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]

    override def autotune = Config.autotuneEnabled
  }


  case class Densematrix_new[T:Manifest](r: Rep[Int], c: Rep[Int])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_new[T](r,c)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]
    override def autotune = Config.autotuneEnabled
  }


  case class Densematrix_matmult_autotune[T:Manifest](m1: Rep[DenseMatrix[T]], m2: Rep[DenseMatrix[T]], out: Rep[DenseMatrix[T]], tunables: Tunable)(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_matmult_impl62a[T](m1, m2, out)(tunables)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]

    override def autotune = Config.autotuneEnabled
  }

  case class Densematrix_matmult_autotune2[T:Manifest](m1: Rep[DenseMatrix[T]], m2: Rep[DenseMatrix[T]], tunables: Tunable)(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_matmult_impl62b[T](m1, m2)(tunables)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]

    override def autotune = Config.autotuneEnabled
  }

  
  //  case class Densematrix_matmult_autotune[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]], res: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseMatrix[T]](reifyEffectsHere(densematrix_matmult_impl62a[T](self,__arg1, res)(implicitly[Manifest[T]],__pos,__imp0))) {
//    val _mT = implicitly[Manifest[T]]
//
//    override def autotune = Config.autotuneEnabled
//  }


  case class Densematrix_matvecmult[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpSingleTask[DenseVector[T]](reifyEffectsHere(densematrix_matvecmult_impl63[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))) {
    val _mT = implicitly[Manifest[T]]
  }

  case class DenseMatrix59_Div[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpZipWith[T,T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val inA = self
    val inB = __arg1
    def func = densematrix_div_impl59_zip[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix60_Div[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_div_impl60_map[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix4_Sum[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpReduce[T] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_sum_impl4_reduce[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    def zero = densematrix_sum_impl4_zero[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    val size = copyTransformedOrElse(_.size)(densematrix_size[T](self)(implicitly[Manifest[T]],__pos))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix3_Prod[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpReduce[T] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_prod_impl3_reduce[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    def zero = densematrix_prod_impl3_zero[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    val size = copyTransformedOrElse(_.size)(densematrix_size[T](self)(implicitly[Manifest[T]],__pos))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix20_Abs[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_abs_impl20_map[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix19_Exp[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_exp_impl19_map[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix19_Log[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Arith[T]) extends DeliteOpMap[T,T,DenseMatrix[T]] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_log_impl19_map[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,T](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix5_Min[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Ordering[T]) extends DeliteOpReduce[T] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_min_impl5_reduce[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    def zero = densematrix_min_impl5_zero[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    val size = copyTransformedOrElse(_.size)(densematrix_size[T](self)(implicitly[Manifest[T]],__pos))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix5_Max[T:Manifest](self: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Ordering[T]) extends DeliteOpReduce[T] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_max_impl5_reduce[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    def zero = densematrix_max_impl5_zero[T](self)(implicitly[Manifest[T]],__pos,__imp0)
    val size = copyTransformedOrElse(_.size)(densematrix_size[T](self)(implicitly[Manifest[T]],__pos))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix1_Clngt[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Ordering[T]) extends DeliteOpZipWith[T,T,Boolean,DenseMatrix[Boolean]] {
    val _mT = implicitly[Manifest[T]]

    val inA = self
    val inB = __arg1
    def func = densematrix_clngt_impl1_zip[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,Boolean](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix1_Clnlt[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit val __pos: SourceContext,val __imp0: Ordering[T]) extends DeliteOpZipWith[T,T,Boolean,DenseMatrix[Boolean]] {
    val _mT = implicitly[Manifest[T]]

    val inA = self
    val inB = __arg1
    def func = densematrix_clnlt_impl1_zip[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,Boolean](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix3_Map[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[R])(implicit val __pos: SourceContext) extends DeliteOpMap[T,R,DenseMatrix[R]] {
    val _mT = implicitly[Manifest[T]]
    val _mR = implicitly[Manifest[R]]

    val in = self
    def func = densematrix_map_impl3_map[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,R](in, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(in))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix5_Zip[T:Manifest,B:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[B]],__arg2: (Rep[T],Rep[B]) => Rep[R])(implicit val __pos: SourceContext) extends DeliteOpZipWith[T,B,R,DenseMatrix[R]] {
    val _mT = implicitly[Manifest[T]]
    val _mB = implicitly[Manifest[B]]
    val _mR = implicitly[Manifest[R]]

    val inA = self
    val inB = __arg1
    def func = densematrix_zip_impl5_zip[T,B,R](self,__arg1,__arg2)(implicitly[Manifest[T]],implicitly[Manifest[B]],implicitly[Manifest[R]],__pos)
    override def alloc(len: Exp[Int]) = densematrix_raw_alloc[T,R](inA, len)
    val size = copyTransformedOrElse(_.size)(densematrix_size(inA))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix4_Foreach[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Unit])(implicit val __pos: SourceContext) extends DeliteOpForeach[T] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def func = densematrix_foreach_impl4_func[T](self,__arg1)(implicitly[Manifest[T]],__pos)
    def sync = n => unit(List())
    val size = copyTransformedOrElse(_.size)(densematrix_size[T](self)(implicitly[Manifest[T]],__pos))
    override val numDynamicChunks = 0
  }

  case class DenseMatrix3_Count[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Boolean])(implicit val __pos: SourceContext) extends DeliteOpFilterReduce[T,Int] {
    val _mT = implicitly[Manifest[T]]

    val in = self
    def zero = densematrix_count_impl3_zero[T](self,__arg1)(implicitly[Manifest[T]],__pos)
    def reduce = densematrix_count_impl3_reduce[T](self,__arg1)(implicitly[Manifest[T]],__pos)
    def func = densematrix_count_impl3_map[T](self,__arg1)(implicitly[Manifest[T]],__pos)
    def cond = densematrix_count_impl3_cond[T](self,__arg1)(implicitly[Manifest[T]],__pos)
    val size = copyTransformedOrElse(_.size)(densematrix_size[T](self)(implicitly[Manifest[T]],__pos))
    override val numDynamicChunks = 0
  }

  case class Densematrix_grouprowsby_helperKeys[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit val __pos: SourceContext) extends DeliteOpFilteredGroupByReduce[Int,K,K,ForgeArray[K]] {
    val _mT = implicitly[Manifest[T]]
    val _mK = implicitly[Manifest[K]]
    val _mV = implicitly[Manifest[V]]

    val in = __arg0
    def cond: Exp[Int] => Exp[Boolean] = null
    val size = copyTransformedOrElse(_.size)(indexvector_length(in))
    def keyFunc = densematrix_grouprowsby_helper_impl_key[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
    def valFunc = keyFunc
    def reduceFunc = (a,b) => a
    def zero = unit(null).asInstanceOf[Rep[K]]
    override def alloc(len: Exp[Int]) = array_raw_alloc[K](null.asInstanceOf[Rep[ForgeArray[K]]], len)
  }

  case class Densematrix_grouprowsby_helperIndex[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit val __pos: SourceContext) extends DeliteOpBuildIndex[Int,K,DeliteIndex[K]] {
    val _mT = implicitly[Manifest[T]]
    val _mK = implicitly[Manifest[K]]
    val _mV = implicitly[Manifest[V]]

    val in = __arg0
    def cond: Exp[Int] => Exp[Boolean] = null
    val size = copyTransformedOrElse(_.size)(indexvector_length(in))
    def keyFunc = densematrix_grouprowsby_helper_impl_key[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
  }

  case class Densematrix_grouprowsby_helper[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit val __pos: SourceContext) extends DeliteOpFilteredGroupBy[Int,K,V,ForgeArrayBuffer[V],ForgeArray[ForgeArrayBuffer[V]]] {
    val _mT = implicitly[Manifest[T]]
    val _mK = implicitly[Manifest[K]]
    val _mV = implicitly[Manifest[V]]

    val in = __arg0
    def cond: Exp[Int] => Exp[Boolean] = null
    val size = copyTransformedOrElse(_.size)(indexvector_length(in))
    def keyFunc = densematrix_grouprowsby_helper_impl_key[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
    def valFunc = densematrix_grouprowsby_helper_impl_map[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
    override def allocI(len: Exp[Int]) = array_buffer_raw_alloc[V](null.asInstanceOf[Rep[ForgeArrayBuffer[V]]], len)
    override def alloc(len: Exp[Int]) = array_raw_alloc[ForgeArrayBuffer[V]](null.asInstanceOf[Rep[ForgeArray[ForgeArrayBuffer[V]]]], len)
  }

  case class Densematrix_groupcolsby_helperKeys[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit val __pos: SourceContext) extends DeliteOpFilteredGroupByReduce[Int,K,K,ForgeArray[K]] {
    val _mT = implicitly[Manifest[T]]
    val _mK = implicitly[Manifest[K]]
    val _mV = implicitly[Manifest[V]]

    val in = __arg0
    def cond: Exp[Int] => Exp[Boolean] = null
    val size = copyTransformedOrElse(_.size)(indexvector_length(in))
    def keyFunc = densematrix_groupcolsby_helper_impl_key[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
    def valFunc = keyFunc
    def reduceFunc = (a,b) => a
    def zero = unit(null).asInstanceOf[Rep[K]]
    override def alloc(len: Exp[Int]) = array_raw_alloc[K](null.asInstanceOf[Rep[ForgeArray[K]]], len)
  }

  case class Densematrix_groupcolsby_helperIndex[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit val __pos: SourceContext) extends DeliteOpBuildIndex[Int,K,DeliteIndex[K]] {
    val _mT = implicitly[Manifest[T]]
    val _mK = implicitly[Manifest[K]]
    val _mV = implicitly[Manifest[V]]

    val in = __arg0
    def cond: Exp[Int] => Exp[Boolean] = null
    val size = copyTransformedOrElse(_.size)(indexvector_length(in))
    def keyFunc = densematrix_groupcolsby_helper_impl_key[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
  }

  case class Densematrix_groupcolsby_helper[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit val __pos: SourceContext) extends DeliteOpFilteredGroupBy[Int,K,V,ForgeArrayBuffer[V],ForgeArray[ForgeArrayBuffer[V]]] {
    val _mT = implicitly[Manifest[T]]
    val _mK = implicitly[Manifest[K]]
    val _mV = implicitly[Manifest[V]]

    val in = __arg0
    def cond: Exp[Int] => Exp[Boolean] = null
    val size = copyTransformedOrElse(_.size)(indexvector_length(in))
    def keyFunc = densematrix_groupcolsby_helper_impl_key[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
    def valFunc = densematrix_groupcolsby_helper_impl_map[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos)
    override def allocI(len: Exp[Int]) = array_buffer_raw_alloc[V](null.asInstanceOf[Rep[ForgeArrayBuffer[V]]], len)
    override def alloc(len: Exp[Int]) = array_raw_alloc[ForgeArrayBuffer[V]](null.asInstanceOf[Rep[ForgeArray[ForgeArrayBuffer[V]]]], len)
  }



  def densematrix_object_apply[T:Manifest](__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext,__imp1: Overload20) = {
    reflectMutable(DenseMatrix20Object_Apply[T](__arg0,__arg1)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_object_apply[T:Manifest](__arg0: Rep[DenseVector[DenseVector[T]]])(implicit __pos: SourceContext,__imp1: Overload21) = {
    densematrix_object_apply_impl21[T](__arg0)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_object_apply[T:Manifest](__arg0: Rep[DenseVector[DenseVectorView[T]]])(implicit __pos: SourceContext,__imp1: Overload22) = {
    densematrix_object_apply_impl22[T](__arg0)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_object_apply[T:Manifest](__arg0: Seq[Rep[DenseVector[T]]])(implicit __pos: SourceContext,__imp1: Overload23) = {
    reflectPure(DenseMatrix23Object_Apply[T](__arg0)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_object_block[T:Manifest](__arg0: Seq[Rep[DenseVector[DenseMatrix[T]]]])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrixObject_Block[T](__arg0)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_object_diag[T:Arith:Manifest](__arg0: Rep[Int],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext) = {
    densematrix_object_diag_impl1[T](__arg0,__arg1)(implicitly[Arith[T]],implicitly[Manifest[T]],__pos)
  }
  def densematrix_object_identity(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext,__imp1: Overload1) = {
    densematrix_object_identity_impl1(__arg0,__arg1)(__pos)
  }
  def densematrix_fromarray[T:Manifest](__arg0: Rep[ForgeArray[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext) = {
    reflectPure(Densematrix_fromarray[T](__arg0,__arg1,__arg2)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_fromfunc[T:Manifest](__arg0: Rep[Int],__arg1: Rep[Int],__arg2: (Rep[Int],Rep[Int]) => Rep[T])(implicit __pos: SourceContext) = {
    densematrix_fromfunc_impl[T](__arg0,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def matrix_shapeindex(idx: Rep[Int],numCols: Rep[Int])(implicit __pos: SourceContext) = {
    matrix_shapeindex_impl(idx,numCols)(__pos)
  }
  def densematrix_grouprowsby_helper[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit __pos: SourceContext) = {
    val keys = reflectPure(Densematrix_grouprowsby_helperKeys[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos))
    val index = reflectPure(Densematrix_grouprowsby_helperIndex[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos))
    val values = reflectPure(Densematrix_grouprowsby_helper[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos))
    reflectPure(DeliteMapNewImm(keys, values, index, darray_length(values)))
  }
  def densematrix_groupcolsby_helper[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit __pos: SourceContext) = {
    val keys = reflectPure(Densematrix_groupcolsby_helperKeys[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos))
    val index = reflectPure(Densematrix_groupcolsby_helperIndex[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos))
    val values = reflectPure(Densematrix_groupcolsby_helper[T,K,V](__arg0,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],implicitly[Manifest[K]],implicitly[Manifest[V]],__pos))
    reflectPure(DeliteMapNewImm(keys, values, index, darray_length(values)))
  }
  def densematrix_object_zeros(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_zeros_impl2(__arg0,__arg1)(__pos)
  }
  def densematrix_object_zerosf(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_zerosf_impl2(__arg0,__arg1)(__pos)
  }
  def densematrix_object_ones(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_ones_impl1(__arg0,__arg1)(__pos)
  }
  def densematrix_object_onesf(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_onesf_impl1(__arg0,__arg1)(__pos)
  }
  def densematrix_object_rand(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_rand_impl2(__arg0,__arg1)(__pos)
  }
  def densematrix_object_randf(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_randf_impl2(__arg0,__arg1)(__pos)
  }
  def densematrix_object_randn(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_randn_impl1(__arg0,__arg1)(__pos)
  }
  def densematrix_object_randnf(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_object_randnf_impl1(__arg0,__arg1)(__pos)
  }
  def densematrix_toboolean[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Boolean]) = {
    reflectPure(DenseMatrix4_ToBoolean[T](self)(implicitly[Manifest[T]],__pos,conv))
  }
  def densematrix_todouble[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Double]) = {
    reflectPure(DenseMatrix5_ToDouble[T](self)(implicitly[Manifest[T]],__pos,conv))
  }
  def densematrix_tofloat[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Float]) = {
    reflectPure(DenseMatrix5_ToFloat[T](self)(implicitly[Manifest[T]],__pos,conv))
  }
  def densematrix_toint[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Int]) = {
    reflectPure(DenseMatrix5_ToInt[T](self)(implicitly[Manifest[T]],__pos,conv))
  }
  def densematrix_flattentovector[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_flattentovector_impl[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_numrows[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    field[Int](self,"_numRows")
  }
  def densematrix_numcols[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    field[Int](self,"_numCols")
  }
  def densematrix_size[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_size_impl13[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_index[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_index_impl[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_apply[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext,__imp1: Overload21) = {
    densematrix_apply_impl21[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_apply[T:Manifest](self: Rep[DenseMatrix[T]],rows: Rep[IndexVector],cols: Rep[IndexVector])(implicit __pos: SourceContext,__imp1: Overload25) = {
    densematrix_apply_impl25[T](self,rows,cols)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_indices[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_indices_impl5[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_rowindices[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_rowindices_impl1[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_colindices[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_colindices_impl1[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_vview[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int],__arg3: Rep[Int],__arg4: Rep[Boolean])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrix_Vview[T](self,__arg1,__arg2,__arg3,__arg4)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_getrow[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_getrow_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_getrows[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[IndexVector])(implicit __pos: SourceContext) = {
    densematrix_getrows_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_getcol[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_getcol_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_getcols[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[IndexVector])(implicit __pos: SourceContext) = {
    densematrix_getcols_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_diag[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_diag_impl[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_triu[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    densematrix_triu_impl[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_tril[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    densematrix_tril_impl[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_pprint[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Stringable[T]) = {
    densematrix_pprint_impl5[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_makestring[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Stringable[T]) = {
    reflectPure(DenseMatrix5_MakeString[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_tostring[T:Manifest](self: Rep[DenseMatrix[T]]) = {
    reflectPure(DenseMatrix14_ToString[T](self))
  }
  def densematrix_t[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    densematrix_t_impl3[T](self)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_clone[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrix5_Clone[T](self)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_mutable[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    reflectMutable(DenseMatrix14_Mutable[T](self)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_replicate[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_replicate_impl3[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_raw_data[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    field[ForgeArray[T]](self,"_data")
  }
  def densematrix_set_raw_data[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[ForgeArray[T]])(implicit __pos: SourceContext) = {
    field_update[ForgeArray[T]](self,"_data",__arg1)
  }
  def densematrix_set_numrows[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    field_update[Int](self,"_numRows",__arg1)
  }
  def densematrix_set_numcols[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    field_update[Int](self,"_numCols",__arg1)
  }
  def densematrix_update[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int],__arg3: Rep[T])(implicit __pos: SourceContext,__imp1: Overload7) = {
    densematrix_update_impl7[T](self,__arg1,__arg2,__arg3)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_update[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload8) = {
    densematrix_update_impl8[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_updaterow[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload1) = {
    densematrix_updaterow_impl1[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_updatecol[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload1) = {
    densematrix_updatecol_impl1[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_update[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVectorView[T]])(implicit __pos: SourceContext,__imp1: Overload9) = {
    densematrix_update_impl9[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_updaterow[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVectorView[T]])(implicit __pos: SourceContext,__imp1: Overload2) = {
    densematrix_updaterow_impl2[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_updatecol[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVectorView[T]])(implicit __pos: SourceContext,__imp1: Overload2) = {
    densematrix_updatecol_impl2[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltlt[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload5) = {
    densematrix_ltlt_impl5[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltlt[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp1: Overload6) = {
    densematrix_ltlt_impl6[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltltor[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload1) = {
    densematrix_ltltor_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltltor[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp1: Overload2) = {
    densematrix_ltltor_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltlteq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload4) = {
    densematrix_ltlteq_impl4[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltlteq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp1: Overload5) = {
    densematrix_ltlteq_impl5[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltltoreq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp1: Overload2) = {
    densematrix_ltltoreq_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_ltltoreq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp1: Overload3) = {
    densematrix_ltltoreq_impl3[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_insertrow[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],y: Rep[DenseVector[T]])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix2_InsertRow[T](self,pos,y)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_insertallrows[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],xs: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix_InsertAllRows[T](self,pos,xs)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_insertcol[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],y: Rep[DenseVector[T]])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix2_InsertCol[T](self,pos,y)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_insertallcols[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],xs: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix_InsertAllCols[T](self,pos,xs)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_trim[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix3_Trim[T](self)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_removerow[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_removerow_impl2[T](self,pos)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_removecol[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_removecol_impl2[T](self,pos)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_removerows[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],num: Rep[Int])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix2_RemoveRows[T](self,pos,num)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_removecols[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],num: Rep[Int])(implicit __pos: SourceContext) = {
    reflectWrite(self)(DenseMatrix2_RemoveCols[T](self,pos,num)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_insertspace[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],len: Rep[Int])(implicit __pos: SourceContext) = {
    reflectWrite(self)(Densematrix_insertspace[T](self,pos,len)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_ensureextra[T:Manifest](self: Rep[DenseMatrix[T]],extra: Rep[Int])(implicit __pos: SourceContext) = {
    reflectWrite(self)(Densematrix_ensureextra[T](self,extra)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_realloc[T:Manifest](self: Rep[DenseMatrix[T]],minLen: Rep[Int])(implicit __pos: SourceContext) = {
    reflectWrite(self)(Densematrix_realloc[T](self,minLen)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_pl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload74) = {
    reflectPure(DenseMatrix74_Pl[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_pl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload75) = {
    reflectPure(DenseMatrix75_Pl[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_pleq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload1) = {
    densematrix_pleq_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_pleq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload2) = {
    densematrix_pleq_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_sub[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload58) = {
    reflectPure(DenseMatrix58_Sub[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_sub[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload59) = {
    reflectPure(DenseMatrix59_Sub[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_subeq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload1) = {
    densematrix_subeq_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_subeq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload2) = {
    densematrix_subeq_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_mulclnmul[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload11) = {
    reflectPure(DenseMatrix11_Mulclnmul[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_mul[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload61) = {
    reflectPure(DenseMatrix61_Mul[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_muleq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload1) = {
    densematrix_muleq_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_muleq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload2) = {
    densematrix_muleq_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }

  def runThis(cmd: scala.Array[String]): String = {
    val p = Runtime.getRuntime.exec(cmd)
    p.waitFor()
    val inputstream = p.getInputStream()
    val out = new StringBuilder
    var in = inputstream.read()
    while (in != -1) {
      out.append(in.asInstanceOf[Char])
      in = inputstream.read()
    }
    out.append("\n")
    out.toString()
  }



  def densematrix_matmult[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    if (Config.autotuneEnabled) {
      // Code generator initialization
      val genFolderName="autotuneCode"
      val kernelFileName="auto_kernelcode.cpp"
      val driverFileName="auto_driver.cpp"
      val initFileName="auto_init.cpp"
      val codegen = new OptiMLCodegenC{val IR: DenseMatrixOpsExp.this.type = DenseMatrixOpsExp.this} 
      codegen.headerStream = new PrintWriter(new FileWriter("%s/avoidNullptrException.h".format(genFolderName)))

      // Clear all the previous runs
      val output = runThis(scala.Array("rm", "-r", "%s/a_*".format(genFolderName)))
      Console.println("[rm -rf output] %s".format(output))
      var executableNum = -1
      def matrixMult(sizes: scala.List[scala.Int])(tunables: Tunable): Double = {
        // Create IR nodes to perform m3 = m1 x m2 for the given tunables 
        val folderName = genFolderName + "/" + "a_" + tunables.mkString("_")
        val outDir = new File(folderName)
        outDir.mkdirs()
        val M = fresh[Int]
        val P = fresh[Int]
        val N = fresh[Int]
        val m1_fresh = fresh[DenseMatrix[T]]
        val m2_fresh = fresh[DenseMatrix[T]]
        val out_fresh = fresh[DenseMatrix[T]]

        val symNumber = fresh[Int]
        Console.println("Every symbol after %s must be purged in each autotune iteration".format(symNumber))


        val m1 = reflectPure(Densematrix_new[T](M, P)(implicitly[Manifest[T]],__pos,__imp0))
        val m2 = reflectPure(Densematrix_new[T](P, N)(implicitly[Manifest[T]],__pos,__imp0))
        val out = reflectPure(Densematrix_new[T](M, N)(implicitly[Manifest[T]],__pos,__imp0))
        val lhs = reflectPure(Densematrix_matmult_autotune[T](m1_fresh, m2_fresh, out_fresh, tunables)(implicitly[Manifest[T]],__pos,__imp0))


        // There must be a better way to do this because:
        // 1. Basic functionality - find the definition, given a symbol. The 'rhs' operator isn't working
        // 2. Repeated again near the 'generateDriver' function, defined currently in OptiML.scala
        def getIRNode(sym: Sym[Any]) = {
          val stm = findDefinition(sym).getOrElse {
            throw new Exception("No Def IR node found for Sym: %s".format(sym))
          }
          val irnode = stm match {
          case TP(lhs: Sym[Any], rhs: Def[Any]) => 
            rhs match {
              case Reflect(x: Def[Any], _, _) => x
              case _ => rhs
            }
          }
          irnode
        }

        val m1_sym = m1.asInstanceOf[Sym[Any]]
        val m2_sym = m2.asInstanceOf[Sym[Any]]
        val out_sym = out.asInstanceOf[Sym[Any]]
        val lhs_sym = lhs.asInstanceOf[Sym[Any]]
        val irnode = getIRNode(lhs_sym)
        val irnode_m1 = getIRNode(m1_sym)
        val irnode_m2 = getIRNode(m2_sym)
        val irnode_out = getIRNode(out_sym)

        val resType = codegen.remap(lhs.tp)

        // Generate other initialization kernels
        // IMPORTANT: This block of code MUST execute before the following block that generates main kernel code.
        // This is because each DeliteOpSingleTask is only codegen'ed once in autotune mode (See CGenDeliteOps::emitNode).
        // Executing this first makes sure that the init file is generated correctly.
        val stream_init = new PrintWriter(new FileWriter("%s/%s".format(folderName, initFileName)))
        codegen.withStream(stream_init) {
          codegen.emitKernelHeader(List(m1_sym), List(M, P), List(), resType, false, false)
          codegen.emitNode(m1_sym, irnode_m1)
          codegen.emitKernelFooter(List(m1_sym), List(M, P), List(), resType, false, false)
          codegen.emitKernelHeader(List(m2_sym), List(P, N), List(), resType, false, false)
          codegen.emitNode(m2_sym, irnode_m2)
          codegen.emitKernelFooter(List(m2_sym), List(P, N), List(), resType, false, false)
          codegen.emitKernelHeader(List(out_sym), List(M, N), List(), resType, false, false)
          codegen.emitNode(out_sym, irnode_out)
          codegen.emitKernelFooter(List(out_sym), List(M, N), List(), resType, false, false)
        }

        // Generate kernel code
        val stream = new PrintWriter(new FileWriter("%s/%s".format(folderName, kernelFileName)))
        codegen.withStream(stream) {
          codegen.emitKernelHeader(List(lhs_sym), List(m1_fresh, m2_fresh, out_fresh), List(), resType, false, false)
          codegen.emitNode(lhs_sym, irnode)
          codegen.emitKernelFooter(List(lhs_sym), List(m1_fresh, m2_fresh, out_fresh), List(), resType, false, false)
        }

        // Generate driver code
        val stream_driver = new PrintWriter(new FileWriter("%s/%s".format(folderName, driverFileName)))
        codegen.generateDriver(lhs_sym, irnode, stream_driver, List(initFileName, kernelFileName), List(m1_sym, m2_sym, out_sym))
        stream_driver.flush

        // Generate Makefile code
        val stream_makefile = new PrintWriter(new FileWriter("%s/Makefile".format(folderName)))
        executableNum += 1
        val makefileStr="""
all: clean
	ln -sf ../cpphelperFuncs.h . 
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ %s 
	sed -i.mybak s/new\ \(resourceInfo.thread_id\)/new/ %s 
	g++ -O3 %s -o %s.%d.out

clean:
	rm -f *.out *.mybak
        """.format(kernelFileName, initFileName, driverFileName, driverFileName, executableNum)
        stream_makefile.println(makefileStr)
        stream_makefile.flush

        // Compile the native code
        val nativeCompileCmd = scala.Array("make", "-C", folderName)
        val nativeCompileOut = runThis(nativeCompileCmd)
//        Console.println("[make] %s".format(nativeCompileOut))

        // Run the native code
        val arg0 = sizes(0).toString
        val arg1 = sizes(1).toString
        val arg2 = sizes(2).toString
        val nativeRunCmd = scala.Array("%s/%s.%d.out".format(folderName, driverFileName, executableNum), arg0, arg1, arg2)
//        Console.println("[run] %s".format(nativeRunCmd))
        val nativeRunOut = runThis(nativeRunCmd)
//        Console.println("[run] %s".format(nativeRunOut))

        // reset symbol table
        purgeSymFromAll(symNumber)

        nativeRunOut.toDouble
      }
      
      // The autotuner
      def autotune2(f: scala.List[scala.Int] => Tunable => scala.Double)
                                     (input: scala.List[scala.Int])
                                     (tunable: Tunable): Tunable = {
        
        // Some autotuning constants
        val numGenerations = 50
        val populationSize = 10
        val invalidScore = scala.Double.MaxValue

        // Population of tunables: map maintains score of each tunable
        var population = scala.collection.immutable.Map[Tunable,scala.Double]()
        var populationCache = scala.collection.immutable.Set[Tunable]()

        // Initialize population with random 'tunable' parameters
        for (i <- 0 until populationSize) {
          var t = tunable.getnew
          while (populationCache contains t) {
            t = tunable.getnew
          }
          populationCache += t
          population += (t -> invalidScore)
        }
        
        Console.println("Initial population = %s".format(population))

        // Genetic search for 'numGenerations' generations
        for (gen <- 0 until numGenerations) {
          Console.println("Generation %d".format(gen))

          // Profile run each of the tunables in population
//          Console.println("population.keySet.size = %d".format(population.keySet.size))
          for (t: Tunable <- population.keySet) {
            Console.println(t.toString)
            val elapsed = f(input)(t)
            Console.println(elapsed)
            population += (t -> elapsed)
          }

          // Get sorted list of tunables by rank
//          val popList = population.toList
//          Console.println("popList")
//          Console.println(popList)
//          val sortedTupleList  = popList.sortBy(x => (x._2, x._1))
//          val sortedTunables: scala.List[Tunable[Int]] = sortedTupleList map { x => x._1 }
//          val scoreList: scala.List[Double] = population.values.toList.sorted
          // Using 'sortBy' is blowing up in my face, so doing this manually
//          var sortedTunables = List[Tunable]()
//          for (s: Double <- scoreList) {
//             for (k: Tunable <- population.keySet) {
//               if (population(k) == s) {
//                 if (!sortedTunables.contains(k)) {
//                   sortedTunables :+= k 
//                 }
//               }
//            }
//          }
//

          val popList: scala.List[(Tunable, scala.Double)] = population.toList
          val sortedTupleList: scala.List[(Tunable, scala.Double)] = popList sortBy { _._2 }
          Console.println("sortedTupleList = %s".format(sortedTupleList))
          val sortedTunables: scala.List[Tunable] = sortedTupleList map { x => x._1 }
          if (sortedTunables.length != population.keySet.size) {
            Console.println("sortedTunables.length (%d) not equal to population.keySet.size (%d) !".format(sortedTunables.length, population.keySet.size))
            Console.println("population.keySet:")
            Console.println(population.keySet)
            Console.println("sortedTunables:")
            Console.println(sortedTunables)
          }

          // Time for deletions - keep only the top half
          val bestList: scala.List[Tunable] = sortedTunables take (populationSize/2)
          val badTunables: scala.List[Tunable] = sortedTunables diff bestList

          Console.println("population.keySet.size = %d".format(population.keySet.size))
          Console.println("bestList.length = %d".format(bestList.length))
          Console.println("badTunables.length = %d".format(badTunables.length))
          Console.println("population.keySet.size before removing %d tunables = %d".format(badTunables.length, population.keySet.size))
          Console.println("Removing %d tunables from population".format(badTunables.length))
          for (bad: Tunable <- badTunables) {
            Console.println("Removing %s".format(bad))
            val before = population.keySet.size
            population -= bad
            val after = population.keySet.size
            if (after == before) {
              Console.println("Could not remove %s!".format(bad))
              Console.println("hashcode = %d".format(bad.hashCode))
              Console.println("**** DUMPING POPULATION ***")
              for (k: Tunable <- population.keySet) {
                Console.println("k: %s".format(k))
                Console.println("hashcode: %d".format(k.hashCode))
                if (k != bad) {
                  Console.println("  is not the thing that's to be removed")
                }
                else {
                  population -= k
                }
              }
              Console.println("**** END DUMP ***")
            }
          }

          Console.println("population.keySet.size after removing %d tunables = %d".format(badTunables.length, population.keySet.size))
          Console.println(population)

          // We need to make up new members of population
          val numNew: scala.Int = populationSize - population.keySet.size
          val numMutation: scala.Int = numNew/2 
//          val numCrossovers: scala.Int = 1 
          val numRandomNew: scala.Int = numNew - numMutation //  - numCrossovers

          Console.println("Creating %d new tunables".format(numRandomNew))
          Console.println("Creating %d mutations".format(numMutation))
          Console.println("Totally we will have %d new tunables".format(numNew))
          // Crossovers - ignoring for now
//          val crossoverList: scala.List[Tunable] = (for (i <- 0 to numCrossovers-1) yield {
//              val t1 = bestList(positiveRand % bestList.length)
//              val t2 = bestList(positiveRand % bestList.length)  // TODO: Could be the same as t1 !
//              var res = t1 crossOver t2
//
//              while (populationCache contains res) {
//                val t1 = bestList(positiveRand % bestList.length)
//                val t2 = bestList(positiveRand % bestList.length)  // TODO: Could be the same as t1 !
//                res = t1 crossOver t2
//              }
//              populationCache += res
//              res
//          }).toList
//          Console.println("Number of crossvers required: %d".format(numCrossovers))
//          Console.println("crossoverList.length = %d".format(crossoverList.length))

          // Mutations - restricting to upper half of bestList tunables
          Console.println("Best list before mutation:")
          Console.println(bestList)
          val mutationList: scala.List[Tunable] = (for (i <- 0 to numMutation-1) yield {
            val t1: Tunable = bestList(positiveRand % bestList.length/2)
            val t1_clone: Tunable = t1.deepCopy
            var res: Tunable = t1_clone.mutate
            
            while (populationCache contains res) {
              val t1 = bestList(positiveRand % bestList.length/2)
              val t1_clone = t1.deepCopy
              res = t1_clone.mutate
            }
            if (!res.validateTunables) {
              Console.println("Invalid tunable generated after mutation, should abort!")
              throw new Exception("stop here")
            }
            populationCache += res
            res
          }).toList
          Console.println("Best list after mutation:")
          Console.println(bestList)

          // - completely random tunables
          val newList: scala.List[Tunable] = (for (i <- 0 to numRandomNew-1) yield {
            var res: Tunable = tunable.getnew
            while (populationCache contains res) {
              res = tunable.getnew
            }
            populationCache += res
            res
          }).toList

          
          // Add all the new guys into population
          val newMembers: scala.List[Tunable] =  mutationList ::: newList

          Console.println("Number of new members = %d".format(newMembers.length))

          for (t: Tunable <- newMembers) {
//              Console.println("Adding %s".format(t))
              val before = population.keySet.size
              population += (t -> invalidScore)
              val after = population.keySet.size
              if (after == before) {
                Console.println("Could not add %s!".format(t))
                Console.println("hashcode = %d".format(t.hashCode))
                Console.println("**** DUMPING POPULATION ***")
                for (k: Tunable <- population.keySet) {
                  Console.println("k: %s".format(k))
                  Console.println("hashcode: %d".format(k.hashCode))
                  if (k != t) {
                    Console.println("  is not the thing that's to be removed")
                  }
                }
                Console.println("**** END DUMP ***")
              }

          }

          Console.println("population size before next gen = %d".format(population.keySet.size))
//          Console.println("\tBest so far: %s -> %f".format(bestList(0), population(bestList(0))))

        }  // End for loop on generations

        // Get sorted list of tunables by rank
        val popList = population.toList
        val sortedTupleList = popList sortBy { _._2 }
        val sortedTunables = sortedTupleList map { x => x._1 }
        Console.println("Best tunables order: %s".format(sortedTupleList.toString))
        sortedTunables(0)
      }

      // Get the set of tunable parameters
      def factors(n: scala.Int) = {
        (1 to scala.math.sqrt(n).toInt) filter (n % _ == 0) flatMap { x => List(x,n/x) } sorted
      }
    
      def getBlockSizes(M: scala.Int) = {
        (for (i <- factors(M)) yield i).toList
      } 

      // Tunables order:
      // (numLevels, bm, bn, bp, um, un, up, bm1, bn1, bp1, um1, un1, up1, ...)
// 			def getTunablesFromMatSizes(M: Int, P: Int, N: Int) = {
//        val numLevelRange = 0 to 2 toList
// 				val bmRange = getBlockSizes(M)
// 				val bpRange = getBlockSizes(P)
// 				val bnRange = getBlockSizes(N)
//        val umRange = scala.List(1)
//        val unRange = scala.List(1)
//        val upRange = scala.List(1)
// 				new Tunable (List(numLevelRange(0), bmRange(4), bnRange(4), bpRange(4), umRange(0), unRange(0), upRange(0), bmRange(2), bnRange(2), bpRange(2), umRange(0), unRange(0), upRange(0), bmRange(0), bnRange(0), bpRange(0), umRange(0), unRange(0), upRange(0)), List(numLevelRange, bmRange, bnRange, bpRange, umRange, unRange, upRange, bmRange, bnRange, bpRange, umRange, unRange, upRange, bmRange, bnRange, bpRange, umRange, unRange, upRange))
// 			}

      val mdim = 512
      val pdim = 512
      val ndim = 512
      val numLevels = 2
      val tunable = new Tunable(mdim, ndim, pdim, numLevels) 
      val sizes = List(mdim, pdim, ndim) 
//      val tunable = getTunablesFromMatSizes(mdim, pdim, ndim, maxLevels)
//      val tunable = getTunablesFromMatSizes(mdim, pdim, ndim, maxLevels)
      val bestTunable = autotune2(matrixMult)(sizes)(tunable)
//      val time = matrixMult(sizes)(tunable)
//      throw new Exception("stop here")
      
      val out = reflectPure(Densematrix_new[T](self.numRows, __arg1.numCols)(implicitly[Manifest[T]],__pos,__imp0))
      reflectPure(Densematrix_matmult_autotune[T](self, __arg1, out, bestTunable)(implicitly[Manifest[T]],__pos,__imp0))
    }
    else {
        reflectPure(Densematrix_matmult[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
    }
  }
  def densematrix_matvecmult[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    reflectPure(Densematrix_matvecmult[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_div[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload59) = {
    reflectPure(DenseMatrix59_Div[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_div[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload60) = {
    reflectPure(DenseMatrix60_Div[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_diveq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload1) = {
    densematrix_diveq_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_diveq[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload2) = {
    densematrix_diveq_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_pl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload76) = {
    densematrix_pl_impl76[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_sub[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload60) = {
    densematrix_sub_impl60[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_mulclnmul[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload12) = {
    densematrix_mulclnmul_impl12[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_div[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T],__imp2: Overload61) = {
    densematrix_div_impl61[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_sum[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    reflectPure(DenseMatrix4_Sum[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_prod[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    reflectPure(DenseMatrix3_Prod[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_mean[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Double]) = {
    densematrix_mean_impl4[T](self)(implicitly[Manifest[T]],__pos,conv)
  }
  def densematrix_abs[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    reflectPure(DenseMatrix20_Abs[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_exp[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    reflectPure(DenseMatrix19_Exp[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_log[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    reflectPure(DenseMatrix19_Log[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_sumrows[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    densematrix_sumrows_impl1[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_sumcols[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    densematrix_sumcols_impl1[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_minrows[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    densematrix_minrows_impl1[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_mincols[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    densematrix_mincols_impl1[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_maxrows[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    densematrix_maxrows_impl1[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_maxcols[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    densematrix_maxcols_impl1[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_min[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    reflectPure(DenseMatrix5_Min[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_max[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    reflectPure(DenseMatrix5_Max[T](self)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_minindex[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    densematrix_minindex_impl3[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_maxindex[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    densematrix_maxindex_impl3[T](self)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_clngt[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    reflectPure(DenseMatrix1_Clngt[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix_clnlt[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]) = {
    reflectPure(DenseMatrix1_Clnlt[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0))
  }
  def densematrix___equal[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp1: Overload10) = {
    densematrix___equal_impl10[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix___equal[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp1: Overload11) = {
    densematrix___equal_impl11[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_map[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[R])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrix3_Map[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos))
  }
  def densematrix_maprowstovector[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[R])(implicit __pos: SourceContext) = {
    densematrix_maprowstovector_impl1[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos)
  }
  def densematrix_mapcolstovector[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[R])(implicit __pos: SourceContext) = {
    densematrix_mapcolstovector_impl1[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos)
  }
  def densematrix_zip[T:Manifest,B:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[B]],__arg2: (Rep[T],Rep[B]) => Rep[R])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrix5_Zip[T,B,R](self,__arg1,__arg2)(implicitly[Manifest[T]],implicitly[Manifest[B]],implicitly[Manifest[R]],__pos))
  }
  def densematrix_foreach[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Unit])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrix4_Foreach[T](self,__arg1)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_count[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Boolean])(implicit __pos: SourceContext) = {
    reflectPure(DenseMatrix3_Count[T](self,__arg1)(implicitly[Manifest[T]],__pos))
  }
  def densematrix_findrows[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext) = {
    densematrix_findrows_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_findcols[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext) = {
    densematrix_findcols_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_filterrows[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext) = {
    densematrix_filterrows_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_filtercols[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext) = {
    densematrix_filtercols_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_foreachrow[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Unit])(implicit __pos: SourceContext) = {
    densematrix_foreachrow_impl2[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_foreachcol[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Unit])(implicit __pos: SourceContext) = {
    densematrix_foreachcol_impl1[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_maprows[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[DenseVector[R]])(implicit __pos: SourceContext) = {
    densematrix_maprows_impl[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos)
  }
  def densematrix_mapcols[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[DenseVector[R]])(implicit __pos: SourceContext) = {
    densematrix_mapcols_impl[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos)
  }
  def densematrix_reducerows[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVector[T]],Rep[DenseVector[T]]) => Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    densematrix_reducerows_impl[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_reducecols[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVector[T]],Rep[DenseVector[T]]) => Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp0: Arith[T]) = {
    densematrix_reducecols_impl[T](self,__arg1)(implicitly[Manifest[T]],__pos,__imp0)
  }
  def densematrix_grouprowsby[T:Manifest,K:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[K])(implicit __pos: SourceContext) = {
    densematrix_grouprowsby_impl[T,K](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[K]],__pos)
  }
  def densematrix_groupcolsby[T:Manifest,K:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[K])(implicit __pos: SourceContext) = {
    densematrix_groupcolsby_impl[T,K](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[K]],__pos)
  }
  def densematrix_raw_alloc[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_raw_alloc_impl[T,R](self,__arg1)(implicitly[Manifest[T]],implicitly[Manifest[R]],__pos)
  }
  def densematrix_densematrix_raw_apply[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    densematrix_densematrix_raw_apply_impl[T](self,__arg1)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_densematrix_raw_update[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[T])(implicit __pos: SourceContext) = {
    densematrix_densematrix_raw_update_impl[T](self,__arg1,__arg2)(implicitly[Manifest[T]],__pos)
  }
  def densematrix_dist(__arg0: Rep[DenseMatrix[Double]],__arg1: Rep[DenseMatrix[Double]],__arg2: DistanceMetric)(implicit __pos: SourceContext) = {
    densematrix_dist_impl3(__arg0,__arg1,__arg2)(__pos)
  }

  /**
   * Syms
   */
  override def syms(e: Any): List[Sym[Any]] = e match {
    case _ => super.syms(e)
  }
  override def boundSyms(e: Any): List[Sym[Any]] = e match {
    case _ => super.boundSyms(e)
  }
  override def symsFreq(e: Any): List[(Sym[Any], Double)] = e match {
    case _ => super.symsFreq(e)
  }

  /**
   * Aliases / Sharing
   */
  override def aliasSyms(e: Any): List[Sym[Any]] = e match {
    case DenseMatrix5_Clone(self) => Nil
    case DenseMatrix14_Mutable(self) => Nil
    case _ => super.aliasSyms(e)
  }
  override def containSyms(e: Any): List[Sym[Any]] = e match {
    case DenseMatrix_Vview(self,__arg1,__arg2,__arg3,__arg4) => syms(self)
    case DenseMatrix5_Clone(self) => Nil
    case DenseMatrix14_Mutable(self) => Nil
    case _ => super.containSyms(e)
  }
  override def extractSyms(e: Any): List[Sym[Any]] = e match {
    case DenseMatrix5_Clone(self) => Nil
    case DenseMatrix14_Mutable(self) => Nil
    case _ => super.extractSyms(e)
  }
  override def copySyms(e: Any): List[Sym[Any]] = e match {
    case DenseMatrix5_Clone(self) => syms(self)
    case DenseMatrix14_Mutable(self) => syms(self)
    case _ => super.copySyms(e)
  }

  /**
   * Mirroring
   */
  override def mirror[A:Manifest](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = (e match {
    case mn@DenseMatrix20Object_Apply(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix20Object_Apply(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix20Object_Apply(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix20Object_Apply(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix23Object_Apply(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix23Object_Apply(f(__arg0))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix23Object_Apply(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix23Object_Apply(f(__arg0))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrixObject_Block(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrixObject_Block(f(__arg0))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrixObject_Block(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrixObject_Block(f(__arg0))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_fromarray(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_fromarray(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_fromarray(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_fromarray(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_grouprowsby_helperKeys(__arg0,__arg1,__arg2,__arg3) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_grouprowsby_helperKeys(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_grouprowsby_helperKeys(__arg0,__arg1,__arg2,__arg3), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_grouprowsby_helperKeys(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_grouprowsby_helperIndex(__arg0,__arg1,__arg2,__arg3) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_grouprowsby_helperIndex(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_grouprowsby_helperIndex(__arg0,__arg1,__arg2,__arg3), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_grouprowsby_helperIndex(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_grouprowsby_helper(__arg0,__arg1,__arg2,__arg3) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_grouprowsby_helper(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_grouprowsby_helper(__arg0,__arg1,__arg2,__arg3), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_grouprowsby_helper(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_groupcolsby_helperKeys(__arg0,__arg1,__arg2,__arg3) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_groupcolsby_helperKeys(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_groupcolsby_helperKeys(__arg0,__arg1,__arg2,__arg3), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_groupcolsby_helperKeys(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_groupcolsby_helperIndex(__arg0,__arg1,__arg2,__arg3) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_groupcolsby_helperIndex(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_groupcolsby_helperIndex(__arg0,__arg1,__arg2,__arg3), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_groupcolsby_helperIndex(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_groupcolsby_helper(__arg0,__arg1,__arg2,__arg3) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_groupcolsby_helper(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_groupcolsby_helper(__arg0,__arg1,__arg2,__arg3), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_groupcolsby_helper(f(__arg0),f(__arg1),f(__arg2),f(__arg3))(mtype(mn._mT),mtype(mn._mK),mtype(mn._mV),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix4_ToBoolean(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix4_ToBoolean(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix4_ToBoolean(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix4_ToBoolean(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_ToDouble(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_ToDouble(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_ToDouble(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_ToDouble(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_ToFloat(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_ToFloat(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_ToFloat(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_ToFloat(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_ToInt(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_ToInt(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_ToInt(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_ToInt(f(__arg0))(mtype(mn._mT),mn.__pos,mn.conv), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix_Vview(__arg0,__arg1,__arg2,__arg3,__arg4) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix_Vview(f(__arg0),f(__arg1),f(__arg2),f(__arg3),f(__arg4))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix_Vview(__arg0,__arg1,__arg2,__arg3,__arg4), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix_Vview(f(__arg0),f(__arg1),f(__arg2),f(__arg3),f(__arg4))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_MakeString(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_MakeString(f(__arg0))(mtype(mn._mT),mn.__pos,strtype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_MakeString(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_MakeString(f(__arg0))(mtype(mn._mT),mn.__pos,strtype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix14_ToString(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix14_ToString(f(__arg0))(mtype(mn._mT)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix14_ToString(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix14_ToString(f(__arg0))(mtype(mn._mT)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_Clone(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_Clone(f(__arg0))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_Clone(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_Clone(f(__arg0))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix14_Mutable(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix14_Mutable(f(__arg0))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix14_Mutable(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix14_Mutable(f(__arg0))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix2_InsertRow(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix2_InsertRow(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix2_InsertRow(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix2_InsertRow(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix_InsertAllRows(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix_InsertAllRows(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix_InsertAllRows(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix_InsertAllRows(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix2_InsertCol(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix2_InsertCol(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix2_InsertCol(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix2_InsertCol(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix_InsertAllCols(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix_InsertAllCols(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix_InsertAllCols(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix_InsertAllCols(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix3_Trim(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix3_Trim(f(__arg0))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix3_Trim(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix3_Trim(f(__arg0))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix2_RemoveRows(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix2_RemoveRows(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix2_RemoveRows(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix2_RemoveRows(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix2_RemoveCols(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix2_RemoveCols(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix2_RemoveCols(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix2_RemoveCols(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_insertspace(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_insertspace(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_insertspace(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_insertspace(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_ensureextra(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_ensureextra(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_ensureextra(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_ensureextra(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_realloc(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_realloc(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_realloc(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_realloc(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix74_Pl(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix74_Pl(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix74_Pl(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix74_Pl(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix75_Pl(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix75_Pl(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix75_Pl(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix75_Pl(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix58_Sub(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix58_Sub(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix58_Sub(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix58_Sub(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix59_Sub(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix59_Sub(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix59_Sub(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix59_Sub(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix11_Mulclnmul(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix11_Mulclnmul(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix11_Mulclnmul(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix11_Mulclnmul(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix61_Mul(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix61_Mul(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix61_Mul(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix61_Mul(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Densematrix_matmult(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_matmult(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_matmult(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_matmult(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
//      TODO: Should I add cases for Densematrix_matmult_autotune here?
    case mn@Densematrix_new(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_new(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)

    case Reflect(mn@Densematrix_new(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_new(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)

    case mn@Densematrix_matmult_autotune(__arg0,__arg1, __arg2, tunables) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_matmult_autotune(f(__arg0),f(__arg1), f(__arg2), tunables)(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_matmult_autotune(__arg0,__arg1, __arg2, tunables), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_matmult_autotune(f(__arg0),f(__arg1), f(__arg2), tunables)(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)

    case mn@Densematrix_matmult_autotune3(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_matmult_autotune3(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_matmult_autotune3(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_matmult_autotune3(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)

    case mn@Densematrix_matvecmult(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Densematrix_matvecmult(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@Densematrix_matvecmult(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Densematrix_matvecmult(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix59_Div(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix59_Div(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix59_Div(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix59_Div(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix60_Div(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix60_Div(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix60_Div(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix60_Div(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix4_Sum(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix4_Sum(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix4_Sum(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix4_Sum(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix3_Prod(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix3_Prod(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix3_Prod(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix3_Prod(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix20_Abs(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix20_Abs(f(__arg0))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix20_Abs(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix20_Abs(f(__arg0))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix19_Exp(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix19_Exp(f(__arg0))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix19_Exp(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix19_Exp(f(__arg0))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix19_Log(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix19_Log(f(__arg0))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix19_Log(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix19_Log(f(__arg0))(mtype(mn._mT),mn.__pos,atype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_Min(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_Min(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_Min(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_Min(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_Max(__arg0) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_Max(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_Max(__arg0), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_Max(f(__arg0.asInstanceOf[Rep[DenseMatrix[A]]]))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix1_Clngt(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix1_Clngt(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix1_Clngt(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix1_Clngt(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix1_Clnlt(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix1_Clnlt(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix1_Clnlt(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix1_Clnlt(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos,otype(mn.__imp0)), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix3_Map(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix3_Map(f(__arg0),f(__arg1))(mtype(mn._mT),mtype(mn._mR),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix3_Map(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix3_Map(f(__arg0),f(__arg1))(mtype(mn._mT),mtype(mn._mR),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix5_Zip(__arg0,__arg1,__arg2) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix5_Zip(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mtype(mn._mB),mtype(mn._mR),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix5_Zip(__arg0,__arg1,__arg2), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix5_Zip(f(__arg0),f(__arg1),f(__arg2))(mtype(mn._mT),mtype(mn._mB),mtype(mn._mR),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix4_Foreach(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix4_Foreach(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix4_Foreach(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix4_Foreach(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@DenseMatrix3_Count(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with DenseMatrix3_Count(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@DenseMatrix3_Count(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with DenseMatrix3_Count(f(__arg0),f(__arg1))(mtype(mn._mT),mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case _ => super.mirror(e, f)
  }).asInstanceOf[Exp[A]]

  /**
   * Delite collection
   */
  def isDenseMatrix[A](x: Exp[DeliteCollection[A]])(implicit ctx: SourceContext) = isSubtype(x.tp.erasure,classOf[DenseMatrix[A]])
  def asDenseMatrix[A](x: Exp[DeliteCollection[A]])(implicit ctx: SourceContext) = x.asInstanceOf[Exp[DenseMatrix[A]]]

  override def dc_size[A:Manifest](x: Exp[DeliteCollection[A]])(implicit ctx: SourceContext) = {
    if (isDenseMatrix(x)) densematrix_size(asDenseMatrix(x))
    else super.dc_size(x)
  }

  override def dc_apply[A:Manifest](x: Exp[DeliteCollection[A]], n: Exp[Int])(implicit ctx: SourceContext) = {
    if (isDenseMatrix(x)) densematrix_densematrix_raw_apply(asDenseMatrix(x), n).asInstanceOf[Exp[A]]
    else super.dc_apply(x,n)
  }

  override def dc_update[A:Manifest](x: Exp[DeliteCollection[A]], n: Exp[Int], y: Exp[A])(implicit ctx: SourceContext) = {
    if (isDenseMatrix(x)) densematrix_densematrix_raw_update(asDenseMatrix(x), n, y.asInstanceOf[Exp[A]])
    else super.dc_update(x,n,y)
  }


  /**
   * Delite struct
   */
  override def unapplyStructType[T:Manifest]: Option[(StructTag[T], List[(String,Manifest[_])])] = {
    val m = manifest[T]
    if (m.erasure == classOf[DenseMatrix[_]]) Some((classTag(m), collection.immutable.List(("_numRows",manifest[Int]),("_numCols",manifest[Int]),("_data",makeManifest(classOf[ForgeArray[_]], List(m.typeArguments(0)))))))
    else super.unapplyStructType(m)
  }
  override def dc_data_field[A:Manifest](x: Exp[DeliteCollection[A]]) = {
    if (isDenseMatrix(x)) "_data"
    else super.dc_data_field(x)
  }

  override def dc_size_field[A:Manifest](x: Exp[DeliteCollection[A]]) = {
    if (isDenseMatrix(x)) "None"
    else super.dc_size_field(x)
  }
}

