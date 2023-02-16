package com.dynamic.program.dp.knapsack

/**
 * Partition to K Equal Sum Subsets
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 * Example 1:
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Example 2:
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 *
 * Constraints:
 * 1 <= k <= nums.length <= 16
 * 1 <= nums[i] <= 104
 * The frequency of each element is in the range [1, 4].
 *
 */
class PartitionToKEqualSumSubsets {
    val map = hashMapOf<String, Boolean>()
    fun canPartitionKSubsets1(nums: IntArray, k: Int): Boolean {
        val sum = nums.sum()
        if (sum % k != 0) return false
        val target = sum / k
        val taken = CharArray(nums.size){'0'}
        return knapshot1(nums, 0, 0, k, target, taken)
    }

    private fun knapshot1(nums: IntArray, count: Int, curSum: Int, k: Int, target: Int, taken: CharArray): Boolean {
        if (count == k - 1) return true
        if (curSum > target) return false
        val s = String(taken)
        if (map.containsKey(s)) return map[s]!!
        if (curSum == target) {
            map[s] = true
            return knapshot1(nums, count + 1, 0, k, target, taken)
        }
        for (i in nums.indices) {
            if (taken[i] == '0') {
                taken[i] = '1'
                if (knapshot1(nums, count, curSum + nums[i], k, target, taken)) {
                    return true
                }
                taken[i] = '1'
            }
        }
        return false
    }

    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        val sum = nums.sum()
        if (sum % k != 0) return false
        val target = sum / k
        val dp = IntArray(k)
        return knapshot(nums, 0, target, k, dp)
    }

    private fun knapshot(nums: IntArray, i: Int, target: Int, k: Int, dp: IntArray): Boolean {
        if (i == nums.size) {
            for (j in dp.indices) {
                if (dp[j] != target) {
                    return false
                }
            }
            return true
        }
        for (j in 0 until k) {
            if (dp[j] + nums[i] <= target) {
                dp[j] += nums[i]
                if (knapshot(nums, i+1, target, k, dp)) {
                    return true
                }
                dp[j] -= nums[i]
            }
        }

        return false
    }
}

fun main() {
    println(PartitionToKEqualSumSubsets().canPartitionKSubsets(intArrayOf(4, 3, 2, 3, 5, 2, 1), 4))
    println(PartitionToKEqualSumSubsets().canPartitionKSubsets(intArrayOf(2, 2, 2, 2, 3, 4, 5), 4))
    println(PartitionToKEqualSumSubsets().canPartitionKSubsets(intArrayOf(1, 1, 1, 1, 2, 2, 2, 2), 2))
}