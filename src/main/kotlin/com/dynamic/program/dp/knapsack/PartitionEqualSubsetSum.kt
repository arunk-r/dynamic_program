package com.dynamic.program.dp.knapsack

/**
 * Given an integer array nums, return true if you can partition the array into two
 * subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
class PartitionEqualSubsetSum {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) return false
        val newTarget = sum / 2
        val dp = Array(newTarget + 1) { BooleanArray(nums.size + 1) }
        for (j in 0..newTarget) {
            for (i in 0..nums.size) {
                dp[j][i] = if (i == 0 && j == 0) true
                else if (i == 0) false
                else if (j == 0) true
                else if (nums[i - 1] <= j) {
                    dp[j - nums[i - 1]][i-1] || dp[j][i - 1]
                } else dp[j][i - 1]
            }
        }
        return dp[newTarget][nums.size]
    }

    val t = Array(201) { IntArray(10001) { -1 } }
    fun canPartition1(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) return false
        return dp(sum / 2, nums.size, nums)
    }

    fun dp(sum: Int, i: Int, nums: IntArray): Boolean {
        if (0 == sum) return true
        else if (i == 0 || sum < 0) return false

        if (t[i][sum] != -1) {
            return t[i][sum] == 1
        }

        if (!dp(sum - nums[i - 1], i - 1, nums)) {
            val r2 = dp(sum, i - 1, nums)
            if (r2) {
                t[i][sum] = 1
            } else {
                t[i][sum] = 0
            }
            return r2
        } else {
            t[i][sum] = 1
            return true
        }
    }
}

fun main() {
    println(PartitionEqualSubsetSum().canPartition(intArrayOf(1, 5, 11, 5)))
    println(PartitionEqualSubsetSum().canPartition(intArrayOf(1, 2, 5)))
}