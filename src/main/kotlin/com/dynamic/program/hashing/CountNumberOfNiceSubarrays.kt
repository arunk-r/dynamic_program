package com.dynamic.program.hashing

/**
 * Count Number of Nice Subarrays
 *
 * Given an array of positive integers nums and an integer k. Find the number of subarrays with exactly k odd numbers in them.
 *
 * For example, given nums = [1, 1, 2, 1, 1], k = 3, the answer is 2. The subarrays with 3 odd numbers in them are [1, 1, 2, 1 .] and [. 1, 2, 1, 1].
 */

fun numberOfSubarrays(nums: IntArray, k: Int): Int {
    var ans = 0
    var cnt = 0
    val map = hashMapOf<Int, Int>()
    map[0] = 1
    nums.forEach { n ->
        cnt += n%2
        ans += map.getOrDefault(cnt - k, 0)
        map[cnt] = map.getOrDefault(cnt, 0) + 1
    }

    return ans
}

fun main() {
    println(numberOfSubarrays(intArrayOf(1, 1, 2, 1, 1, 2), 3))
}
