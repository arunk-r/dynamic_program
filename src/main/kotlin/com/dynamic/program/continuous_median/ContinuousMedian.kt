package com.dynamic.program.continuous_median

import com.dynamic.program.max_heap.MaxHeap
import com.dynamic.program.min_heap.MinHeap

/**
 *
 * Write a ContinuousMedianHandler class that supports:
 *
 *  The continuous insertion of numbers with the insert method.
 *  The instant (O(1) time) retrieval of the median of the numbers that have been inserted thus far with the getMedian method.
 *
 * The getMedian method has already been written for you. You simply have to write the insert method.
 *
 * The median of a set of numbers is the "middle" number when the numbers are ordered from smallest to greatest.
 * If there's an odd number of numbers in the set, as in {1, 3, 7}, the median is the number in the middle
 * (3 in this case); if there's an even number of numbers in the set, as in {1, 3, 7, 8}, the median is the
 * average of the two middle numbers ((3 + 7) / 2 == 5 in this case).
 *
 * Sample Usage
 * // All operations below are performed sequentially.
 * ContinuousMedianHandler(): - // instantiate a ContinuousMedianHandler
 * insert(5): -
 * insert(10): -
 * getMedian(): 7.5
 * insert(100): -
 * getMedian(): 10
 *
 */

/**
 * How to solve
 *  1. Build minHeap
 *  2. Build maxHeap
 *  3. Re-balance heap
 *  4. get to top element and calculate mean
 */

// Do not edit the class below except for the insert method. Feel free to add new
// properties and methods to the class.
open class ContinuousMedianHandler() {

    private val lowers = MaxHeap(mutableListOf())
    private val greater = MinHeap(mutableListOf())

    // Write your code here.
    private var median: Double? = null

    fun insert(number: Int) {
        if (lowers.heap.isNotEmpty() && number < lowers.peek()) {
            lowers.insert(number)
        } else {
            greater.insert(number)
        }
        rebalanceHeap()
        updateMedian()
    }

    private fun rebalanceHeap() {
        if (lowers.heap.size - greater.heap.size == 2) {
            lowers.remove().let { greater.insert(it) }
        }
        if (greater.heap.size - lowers.heap.size == 2) {
            greater.remove().let { lowers.insert(it) }
        }
    }

    private fun updateMedian() {
        median = if (lowers.heap.size == greater.heap.size) {
            (lowers.peek().toDouble() + greater.peek().toDouble()) / 2
        } else if (lowers.heap.size > greater.heap.size) {
            lowers.peek().toDouble()
        } else {
            greater.peek().toDouble()
        }
    }

    fun getMedian(): Double? {
        return this.median
    }
}

fun main() {
    val c = ContinuousMedianHandler()
    c.insert(5)
    println(c.getMedian())
    c.insert(10)
    println(c.getMedian())
    c.insert(100)
    println(c.getMedian())
    c.insert(200)
    println(c.getMedian())
    c.insert(6)
    println(c.getMedian())
    c.insert(13)
    println(c.getMedian())
    c.insert(14)
    println(c.getMedian())
}