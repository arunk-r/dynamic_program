package com.dynamic.program.arrays.medium

import java.util.*

/**
 * 209. Minimum Size Subarray Sum
Medium

company
Microsoft
company
Apple
company
Amazon
Given an array of positive integers nums and a positive integer target, return the minimal length of a
subarray
whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.



Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0


Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
class MinimumSizeSubArraySum {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var ans = Int.MAX_VALUE
        val op = IntArray(nums.size)
        op[0] = nums[0]
        for (i in 1 until nums.size) {
            op[i] = op[i-1] + nums[i]
        }
        for (i in 1 until nums.size) {
            val newT = target + op[i-1]
            val bounds = Arrays.binarySearch(op, newT)
            if (bounds > 0) {
                ans = minOf(ans, (bounds - i+1))
            }
        }


        if (ans  == Int.MAX_VALUE) {
            ans = 0
        }
        return ans
    }
    fun minSubArrayLen1(target: Int, nums: IntArray): Int {

        var ans = Int.MAX_VALUE
        var sum = 0
        var left = 0
        for(i in nums.indices) {
            sum += nums[i]

            while(sum >= target) {
                ans = minOf(ans, i-left)
                sum -= nums[left++]
            }
        }

        if (ans  == Int.MAX_VALUE) {
            ans = 0
        } else {
            ans++
        }
        return ans
    }
}

fun main() {
    println(MinimumSizeSubArraySum().minSubArrayLen(15, intArrayOf(1,2,3,4,5)))
}
