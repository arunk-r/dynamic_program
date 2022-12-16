package com.dynamic.program.hashing

/**
 *  Subarray Sum Equals K
 *
 *  Given an integer array nums and an integer k, find the number of subarrays whose sum is equal to k.
 *
 *   the constraints say -1000 <= nums[\i] <= 1000
 */

fun subarraySum(nums: IntArray, k: Int): Int {
    var total = 0
    var cnt = 0
    val map = hashMapOf<Int, Int>()
    map[0] = 1
    nums.forEach { n ->
        cnt += n
        total += map[cnt - k] ?: 0
        map[cnt] = map[cnt]?.plus(1) ?: 1
    }
    return total
}

fun main() {
    println(subarraySum(intArrayOf(1, 2, 1, 2, 1), 3))
    println(subarraySum(intArrayOf(1, -1, 1, -1), 0))
}
