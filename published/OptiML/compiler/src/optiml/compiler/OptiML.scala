package optiml.compiler

import java.io.{BufferedWriter, FileWriter, PrintWriter}
import scala.reflect.{Manifest,SourceContext}
import scala.virtualization.lms.common.{Base,BaseExp,EffectExp,BaseFatExp}
import scala.virtualization.lms.common.{ScalaGenBase,ScalaGenEffect,ScalaGenFat,CudaGenFat,CGenFat}
import scala.virtualization.lms.util._
import scala.virtualization.lms.internal._
import optiml.shared._
import optiml.shared.ops._
import optiml.shared.typeclass._
import optiml.compiler._
import optiml.compiler.ops._
import scala.tools.nsc.io._
import scala.virtualization.lms.common.{Base,BaseExp,EffectExp,BaseFatExp}
import scala.virtualization.lms.common.{ScalaGenBase,ScalaGenEffect,ScalaGenFat,CudaGenFat,CGenFat}
import scala.virtualization.lms.util._
import scala.virtualization.lms.internal._
import ppl.delite.framework.{Config, DeliteApplication, ExpressionsOpt}
import ppl.delite.framework.codegen.Target
import ppl.delite.framework.codegen.scala.TargetScala
import ppl.delite.framework.codegen.cuda.TargetCuda
import ppl.delite.framework.codegen.cpp.TargetCpp
import ppl.delite.framework.codegen.opencl.TargetOpenCL
import ppl.delite.framework.ops._
import ppl.delite.framework.datastructures._
import ppl.delite.framework.codegen.delite.overrides._
import ppl.delite.framework.transform._
import ppl.delite.framework.ops.DeliteCollection
import ppl.delite.framework.datastructures._
import ppl.delite.framework.ops.{DeliteOpsExp, DeliteCollectionOpsExp}
import ppl.delite.framework.Util._
import ppl.delite.framework.codegen.restage.{DeliteCodeGenRestage,TargetRestage}
import ppl.delite.framework.{DeliteInteractive, DeliteInteractiveRunner, DeliteRestageOps, DeliteRestageOpsExp, DeliteRestageRunner}
import ppl.tests.scalatest._


import scala.collection.mutable.ListBuffer

trait OptiMLApplicationCompiler extends OptiMLApplication with DeliteApplication with OptiMLExp

/**
 * dsl compiler definition
 */
trait OptiMLCompiler extends OptiML
 with TableFactorOpsImpl with SparseVectorViewOpsImpl with SparseVectorViewCompilerOps with Tup2OpsImpl with Tup2CompilerOps with ComplexOpsImpl with ContinuousFeatureOpsImpl with Tup9OpsImpl with Tup9CompilerOps with Tup8OpsImpl with Tup8CompilerOps with DiscreteFeatureOpsImpl with DiscreteFeatureCompilerOps with Primitive2OpsImpl with CHashMapOpsImpl with CHashMapCompilerOps with IndexVectorOpsImpl with IndexVectorCompilerOps with Tup7OpsImpl with Tup7CompilerOps with BinaryFeatureOpsImpl with FactorGraphOpsImpl with FactorGraphCompilerOps with SparseVectorOpsImpl with SparseVectorCompilerOps with UTriangleOpsImpl with UTriangleCompilerOps with RandOpsImpl with RandCompilerOps with Tup6OpsImpl with Tup6CompilerOps with MLioOpsImpl with MLioCompilerOps with FunctionFactorOpsImpl with FunctionFactorCompilerOps with ComputeStreamOpsImpl with ComputeStreamCompilerOps with FStringOpsImpl with FStringCompilerOps with SHashMapOpsImpl with SHashMapCompilerOps with TrainingSetOpsImpl with LinAlgOpsImpl with LinAlgCompilerOps with SparseMatrixBuildableOpsImpl with SparseMatrixBuildableCompilerOps with RangeOpsImpl with RangeCompilerOps with Tup3OpsImpl with Tup3CompilerOps with DenseVectorViewOpsImpl with DenseVectorViewCompilerOps with Tup4OpsImpl with Tup4CompilerOps with Tup5OpsImpl with Tup5CompilerOps with DenseMatrixOpsImpl with DenseMatrixCompilerOps with DenseVectorOpsImpl with DenseVectorCompilerOps with SparseMatrixOpsImpl with SparseMatrixCompilerOps with GrayscaleImageOpsImpl with ControlOpsImpl with LAioOpsImpl with LAioCompilerOps with FileStreamOpsImpl with FileStreamCompilerOps with BasicMathOpsImpl with ForgeArrayCompilerOps with ForgeArrayBufferCompilerOps with ForgeHashMapCompilerOps with VarCompilerOps with LambdaCompilerOps with RecordCompilerOps with InputOutputCompilerOps with ProfilingCompilerOps with ReppableCompilerOps with TestsCompilerOps with AssertsCompilerOps with BLASCompilerOps with LAPACKCompilerOps with RewriteCompilerOps with SumCompilerOps {
  this: OptiMLApplication with OptiMLExp => 

}

trait OptiMLInteractive extends OptiMLApplication with DeliteInteractive
trait OptiMLInteractiveRunner[R] extends OptiMLApplicationCompiler with DeliteInteractiveRunner[R]

// executes scope immediately
object OptiML {
  def apply[R](b: => R) = new Scope[OptiMLInteractive, OptiMLInteractiveRunner[R], R](b)
}

trait OptiMLLower extends OptiMLApplication with DeliteRestageOps
trait OptiMLLowerRunner[R] extends OptiMLApplicationCompiler with DeliteRestageRunner[R]

// stages scope and generates re-stageable code
object OptiML_ {
  def apply[R](b: => R) = new Scope[OptiMLLower, OptiMLLowerRunner[R], R](b)
}

trait OptiMLExp extends OptiMLCompiler
 with TableFactorOpsExp with SparseVectorViewOpsExp with Tup2OpsExp with ComplexOpsExp with ContinuousFeatureOpsExp with Tup9OpsExp with Tup8OpsExp with DiscreteFeatureOpsExp with Primitive2OpsExp with CHashMapOpsExp with IndexVectorOpsExp with MathOpsExp with Tup7OpsExp with WeightOpsExp with BinaryFeatureOpsExp with FactorGraphOpsExp with SparseVectorOpsExp with UTriangleOpsExp with RandOpsExp with Tup6OpsExp with MLioOpsExp with FunctionFactorOpsExp with ComputeStreamOpsExp with FStringOpsExp with SHashMapOpsExp with TrainingSetOpsExp with LinAlgOpsExp with SparseMatrixBuildableOpsExp with MiscOpsExp with RangeOpsExp with FactorVariableOpsExp with CastOpsExp with Tup3OpsExp with DenseVectorViewOpsExp with Tup4OpsExp with RandomVariableOpsExp with Tup5OpsExp with DenseMatrixOpsExp with DenseVectorOpsExp with SparseMatrixOpsExp with GrayscaleImageOpsExp with ControlOpsExp with Ordering2OpsExp with LAioOpsExp with FileStreamOpsExp with BasicMathOpsExp with ForgeArrayOpsExp with ForgeArrayBufferOpsExp with ForgeHashMapOpsExp with VarOpsExp with LambdaOpsExp with RecordOpsExp with InputOutputOpsExp with ProfilingOpsExp with ReppableOpsExp with TestsOpsExp with AssertsOpsExp with BLASOpsExp with LAPACKOpsExp with RewriteOpsExp with SumOpsExp with ExpressionsOpt with DeliteOpsExp with DeliteRestageOpsExp with DeliteAllOverridesExp with MultiloopSoATransformExp {
 this: DeliteApplication with OptiMLApplication => 

  /**
   * disambiguations for LMS classes pulled in by Delite
   */
  override def infix_unary_!(x: Rep[Boolean])(implicit pos: SourceContext) = boolean_negate(x)
  override def infix_&&(lhs: Rep[Boolean], rhs: Rep[Boolean])(implicit pos: SourceContext) = boolean_and(lhs,rhs)
  override def infix_||(lhs: Rep[Boolean], rhs: Rep[Boolean])(implicit pos: SourceContext) = boolean_or(lhs,rhs)
  override def infix_unsafeImmutable[A:Manifest](lhs: Rep[A])(implicit pos: SourceContext) = object_unsafe_immutable(lhs)
  override def infix_unsafeMutable[A:Manifest](lhs: Rep[A])(implicit pos: SourceContext) = object_unsafe_mutable(lhs)
  override def infix_trim(lhs: Rep[String])(implicit pos: SourceContext) = string_trim(lhs)
  override def infix_startsWith(lhs: Rep[String], rhs: Rep[String])(implicit pos: SourceContext) = fstring_startswith(lhs,rhs)
  override def infix_endsWith(lhs: Rep[String], rhs: Rep[String])(implicit pos: SourceContext) = fstring_endswith(lhs,rhs)
  override def infix_contains(lhs: Rep[String], rhs: Rep[String])(implicit pos: SourceContext) = fstring_contains(lhs,rhs)
  override def __whileDo(cond: => Exp[Boolean], body: => Rep[Unit])(implicit pos: SourceContext) = delite_while(cond, body)
  override def __ifThenElse[T:Manifest](cond: Rep[Boolean], thenp: => Rep[T], elsep: => Rep[T])(implicit ctx: SourceContext) = delite_ifThenElse(cond, thenp, elsep, false, true)
  override def __ifThenElse[T:Manifest](cond: => Rep[Boolean], thenp: => Rep[T], elsep: => Rep[T])(implicit ctx: SourceContext) = delite_ifThenElse(cond, thenp, elsep, false, true)
  implicit def repToOrderingOps[A:Manifest:Ordering](x: Rep[A]) = repOrderingToOrderingOps(x)
  implicit def varToOrderingOps[A:Manifest:Ordering](x: Var[A]) = varOrderingToOrderingOps(x)
  // forward to LMS primitive ops to make stencil analysis detect intervals (and get constant folding rewrites)
  override def primitive2_forge_int_plus(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = int_plus(__arg0, __arg1)
  override def primitive2_forge_int_minus(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = int_minus(__arg0, __arg1)
  override def primitive2_forge_int_times(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext) = int_times(__arg0, __arg1)

  /**
   * dsl types
   */
  abstract class Tup2[A,B]
  def m_Tup2[A:Manifest,B:Manifest] = manifest[Tup2[A,B]]
  abstract class Tup3[A,B,C]
  def m_Tup3[A:Manifest,B:Manifest,C:Manifest] = manifest[Tup3[A,B,C]]
  abstract class Tup4[A,B,C,D]
  def m_Tup4[A:Manifest,B:Manifest,C:Manifest,D:Manifest] = manifest[Tup4[A,B,C,D]]
  abstract class Tup5[A,B,C,D,E]
  def m_Tup5[A:Manifest,B:Manifest,C:Manifest,D:Manifest,E:Manifest] = manifest[Tup5[A,B,C,D,E]]
  abstract class Tup6[A,B,C,D,E,F]
  def m_Tup6[A:Manifest,B:Manifest,C:Manifest,D:Manifest,E:Manifest,F:Manifest] = manifest[Tup6[A,B,C,D,E,F]]
  abstract class Tup7[A,B,C,D,E,F,G]
  def m_Tup7[A:Manifest,B:Manifest,C:Manifest,D:Manifest,E:Manifest,F:Manifest,G:Manifest] = manifest[Tup7[A,B,C,D,E,F,G]]
  abstract class Tup8[A,B,C,D,E,F,G,H]
  def m_Tup8[A:Manifest,B:Manifest,C:Manifest,D:Manifest,E:Manifest,F:Manifest,G:Manifest,H:Manifest] = manifest[Tup8[A,B,C,D,E,F,G,H]]
  abstract class Tup9[A,B,C,D,E,F,G,H,I]
  def m_Tup9[A:Manifest,B:Manifest,C:Manifest,D:Manifest,E:Manifest,F:Manifest,G:Manifest,H:Manifest,I:Manifest] = manifest[Tup9[A,B,C,D,E,F,G,H,I]]
  abstract class DenseVector[T] extends DeliteCollection[T]
  def m_DenseVector[T:Manifest] = manifest[DenseVector[T]]
  abstract class DenseVectorView[T] extends DeliteCollection[T]
  def m_DenseVectorView[T:Manifest] = manifest[DenseVectorView[T]]
  abstract class DenseMatrix[T] extends DeliteCollection[T]
  def m_DenseMatrix[T:Manifest] = manifest[DenseMatrix[T]]
  abstract class IndexVector extends DeliteCollection[Int]
  def m_IndexVector = manifest[IndexVector]
  abstract class SparseVector[T]
  def m_SparseVector[T:Manifest] = manifest[SparseVector[T]]
  abstract class SparseVectorView[T]
  def m_SparseVectorView[T:Manifest] = manifest[SparseVectorView[T]]
  abstract class SparseMatrix[T]
  def m_SparseMatrix[T:Manifest] = manifest[SparseMatrix[T]]
  abstract class SparseMatrixBuildable[T]
  def m_SparseMatrixBuildable[T:Manifest] = manifest[SparseMatrixBuildable[T]]
  abstract class Range
  def m_Range = manifest[Range]
  abstract class Complex
  def m_Complex = manifest[Complex]
  abstract class UTriangle
  def m_UTriangle = manifest[UTriangle]
  abstract class ContinuousFeature
  def m_ContinuousFeature = manifest[ContinuousFeature]
  abstract class DiscreteFeature
  def m_DiscreteFeature = manifest[DiscreteFeature]
  abstract class BinaryFeature
  def m_BinaryFeature = manifest[BinaryFeature]
  abstract class TrainingSet[D,L]
  def m_TrainingSet[D:Manifest,L:Manifest] = manifest[TrainingSet[D,L]]
  abstract class FactorVariable
  def m_FactorVariable = manifest[FactorVariable]
  abstract class TableFactor
  def m_TableFactor = manifest[TableFactor]
  abstract class FunctionFactor
  def m_FunctionFactor = manifest[FunctionFactor]
  abstract class RandomVariable
  def m_RandomVariable = manifest[RandomVariable]
  abstract class Weight
  def m_Weight = manifest[Weight]
  abstract class FactorGraph[F]
  def m_FactorGraph[F:Factor:Manifest] = manifest[FactorGraph[F]]
  abstract class FileStream
  def m_FileStream = manifest[FileStream]
  abstract class ComputeStream[T]
  def m_ComputeStream[T:Manifest] = manifest[ComputeStream[T]]
  abstract class GrayscaleImage
  def m_GrayscaleImage = manifest[GrayscaleImage]

  def getCodeGenPkg(t: Target{val IR: OptiMLExp.this.type}): GenericFatCodegen{val IR: OptiMLExp.this.type} = {
    t match {
      case _:TargetScala => new OptiMLCodegenScala{val IR: OptiMLExp.this.type = OptiMLExp.this}
      case _:TargetCuda => new OptiMLCodegenCuda{val IR: OptiMLExp.this.type = OptiMLExp.this}
      case _:TargetOpenCL => new OptiMLCodegenOpenCL{val IR: OptiMLExp.this.type = OptiMLExp.this}
      case _:TargetCpp => new OptiMLCodegenC{val IR: OptiMLExp.this.type = OptiMLExp.this}
      case _:TargetRestage => new OptiMLCodegenRestage{val IR: OptiMLExp.this.type = OptiMLExp.this}
      case _ => throw new RuntimeException("OptiML does not support this target")
    }
  }
}

/**
 * code generators
 */
trait OptiMLCodegenBase extends GenericFatCodegen {
  val IR: DeliteApplication with OptiMLExp
  override def initialDefs = IR.deliteGenerator.availableDefs

  def dsmap(line: String) = line
  override def emitDataStructures(path: String) {
    val s = File.separator
    val dsRoot = System.getProperty("user.dir")+s+"compiler"+s+"src"+s+"optiml"+s+"compiler"+s+"datastruct"+s+this.toString

    val dsDir = Directory(Path(dsRoot))
    val outDir = Directory(Path(path))
    outDir.createDirectory()

    for(f <- dsDir.files) {
      val outFile = path + s + f.name
      val out = new BufferedWriter(new FileWriter(outFile))
      for (line <- scala.io.Source.fromFile(f.jfile).getLines) {
        out.write(dsmap(line) + "\n")
      }
      out.close()
    }
  }

  override def remap[A](m: Manifest[A]): String = {
    var res = super.remap(m)
    if (res.contains("$")) {
      res = res.slice(res.indexOf("#")+1,res.length)
      res = res.slice(0, res.lastIndexOf(".")+1) + res.slice(res.lastIndexOf("$")+1, res.length)
    }
    res
  }

}

trait OptiMLCodegenScala extends OptiMLCodegenBase
 with ScalaGenPrimitive2Ops with ScalaGenCHashMapOps with ScalaGenMathOps with ScalaGenRandOps with ScalaGenMLioOps with ScalaGenFStringOps with ScalaGenSHashMapOps with ScalaGenMiscOps with ScalaGenRangeOps with ScalaGenCastOps with ScalaGenDenseVectorOps with ScalaGenOrdering2Ops with ScalaGenLAioOps with ScalaGenFileStreamOps with ScalaGenBasicMathOps with ScalaGenForgeArrayOps with ScalaGenForgeArrayBufferOps with ScalaGenForgeHashMapOps with ScalaGenVarOps with ScalaGenLambdaOps with ScalaGenRecordOps with ScalaGenInputOutputOps with ScalaGenProfilingOps with ScalaGenReppableOps with ScalaGenTestsOps with ScalaGenAssertsOps with ScalaGenBLASOps with ScalaGenLAPACKOps with ScalaGenSumOps with ScalaGenDeliteOps with DeliteScalaGenAllOverrides {
  val IR: DeliteApplication with OptiMLExp

 // these methods translate types in the compiler to typed in the generated code
  override def dsmap(line: String): String = {
    var res = line.replaceAll("optiml.compiler.datastruct", "generated")
    res = res.replaceAll("ppl.delite.framework.datastruct", "generated")
    res = res.replaceAll("optiml.compiler", "generated.scala")
    res
  }

  override def remap[A](m: Manifest[A]): String = {
    var res = super.remap(m)
    res = res.replaceAllLiterally("package$", "")
    dsmap(res)
  }

}
trait OptiMLCodegenCuda extends OptiMLCodegenBase
 with CudaGenPrimitive2Ops with CudaGenMathOps with CudaGenMiscOps with CudaGenCastOps with CudaGenOrdering2Ops with CudaGenBasicMathOps with CudaGenForgeArrayOps with CudaGenForgeArrayBufferOps with CudaGenForgeHashMapOps with CudaGenVarOps with CudaGenLambdaOps with CudaGenRecordOps with CudaGenInputOutputOps with CudaGenProfilingOps with CudaGenReppableOps with CudaGenTestsOps with CudaGenBLASOps with CudaGenLAPACKOps with CudaGenSumOps with DeliteCppHostTransfer with DeliteCudaDeviceTransfer 
 with CudaGenDeliteOps with DeliteCudaGenAllOverrides {
  val IR: DeliteApplication with OptiMLExp
}
trait OptiMLCodegenOpenCL extends OptiMLCodegenBase
 with OpenCLGenForgeArrayOps with OpenCLGenForgeArrayBufferOps with OpenCLGenForgeHashMapOps with OpenCLGenVarOps with OpenCLGenLambdaOps with OpenCLGenRecordOps with OpenCLGenInputOutputOps with OpenCLGenProfilingOps with OpenCLGenReppableOps with OpenCLGenTestsOps with OpenCLGenBLASOps with OpenCLGenLAPACKOps with OpenCLGenSumOps with OpenCLGenDeliteOps with DeliteOpenCLGenAllOverrides {
  val IR: DeliteApplication with OptiMLExp
}
trait OptiMLCodegenC extends OptiMLCodegenBase
 with CGenPrimitive2Ops with CGenMathOps with CGenFStringOps with CGenMiscOps with CGenRangeOps with CGenCastOps with CGenOrdering2Ops with CGenLAioOps with CGenBasicMathOps with CGenForgeArrayOps with CGenForgeArrayBufferOps with CGenForgeHashMapOps with CGenVarOps with CGenLambdaOps with CGenRecordOps with CGenInputOutputOps with CGenProfilingOps with CGenReppableOps with CGenTestsOps with CGenBLASOps with CGenLAPACKOps with CGenSumOps with DeliteCppHostTransfer 
 with CGenDeliteOps with DeliteCGenAllOverrides {
  val IR: DeliteApplication with OptiMLExp
  // Function to generate driver code


  def getKernelArgSyms(irnode: IR.Def[Any]) = {
    val argSymList = new ListBuffer[IR.Sym[Any]]
    irnode match {
      case p: Product => 
        val iter = p.productIterator
        while (iter.hasNext) {
          // Value parameters of case classes will have both symbols, which are 'Rep' typed inputs
          // as well as tunable parameters, which can be resolved at staging time. 
          // Pattern match here so that only the Rep typed symbols are counted.
          val tmp = iter.next
          tmp match {
            case x: IR.Sym[Any] => 
             argSymList.append(x)
            case y: Any =>
              // Ignore
            case _ =>
              throw new Exception("Unknown type, don't know what I'm dealing with. ABORT!")
          }
        }
        // Main function needs to read p.productArity number of arguments from command line
      case _ => throw new Exception("Expecting only product here, ABORT!")
    }
    argSymList
  }

  def getArgTypeFromSyms(argList: List[IR.Sym[Any]]) = {
    val argTypeList = new ListBuffer[(String, String)]
    for (arg <- argList) {
      val argStr = quote(arg)
      val typeStr = remap(arg.tp)
      val refStr = addRef(typeStr).trim
      argTypeList.append((argStr, (typeStr+refStr)))
    }
    argTypeList
  }

  def getKernelArgs(irnode: IR.Def[Any]) = {
    val argList: ListBuffer[IR.Sym[Any]] = getKernelArgSyms(irnode)
    getArgTypeFromSyms(argList.toList)
//    val argTypeList = new ListBuffer[(String, String)]
//    for (arg <- argList) {
//      val argStr = quote(arg)
//      val typeStr = remap(arg.tp)
//      val refStr = addRef(typeStr).trim
//      argTypeList.append((argStr, (typeStr+refStr)))
//    }
//    argTypeList
  }

  def getKernelCall(sym: IR.Sym[Any], irnodeOption: Option[IR.Def[Any]]) = {
    val argTypeList = new ListBuffer[(String, String)]
    argTypeList.append(("r", "mandatoryCrapType"))
    if (!irnodeOption.isEmpty) {
      val irnode = irnodeOption.get 
      val tmpList = getKernelArgs(irnode) 
      for (tuple <- tmpList) {
        argTypeList.append(tuple)
      }
    }
    val kernelName = "kernel_" + quote(sym)
    val args = argTypeList map { i => i._1 } mkString(",")
    kernelName + "(" + args + ");"

  }

  // There must be a better way to do this
  def getIRNodeForSym(sym: IR.Sym[Any]): Option[IR.Def[Any]] = {
//    Console.println("Trying to get IR node for %s".format(sym))
    val stm = IR.findDefinition(sym).getOrElse {
      Console.println("Was %s defined as a fresh? Doesn't have an IR node".format(sym))
    }
    val irnode = stm match {
      case IR.TP(lhs: IR.Sym[Any], rhs: IR.Def[Any]) => 
        rhs match {
          case IR.Reflect(x: IR.Def[Any], _, _) => Some(x)
          case _ => Some(rhs)
        }
      case _ => None
    }
    irnode
  }

  var declaredSet: Set[String] = Set()
  var definedSet: Set[String] = Set()
  def handleArgs(irnode: IR.Def[Any], argvStartNumber: Int): (String, Int) = {
    val argSymList: ListBuffer[IR.Sym[Any]] = getKernelArgSyms(irnode) 
    val argTypeList: ListBuffer[(String, String)] = getKernelArgs(irnode)
    val buffer = new StringBuilder
    var argvNumber = argvStartNumber

    // For each arg, two things can happen. Handle as follows:
    // 1. Arg is primitive type: Increment the arg number, insert statement to populate it from argv
    // 2. Arg is not a primitive type: This argument must be initialized from another kernel, which takes its args from primitive args.
    //    Obtain its irnode, call 'handleArgs' recursively on that IR node. When it ends, add a call to 'kernel_<name>'.
    for (i: Int <- argTypeList.indices) {
      val arg = argTypeList(i)._1
      val typename = argTypeList(i)._2
      if (isPrimitiveType(typename)) {  // TODO: isPrimitiveType == integer??
        if (!declaredSet.contains(arg)) {
          declaredSet += arg
          buffer.append("  " + argTypeList(i)._2 + " " + argTypeList(i)._1 + ";")
          argvNumber += 1
          buffer.append("  " + argTypeList(i)._1 + " = atoi(argv[" + argvNumber + "]);" )
        }
      }
      else {
//        if (!declaredSet.contains(arg)) {
//          declaredSet += arg
          buffer.append("  " + argTypeList(i)._2 + " " + argTypeList(i)._1 + ";")
          val sym = argSymList(i)
          val argIRNode: IR.Def[Any] = getIRNodeForSym(sym).getOrElse {
            throw new Exception("Argument is non-primitive type, but does not have associated IR node. How the F** should I initialize it?")
          }
          val kernelCall = getKernelCall(sym, Some(argIRNode))
          val argStringAndArgvNumber = handleArgs(argIRNode, argvNumber)
          val argString : String= argStringAndArgvNumber._1
          argvNumber = argStringAndArgvNumber._2
          buffer.append(argString + "\n")
          buffer.append("  " + argTypeList(i)._1 + "= " + kernelCall + ";")
//        }
      }
    }
    (buffer.toString, argvNumber)
  }

  def generateDriver(lhs: IR.Sym[Any], irnode: IR.Def[Any], stream: PrintWriter, includeFiles: List[String], deps: List[IR.Sym[Any]]) = {
    // Driver file header
    stream.println("// This file is automatically generated")
    for (file: String <- includeFiles) {
      stream.println("#include \"%s\"".format(file))
    }
    stream.println("int main(int argc, char **argv) {")
    stream.println("  resourceInfo_t r;")

    var argvs = 0
    val argTypeList: ListBuffer[(String, String)] = getArgTypeFromSyms(deps) 
    val initBuffer = new StringBuilder
    for (i <- deps.indices) {
      val sym = deps(i)
      val irnodeDep = getIRNodeForSym(sym).getOrElse {
        throw new Exception("Fresh variable encountered in deps; this shouldn't happen")
      }
      val strAndArgv = handleArgs(irnodeDep, argvs)
      initBuffer.append(strAndArgv._1)
      val kernelCallDep = getKernelCall(sym, Some(irnodeDep))
      initBuffer.append("  " + argTypeList(i)._2 + " " + argTypeList(i)._1 + ";")
      initBuffer.append("  " + argTypeList(i)._1 + "= " + kernelCallDep + ";")
      argvs = strAndArgv._2
    }

    // Correct argument handling
//    val argsAndArgvs = handleArgs(irnode, -1)
//    val kernelArgs: String = argsAndArgvs._1
//    val argvs: Int = argsAndArgvs._2
    stream.println("  if (argc <= " + (argvs) + ") {")
    stream.println("    printf(\"Wrong number of args\\n\");")
    stream.println("    exit(-1);")
    stream.println("  }")

    stream.println(initBuffer.toString)
    // Definition of arguments
//    val argSymList: ListBuffer[IR.Sym[Any]] = getKernelArgSyms(irnode) 
//    for (i: Int <- argTypeList.indices) {
//      if (isPrimitiveType(argTypeList(i)._2)) {  // TODO: isPrimitiveType == integer??
//        stream.println("  " + argTypeList(i)._1 + " = atoi(argv[" + (i+1) + "]);" )
//      }
//      else {
//        val sym = argSymList(i)
//        val argIRNode: Option[IR.Def[Any]] = getIRNodeForSym(sym)
//        val kernelCall = getKernelCall(sym, argIRNode)
//        stream.println("  " + argTypeList(i)._1 + "= " + kernelCall + ";")
//      }
//    }
//    stream.println(kernelArgs)
//    val kernelName = "kernel_" + quote(lhs)
//    val args = argTypeList map { i => i._1 } mkString(",")
//    val kernelCall = getKernelCall(lhs, Some(irnode))

    val kernelArgsList = new ListBuffer[String]()
    kernelArgsList.append("r")
    for (argType <- argTypeList) {
      kernelArgsList.append(argType._1)
    }
    val kernelArgs = kernelArgsList.mkString(", ")

    val kernelCall = "kernel_" + quote(lhs) + "(" + kernelArgs + ");"
 
    
    stream.println("  Timer t;")
    val numTries = 31
    for (i <- 0 until numTries) {
      stream.println("  t.start();")        
      stream.println("  " + kernelCall + ";")
      stream.println("  t.stop();")        
    }
   
    // Print out the average elapsed time.
    // This should be the ONLY number printed - other parts of the autotuner
    // depend on this!!
    // TODO: Use a file interface instead of this?
    stream.println("  std::cout << t.getAvgElapsed() << std::endl;")
    stream.println("  return 0;")
    stream.println("}")
  }

}
trait OptiMLCodegenRestage extends OptiMLCodegenBase
 with OptiMLCodegenScala with DeliteCodeGenRestage { 
  val IR: DeliteApplication with OptiMLExp
}
