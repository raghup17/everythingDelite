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

/**
 * IR Definitions
 */

trait RangeOpsExp extends RangeCompilerOps with BaseFatExp with DeliteStructsExp {
  this: OptiMLExp => 

  case class Infix_until(__arg0: Rep[Int],__arg1: Rep[Int])(implicit val __pos: SourceContext) extends DeliteStruct[Range] {
    val elems = copyTransformedElems(collection.Seq(("start",__arg0),("end",__arg1)))
  }

  case class Range_foreach(start: Rep[Int],end: Rep[Int],func: Block[Unit],f_func___arg0: Rep[Int])(implicit val __pos: SourceContext) extends Def[Unit] {
  }



  def range_start(__arg0: Rep[Range])(implicit __pos: SourceContext) = {
    field[Int](__arg0,"start")
  }
  def range_end(__arg0: Rep[Range])(implicit __pos: SourceContext) = {
    field[Int](__arg0,"end")
  }
  def infix_until(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = {
    reflectPure(Infix_until(__arg0,__arg1)(__pos))
  }
  def infix_foreach(__arg0: Rep[Range],__arg1: (Rep[Int]) => Rep[Unit])(implicit __pos: SourceContext) = {
    infix_foreach_impl(__arg0,__arg1)(__pos)
  }
  def range_foreach(start: Rep[Int],end: Rep[Int],func: (Rep[Int]) => Rep[Unit])(implicit __pos: SourceContext) = {

    val f_func___arg0 = fresh[Int]
    val b_func = reifyEffects(func(f_func___arg0))
    val sb_func = summarizeEffects(b_func)
    reflectEffect(Range_foreach(start,end,b_func,f_func___arg0)(__pos), sb_func)
  }

  /**
   * Syms
   */
  override def syms(e: Any): List[Sym[Any]] = e match {
    case Range_foreach(start,end,func,f_func___arg0) => syms(start):::syms(end):::syms(func)
    case _ => super.syms(e)
  }
  override def boundSyms(e: Any): List[Sym[Any]] = e match {
    case Range_foreach(start,end,func,f_func___arg0) => effectSyms(start):::effectSyms(end):::effectSyms(func) ::: List(f_func___arg0.asInstanceOf[Sym[Any]])
    case _ => super.boundSyms(e)
  }
  override def symsFreq(e: Any): List[(Sym[Any], Double)] = e match {
    case Range_foreach(start,end,func,f_func___arg0) => freqNormal(start):::freqNormal(end):::freqNormal(func)
    case _ => super.symsFreq(e)
  }


  /**
   * Mirroring
   */
  override def mirror[A:Manifest](e: Def[A], f: Transformer)(implicit pos: SourceContext): Exp[A] = (e match {
    case mn@Infix_until(__arg0,__arg1) => reflectPure(new { override val original = Some(f,mn) } with Infix_until(f(__arg0),f(__arg1))(mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Infix_until(__arg0,__arg1), u, es) => reflectMirrored(Reflect(new { override val original = Some(f,mn) } with Infix_until(f(__arg0),f(__arg1))(mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case mn@Range_foreach(__arg0,__arg1,__arg2,f___arg2___arg0) => reflectPure(Range_foreach(f(__arg0),f(__arg1),f(__arg2),f___arg2___arg0)(mn.__pos))(mtype(manifest[A]), pos)
    case Reflect(mn@Range_foreach(__arg0,__arg1,__arg2,f___arg2___arg0), u, es) => reflectMirrored(Reflect(Range_foreach(f(__arg0),f(__arg1),f(__arg2),f___arg2___arg0)(mn.__pos), mapOver(f,u), f(es)))(mtype(manifest[A]), pos)
    case _ => super.mirror(e, f)
  }).asInstanceOf[Exp[A]]


  /**
   * Delite struct
   */
  override def unapplyStructType[T:Manifest]: Option[(StructTag[T], List[(String,Manifest[_])])] = {
    val m = manifest[T]
    if (m.erasure == classOf[Range]) Some((classTag(m), collection.immutable.List(("start",manifest[Int]),("end",manifest[Int]))))
    else super.unapplyStructType(m)
  }
}

/**
 * Code generators
 */

trait ScalaGenRangeOps extends ScalaGenFat {
  val IR: RangeOpsExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = rhs match {
    case mn@Range_foreach(start,end,func,f_func___arg0) => 
      stream.println("val "+quote(sym)+" = {")
      stream.print("var i = "+quote(start)+"\nwhile (i < "+quote(end)+") {\n  { ")
      emitValDef(f_func___arg0.asInstanceOf[Sym[Any]],"i")
      emitBlock(func)
      stream.print( " }\n \n  i += 1\n}")
      stream.println("}")

    case _ => super.emitNode(sym, rhs)
  }
}
trait CGenRangeOps extends CGenFat {
  val IR: RangeOpsExp
  import IR._

  override def emitNode(sym: Sym[Any], rhs: Def[Any]) = rhs match {
    case mn@Range_foreach(start,end,func,f_func___arg0) => 
      stream.print("for(int i="+quote(start)+" ; i<"+quote(end)+" ; i++) {\n  { ")
      emitValDef(f_func___arg0.asInstanceOf[Sym[Any]],"i")
      emitBlock(func)
      stream.print( " }\n \n}")
      stream.println(";")

    case _ => super.emitNode(sym, rhs)
  }
}
