package com.dynamic.program.strings_arrays

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 * Example 1:
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[\i] <= 104
 * nums is sorted in non-decreasing order.
 *
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 *
 * time - O(n/2) -> O(n)
 */

fun sortedSquares(nums: IntArray): IntArray {
    var i = 0
    var j = nums.size - 1
    val a = IntArray(nums.size) { -1 }

    var idx = j

    var sq1: Int
    var sq2: Int

    while (i < j) {
        sq1 = nums[i] * nums[i]
        sq2 = nums[j] * nums[j]

        if (sq1 > sq2) {
            a[idx] = sq1
            i++
        } else {
            a[idx] = sq2
            j--
        }
        idx--
    }
    a[idx] = nums[j] * nums[j]
    return a
}

fun main() {
    println(sortedSquares(intArrayOf(-7,-3,2,3,11)).toMutableList())
    println(sortedSquares(intArrayOf(-4,-1,0,3,10)).toMutableList())
}
