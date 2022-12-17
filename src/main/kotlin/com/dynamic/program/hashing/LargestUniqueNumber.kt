package com.dynamic.program.hashing

import java.util.TreeMap

/**
 * Largest Unique Number
 *
 * Solution
 * Given an integer array nums, return the largest integer that only occurs once. If no integer occurs once, return -1.
 *
 * Example 1:
 * Input: nums = [5,7,3,9,4,9,8,3,1]
 * Output: 8
 * Explanation: The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it is the answer.
 *
 * Example 2:
 *Input: nums = [9,9,8,8]
 * Output: -1
 * Explanation: There is no number that occurs only once.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2000
 * 0 <= nums[\i] <= 1000
 */

fun largestUniqueNumber(nums: IntArray): Int {
    val map = TreeMap<Int, Int>()
    nums.forEach{ n ->
        map[n] = map[n]?.plus(1) ?: 1
    }

    var max = -1
    map.forEach{ (key, value) ->
        if(value == 1) {
            max = key
        }
    }
    return max
}

/**
 * using constrain
 *
 * define array with size 2000
 */
fun largestUniqueNumber_2(nums: IntArray): Int {
    val list = MutableList<Int>(2000){0}
    nums.forEach{ n ->
        list[n] += 1
    }

    var max = -1
    for (i in list.indices) {
        if (list[i] == 1) {
            max = i
        }
    }
    return max
}

fun main() {
    println(largestUniqueNumber(intArrayOf(5,7,3,9,4,9,8,3,1)))
    println(largestUniqueNumber(intArrayOf(9,9,8,8)))

    println(largestUniqueNumber_2(intArrayOf(5,7,3,9,4,9,8,3,1)))
    println(largestUniqueNumber_2(intArrayOf(9,9,8,8)))
}
