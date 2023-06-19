package com.dynamic.program.strings_arrays.medium

/**
 * 53. Maximum Subarray
Medium

Amazon

Apple

Cisco
Given an integer array nums, find the
subarray
with the largest sum, and return its sum.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
class MaximumSubarray {
    fun maxSubArray(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        var max = dp[0]
        for(i in 1 until nums.size) {
            dp[i] = maxOf(dp[i-1], 0) + nums[i]
            max = maxOf(max, dp[i])
        }

        return max
    }

    fun maxSubArray1(nums: IntArray): Int {
        var max = Int.MIN_VALUE
        var curSum = 0
        for (i in nums) {
            if(curSum < 0) {
                curSum = i
            } else {
                curSum += i
            }
            max = maxOf(max, curSum)
        }

        return max
    }
}
