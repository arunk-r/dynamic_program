package com.dynamic.program.arrays.easy

/**
 * 1991. Find the Middle Index in Array
Easy

Facebook
Adobe
tcs
Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).

A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].

If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.

Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.



Example 1:

Input: nums = [2,3,-1,8,4]
Output: 3
Explanation: The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
The sum of the numbers after index 3 is: 4 = 4
Example 2:

Input: nums = [1,-1,4]
Output: 2
Explanation: The sum of the numbers before index 2 is: 1 + -1 = 0
The sum of the numbers after index 2 is: 0
Example 3:

Input: nums = [2,5]
Output: -1
Explanation: There is no valid middleIndex.


Constraints:

1 <= nums.length <= 100
-1000 <= nums[i] <= 1000
 */
class FindTheMiddleIndexInArray {
    fun findMiddleIndex(nums: IntArray): Int {
        val s = nums.size
        if (s == 1) return 0
        val prefix = IntArray(s)
        prefix[0] = nums[0]
        val suffix = IntArray(s)
        suffix[s-1] = nums[s-1]

        for(i in 1 until s) {
            prefix[i] = prefix[i-1] + nums[i]
        }

        for(i in s-2 downTo 0) {
            suffix[i] = suffix[i+1]+nums[i]
        }

        for(i in nums.indices) {
            val l = i - 1
            val r = i + 1

            if (l < 0 && r < s && suffix[r] == 0) return i
            else if (l >= 0 && r == s && prefix[l] == 0) return i
            else if (l >= 0 && r < s && prefix[l] == suffix[r]) return i

        }

        return -1
    }
}
