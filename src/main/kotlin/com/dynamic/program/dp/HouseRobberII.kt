package com.dynamic.program.dp

/**
 * House Robber II
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 *
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 *
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 3
 *
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
class HouseRobberII {
    fun rob(nums: IntArray): Int {
        val n = nums.size
        var lb = nums[0]
        var ub = 0
        if (n > 1) {
            lb = helper(nums,0, n-1)
            ub = helper(nums,1,n)
        }

        return maxOf(lb,ub)
    }

    fun helper(nums: IntArray, idx: Int, size: Int) : Int {
        var lb = nums[idx]
        var ub = 0
        if (idx+1 < size) {
            ub = maxOf(lb, nums[idx+1])
            for(i in idx+ 2 until size) {
                val tmp = ub
                ub = maxOf(ub, lb+nums[i])
                lb=tmp
            }
        }
        return maxOf(lb,ub)
    }
}