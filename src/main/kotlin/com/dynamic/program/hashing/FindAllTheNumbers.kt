package com.dynamic.program.hashing

/**
 *  Given an integer array nums, find all the numbers x that satisfy the following: x + 1 is not in nums, and x - 1 is not in nums.
 */
fun findAllTheNumbers(nums: IntArray): MutableList<Int> {
    val set = hashSetOf<Int>()
    for (num in nums) {
        set.add(num)
    }
    val d = mutableListOf<Int>()
    for (num in nums) {
        if (!set.contains(num - 1) && !set.contains(num + 1)) {
            d.add(num)
        }
    }
    return d
}

fun main() {
    println(findAllTheNumbers(intArrayOf(3,4,5,6,7,8,9,10,12,14,16)))
}
