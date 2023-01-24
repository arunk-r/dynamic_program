package com.dynamic.program.binary.search

/**
 * Single Element in a Sorted Array
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
class SingleElementInASortedArray {
    fun singleNonDuplicate(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1
        while(left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] != nums[mid -1] && nums[mid] != nums[mid + 1]) {
                return nums[mid]
            } else if (nums[mid] == nums[mid -1]) {
                if ((mid - left + 1) % 2 == 0) {
                    left = mid + 1
                }
                else {
                    right = mid
                }
            } else {
                if ((right - mid + 1) % 2 == 0) {
                    right = mid - 1
                } else {
                    left = mid
                }
            }
        }

        return nums[left]
    }
}

fun main() {
    println(SingleElementInASortedArray().singleNonDuplicate(intArrayOf(1,2,2,3,3)))
    println(SingleElementInASortedArray().singleNonDuplicate(intArrayOf(1,1,2)))
    println(SingleElementInASortedArray().singleNonDuplicate(intArrayOf(3,3,7,7,10,11,11)))
}