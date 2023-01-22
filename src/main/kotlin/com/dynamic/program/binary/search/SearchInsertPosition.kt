package com.dynamic.program.binary.search

/**
 * Search Insert Position
 *
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums contains distinct values sorted in ascending order.
 * -10^4 <= target <= 10^4
 */
class SearchInsertPosition {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while(left <= right) {
            val mid = left + (right - left) / 2
            val tgt = nums[mid]
            if(tgt == target) return mid
            else if (tgt < target) left = mid+1
            else right = mid - 1
        }
        return left
    }
}