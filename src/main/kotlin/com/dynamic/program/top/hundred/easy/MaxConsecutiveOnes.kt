package com.dynamic.program.top.hundred.easy

/**
 * https://leetcode.com/problems/max-consecutive-ones/description/
 *
 * 485. Max Consecutive Ones
 * Easy
 * 4.9K
 * 436
 * company
 * Yandex
 * company
 * Google
 * company
 * Adobe
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
class MaxConsecutiveOnes {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        if(nums.size == 1) {
            if(nums[0] == 1) return 1
            else return 0
        }
        var l = 0
        var r = 0
        var len = 0
        while(r < nums.size){
            if(nums[l] == 0 && nums[r] == 0) {
                r++
                l++
            } else {
                len = maxOf(len, r - l)
                while(nums[l] != nums[r]) {
                    l++
                }
                r++
            }
        }
        len = maxOf(len, r - l)
        return len
    }
}
