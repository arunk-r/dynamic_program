package com.dynamic.program.arrays.easy

/**
 * https://leetcode.com/problems/contains-duplicate/description/
 *
 * 217. Contains Duplicate
 * Easy
 * 9.6K
 * 1.2K
 * company
 * Apple
 * company
 * Google
 * company
 * Uber
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
class ContainsDuplicate {
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = hashSetOf<Int>()
        for(n in nums) {
            if(set.contains(n)) return true
            set.add(n)
        }
        return false
    }
}
