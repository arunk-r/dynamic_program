package com.dynamic.program.pramp.medium

/**
 * Array of Array Products
 * Given an array of integers arr, you’re asked to calculate for each index i the product of all integers except the
 * integer at that index (i.e. except arr[i]). Implement a function arrayOfArrayProducts that takes an array of
 * integers and returns an array of the products.
 *
 * Solve without using division and analyze your solution’s time and space complexities.
 *
 * Examples:
 *
 * input:  arr = [8, 10, 2]
 * output: [20, 16, 80] // by calculating: [10*2, 8*2, 8*10]
 *
 * input:  arr = [2, 7, 3, 4]
 * output: [84, 24, 56, 42] // by calculating: [7*3*4, 2*3*4, 2*7*4, 2*7*3]
 * Constraints:
 *
 * [time limit] 5000ms
 * [input] array.integer arr
 * 0 ≤ arr.length ≤ 20
 * [output] array.integer
 */
class ArrayOfArrayProducts {
    fun product(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        val pf = IntArray(nums.size)
        val sf = IntArray(nums.size)
        pf[0] = 1
        pf[1] = nums[0]
        for (i in 2 until nums.size) {
            pf[i] = pf[i-1] * nums[i-1]
        }

        sf[nums.size - 1] = 1
        sf[nums.size - 2] = nums[nums.size - 1]
        for (i in nums.size - 3 downTo  0) {
            sf[i] = sf[i+1] * nums[i+1]
        }

        for (i in nums.indices) {
            result[i] = pf[i] * sf[i]
        }

        return result
    }
}

fun main() {
    println(ArrayOfArrayProducts().product(intArrayOf(8, 10, 2)).toList())
    println(ArrayOfArrayProducts().product(intArrayOf(2, 7, 3, 4)).toList())
}
