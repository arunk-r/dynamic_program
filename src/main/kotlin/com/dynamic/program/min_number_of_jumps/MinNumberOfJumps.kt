package com.dynamic.program.min_number_of_jumps

import kotlin.math.max
import kotlin.math.min

/**
 * Min Number Of Jumps
 *
 *
 * You're given a non-empty array of positive integers where each integer represents the
 * maximum number of steps you can take forward in the array. For example, if the
 * element at index 1 is 3, you can go from index
 * 1 to index 2, 3, or 4.
 *
 *
 * Write a function that returns the minimum number of jumps needed to reach the
 * final index.
 *
 *
 * Note that jumping from index i to index i + x always
 * constitutes one jump, no matter how large x is.
 *
 * Sample Input
 * array = [3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3]
 *
 * Sample Output
 * 4 // 3 --> (4 or 2) --> (2 or 3) --> 7 --> 3
 *
 * HINT
 * Try building an array of the minimum number of jumps needed to go from index 0 to all indices. Start at index 0 and progressively build up the array, using previously calculated values to find next ones.
 */

fun minNumberOfJumps(array: List<Int>): Int {
    // Write your code here.
    val jumps = MutableList(array.size) {Int.MAX_VALUE}
    jumps[0] = 0
    for (i in 1 until array.size) {
        for (j in 0..i) {
            if (array[j] >= i-j) {
                jumps[i] = min(jumps[i], jumps[j]+1)
            }
            println(jumps)
        }
    }
    return jumps[jumps.size - 1]
}

fun minNumberOfJumps1(array: List<Int>): Int {
    var maxReach = array[0]
    var steps = array[0]
    var jumps = 1
    for (i in array.indices) {
        maxReach = max(maxReach, array[i]+i)
        steps --
        if (steps ==0) {
            jumps++
            steps = maxReach - i
        }
    }
    return jumps
}

fun main() {
    println(minNumberOfJumps1(listOf(1,1)))
}