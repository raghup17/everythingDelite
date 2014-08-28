import generated.scala._
import java.util.concurrent.locks._
object Sync_Executable1 {
def get0_xop_x699 : Unit = Result_x699_op_x699.get0
private object Result_x699_op_x699 {
private var count : Int = 0
private var takeIndex0 : Int = 0
private var putIndex : Int = 0
private var _result : Unit = _
private val lock = new ReentrantLock
private val notEmpty = lock.newCondition
private val notFull = lock.newCondition
def get0 : Unit = { val takeIndex = takeIndex0; val lock = this.lock; lock.lock; try { while (takeIndex == putIndex) { notEmpty.await }; extract0 } finally { lock.unlock } }
private def extract0 : Unit = { val res = _result; takeIndex0+= 1; count -= 1; if (count == 0) { _result = null.asInstanceOf[Unit]; notFull.signal }; res }
def set(result : Unit) { val lock = this.lock; lock.lock; try { while (count != 0) { notFull.await }; insert(result) } finally { lock.unlock } }
private def insert(result: Unit) { _result = result; count = 1; putIndex += 1; notEmpty.signalAll }
}
def set_xop_x699(result : Unit) = Result_x699_op_x699.set(result)
def self = this
}
