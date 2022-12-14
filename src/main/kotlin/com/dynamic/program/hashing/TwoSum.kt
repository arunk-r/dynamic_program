package com.dynamic.program.hashing

/**
 * Given an array of integers nums and an integer target, return indices of two numbers such that they add up to target.
 * You cannot use the same index twice.
 */

fun twoSum(nums: IntArray, target: Int): Pair<Int?, Int?> {
    val map = hashMapOf<Int, Int>()
    for (i in nums.indices) {
        val diff = target - nums[i]
        if (map.containsKey(diff)) {
            return Pair(map[diff], i)
        }
        map[nums[i]] = i
    }
    return Pair(null, null)
}

fun main() {
    println(twoSum(intArrayOf(5,2,7,10,3,9), 8))
}
