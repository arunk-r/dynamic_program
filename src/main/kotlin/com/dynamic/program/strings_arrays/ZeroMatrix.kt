package com.dynamic.program.strings_arrays

/**
 * Write an algorithm such that if an element in MxN  matrix is 0, then set entire row and column to 0.
 *
 * What is the catch?
 * during zero process how will you differentiate zero is part of original matrix data or part of zero process
 *
 * time ~ O(mn)
 */
fun zeroMatrix(m: MutableList<MutableList<Int>>) {
    val zeroData = mutableListOf<Pair<Int, Int>>()
    for (i in m.indices) {
        for (j in m[i].indices) {
            if (m[i][j] == 0){
                zeroData.add(Pair(i,j))
            }
        }
    }
    zeroData.forEach { d ->
        nullifyRow(m, d.first, m[d.first].size)
        nullifyCol(m, d.second, m.size)
    }
}

fun nullifyRow(m: MutableList<MutableList<Int>>,  r: Int, colCnt: Int) {
    for (i in 0 until colCnt){
        m[r][i] = 0
    }
}

fun nullifyCol(m: MutableList<MutableList<Int>>,  c: Int, rowCnt: Int) {
    for (i in 0 until rowCnt) {
        m[i][c] = 0
    }
}

fun main(){
    val m = mutableListOf(mutableListOf(4,3,2,0), mutableListOf(4,0,2,3), mutableListOf(4,3,1,2), mutableListOf(0,3,2,4))
    zeroMatrix(m)
    println(m)
}
