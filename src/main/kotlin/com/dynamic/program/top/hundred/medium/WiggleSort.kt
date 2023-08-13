package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/wiggle-sort/
 *
 * 280. Wiggle Sort
 * Medium
 * 1.1K
 * 98
 * company
 * Amazon
 * company
 * Microsoft
 * company
 * Google
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 * Example 2:
 *
 * Input: nums = [6,6,5,6,3,8]
 * Output: [6,6,5,6,3,8]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 104
 * It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow up: Could you solve the problem in O(n) time complexity?
 */
class WiggleSort {
    fun wiggleSort(nums: IntArray): Unit {
        for (i in nums.indices) {
            if (i + 1 < nums.size) {
                if (i % 2 == 0) {
                    if (nums[i] > nums[i + 1]) {
                        swap(nums, i, i + 1)
                    }
                } else {
                    if (nums[i] < nums[i + 1]) {
                        swap(nums, i, i + 1)
                    }
                }
            }
        }
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        val t = nums[i]
        nums[i] = nums[j]
        nums[j] = t
    }
}
