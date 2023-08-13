package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/max-consecutive-ones-ii/
 *
 * 487. Max Consecutive Ones II
 * Medium
 * 1.4K
 * 24
 * company
 * Yandex
 * company
 * TikTok
 * company
 * Google
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation:
 * - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
 * The max number of consecutive ones is 4.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 * Explanation:
 * - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
 * The max number of consecutive ones is 4.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 *
 *
 * Follow up: What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */
class MaxConsecutiveOnesII {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var flip = 0
        var l = 0
        var r = 0
        var len = 0
        while(r < nums.size) {
            if(nums[r] == 0) {
                flip++
            }
            while(flip == 2) {
                if(nums[l] == 0) {
                    flip--
                }
                l++
            }
            len = maxOf(len, r - l + 1)
            r++
        }
        return len
    }
}
