package com.dynamic.program.dp

/**
 * Longest Arithmetic Subsequence
 *
 * Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 * Note that:
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 *
 * Example 1:
 * Input: nums = [3,6,9,12]
 * Output: 4
 * Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
 *
 * Example 2:
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation:  The longest arithmetic subsequence is [4,7,10].
 *
 *
 * Example 3:
 * Input: nums = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:  The longest arithmetic subsequence is [20,15,10,5].
 *
 *
 * Constraints:
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 */
class LongestArithmeticSubsequence {
    fun longestArithSeqLength(nums: IntArray): Int {
        var max = 2
        val map = Array(nums.size){hashMapOf<Int, Int>()}
        for (i in nums.indices) {
            for (j in 0 until i) {
                val d = nums[i] - nums[j]
                var len = 2
                if (map[j].containsKey(d)) {
                    len = map[j][d]!! + 1
                }
                map[i][d] = maxOf(len, map[i].getOrDefault(d,0))
                max = maxOf(max, map[i][d]!!)
            }
        }
        return max
    }

}

fun main() {
    println(LongestArithmeticSubsequence().longestArithSeqLength(intArrayOf(3,6,9,12)))
    println(LongestArithmeticSubsequence().longestArithSeqLength(intArrayOf(9,4,7,2,10)))
    println(LongestArithmeticSubsequence().longestArithSeqLength(intArrayOf(20,1,15,3,10,5,8)))
}