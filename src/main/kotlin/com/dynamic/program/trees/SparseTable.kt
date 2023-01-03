package com.dynamic.program.trees

import kotlin.math.pow

/**
 * Sparse table is one of the best approach to find the lowest minimum query.
 *
 * https://www.youtube.com/watch?v=uUatD9AudXo
 *
 * Here we need table of size  n x m
 * where m is floor(log(n)) + 1
 * and n is size of the input array
 *
 * Range calculation is between s and e
 * Number of Elements between s and e is `l = e - s + 1`
 *
 * Find the LMQ with number of elements in `l`
 * k = floor(log(l))
 *
 * Next one element always is calculated using j + 2^j
 */
class SparseTable(val list: List<Int>) {
    private var table = MutableList(1) { MutableList(1) { 0 } }

    init {
        val n = list.size
        val m = (Math.floor(Math.log(n * 1.0) / Math.log(2.0)) + 1).toInt() // log(n) + 1
        table = MutableList(n) { MutableList(m) { 0 } }
        for (i in list.indices) { //copy all index values as is from source reference
            table[i][0] = i
        }
        var col = 1
        while (2.0.pow(col) <= n) { // dont want column ( log(n)) values more than n // ex log(6) == 2 should be <= 6
            var row = 0
            while (row + 2.0.pow(col) - 1 < n) { // need to restrict number sublist comparison
                val leftComputedData = table[row][col - 1]
                val rightComputedData = table[row + 2.0.pow(col - 1).toInt()][col - 1] //
                if (list[leftComputedData] < list[rightComputedData]) {
                    table[row][col] = leftComputedData
                } else {
                    table[row][col] = rightComputedData
                }
                row++
            }
            col++
        }
    }

    fun queryMin(left: Int, right: Int): Int {
        if (left >= 0 && left < list.size && right >= 0 && right < list.size) {
            val length = right - left + 1
            val k = Math.floor(Math.log(length * 1.0) / Math.log(2.0)).toInt()
            val leftIdx = table[left][k]
            val rightIdx = table[left + length - 2.0.pow(k).toInt()][k]
            return Math.min(list[leftIdx], list[rightIdx])
        }
        return -1
    }
}

fun main() {
    val sparseTable = SparseTable(listOf(4, 6, 1, 5, 7, 3))
    println(sparseTable.queryMin(1, 4))
    println(sparseTable.queryMin(0, 2))
    println(sparseTable.queryMin(3, 5))
}