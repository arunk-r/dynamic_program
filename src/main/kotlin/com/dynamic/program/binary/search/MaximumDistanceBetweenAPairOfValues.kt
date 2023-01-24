package com.dynamic.program.binary.search

/**
 * Maximum Distance Between a Pair of Values
 * You are given two non-increasing 0-indexed integer arrays nums1 and nums2
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 *
 *
 * Example 1:
 * Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * Output: 2
 * Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
 * The maximum distance is 2 with pair (2,4).
 *
 *
 * Example 2:
 * Input: nums1 = [2,2,2], nums2 = [10,10,1]
 * Output: 1
 * Explanation: The valid pairs are (0,0), (0,1), and (1,1).
 * The maximum distance is 1 with pair (0,1).
 *
 *
 * Example 3:
 * Input: nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * Output: 2
 * Explanation: The valid pairs are (2,2), (2,3), (2,4), (3,3), and (3,4).
 * The maximum distance is 2 with pair (2,4).
 *
 *
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^5
 * Both nums1 and nums2 are non-increasing.
 */
class MaximumDistanceBetweenAPairOfValues {
    fun maxDistance(nums1: IntArray, nums2: IntArray): Int {
        var max = 0
        for(i in nums1.indices) {
            val k = binarySearch(nums1[i], nums2)
            if (i < k) {
                max = Math.max(max, k - i)
            }
        }
        return max
    }

    private fun binarySearch(k: Int, nums2: IntArray): Int {
        var left = 0
        var right = nums2.size - 1
        while(left < right) {
            val mid = left + (right - left + 1) / 2
            if (nums2[mid] < k) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return left
    }

    fun maxDistance1(nums1: IntArray, nums2: IntArray): Int {
        var i = 0
        var j = 0
        var max = 0
        while(j < nums2.size && i < nums1.size) {
            if(nums1[i] <= nums2[j]) {
                max = kotlin.math.max(max, j - i)
            }
            else if(nums1[i] > nums2[j]) {
                ++i
            }
            ++j
        }

        return max
    }
}