package com.dynamic.program.hashing

/**
 * Intersection of Multiple Arrays
 *
 * Given a 2D array nums that contains n arrays of distinct integers, return a sorted array containing all the numbers that appear in all n arrays.
 *
 * For example, given nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]], return [3, 4]. 3 and 4 are the only numbers that are in all arrays.
 */

fun intersectionOfMultipleArrays(nums: List<IntArray>): List<Int> {
    val list = mutableListOf<Int>()
    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        for (n in num) {
            map[n] = map[n]?.plus(1) ?: 1
        }
    }
    map.forEach { (key, value) ->
        if (value == nums.size) {
            list.add(key)
        }
    }
    return list
}

fun main() {
    println(intersectionOfMultipleArrays(listOf(intArrayOf(3, 1, 2, 4, 5), intArrayOf(1, 2, 3, 4), intArrayOf(3, 4, 5, 6))))
}
