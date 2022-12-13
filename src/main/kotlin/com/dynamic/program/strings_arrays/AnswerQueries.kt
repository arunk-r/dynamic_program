package com.dynamic.program.strings_arrays

/**
 * Given an integer array nums, an array queries where queries[i] = [x, y] and an integer limit,
 * return a boolean array that represents the answer to each query.
 *
 * A query is true if the sum of the subarray from x to y is less than limit, or false otherwise.
 *
 * For example, given nums = [1, 6, 3, 2, 7, 2] and queries = [[0, 3], [2, 5], [2, 4]] and limit = 13,
 * the answer is [true, false, true]. For each query, the subarray sums are [12, 14, 12].
 */

fun answerQueries(nums: IntArray, queries: List<IntArray>, limit: Int): BooleanArray {
    val bool = BooleanArray(queries.size) { false }
    if (nums.isEmpty()) return bool

    val sums = mutableListOf<Int>()
    sums.add(nums[0])
    for (i in 1 until nums.size) {
        sums.add(sums[i - 1] + nums[i])
    }
    for (i in queries.indices) {
        val min = if (queries[i][0] == 0) 0 else queries[i][0] - 1
        val max = queries[i][1]
        if (max == nums.size) {
            bool[i] = false
        } else {
            bool[i] = sums[max] - sums[min] <= limit
        }
    }
    return bool
}

fun main() {
    println(answerQueries(intArrayOf(1, 6, 3, 2, 7, 2), listOf(intArrayOf(0, 3), intArrayOf(2, 5), intArrayOf(2, 4)), 13).toMutableList())
}
