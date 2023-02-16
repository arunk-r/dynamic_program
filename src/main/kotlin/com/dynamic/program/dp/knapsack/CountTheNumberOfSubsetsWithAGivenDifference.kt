package com.dynamic.program.dp.knapsack

/**
 * Target Sum
 *
 * You are given an integer array nums and an integer target.
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 *
 * Example 1:
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 *
 * Example 2:
 * Input: nums = [1], target = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
class CountTheNumberOfSubsetsWithAGivenDifference {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        // problem statement is divide arrary in 2 subsets and difference of 2 subsets id target
        // can I write this like                            s1 - s2 = target
        // can also write this like                         s1 + s2 = sum
        // simplify these 2 equations by adding both =>     2s1 + 0 = target + sum
        //                                                  s1 = (target + sum)/2
        val s1 = (target + sum) / 2

        return countOfSubSetSum(nums, s1)
    }

    private fun countOfSubSetSum(nums: IntArray, sum: Int): Int {
        val dp = Array(nums.size+1){IntArray(sum+1)}
        for (i in 0 .. nums.size) {
            for (j in 0.. sum) {
                dp[i][j] = if (i == 0 && j == 0) 1
                else if (j == 0) 1
                else if (i == 0) 0
                else if (nums[i-1] <= j) {
                    dp[i-1][j-nums[i-1]] + dp[i-1][j]
                } else dp[i-1][j]
            }
        }
        return dp[nums.size][sum]
    }
}

fun main() {
    println(CountTheNumberOfSubsetsWithAGivenDifference().findTargetSumWays(intArrayOf(1,1,1,1,1), 3))
    println(CountTheNumberOfSubsetsWithAGivenDifference().findTargetSumWays(intArrayOf(1), 1))
    println(CountTheNumberOfSubsetsWithAGivenDifference().findTargetSumWays(intArrayOf(1), 2))
}