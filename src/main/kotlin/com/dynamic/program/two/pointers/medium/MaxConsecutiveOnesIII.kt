package com.dynamic.program.two.pointers.medium

/**
 * 1004. Max Consecutive Ones III
Medium

Amazon

Facebook

Adobe
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.



Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
 */
class MaxConsecutiveOnesIII {

    fun longestOnes(nums: MutableList<Int>, k: Int): Int {
        var left = 0
        var max = 0
        var cur = 0

        for(right in nums.indices) {
            if (nums[right] == 0) cur++

            while (cur > k) {
                if (nums[left] == 0) {
                    cur--
                }
                left++
            }
            max = maxOf(max, right - left + 1)
        }
        return max
    }

}

fun main() {
    println(MaxConsecutiveOnesIII().longestOnes(mutableListOf(0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1), 3))
}
