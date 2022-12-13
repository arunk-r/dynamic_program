package com.dynamic.program.strings_arrays

/**
 * Given an integer array nums, find the number of ways to split the array into two parts
 * so that the first section has a sum greater than or equal to the sum of the second section.
 * The second section should have at least one number.
 */
fun waysToSplitArray(nums: IntArray): Int {
    val sum = mutableListOf<Int>()
    for (i in nums.indices) {
        if (i == 0) sum.add(nums[0])
        else sum.add(nums[i] + sum[i - 1])
    }
    var cnt = 0

    println(sum)
    for (i in 0 until sum.size - 1) {
        if (sum[i] > (sum[sum.size - 1] - sum[i])) {
            cnt++
        }
    }
    return cnt
}

/**
 * Another improvement
 */
fun waysToSplitArrayImprove(nums: IntArray): Int {
    var sum = 0
    for (i in nums.indices) {
        sum += nums[i]
    }
    var cnt = 0
    var leftSection = 0
    for (i in 0 until nums.size - 1) {
        leftSection += nums[i]
        if (leftSection > (sum - leftSection)) {
            cnt++
        }
    }
    return cnt
}

fun main() {
    println(waysToSplitArray(intArrayOf(10, 4, -8, 7)))
    println(waysToSplitArrayImprove(intArrayOf(10, 4, -8, 7)))
}
