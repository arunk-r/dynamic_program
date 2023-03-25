package com.dynamic.program.arrays.medium

/**
 * 238. Product of Array Except Self
Medium

Amazon
Bloomberg
Google
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
class ProductOfArrayExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {
        val size = nums.size
        val op = IntArray(size)

        op[0] = 1
        for(i in 1 until size) {
            op[i] = op[i-1] * nums[i-1]
        }

        var right = 1
        for(i in size - 1 downTo 0) {
            op[i] = op[i] * right
            right *= nums[i]
        }

        return op
    }

    fun productExceptSelf1(nums: IntArray): IntArray {
        val size = nums.size
        val op = IntArray(size)

        val left = IntArray(size)
        left[0] = 1
        val right = IntArray(size)
        right[size-1] = 1

        var l = 1
        var r = size - 2

        while(l<size && r >= 0) {
            left[l] = left[l-1] * nums[l-1]
            right[r] = right[r+1] * nums[r+1]
            r--
            l++
        }

        for(i in left.indices) {
            op[i] = left[i] * right[i]
        }

        return op
    }
}
