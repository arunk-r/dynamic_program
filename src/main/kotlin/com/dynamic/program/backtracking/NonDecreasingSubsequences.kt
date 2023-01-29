package com.dynamic.program.backtracking

/**
 * Non-decreasing Subsequences
 * Given an integer array nums, return all the different possible non-decreasing subsequences of the given array
 * with at least two elements. You may return the answer in any order.
 *
 *
 * Example 1:
 * Input: nums = [4,6,7,7]
 * Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 *
 *
 * Example 2:
 * Input: nums = [4,4,3,2,1]
 * Output: [[4,4]]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 15
 * -100 <= nums[i] <= 100
 */
class NonDecreasingSubsequences {
    fun findSubsequences(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<MutableList<Int>>()
        for( (i, v) in nums.withIndex()) {
            backtrack(i+1, mutableListOf(v), result, nums)
        }
        return result.toList()
    }

    private fun backtrack(idx: Int, cur: MutableList<Int>, result: MutableSet<MutableList<Int>>, nums: IntArray) {
        if (idx >= nums.size) return
        else {
            for( i in idx until nums.size) {
                if (nums[i] >= cur[cur.size - 1]) {
                    cur.add(nums[i])
                    result.add(cur.toMutableList())
                    backtrack(i+1, cur, result, nums)
                    cur.removeAt(cur.size - 1)
                }
            }
        }
    }
}

fun main() {
    println(NonDecreasingSubsequences().findSubsequences(intArrayOf(4,6,7,7)))
}