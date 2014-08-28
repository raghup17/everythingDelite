import ppl.delite.runtime.{Config,Delite}
object DeliteApplication extends ((Array[java.lang.String])=>(Unit)) {
def apply(in0:Array[java.lang.String]): Unit = {
initEnv()
val res = Delite.executeCached("",in0)
()
}
def initEnv() {
Config.numThreads = 1
Config.numCpp = 1
Config.numCuda = 0
Config.numOpenCL = 0
Config.executor = "default"
Config.performWalk = false
Delite.expectedResources = Seq(0,1)
}
}
