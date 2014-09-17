package optiml.compiler.ops

import scala.reflect.{Manifest,SourceContext}
import optiml.shared._
import optiml.shared.ops._
import optiml.shared.typeclass._
import optiml.compiler._
import optiml.compiler.ops._
import ppl.delite.framework.Config
import scala.collection.mutable.ListBuffer

/**
 * Op Impls
 */

trait DenseMatrixOpsImpl {
  this: OptiMLCompiler with OptiMLLift => 

  def densematrix_object_apply_impl21[T:Manifest](__arg0: Rep[DenseVector[DenseVector[T]]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val numRows = __arg0.length
    val numCols = __arg0(0).length
    (0::numRows, 0::numCols) { (i,j) => __arg0(i).apply(j) }
  }

  def densematrix_object_apply_impl22[T:Manifest](__arg0: Rep[DenseVector[DenseVectorView[T]]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val numRows = __arg0.length
    val numCols = __arg0(0).length
    (0::numRows, 0::numCols) { (i,j) => __arg0(i).apply(j) }
  }

  def densematrix_object_apply_impl23[T:Manifest](__arg0: Seq[Rep[DenseVector[T]]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](0, 0)
    
    for (i: Int <- scala.collection.immutable.Range(0,__arg0.length)) {
      out <<= __arg0(i)
    }
    out.unsafeImmutable
  }

  def densematrix_object_block_impl[T:Manifest](__arg0: Seq[Rep[DenseVector[DenseMatrix[T]]]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    var totalNumRows = 0
    var totalNumCols = 0
    val seq = array_fromseq(__arg0)
    for (i <- 0 until array_length(seq)) {
      val subMatRow = array_apply(seq,i)
      val numRows = subMatRow(0).numRows
      var numCols = subMatRow(0).numCols
      for (j <- 1 until subMatRow.length) {
        fassert(subMatRow(j).numRows == numRows, "dimension mismatch in block matrix constructor: " + subMatRow(j).numRows + " != " + numRows)
        numCols += subMatRow(j).numCols
      }
      totalNumRows += numRows
      if (i == 0) {
        totalNumCols = numCols
      }
      else {
        fassert(numCols == totalNumCols, "dimension mismatch in block matrix constructor: row " + i + " has wrong number of cols " + numCols + " (expected " + totalNumCols + ")")    
      }
      ()
    }
    
    
    val out = DenseMatrix[T](totalNumRows, totalNumCols)
    var rowIdx = 0
    var colIdx = 0
    for (i <- 0 until array_length(seq)) {
      val subMatRow = array_apply(seq,i)
      colIdx = 0
      for (j <- 0 until subMatRow.length) {
        for (k <- 0 until subMatRow(j).numRows) {
    for (l <- 0 until subMatRow(j).numCols) {
      out(rowIdx+k, colIdx+l) = subMatRow(j).apply(k,l)
    }
        }
        colIdx += subMatRow(j).numCols
      }
      rowIdx += subMatRow(0).numRows
    }
    
    out.unsafeImmutable
  }

  def densematrix_object_diag_impl1[T:Arith:Manifest](__arg0: Rep[Int],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    densematrix_fromfunc(__arg0, __arg0, (i,j) =>
    if (i == j) __arg1(i)
    else implicitly[Arith[T]].empty
        )
  }

  def densematrix_object_identity_impl1(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Double]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) =>
    if (i == j) 1.0
    else 0.0
        )
  }

  def densematrix_fromfunc_impl[T:Manifest](__arg0: Rep[Int],__arg1: Rep[Int],__arg2: (Rep[Int],Rep[Int]) => Rep[T])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    (0::__arg0, 0::__arg1) { (i,j) => __arg2(i,j) }
  }

  def matrix_shapeindex_impl(idx: Rep[Int],numCols: Rep[Int])(implicit __pos: SourceContext): Rep[Tup2[Int,Int]] = {
    val rowIndex = idx / numCols
    val colIndex = idx % numCols
    pack(rowIndex,colIndex)
  }

  def densematrix_grouprowsby_helper_impl_key[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit __pos: SourceContext): (Rep[Int]) => Rep[K] = {
    i => __arg2(__arg1.getRow(i))
  }

  def densematrix_grouprowsby_helper_impl_map[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit __pos: SourceContext): (Rep[Int]) => Rep[V] = {
    i => __arg3(__arg1.getRow(i))
  }

  def densematrix_groupcolsby_helper_impl_key[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit __pos: SourceContext): (Rep[Int]) => Rep[K] = {
    i => __arg2(__arg1.getCol(i))
  }

  def densematrix_groupcolsby_helper_impl_map[T:Manifest,K:Manifest,V:Manifest](__arg0: Rep[IndexVector],__arg1: Rep[DenseMatrix[T]],__arg2: (Rep[DenseVectorView[T]]) => Rep[K],__arg3: (Rep[DenseVectorView[T]]) => Rep[V])(implicit __pos: SourceContext): (Rep[Int]) => Rep[V] = {
    i => __arg3(__arg1.getCol(i))
  }

  def densematrix_object_zeros_impl2(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Double]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => 0.0 )
  }

  def densematrix_object_zerosf_impl2(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Float]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => 0f )
  }

  def densematrix_object_ones_impl1(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Double]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => 1.0 )
  }

  def densematrix_object_onesf_impl1(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Float]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => 1f )
  }

  def densematrix_object_rand_impl2(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Double]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => random[Double] )
  }

  def densematrix_object_randf_impl2(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Float]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => random[Float] )
  }

  def densematrix_object_randn_impl1(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Double]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => randomGaussian )
  }

  def densematrix_object_randnf_impl1(__arg0: Rep[Int],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[Float]] = {
    densematrix_fromfunc(__arg0, __arg1, (i,j) => randomGaussian.toFloat )
  }

  def densematrix_toboolean_impl4_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Boolean]): (Rep[T]) => Rep[Boolean] = {
    conv
  }

  def densematrix_todouble_impl5_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Double]): (Rep[T]) => Rep[Double] = {
    conv
  }

  def densematrix_tofloat_impl5_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Float]): (Rep[T]) => Rep[Float] = {
    conv
  }

  def densematrix_toint_impl5_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Int]): (Rep[T]) => Rep[Int] = {
    conv
  }

  def densematrix_flattentovector_impl[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[DenseVector[T]] = {
    (0::self.size) { i => densematrix_raw_apply(self, i) }
  }

  def densematrix_size_impl13[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Int] = {
    self.numRows*self.numCols
  }

  def densematrix_index_impl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext): Rep[Int] = {
    __arg1*self.numCols + __arg2
  }

  def densematrix_apply_impl21[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext): Rep[T] = {
    array_apply(densematrix_raw_data(self), densematrix_index(self,__arg1,__arg2))
  }

  def densematrix_apply_impl25[T:Manifest](self: Rep[DenseMatrix[T]],rows: Rep[IndexVector],cols: Rep[IndexVector])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    (rows,cols) { (i,j) => self(i,j) }
  }

  def densematrix_indices_impl5[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[IndexVector] = {
    IndexVector(0, self.size)
  }

  def densematrix_rowindices_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[IndexVector] = {
    IndexVector(0, self.numRows, false)
  }

  def densematrix_colindices_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[IndexVector] = {
    IndexVector(0, self.numCols)
  }

  def densematrix_vview_impl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int],__arg3: Rep[Int],__arg4: Rep[Boolean])(implicit __pos: SourceContext): Rep[DenseVectorView[T]] = {
    DenseVectorView[T](densematrix_raw_data(self), __arg1, __arg2, __arg3, __arg4)
  }

  def densematrix_getrow_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseVectorView[T]] = {
    self.vview(__arg1*self.numCols, 1, self.numCols, true)
  }

  def densematrix_getrows_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[IndexVector])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    (__arg1, *) { i => self(i) }
  }

  def densematrix_getcol_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseVectorView[T]] = {
    self.vview(__arg1, self.numCols, self.numRows, false)
  }

  def densematrix_getcols_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[IndexVector])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    (*, __arg1) { j => self.getCol(j) }
  }

  def densematrix_diag_impl[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[DenseVector[T]] = {
    val dim = min(self.numRows, self.numCols)
    val indices = (0::dim) { i => i + i*self.numCols }
    indices.t map { i => densematrix_raw_apply(self,i) }
  }

  def densematrix_triu_impl[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    (0::self.numRows, 0::self.numCols) { (i,j) =>
      if (i <= j) self(i,j) else implicitly[Arith[T]].empty  
    }
  }

  def densematrix_tril_impl[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    (0::self.numRows, 0::self.numCols) { (i,j) =>
      if (i >= j) self(i,j) else implicitly[Arith[T]].empty
    }
  }

  def densematrix_pprint_impl5[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Stringable[T]): Rep[Unit] = {
    println(self.makeStr + "\n")
  }

  def densematrix_makestring_impl5[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Stringable[T]): Rep[String] = {
    var s = ""
    if (self == null) {
      s = "null"
    }
    else if (self.numRows == 0) {
      s = "[ ]"
    }
    else {
      for (i <- 0 until self.numRows-1) {
        s = s + self(i).makeStr + "\n"
      }
      s = s + self(self.numRows-1).makeStr
    }
    s
  }

  def densematrix_tostring_impl14[T:Manifest](self: Rep[DenseMatrix[T]]): Rep[String] = {
    var s = ""
    if (self == null) {
      s = "null"
    }
    else if (self.numRows == 0) {
      s = "[ ]"
    }
    else {
      for (i <- 0 until self.numRows-1) {
        s = s + densevectorview_tostring(self(i)) + "\n"
      }
      s = s + densevectorview_tostring(self(self.numRows-1))
    }
    s
  }

  def densematrix_t_impl3[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    (0::self.numCols, 0::self.numRows) { (i,j) => self(j, i) }
  }

  def densematrix_clone_impl5_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): (Rep[T]) => Rep[T] = {
    e => e
  }

  def densematrix_mutable_impl14[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](self.numRows, self.numCols)
    for (i <- 0 until self.numRows) {
      for (j <- 0 until self.numCols) {
        out(i,j) = self(i,j)
      }
    }
    out
  }

  def densematrix_replicate_impl3[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](__arg1*self.numRows, __arg2*self.numCols)
    for (ii <- 0 until __arg1) {
      for (i <- 0 until self.numRows) {
        for (jj <- 0 until __arg2) {
          for (j <- 0 until self.numCols) {
            out(ii*self.numRows+i, jj*self.numCols+j) = self(i,j)
          }
        }
      }
    }
    out.unsafeImmutable
  }

  def densematrix_update_impl7[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[Int],__arg3: Rep[T])(implicit __pos: SourceContext): Rep[Unit] = {
    array_update(densematrix_raw_data(self), densematrix_index(self,__arg1,__arg2), __arg3)
  }

  def densematrix_update_impl8[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    self.updateRow(__arg1, __arg2)
  }

  def densematrix_updaterow_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    (0::__arg2.length) foreach { j => self(__arg1,j) = __arg2(j) }
  }

  def densematrix_updatecol_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    (0::__arg2.length) foreach { i => self(i,__arg1) = __arg2(i) }
  }

  def densematrix_update_impl9[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVectorView[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    self.updateRow(__arg1, __arg2)
  }

  def densematrix_updaterow_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVectorView[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    (0::__arg2.length) foreach { j => self(__arg1,j) = __arg2(j) }
  }

  def densematrix_updatecol_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[DenseVectorView[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    (0::__arg2.length) foreach { i => self(i,__arg1) = __arg2(i) }
  }

  def densematrix_ltlt_impl5[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](0, 0)
    out <<= self
    out <<= __arg1
    out.unsafeImmutable
  }

  def densematrix_ltlt_impl6[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](0, 0)
    out <<= self
    out <<= __arg1
    out.unsafeImmutable
  }

  def densematrix_ltltor_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](0, 0)
    out.insertAllCols(0, self)
    out.insertCol(self.numCols, __arg1)
    out.unsafeImmutable
  }

  def densematrix_ltltor_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](0, 0)
    out.insertAllCols(0, self)
    out.insertAllCols(self.numCols, __arg1)
    out.unsafeImmutable
  }

  def densematrix_ltlteq_impl4[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    self.insertRow(self.numRows, __arg1)
  }

  def densematrix_ltlteq_impl5[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    self.insertAllRows(self.numRows, __arg1)
  }

  def densematrix_ltltoreq_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    self.insertCol(self.numCols, __arg1)
  }

  def densematrix_ltltoreq_impl3[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    self.insertAllCols(self.numCols, __arg1)
  }

  def densematrix_insertrow_impl2[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],y: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    val idx = pos*self.numCols
    if (self.size == 0) densematrix_set_numcols(self, y.length)
    densematrix_insertspace(self, idx, self.numCols)
    val data = densematrix_raw_data(self)
    for (i <- idx until idx+self.numCols){
      array_update(data,i,y(i-idx))
    }
    densematrix_set_numrows(self, self.numRows+1)
  }

  def densematrix_insertallrows_impl[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],xs: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    val idx = pos*self.numCols
    if (self.size == 0) densematrix_set_numcols(self, xs.numCols)
    val sz = self.numCols*xs.numRows
    densematrix_insertspace(self, idx, sz)
    val data = densematrix_raw_data(self)
    for (i <- idx until idx+sz){
      array_update(data,i,densematrix_raw_apply(xs, i-idx))
    }
    densematrix_set_numrows(self, self.numRows+xs.numRows)
  }

  def densematrix_insertcol_impl2[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],y: Rep[DenseVector[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    val newCols = self.numCols+1
    if (self.size == 0) densematrix_set_numrows(self, y.length)
    val outData = array_empty[T](self.numRows*newCols)
    for (i <- 0 until self.numRows){
      var col = 0
      for (j <- 0 until newCols) {
        if (j == pos){
          outData(i*newCols+j) = y(i)
        }
        else{
          outData(i*newCols+j) = self(i,col)
          col += 1
        }
      }
    }
    densematrix_set_raw_data(self, outData.unsafeImmutable)
    densematrix_set_numcols(self, newCols)
  }

  def densematrix_insertallcols_impl[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],xs: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    val newCols = self.numCols+xs.numCols
    if (self.size == 0) densematrix_set_numrows(self, xs.numRows)
    val outData = array_empty[T](self.numRows*newCols)
    for (i <- 0 until self.numRows){
      var col = 0
      for (j <- 0 until newCols){
        if (j < pos || j >= pos+xs.numCols){
          outData(i*newCols+j) = self(i,col)
          col += 1
        }
        else{
          outData(i*newCols+j) = xs(i,j-pos)
        }
      }
    }
    densematrix_set_raw_data(self, outData.unsafeImmutable)
    densematrix_set_numcols(self, newCols)
  }

  def densematrix_trim_impl3[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Unit] = {
    val data = densematrix_raw_data(self)
    if (self.size < array_length(data)) {
      val d = array_empty[T](self.size)
      array_copy(data, 0, d, 0, self.size)
      densematrix_set_raw_data(self, d.unsafeImmutable)
    }
  }

  def densematrix_removerow_impl2[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    self.removeRows(pos, 1)
  }

  def densematrix_removecol_impl2[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    self.removeCols(pos, 1)
  }

  def densematrix_removerows_impl2[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],num: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    val idx = pos*self.numCols
    val len = num*self.numCols
    val data = densematrix_raw_data(self)
    array_copy(data, idx + len, data, idx, self.size - (idx + len))
    densematrix_set_numrows(self, self.numRows - num)
  }

  def densematrix_removecols_impl2[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],num: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    val newCols = self.numCols-num
    val outData = array_empty[T](self.numRows*newCols)
    for (i <- 0 until self.numRows){
      var col = 0
      for (j <- 0 until self.numCols){
        if (j < pos || j >= pos+num){
          outData(i*newCols+col) = self(i,j)
          col += 1
        }
      }
    }
    densematrix_set_raw_data(self, outData.unsafeImmutable)
    densematrix_set_numcols(self, newCols)
  }

  def densematrix_insertspace_impl[T:Manifest](self: Rep[DenseMatrix[T]],pos: Rep[Int],len: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    fassert(pos >= 0 && pos <= self.size, "densematrix_insertspace: index out of bounds")
    densematrix_ensureextra(self,len)
    val d = densematrix_raw_data(self)
    array_copy(d, pos, d, pos + len, self.size - pos)
  }

  def densematrix_ensureextra_impl[T:Manifest](self: Rep[DenseMatrix[T]],extra: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    val data = densematrix_raw_data(self)
    if (array_length(data) - self.size < extra) {
      densematrix_realloc(self, self.size+extra)
    }
  }

  def densematrix_realloc_impl[T:Manifest](self: Rep[DenseMatrix[T]],minLen: Rep[Int])(implicit __pos: SourceContext): Rep[Unit] = {
    val data = densematrix_raw_data(self)
    var n = max(4, array_length(data) * 2)
    while (n < minLen) n = n*2
    val d = array_empty[T](n)
    array_copy(data, 0, d, 0, self.size)
    densematrix_set_raw_data(self, d.unsafeImmutable)
  }

  def densematrix_pl_impl74_zip[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => a+b
  }

  def densematrix_pl_impl75_map[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e+__arg1
  }

  def densematrix_pleq_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)+densematrix_raw_apply(__arg1,i)) }
  }

  def densematrix_pleq_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)+__arg1) }
  }

  def densematrix_sub_impl58_zip[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => a-b
  }

  def densematrix_sub_impl59_map[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e-__arg1
  }

  def densematrix_subeq_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)-densematrix_raw_apply(__arg1,i)) }
  }

  def densematrix_subeq_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)-__arg1) }
  }

  def densematrix_mulclnmul_impl11_zip[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => a*b
  }

  def densematrix_mul_impl61_map[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e*__arg1
  }

  def densematrix_muleq_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)*densematrix_raw_apply(__arg1,i)) }
  }

  def densematrix_muleq_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)*__arg1) }
  }

  def fact(x: scala.Int): scala.Int = {
    if (x <= 1) 1
    else x * fact(x-1)
  }

  def testWrapper(): scala.Unit = { 
    Console.println("Printing something") 
    for (i <- 0 to 10)
      Console.println(i)
    Console.println("Factorial of 10 is " + fact(10))
  }

//
//  def matrixMult_gold(args: List[Matrix]): Matrix = {
//    val A: Matrix = args(0)  // M x P
//    val B: Matrix = args(1)  // P x N
//
//    val M = A.numRows
//    val P = A.numCols
//    val N = B.numCols
//    val C = new Matrix(M, N, x => 0)  // M x N
//
//    for (rowIdx <- 0 until M) {
//      for (colIdx <- 0 until N) {
//        var acc = 0
//        for (iter <- 0 until P) {
//          acc += A(rowIdx, iter) * B(iter, colIdx)
//        }
//        C(rowIdx, colIdx) = acc
//      }
//    }
//    C
//  }


  def unroll(unrollFactor: scala.Int)(loopStart: Rep[Int], loopEnd: Rep[Int], step: Int)(body: Rep[Int] => scala.Unit) = {
    val iterCount = (loopEnd-loopStart)
    val residueCount: Rep[Int] = iterCount % unrollFactor
    val residueStart: Rep[Int] = loopEnd - residueCount
    val superStep: Rep[Int] = unrollFactor * step
    for (iter <- loopStart until residueStart by superStep) {
        for (u: Int <- 0 to unrollFactor-1) {
          val arg = iter + u*step
          body(arg)
        }
    }

    // Residue part - makes sense only if the unroll factor is greater than 1
    if (unrollFactor > 1) {
      for (i <- residueStart until loopEnd by step)  {
        body(i)
      }
    }
  }


//  def matrixMult(args: List[Rep[DenseMatrix[T]]])(blockSizes: Tunable[Int]): Matrix = {
//    val A: Matrix = args(0)  // M x P
//    val B: Matrix = args(1)  // P x N
//
//    // Matrix sizes
//    val M = A.numRows
//    val P = A.numCols
//    val N = B.numCols
//
//    // Block sizes
//    val m = blockSizes(0)
//    val p = blockSizes(1)
//    val n = blockSizes(2)
//
//    // Unroll factors
//    val um = blockSizes(3)
//    val up = blockSizes(4)
//    val un = blockSizes(5)
//
//
//    // Number of blocks in each dimension
//    val bm: Int = M / m
//    val bp: Int = P / p
//    val bn: Int = N / n 
//
//    val C = new Matrix(M, N, x => 0)  // M x N
//
//    for (blockm <- 0 until M by m) {
//      for (blockn <- 0 until N by n) {
//        for (blockp <- 0 until P by p) {
//          
//          for (rowIdx <- blockm until blockm+m) {
//            for (colIdx <- blockn until blockn+n) {
//              var acc = C(rowIdx, colIdx)
//              for (tempIter <- blockp until blockp + p) {
//                acc += A(rowIdx, tempIter) * B(tempIter, colIdx)
//              }
//              C(rowIdx, colIdx) = acc
//            }
//          }
//
//        }
//      }
//    }
//
//  C  
//  }


  def densematrix_new[T:Manifest](r: Rep[Int], c: Rep[Int])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    val out = DenseMatrix[T](r, c)
    out.unsafeMutable
  }


//  def densematrix_matmult_impl62a[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]], res: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
//    // Allocation happening here - do not need this for autotuning!
//    //    val out = DenseMatrix[T](self.numRows, __arg1.numCols)
//    val out = res 
//    if (Config.autotuneEnabled) {
//      Console.println("[AUTOTUNER] Autotuner enabled, matmult")
//      Console.println("[AUTOTUNER] Autotunable parameters at this level:")
//      Console.println("[AUTOTUNER] #blocking levels, blockSize, double buffering, block location, transpose")
//
//      val M = self.numRows
//      val P = self.numCols
//      val N = __arg1.numCols
//      val m = 4
//      val p = 4
//      val n = 4
//
//      unroll(1) (0, M, m) { blockm => {
//        unroll(1) (0, N, n) { blockn => { 
//          unroll(1) (0, P, p) { blockp => {
//          
//            unroll(1) (blockm, blockm+m, 1) { rowIdx => {
//              unroll(1) (blockn ,blockn+n, 1) { colIdx => {
//                var acc = out(rowIdx, colIdx)
//                unroll(4) (blockp, blockp + p, 1) { tempIter => {
//                  acc += self(rowIdx, tempIter) * __arg1(tempIter, colIdx)
//                }}
//                out(rowIdx, colIdx) = acc
//              }}
//            }}
//          }}
//        }}
//      }}
//
//    }
//    else {
//      throw new Exception("Non-autotuned version should not use this implementation! ABORT!!")
//    }
//    out.unsafeImmutable
//  }



def densematrix_matmult_impl62a[T:Manifest](m1: Rep[DenseMatrix[T]], m2: Rep[DenseMatrix[T]], out: Rep[DenseMatrix[T]])(tunables: Tunable)(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    val M = m1.numRows
    val P = m1.numCols
    val N = m2.numRows

    if (Config.autotuneEnabled) {
//      Console.println("[AUTOTUNER] Autotuner enabled, matmult")
//      Console.println("[AUTOTUNER] Autotunable parameters at this level:")
//      Console.println("[AUTOTUNER] #blocking levels, blockSize, double buffering, block location, transpose")

      
//      val u1: scala.Int = tunables(3)
//      val u2: scala.Int = tunables(4)
//      val u3: scala.Int = tunables(5)
//      val u4: scala.Int = tunables(6)
//      val u5: scala.Int = tunables(7)
//      val u6: scala.Int = tunables(8)
    
      // Note: Don't add any prints here - that adds a 'Misc1_Println' node in the IR which has a 'Simple' summary. This
      // inadvertently adds a dependency on previously created IR nodes, even if the string to be printed doesn't depend 
      // on anything. If you are unconvinced, add a println("blah") and see how the generated code explodes in size due to
      // all the dependencies

/*
      val m: scala.Int = tunables(0)
      val p: scala.Int = tunables(1)
      val n: scala.Int = tunables(2)
      val u1: scala.Int = 1 
      val u2: scala.Int = 1 
      val u3: scala.Int = 1 
      val u4: scala.Int = 1 
      val u5: scala.Int = 1 
      val u6: scala.Int = 1

      unroll(u1) (0, M, m) { blockm => {
        unroll(u2) (0, N, n) { blockn => { 
          unroll(u3) (0, P, p) { blockp => {
          
            unroll(u4) (blockm, blockm+m, 1) { rowIdx => {
              unroll(u5) (blockn ,blockn+n, 1) { colIdx => {
                var acc = out(rowIdx, colIdx)
                unroll(u6) (blockp, blockp + p, 1) { tempIter => {
                  acc += m1(rowIdx, tempIter) * m2(tempIter, colIdx)
                }}
                out(rowIdx, colIdx) = acc
              }}
            }}
          }}
        }}
      }}

*/



      def bmm(startm: Rep[Int], endm: Rep[Int], startn: Rep[Int], endn: Rep[Int], startp: Rep[Int], endp: Rep[Int], ijkOrder: scala.Int, tunablesList: scala.List[scala.Int]) = { // (implicit __pos: SourceContext,__imp0: Arith[T]) 
        // Ignore block sizes here
        val um: scala.Int = tunablesList(3)
        val un: scala.Int = tunablesList(4)
        val up: scala.Int = tunablesList(5)

        if (ijkOrder == 123) {
           unroll(1) (startm, endm, 1) { i => {
             unroll(1) (startn, endn, 1) { j => {
               unroll(up) (startp, endp, 1) { k => {
                 out(i, j) = out(i, j) + m1(i, k) * m2(k, j) 
               }}
             }}
           }}
        }
        else if (ijkOrder == 132) {
            unroll(1) (startm, endm, 1) { i => {
             unroll(1) (startp, endp, 1) { k => {
               unroll(up) (startn, endn, 1) { j => {
                 out(i, j) = out(i, j) + m1(i, k) * m2(k, j) 
               }}
             }}
           }}
        }
        else if (ijkOrder == 213) {
            unroll(1) (startn, endn, 1) { j => {
             unroll(1) (startm, endm, 1) { i => {
               unroll(up) (startp, endp, 1) { k => {
                 out(i, j) = out(i, j) + m1(i, k) * m2(k, j) 
               }}
             }}
           }}
        }
        else if (ijkOrder == 231) {
            unroll(1) (startn, endn, 1) { j => {
             unroll(1) (startp, endp, 1) { k => {
               unroll(up) (startm, endm, 1) { i => {
                 out(i, j) = out(i, j) + m1(i, k) * m2(k, j) 
               }}
             }}
           }}
        }
        else if (ijkOrder == 312) {
            unroll(1) (startp, endp, 1) { k => {
             unroll(1) (startm, endm, 1) { i => {
               unroll(up) (startn, endn, 1) { j => {
                 out(i, j) = out(i, j) + m1(i, k) * m2(k, j) 
               }}
             }}
           }}
        }
        else if (ijkOrder == 321) {
            unroll(1) (startp, endp, 1) { k => {
             unroll(1) (startn, endn, 1) { j => {
               unroll(up) (startm, endm, 1) { i => {
                 out(i, j) = out(i, j) + m1(i, k) * m2(k, j) 
               }}
             }}
           }}
        }
        else {
          Console.println("Unknown ijkOrder: %d".format(ijkOrder))
          throw new Exception("Abort")
        }
      }
      
      val numLevels:scala.Int = tunables(0)
      val tunableParamsPerLevel = tunables.paramsPerLevel // bm, bn, bp, um, un, up 
      val ijkOrder: scala.Int = tunables(tunables.length-1)
//      val transposeLevel: scala.Int = tunables(tunables.length-1)

      def getTunablesForLevel(level: scala.Int): scala.List[scala.Int] = {
        val startIdx: scala.Int = level * tunableParamsPerLevel + 1
        val endIdx: scala.Int = startIdx + tunableParamsPerLevel - 1
        val tunablesList = new ListBuffer[scala.Int]
        for (i: scala.Int <- startIdx to endIdx) {
          tunablesList.append(tunables(i))
        }
        tunablesList.toList
      }

      def transpose(transposeBuffer: Rep[DenseMatrix[T]], original: Rep[DenseMatrix[T]], startRow: Rep[Int], startCol: Rep[Int], numRows: scala.Int, numCols: scala.Int) = {
        for (trow: scala.Int <- 0 to numRows-1) {
          for (tcol: scala.Int <- 0 to numCols-1) {
            transposeBuffer(trow, tcol) = original(startRow+tcol, startCol+trow)
          }
        }
      }

      // Generate <m,n,p> loop
      def levelGen(level: scala.Int, tunablesList: scala.List[scala.Int]) // Level specific information
                  (startm: Rep[Int], endm: Rep[Int], startn: Rep[Int], endn: Rep[Int], startp: Rep[Int], endp: Rep[Int])
                  (f0: (Rep[Int],Rep[Int],Rep[Int],Rep[Int],Rep[Int],Rep[Int], scala.Int, scala.List[scala.Int]) => scala.Unit)
                  (implicit __pos: SourceContext,__imp0: Arith[T]): scala.Unit = {
//      Console.println("numLevels = %d, level = %d".format(numLevels, level))

//      if (level == transposeLevel) {
//        transpose(transposeBuffer, m2, startRow, startCol, numRows, numCols) 
//      }

        
        if (level == numLevels) {
          f0(startm, endm, startn, endn, startp, endp, ijkOrder, tunablesList)
        }
        else {
          val stepm = tunablesList(0)
          val stepn = tunablesList(1)
          val stepp = tunablesList(2)

          for (m <- startm until endm by stepm) {
            for (n <- startn until endn by stepn) {
              for (p <- startp until endp by stepp) {
                val nextLevel: scala.Int = level+1
                val nextTunables: scala.List[scala.Int] = getTunablesForLevel(nextLevel)
                levelGen(nextLevel, nextTunables)(m, m+stepm, n, n+stepn, p, p+stepp)(f0)
              }
            }
          }
        }
      }

      // Invocation
      val tunablesList: scala.List[scala.Int] = getTunablesForLevel(0)
      levelGen(0, tunablesList)(0, M, 0, N, 0, P)(bmm)

//      val m: scala.Int = tunablesList(0)
//      val n: scala.Int = tunablesList(1)
//      val p: scala.Int = tunablesList(2)
//      val u1: scala.Int = 1
//      val u2: scala.Int = 1
//      val u3: scala.Int = 1
//      val u4: scala.Int = 1
//      val u5: scala.Int = 1
//      val u6: scala.Int = 1
//      unroll(u1) (0, M, m) { blockm => {
//        unroll(u2) (0, N, n) { blockn => { 
//          unroll(u3) (0, P, p) { blockp => {
//            bmm(blockm, blockm+m, blockn, blockn+n, blockp, blockp+p, scala.List(m, p, n, u4, u5, u6))
//          }}
//        }}
//      }}
    }
    else {
      throw new Exception("Non-autotuned version should not use this implementation! ABORT!!")
    }
    out.unsafeImmutable
  }

def densematrix_matmult_impl62b[T:Manifest](m1: Rep[DenseMatrix[T]], m2: Rep[DenseMatrix[T]])(tunables: Tunable)(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {

    val M = m1.numRows
    val P = m1.numCols
    val N = m2.numRows
    val out = DenseMatrix[T](M, N)

    if (Config.autotuneEnabled) {

      val m: scala.Int = tunables(0)
      val p: scala.Int = tunables(1)
      val n: scala.Int = tunables(2)

//      val u1: scala.Int = tunables(3)
//      val u2: scala.Int = tunables(4)
//      val u3: scala.Int = tunables(5)
//      val u4: scala.Int = tunables(6)
//      val u5: scala.Int = tunables(7)
//      val u6: scala.Int = tunables(8)

//      val u1: scala.Int = 1 
//      val u2: scala.Int = 1 
//      val u3: scala.Int = 1 
//      val u4: scala.Int = 1 
//      val u5: scala.Int = 1 
//      val u6: scala.Int = 1
//
//      unroll(u1) (0, M, m) { blockm => {
//        unroll(u2) (0, N, n) { blockn => { 
//          unroll(u3) (0, P, p) { blockp => {
//          
//            unroll(u4) (blockm, blockm+m, 1) { rowIdx => {
//              unroll(u5) (blockn ,blockn+n, 1) { colIdx => {
//                var acc = out(rowIdx, colIdx)
//                unroll(u6) (blockp, blockp + p, 1) { tempIter => {
//                  acc += m1(rowIdx, tempIter) * m2(tempIter, colIdx)
//                }}
//                out(rowIdx, colIdx) = acc
//              }}
//            }}
//          }}
//        }}
//      }}
//
      for (blockm <- 0 until M by m) {
        for (blockn <- 0 until N by n) { 
          for (blockp <- 0 until P by p) {
          
            for (rowIdx <- blockm until (blockm+m)) {
              for (colIdx <- blockn until (blockn+n)) {
                var acc = out(rowIdx, colIdx)
                for (tempIter <- blockp until (blockp+p)) {
                  acc += m1(rowIdx, tempIter) * m2(tempIter, colIdx)
                }
                out(rowIdx, colIdx) = acc
              }
            }
          }
        }
      }


    }
    else {
      throw new Exception("Non-autotuned version should not use this implementation! ABORT!!")
    }
    out.unsafeImmutable
  }



  def densematrix_matmult_impl62[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    fassert(self.numCols == __arg1.numRows, "dimension mismatch: matrix multiply")

//    val out = DenseMatrix[T](self.numRows, __arg1.numCols)
//    val yT = __arg1.t
    val yT = __arg1
      for (rowIdx <- 0 until self.numRows) {
        for (i <- 0 until __arg1.numCols) {
          var acc = self(rowIdx, 0) * yT(i, 0)
          for (j <- 1 until yT.numCols) {
            acc += self(rowIdx, j) * yT(i, j)
          }
//          out(rowIdx, i) = acc
          __arg1(rowIdx, i) = acc
        }
      }
    __arg1.unsafeImmutable
  }

  def densematrix_matmult_impl62c[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {

      var acc = self(0, 0)
      for (rowIdx <- 0 until self.numRows) {
        acc += self(rowIdx, 0)
        __arg1(rowIdx, 0) = acc
      }
    __arg1.unsafeImmutable
  }

  def densematrix_matvecmult_impl63[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseVector[T]] = {
    fassert(self.numCols == __arg1.length && !__arg1.isRow, "dimension mismatch: matrix * vector")
    val out = DenseVector[T](self.numRows, false)
    for (rowIdx <- 0 until self.numRows) {
      out(rowIdx) = self(rowIdx) *:* __arg1
    }
    out.unsafeImmutable
  }

  def densematrix_div_impl59_zip[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => a/b
  }

  def densematrix_div_impl60_map[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e/__arg1
  }

  def densematrix_diveq_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)/densematrix_raw_apply(__arg1,i)) }
  }

  def densematrix_diveq_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[T])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[Unit] = {
    self.indices.foreach { i => densematrix_raw_update(self,i,densematrix_raw_apply(self,i)/__arg1) }
  }

  def densematrix_pl_impl76[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    self + __arg1.toDense
  }

  def densematrix_sub_impl60[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    self - __arg1.toDense
  }

  def densematrix_mulclnmul_impl12[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    self *:* __arg1.toDense
  }

  def densematrix_div_impl61[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseMatrix[T]] = {
    self / __arg1.toDense
  }

  def densematrix_sum_impl4_reduce[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => a+b
  }

  def densematrix_sum_impl4_zero[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[T] = {
    implicitly[Arith[T]].empty
  }

  def densematrix_prod_impl3_reduce[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => a*b
  }

  def densematrix_prod_impl3_zero[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[T] = {
    unit(1.asInstanceOf[T])
  }

  def densematrix_mean_impl4[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,conv: (Rep[T]) => Rep[Double]): Rep[Double] = {
    self.map(conv).sum / self.size
  }

  def densematrix_abs_impl20_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e.abs
  }

  def densematrix_exp_impl19_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e.exp
  }

  def densematrix_log_impl19_map[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): (Rep[T]) => Rep[T] = {
    e => e.log
  }

  def densematrix_sumrows_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseVector[T]] = {
    self.mapRowsToVector { row => sum(row) }
  }

  def densematrix_sumcols_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseVector[T]] = {
    self.mapColsToVector { col => sum(col) }
  }

  def densematrix_minrows_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[DenseVector[T]] = {
    self.mapRowsToVector { row => min(row) }
  }

  def densematrix_mincols_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[DenseVector[T]] = {
    self.mapColsToVector { col => min(col) }
  }

  def densematrix_maxrows_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[DenseVector[T]] = {
    self.mapRowsToVector { row => max(row) }
  }

  def densematrix_maxcols_impl1[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[DenseVector[T]] = {
    self.mapColsToVector { col => max(col) }
  }

  def densematrix_min_impl5_reduce[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => if (a < b) a else b
  }

  def densematrix_min_impl5_zero[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[T] = {
    self(0,0)
  }

  def densematrix_max_impl5_reduce[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): (Rep[T],Rep[T]) => Rep[T] = {
    (a,b) => if (a > b) a else b
  }

  def densematrix_max_impl5_zero[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[T] = {
    self(0,0)
  }

  def densematrix_minindex_impl3[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[Tup2[Int,Int]] = {
    var min = self(0,0)
    var minRow = 0
    var minCol = 0
    for (i <- 0 until self.numRows) {
      for (j <- 0 until self.numCols) {
        if (self(i,j) < min) {
          min = self(i,j)
          minRow = i
          minCol = j
        }
      }
    }
    pack((minRow,minCol))
  }

  def densematrix_maxindex_impl3[T:Manifest](self: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): Rep[Tup2[Int,Int]] = {
    var max = self(0,0)
    var maxRow = 0
    var maxCol = 0
    for (i <- 0 until self.numRows) {
      for (j <- 0 until self.numCols) {
        if (self(i,j) > max) {
          max = self(i,j)
          maxRow = i
          maxCol = j
        }
      }
    }
    pack((maxRow,maxCol))
  }

  def densematrix_clngt_impl1_zip[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): (Rep[T],Rep[T]) => Rep[Boolean] = {
    (a,b) => a > b
  }

  def densematrix_clnlt_impl1_zip[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext,__imp0: Ordering[T]): (Rep[T],Rep[T]) => Rep[Boolean] = {
    (a,b) => a < b
  }

  def densematrix___equal_impl10[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[T]])(implicit __pos: SourceContext): Rep[Boolean] = {
    if (self.numRows != __arg1.numRows || self.numCols != __arg1.numCols) false
      else {
        val c = sum(self.zip(__arg1) { (a,b) => if (a == b) 0 else 1})
        c == 0
      }
  }

  def densematrix___equal_impl11[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[SparseMatrix[T]])(implicit __pos: SourceContext): Rep[Boolean] = {
    self == __arg1.toDense
  }

  def densematrix_map_impl3_map[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[R])(implicit __pos: SourceContext): (Rep[T]) => Rep[R] = {
    e => __arg1(e)
  }

  def densematrix_maprowstovector_impl1[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[R])(implicit __pos: SourceContext): Rep[DenseVector[R]] = {
    self.rowIndices.map(i => __arg1(self(i)))
  }

  def densematrix_mapcolstovector_impl1[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[R])(implicit __pos: SourceContext): Rep[DenseVector[R]] = {
    self.colIndices.map(i => __arg1(self.getCol(i)))
  }

  def densematrix_zip_impl5_zip[T:Manifest,B:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[DenseMatrix[B]],__arg2: (Rep[T],Rep[B]) => Rep[R])(implicit __pos: SourceContext): (Rep[T],Rep[B]) => Rep[R] = {
    (a,b) => __arg2(a,b)
  }

  def densematrix_foreach_impl4_func[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Unit])(implicit __pos: SourceContext): (Rep[T]) => Rep[Unit] = {
    e => __arg1(e)
  }

  def densematrix_count_impl3_map[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Boolean])(implicit __pos: SourceContext): (Rep[T]) => Rep[Int] = {
    e => 1
  }

  def densematrix_count_impl3_reduce[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Boolean])(implicit __pos: SourceContext): (Rep[Int],Rep[Int]) => Rep[Int] = {
    (a,b) => a+b
  }

  def densematrix_count_impl3_zero[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Boolean])(implicit __pos: SourceContext): Rep[Int] = {
    0
  }

  def densematrix_count_impl3_cond[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[T]) => Rep[Boolean])(implicit __pos: SourceContext): (Rep[T]) => Rep[Boolean] = {
    e => __arg1(e)
  }

  def densematrix_findrows_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext): Rep[IndexVector] = {
    IndexVector(self.rowIndices.filter(i => __arg1(self(i))))
  }

  def densematrix_findcols_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext): Rep[IndexVector] = {
    IndexVector(self.colIndices.filter(i => __arg1(self.getCol(i))))
  }

  def densematrix_filterrows_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    self(self.findRows(__arg1))
  }

  def densematrix_filtercols_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Boolean])(implicit __pos: SourceContext): Rep[DenseMatrix[T]] = {
    self.getCols(self.findCols(__arg1))
  }

  def densematrix_foreachrow_impl2[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Unit])(implicit __pos: SourceContext): Rep[Unit] = {
    self.rowIndices foreach { i => __arg1(self(i)) }
  }

  def densematrix_foreachcol_impl1[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[Unit])(implicit __pos: SourceContext): Rep[Unit] = {
    self.colIndices foreach { i => __arg1(self.getCol(i)) }
  }

  def densematrix_maprows_impl[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[DenseVector[R]])(implicit __pos: SourceContext): Rep[DenseMatrix[R]] = {
    val out = DenseMatrix[R](self.numRows, self.numCols)
    (0::self.numRows) foreach { i =>
      out(i) = __arg1(self(i))
    }
    out.unsafeImmutable
  }

  def densematrix_mapcols_impl[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[DenseVector[R]])(implicit __pos: SourceContext): Rep[DenseMatrix[R]] = {
    val out = DenseMatrix[R](self.numRows, self.numCols)
    (0::self.numCols) foreach { j =>
      out.updateCol(j, __arg1(self.getCol(j)))
    }
    out.unsafeImmutable
  }

  def densematrix_reducerows_impl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVector[T]],Rep[DenseVector[T]]) => Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseVector[T]] = {
    val vv = self.rowIndices.map(i => self(i).toDense)
    vv.reduce((a,b) => __arg1(a,b))
  }

  def densematrix_reducecols_impl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVector[T]],Rep[DenseVector[T]]) => Rep[DenseVector[T]])(implicit __pos: SourceContext,__imp0: Arith[T]): Rep[DenseVector[T]] = {
    val vv = self.colIndices.map(i => self.getCol(i).toDense)
    vv.reduce((a,b) => __arg1(a,b))
  }

  def densematrix_grouprowsby_impl[T:Manifest,K:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[K])(implicit __pos: SourceContext): Rep[ForgeHashMap[K,DenseMatrix[T]]] = {
    val grps = densematrix_grouprowsby_helper(self.rowIndices, self, __arg1, (row: Rep[DenseVectorView[T]]) => row)
    val vals = fhashmap_values(grps)
    val submats = vals.map(buf => (0::array_buffer_length(buf), *) { i => array_buffer_apply(buf,i) })
    fhashmap_from_arrays(fhashmap_keys(grps), submats)
  }

  def densematrix_groupcolsby_impl[T:Manifest,K:Manifest](self: Rep[DenseMatrix[T]],__arg1: (Rep[DenseVectorView[T]]) => Rep[K])(implicit __pos: SourceContext): Rep[ForgeHashMap[K,DenseMatrix[T]]] = {
    val grps = densematrix_groupcolsby_helper(self.colIndices, self, __arg1, (col: Rep[DenseVectorView[T]]) => col)
    val vals = fhashmap_values(grps)
    val submats = vals.map(buf => (*, 0::array_buffer_length(buf)) { j => array_buffer_apply(buf,j) })
    fhashmap_from_arrays(fhashmap_keys(grps), submats)
  }

  def densematrix_raw_alloc_impl[T:Manifest,R:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[DenseMatrix[R]] = {
    DenseMatrix[R](self.numRows, self.numCols)
  }

  def densematrix_densematrix_raw_apply_impl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int])(implicit __pos: SourceContext): Rep[T] = {
    array_apply(densematrix_raw_data(self), __arg1)
  }

  def densematrix_densematrix_raw_update_impl[T:Manifest](self: Rep[DenseMatrix[T]],__arg1: Rep[Int],__arg2: Rep[T])(implicit __pos: SourceContext): Rep[Unit] = {
    array_update(densematrix_raw_data(self), __arg1, __arg2)
  }

  def densematrix_dist_impl3(__arg0: Rep[DenseMatrix[Double]],__arg1: Rep[DenseMatrix[Double]],__arg2: DistanceMetric)(implicit __pos: SourceContext): Rep[Double] = {
    __arg2 match {
      case ABS => sum(abs(__arg0 - __arg1))
      case SQUARE => sum(square(__arg0 - __arg1))
      case EUC => sqrt(sum(square(__arg0 - __arg1)))
    }
  }

}
