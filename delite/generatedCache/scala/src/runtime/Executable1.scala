import ppl.delite.runtime.codegen.DeliteExecutable
import ppl.delite.runtime.profiler.PerformanceTimer
import ppl.delite.runtime.profiler.MemoryProfiler
import generated.scala._
import Sync_Executable0._
import Sync_Executable1._
object Executable1 extends DeliteExecutable {
@native def hostExecutable1: Unit
System.load("""/usr/local/google/home/raghuprabhakar/work/stanford/hyperdsl/delite/generatedCache/cpp/bin/runtime/cppHostMatMultCompiler.so""")
def run() {
hostExecutable1
}
def self = this
}
