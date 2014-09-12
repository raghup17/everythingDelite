package optiml.compiler.ops
import scala.util.Random

/*
import scala.util.Random
import scala.collection.mutable.ListBuffer

object NewTunable {
  class Tunable {
  	var tunable: ListBuffer[Int] = new ListBuffer[Int]
  	var geneList: ListBuffer[scala.List[Int]] = new ListBuffer[List[Int]]()
  	var depList: ListBuffer[List[(Int, (Int, Int) => Unit)]] = new ListBuffer[List[(Int, (Int, Int) => Unit)]]()
  	val paramsPerLevel: Int = 6
  	
	  def posRand(limit: Int) = {
	    scala.math.abs(Random.nextInt) % limit
	  }

		def factors(n: scala.Int) = {
      (1 to scala.math.sqrt(n).toInt) filter (n % _ == 0) flatMap { x => List(x,n/x) } sorted
    }
    
    def getLevelOf(id: Int): Int = {
    	if (id >= tunable.length)
    		-1
    	else {
    		(id-1)/paramsPerLevel
    	}
    }
    
    def updateBlock(idx: Int, depIdx: Int) = {
    	val newMaxBlock = tunable(depIdx)
    	val newRange = factors(newMaxBlock).distinct.toList
    	geneList(idx) = newRange
    	if (!newRange.contains(tunable(idx))) {
    		val newTunable = newRange(posRand(newRange.length))
    		tunable(idx) = newTunable
    	}
    }
    
    def updateUnroll(idx: Int, depIdx: Int) = {
//    	println("[updateUnroll] Updating location %d".format(idx))
    	val newMaxTripcount = geneList(depIdx).max / tunable(depIdx)
    	if (getLevelOf(idx) == 2) {
//    		println("[updateUnroll] location %d depends on location %d:.format(idx, depIdx)".format(idx, depIdx))
//    		println("[updateUnroll] geneList(%d) = %s".format(depIdx, geneList(depIdx)))
//    		println("[updateUnroll] location=%d, Max trip count = %d".format(idx, newMaxTripcount))
    	}
    	val newRange = factors(newMaxTripcount).distinct.toList
    	geneList(idx) = newRange
    	if (!newRange.contains(tunable(idx))) {
    		val newTunable = newRange(posRand(newRange.length))
    		tunable(idx) = newTunable
    	}
    }
    
    def updateEverythingFrom(idx: Int) = {
    	for (i <- idx to tunable.length-1) {
    		//println("Before updating location %d".format(i))
    		//println(tunable)
    		//println(geneList)
    		val depInfo = depList(i)
    		for (d <- depInfo) {
    			val tobeUpdated = d._1
    			val f = d._2
  //  			println("Updating location %d".format(tobeUpdated))
    			f(tobeUpdated, i)
    		}
    		//println("After updating location %d:".format(i))
    		//println(tunable)
    		//println(geneList)
    	}
    }

		def mutate() = {
			val excludeThese = Range((tunable.length - paramsPerLevel), tunable.length-3)
			println("Excluding these: %s".format(excludeThese))
			for (idx <- 1 to (tunable.length-1) diff excludeThese ) {
				if (posRand(Int.MaxValue)%2 == 0) {
					val range = geneList(idx)
					tunable(idx) = range(posRand(range.length))
					updateEverythingFrom(idx)
				}
			}
		}
		
		def mutate(posi: Int) = {
			val range = geneList(posi)
			println("Old value = %d".format(tunable(posi)))
			
			tunable(posi) = range(posRand(range.length))
			
			println("Old value = %d".format(tunable(posi)))
			println("Before updating everything")
			println(tunable)
			updateEverythingFrom(posi)
			println("After updating everything")
			println(tunable)
		}
		
  	def this(M: Int, N: Int, P: Int, levels: Int) = {
  		this()
			val numLevelsRange = List(levels)
			val emptyDepList = List[(Int, (Int, Int) => Unit)]()
	    tunable.append(levels)
	    geneList.append(numLevelsRange.toList)
	    depList.append(emptyDepList)
	    
	    var loopm = M
	    var loopn = N
	    var loopp = P
	    
	    for (l <- 0 to levels) {
	      val bmRange = factors(loopm).distinct.toList
        val bnRange = factors(loopn).distinct.toList
        val bpRange = factors(loopp).distinct.toList
       	var bm: Int = 1
       	var bn: Int = 1
       	var bp: Int = 1

	      if (l != levels) {
					bm = bmRange(posRand(bmRange.length))
	      	bn = bnRange(posRand(bnRange.length))
	      	bp = bpRange(posRand(bpRange.length))
	      }
	      
	      val umRange = factors(loopm/bm).distinct.toList
	      val unRange = factors(loopn/bn).distinct.toList
	      val upRange = factors(loopp/bp).distinct.toList
	      val um = umRange(posRand(umRange.length))
	      val un = unRange(posRand(unRange.length))
	      val up = upRange(posRand(upRange.length))
	      
//	      println("Level %d, block sizes = %d %d %d".format(l, bm, bn, bp))
	      //println("Level %d,  unroll ranges:".format(l))
//	      println(umRange)
//	      println(unRange)
//	      println(upRange)
	      
	      val bmLoc = l * paramsPerLevel + 1
	      val bnLoc = l * paramsPerLevel + 2
	      val bpLoc = l * paramsPerLevel + 3
	      val umLoc = l * paramsPerLevel + 4
	      val unLoc = l * paramsPerLevel + 5
	      val upLoc = l * paramsPerLevel + 6
	      
	      tunable.append(bm, bn, bp, um, un, up)
	      geneList.append(bmRange, bnRange, bpRange, umRange, unRange, upRange)
	      
	      if (l == levels) {
		      depList.append(
		    		List((bmLoc+3, updateUnroll)),
		      	List((bnLoc+3, updateUnroll)),
		      	List((bpLoc+3, updateUnroll)),
		      	emptyDepList,
		      	emptyDepList,
		      	emptyDepList
		      )
		    }
		    else {
		    	depList.append(
		      	List((bmLoc+paramsPerLevel, updateBlock), (bmLoc+3, updateUnroll)),
		      	List((bnLoc+paramsPerLevel, updateBlock), (bnLoc+3, updateUnroll)),
		      	List((bpLoc+paramsPerLevel, updateBlock), (bpLoc+3, updateUnroll)),
		      	emptyDepList,
		      	emptyDepList,
		      	emptyDepList
		      )
		    }
		    loopm = bm
	    	loopn = bn
	    	loopp = bp
		      
			}
		}
  
 	  def apply(x: Int) = { tunable(x) }
		
	  override def equals(o: Any) = o match {
	    case that: Tunable =>
	      if(that.tunable == tunable && that.geneList == geneList) true
	      else false
	    case _ => super.equals(o)
	  }
	  override def hashCode = tunable.hashCode + geneList.hashCode
	  def mkString(s: String) = this.tunable.mkString(s)
	  override def toString = { tunable.toString }
	

  }
	
			val t1 = new Tunable(32, 32, 32, 2)
			val t2 = new Tunable(32, 32, 32, 2)
			val t3 = new Tunable(32, 32, 32, 2)
			val t4 = new Tunable(32, 32, 32, 2)
				 t1.mutate        //> Excluding these: Range(13, 14, 15)
				 println(t1)      //> ListBuffer(2, 1, 1, 16, 2, 2, 2, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1)
				 t1.mutate        //> Excluding these: Range(13, 14, 15)
				 println(t1)      //> ListBuffer(2, 1, 1, 4, 1, 2, 8, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1)
                                                  
				t1.getLevelOf(21) //> res0: Int = -1
}

*/

class Element[T](val item: T, val itemList: scala.List[T]) {

}

/**  Class Tunable - represents an n-tuple of values of type T that need to be tuned.
 *   This class is typically used with the Autotuner function to represent arguments
 *   tunable arguments from a search space of possibilities. Currently, all members of
 *   the n-tuple must be of the same type T.
 *   Member functions:
 *   mutate() - Change the tuple randomly and create a new Tunable object
 *   crossOver() - Make a new Tunable by choosing two halves from two different Tunable's
 */
class Tunable[T](val tunable: scala.List[T], val geneList: scala.List[scala.List[T]]) {
  def apply(x: Int) = { tunable(x) }

  def crossOver(that: Tunable[T]): Tunable[T] = {
    var fromThis = scala.List[T]()
    var fromThat = scala.List[T]()
    val numFromThis = Math.abs(Random.nextInt) % tunable.length
    val numFromThat = tunable.length - numFromThis
    if (Random.nextInt % 2 == 0) {
      fromThis = tunable take (numFromThis)
      fromThat = that.tunable takeRight (numFromThat)
    }
    else {
      fromThat = that.tunable take (numFromThat)
      fromThis = tunable takeRight (numFromThis)
    }

    val ret = fromThis ::: fromThat
    new Tunable[T](ret, geneList)
  }

  override def equals(o: Any) = o match {
    case that: Tunable[T] =>
      if(that.tunable == tunable && that.geneList == geneList) {
        true
      }
      else {
        false               
      }
    case _ => super.equals(o)
  }

  override def hashCode = this.tunable.hashCode + this.geneList.hashCode

  def mutate(): Tunable[T] = {
    // Some mutation constants
    val maxMutations = tunable.length

    // Pick number of mutations at random
    val numMutations = Math.abs(Random.nextInt) % maxMutations + 1
    
    // Pick mutation positions at random
    var mutationPos = scala.collection.immutable.Set[Int]()
    while (mutationPos.size < numMutations) {
      val idx = Math.abs(Random.nextInt) % tunable.length
      mutationPos += idx
    }

    // Apply mutations using mutationPos
    var newTunable =  scala.List[T]()
    for (i <- 0 to tunable.length-1) {
      if (mutationPos contains i) {
        if (geneList.length > 1) { // Use geneList(i) to get replacement
          val replacementList = geneList(i)
          newTunable ::= replacementList(Math.abs(Random.nextInt)%replacementList.length)
        }
        else {
        val replacementList = geneList(0)
          newTunable ::= replacementList(Math.abs(Random.nextInt)%replacementList.length)
        }
      }
      else {
          newTunable ::= tunable(i)
      }
    }
    new Tunable[T](newTunable.reverse, geneList)
	}

  def mkString(s: String) = this.tunable.mkString(s)
 
	def getnew() = {
		var newTunable =  scala.List[T]()
		for (i <- 0 to tunable.length-1) {
			if (geneList.length > 1) { // Multiple sets, use the set at geneList(i) to get replacement
				val replacementList = geneList(i)
				newTunable ::= replacementList(Math.abs(Random.nextInt)%replacementList.length)
			}
			else {
			val replacementList = geneList(0)
				newTunable ::= replacementList(Math.abs(Random.nextInt)%replacementList.length)
			}
		}
   	new Tunable[T](newTunable.reverse, geneList)
	}

  override def toString = tunable.toString
}

/** Example usage:
  def factors(n: Int) = {
      (1 to Math.sqrt(n).toInt) filter (n % _ == 0) flatMap { x => List(x,n/x) } sorted
    }                                               //> factors: (n: Int)scala.collection.immutable.IndexedSeq[Int]
    
    def getBlockSizes(M: Int) = {
      (for (i <- factors(M)) yield i).toList
    }                                               //> getBlockSizes: (M: Int)List[Int]
    
    val arr1 = getBlockSizes(64)                    //> arr1  : List[Int] = List(1, 2, 4, 8, 8, 16, 32, 64)
    val arr2 = getBlockSizes(33)                    //> arr2  : List[Int] = List(1, 3, 11, 33)
    val arr3 = getBlockSizes(77)                    //> arr3  : List[Int] = List(1, 7, 11, 77)
    
    val t = new Tunable(List(1,1,1), List(arr1, arr2, arr3))
                                                    //> t  : Tunable.Tunable[Int] = List(1, 1, 1)
   val t1 = t.mutate                                //> t1  : Tunable.Tunable[Int] = List(1, 3, 1)
   val t2 = t.mutate                                //> t2  : Tunable.Tunable[Int] = List(1, 33, 7)
   
   val t3 = t1.crossOver(t2)   
**/
