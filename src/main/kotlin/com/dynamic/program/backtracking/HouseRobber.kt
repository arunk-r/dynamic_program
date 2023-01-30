package com.dynamic.program.backtracking

/**
 * House Robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 *
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
class HouseRobber {
    fun rob(nums: IntArray): Int {
        val mem = IntArray(101){-1}
        return backtrack(0,nums, mem)
    }

    fun backtrack(n: Int, nums: IntArray, mem: IntArray): Int {
        if (n >= nums.size) {
            return 0
        }
        if (mem[n] > -1) {
            return mem[n]
        }
        val ans = Math.max(backtrack(n+1, nums, mem), backtrack(n+2, nums, mem) + nums[n])
        mem[n] = ans
        return ans
    }

    fun rob1(nums: IntArray): Int {
        var nextRob = nums[nums.size - 1]
        var currentRob = 0
        for (i in nums.size - 2 downTo 0) {
            val current = Math.max(nextRob, currentRob + nums[i])
            currentRob = nextRob
            nextRob = current
        }
        return nextRob
    }
}