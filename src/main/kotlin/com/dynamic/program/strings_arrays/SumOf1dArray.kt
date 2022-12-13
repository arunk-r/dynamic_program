package com.dynamic.program.strings_arrays

fun runningSum(nums: IntArray): IntArray {
    val r = IntArray(nums.size)
    r[0] = nums[0]
    for (i in 1 until nums.size) {
        r[i] = nums[i]+r[i-1]
    }
    return r
}

fun main() {
    println(runningSum(intArrayOf(1,2,3,4)).toMutableList())
}
