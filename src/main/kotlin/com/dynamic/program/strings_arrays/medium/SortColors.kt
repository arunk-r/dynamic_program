package com.dynamic.program.strings_arrays.medium

/**
 * 75. Sort Colors
Medium
Amazon
Microsoft
Yahoo
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.


Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
class SortColors {
    fun sortColors(nums: IntArray): Unit {
        var c0 = 0
        var c1 = 0
        var c2 = 0

        for(n in nums) {
            when(n) {
                0 -> c0++
                1 -> c1++
                2 -> c2++
            }
        }
        for(i in nums.indices) {
            if (c0 > 0) {
                nums[i] = 0
                c0--
            } else if (c0 == 0 && c1 > 0) {
                nums[i] = 1
                c1--
            } else {
                nums[i] = 2
                c2--
            }
        }
    }
    fun sortColors1(nums: IntArray): Unit {
        val map = java.util.TreeMap<Int, Int>()
        for(n in nums) {
            if(map[n] == null) {
                map[n] = 0
            }
            map[n] = map[n]!!+1
        }

        val lst = mutableListOf<Int>()

        var idx = 0
        map.forEach{ (k, v) ->
            for(i in 0 until v) {
                nums[idx++] = k
            }
        }
    }
}
