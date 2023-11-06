package com.dynamic.program.arrays.medium

/**
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 *
 * 1493. Longest Subarray of 1's After Deleting One Element
 * Medium
 * 3.6K
 * 61
 * company
 * Yandex
 * company
 * Amazon
 * company
 * Apple
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
class LongestSubarrayAfterDeletingOneElement {
    fun longestSubarray(nums: IntArray): Int {
        var l = 0
        var cnt = 0
        var ans = 0
        for(r in nums.indices) {
            if(nums[r] == 0) {
                cnt++
            }
            while(cnt > 1) {
                if(nums[l++] == 0) {
                    cnt --
                }
            }
            ans = maxOf(ans, r - l)
        }
        return ans
    }
}

fun main() {
    println(LongestSubarrayAfterDeletingOneElement().longestSubarray(intArrayOf(1,1,0,1)))
}
