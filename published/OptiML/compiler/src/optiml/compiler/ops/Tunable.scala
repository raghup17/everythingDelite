package optiml.compiler.ops
import scala.util.Random

/**  Class Tunable - represents an n-tuple of integer parameters that need to be tuned
 *   specifically for a matrix multiply. An instance contains information about the number
 *   of levels of blocking, block sizes and unroll factors at each level
 *   While this class is intended to be a generic type for all tunable parameters, currently
 *   it has enough specialization so that it works only for a matrix multiply
 *   This class is used with the autotuner function to represent 
 *   tunable arguments in a search space.
 *   Member functions:
 *   mutate() - Change the tuple randomly and create a new Tunable object
 */

class Tunable {
  var tunable: ListBuffer[Int] = new ListBuffer[Int]
  var geneList: ListBuffer[scala.List[Int]] = new ListBuffer[List[Int]]()
  var depList: ListBuffer[List[(Int, (Int, Int) => Unit)]] = new ListBuffer[List[(Int, (Int, Int) => Unit)]]()
  val paramsPerLevel: Int = 6
  var numLevels: Int = -1 // Zero based. If numLevels == 2, there are three levels 0,1,2. 0 is the outermost loop nest
  var mdim : Int = -1
  var ndim: Int = -1
  var pdim: Int = -1
  def posRand(limit: Int) = {
    scala.math.abs(Random.nextInt) % limit
  }

  def factors(n: scala.Int) = {
    (1 to scala.math.sqrt(n).toInt) filter (n % _ == 0) flatMap { x => List(x,n/x) } sorted
  }

  // The Tunables are assumed to be stored in the following order:
  // --------------------------------------------------------------------------------------------------------
  // | #levels | stepm | stepn | stepp | unrollm | unrolln | unrollp | stepm | stepn | stepp | .. 
  // |         | <<----------- level 0 parameters ----------------->>|<<-- level 1 parameters -------
  // --------------------------------------------------------------------------------------------------------
  // The following invariants must hold good:
  // 1. If step size in level 'l-1' is 'sm', then the corresponding loop in level 'l'must 
  //    have atmost 'sm' iterations. If the loop in level 'l' has step size 'sm2', then 
  //    the max iteration count is 'sm/sm2'.
  // 2. Block sizes at level 'l' must be <= block size at level 'l-1', and must be an integral
  //    multiple of block size at l-1
  def validateTunables(): scala.Boolean = {

    def validateBlockSizes(prevB: Int, b: Int) = {
      if (prevB == -1) {
        true
      }
      else {
        val factorsOfPrevB = factors(prevB).distinct.toList
        if (b > prevB)
          false
        if (!(factorsOfPrevB contains b)) {
          false
        }
        true
      }
    }

    def validateUnrollFactors(prevB: Int, currB: Int, uf: Int) = {
      if (prevB == -1) {
        true
      }
      else {
        val maxTripCount = prevB/currB
        val validUnrollFactors = factors(maxTripCount).distinct.toList
        if (uf > maxTripCount) {
          Console.println("Unroll factor of %d too large with prevB=%d, currB=%d!".format(uf, prevB, currB))
          false
        }
        else if (!(validUnrollFactors.contains(uf))) {
          Console.println("Unroll factor %d not a factor of trip count %d!".format(uf, maxTripCount))
          false
        }
        true
      }
    }

    var prevBm = -1
    var prevBn = -1
    var prevBp = -1
    for (l <- 0 to numLevels) {
      val levelStart = l*paramsPerLevel + 1
      val levelEnd = levelStart + paramsPerLevel - 1
      val bm = tunable(levelStart + 0)
      val bn = tunable(levelStart + 1)
      val bp = tunable(levelStart + 2)
      if (!validateBlockSizes(prevBm, bm) || !validateBlockSizes(prevBn, bn) || !validateBlockSizes(prevBp, bp)) {
        false
      }

      val um = tunable(levelStart + 3)
      val un = tunable(levelStart + 4)
      val up = tunable(levelStart + 5)
     
      if (!validateUnrollFactors(prevBm, bm, um) || !validateUnrollFactors(prevBn, bn, un) || !validateUnrollFactors(prevBp, bp, up)) {
        false
      }
      prevBm = bm
      prevBn = bn
      prevBp = bp
    }
    true
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

  def mutate(): Tunable = {
    val excludeThese = Range((tunable.length - paramsPerLevel), tunable.length-3)
//			println("Excluding these: %s".format(excludeThese))
    for (idx <- 1 to (tunable.length-1) diff excludeThese ) {
      if (posRand(Int.MaxValue)%2 == 0) {
        val range = geneList(idx)
        tunable(idx) = range(posRand(range.length))
        updateEverythingFrom(idx)
      }
    }
    if (!validateTunables) {
      Console.println("Invalid tunables generated after mutation!")
    }
    this
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
    if (!validateTunables) {
      Console.println("Invalid tunables generated after mutation!")
    }

  }

  def getnew() = {
    new Tunable(mdim, ndim, pdim, numLevels)
  }

  def this(M: Int, N: Int, P: Int, levels: Int) = {
    this()
    val numLevelsRange = List(levels)
    val emptyDepList = List[(Int, (Int, Int) => Unit)]()
    numLevels = levels
    mdim = M
    ndim = N
    pdim = P
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
    if (!validateTunables) {
      Console.println("Invalid tunables created within new!")
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
