package com.dynamic.program.arrays.easy

/**
 * https://leetcode.com/problems/majority-element/submissions/
 *
 * 169. Majority Element
 * Easy
 * 15.2K
 * 454
 * company
 * Amazon
 * company
 * Apple
 * company
 * Google
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
class MajorityElement {
    fun majorityElement(nums: IntArray): Int {
        var candidate = 0
        var count = 0
        for(n in nums) {
            if(count == 0) {
                candidate = n
            }
            count += if(n == candidate) 1 else -1
        }
        return candidate
    }

    fun majorityElement1(nums: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        var maxCnt = 0
        var maxValue = 0
        for( n in nums) {
            map.putIfAbsent(n, 0)
            map[n] = map[n]!!+1
            if(maxCnt < map[n]!!) {
                maxCnt = map[n]!!
                maxValue = n
            }
        }

        return maxValue
    }
}
