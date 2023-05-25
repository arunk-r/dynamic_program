package com.dynamic.program.pramp.medium

/**
 * Given an array of integers arr:
 *
 * Write a function flip(arr, k) that reverses the order of the first k elements in the array arr. Write a function pancakeSort(arr) that sorts and returns the input array. You are allowed to use only the function flip you wrote in the first step in order to make changes in the array.
 *
 * Example:
 *
 * input:  arr = [1, 5, 4, 3, 2]
 *
 * output: [1, 2, 3, 4, 5]
 * // to clarify, this is pancakeSort's output
 * // Analyze the time and space complexities of your solution.
 * Note: it’s called pancake sort because it resembles sorting pancakes on a plate with a spatula, where you can only use the spatula to flip some of the top pancakes in the plate. To read more about the problem, see the Pancake Sorting Wikipedia page.
 *
 * Constraints:
 *
 * [time limit] 5000ms
 * [input] array.integer arr
 * [input] integer k
 * 0 ≤ k
 * [output] array.integer
 */
class PancakeSorting {
    fun pancakeSort(nums: IntArray) {
        for (last in nums.size - 1 downTo 1) {
            val maxIdx = findMax(nums, last)
            flip(nums, maxIdx)
            flip(nums, last)
        }
    }

    private fun flip(nums: IntArray, k: Int) {
        if (k == 0) return
        val t = nums[0]
        nums[0] = nums[k]
        nums[k] = t
    }

    private fun findMax(nums: IntArray, end: Int) : Int {
        var idx = 0
        var max = Int.MIN_VALUE
        for (i in 0 .. end) {
            if (nums[i] > max) {
                idx = i
                max = nums[i]
            }
        }
        return idx
    }
}

fun main() {
    val data = intArrayOf(23, 10, 20, 11, 12, 6, 7)
    PancakeSorting().pancakeSort(data)
    println(data.toList())
}
