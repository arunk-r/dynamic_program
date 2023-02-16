package com.dynamic.program.binary.search

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
class TargetSum {
    var max = 0
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        dp(0, 0, nums, target)
        return max
    }

    fun dp(idx: Int, sum: Int, nums: IntArray, target: Int){
        println("$idx, ${sum+target}")
        if(idx == nums.size) {
            if(sum == target) {
                max++
            }
            return
        } else  {
            dp(idx+1, sum + nums[idx], nums, target)
            dp(idx+1, sum - nums[idx], nums, target)
        }
    }
}

fun main() {
    println(TargetSum().findTargetSumWays(intArrayOf(1,1,1,1,1), 3))
}