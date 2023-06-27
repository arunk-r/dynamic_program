package com.dynamic.program.arrays.medium

/**
 * https://leetcode.com/problems/3sum-closest/description/
 *
 * 16. 3Sum Closest
 * Medium
 * 9.3K
 * 484
 * company
 * Adobe
 * company
 * Bloomberg
 * company
 * Amazon
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 *
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 500
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 */
class `3SumClosest` {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()
        var min = Int.MAX_VALUE

        for(i in nums.indices) {
            var l = i+1
            var r = nums.size -1
            while(l < r) {
                val sum = nums[i] + nums[l] + nums[r]
                if (Math.abs(target - sum) < Math.abs(min)) {
                    min = target - sum
                }
                if(sum > target) {
                    r--
                } else {
                    l++
                }
            }
        }

        return target - min
    }
}
