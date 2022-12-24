package com.dynamic.program.stacks_queues

import kotlin.math.max

/**
 * Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 *
 * Given an array of integers nums and an integer limit, return the size of the longest subarray
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 */

fun longestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit(w: IntArray, k: Int): Int {
    var left = 0
    var ans = 0
    var cnt = w[0]
    for(right in 1 until w.size) {
        while (cnt - w[right] > k) {
            cnt -= w[left]
            left++
        }
        cnt += w[right]
        ans = max(ans, right - left + 1)
    }
    return ans
}

fun main() {
    println(longestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit(intArrayOf(8,2,4,7), 4))
    println(longestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit(intArrayOf(10,1,2,4,7,2), 5))
    println(longestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit(intArrayOf(4,2,2,2,4,4,2,2), 0))
}
