package com.dynamic.program.dp

/**
 * 300. Longest Increasing Subsequence
 * Medium
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
class LongestIncreasingSubsequence {
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size){1}
        var ans = 1
        for (i in nums.indices) {
            for(j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        println(dp.toList())
        return ans
    }
}

fun main() {
    println(LongestIncreasingSubsequence().lengthOfLIS(intArrayOf(10,9,2,5,3,7,101,18)))
}
