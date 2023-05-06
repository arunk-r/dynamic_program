package com.dynamic.program.arrays.hard

/**
 * 41. First Missing Positive
Hard

Amazon
Microsoft
IBM
Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.



Example 1:

Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.


Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
 */
class FirstMissingPositive {
    fun firstMissingPositive(nums: IntArray): Int {
        val size  = nums.size
        for (i in nums.indices) {
            var v = nums[i]
            if (v < size) {
                while (v != i && v < size) {
                    val t = nums[v]
                    nums[v] = v
                    nums[i] = t
                    v = t
                }
            }
        }

        for (i in nums.indices) {
            if (i != nums[i]) return i
        }

        return size
    }

    fun firstMissingPositive1(nums: IntArray): Int {
        for(i in nums.indices) {
            if (nums[i] < 0) {
                nums[i] = 0
            }
        }

        for(i in nums.indices) {
            val v = Math.abs(nums[i])
            if (v in 1 .. nums.size) {
                if (nums[v-1] > 0) {
                    nums[v-1] *= -1
                } else if (nums[v-1] == 0) {
                    nums[v-1] = -1 * nums.size+1
                }
            }
        }

        for(i in 1 .. nums.size) {
            if (nums[i-1] >= 0) return i
        }

        return nums.size+1
    }

    fun firstMissingPositive3(nums: IntArray): Int {
        val set = hashSetOf<Int>()
        for(n in nums) {
            if (n > 0) {
                set.add(n)
            }
        }
        var i = 1
        while (i <= nums.size) {
            if(set.contains(i)) {
                i++
            } else {
                return i
            }
        }

        return i
    }

    fun firstMissingPositive2(nums: IntArray): Int {
        nums.sort()
        var ans = 1

        for(n in nums) {
            if (n > 0) {
                if (n == ans){
                    ans++
                } else {
                    if (n > ans) {
                        return ans
                    }
                }
            }
        }

        return ans
    }
}

fun main() {
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(74,2,1,4,3,0)))
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(0,11,12,13,14,15,16)))
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(1,2,0)))
    println(FirstMissingPositive().firstMissingPositive(intArrayOf(1)))
}
