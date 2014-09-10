import optiml.compiler._
import optiml.shared._

object MatMultCompiler extends OptiMLApplicationCompiler with MatMult

trait MatMult extends OptiMLApplication {
  def printUsage = {
    println("Usage: MatMult <f1> <f2>")
    exit(-1)
  }

  def main() = {
    if (args.length < 2) printUsage

    val A = readMatrix(args(0))
    val B = readMatrix(args(1))
    val C = A * B
    C.pprint
  }
}
