package com.dynamic.program.stacks_queues

import java.util.*

/**
 * Monotonic - stack problem
 * A monotonic stack or queue is one whose elements are always sorted.
 * It can be sorted either ascending or descending, depending on the algorithm.
 * Monotonic stacks and queues maintain their sorted property by removing elements
 * that would violate the property before adding new elements.
 *
 *
 * Daily Temperatures
 *
 * Given an array of integers temperatures that represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith
 * day to get a warmer temperature. If there is no future day that is warmer, have answer[i] = 0 instead.
 */

fun dailyTemperatures(t: IntArray): List<Int> {
    val queue = Stack<Int>()
    val wDays = MutableList(t.size) { 0 }
    for (i in t.indices) {
        while (queue.isNotEmpty() && t[i] > t[queue.peek()]) {
            val qIdx = queue.pop()
            wDays[qIdx] = i - qIdx
        }
        queue.push(i)
    }
    return wDays
}

fun main() {
    println(dailyTemperatures(intArrayOf(40, 35, 32, 37, 50)))
}
