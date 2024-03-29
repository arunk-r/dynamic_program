package com.dynamic.program.binary.search.hard

/**
 * 4. Median of Two Sorted Arrays
Hard

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).



Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.


Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */
class MedianOfTwoSortedArrays {
    fun findMedianSortedArrays3(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size
        val new = IntArray(m+n)
        var n1Idx = 0
        var n2Idx = 0
        var idx = 0
        while (n1Idx < m && n2Idx < n) {
            if (nums1[n1Idx] <= nums2[n2Idx]) {
                new[idx] = nums1[n1Idx]
                n1Idx++
            } else {
                new[idx] = nums2[n2Idx]
                n2Idx++
            }
            idx++
        }
        while(n1Idx < m) {
            new[idx] = nums1[n1Idx]
            n1Idx++
            idx++
        }
        while(n2Idx < n) {
            new[idx] = nums2[n2Idx]
            n2Idx++
            idx++
        }
        val midIndex = (new.size - 1) / 2

        return if (new.isEmpty()) {
            -1.0
        } else if (new.size == 1) {
            new[0].toDouble()
        } else if (new.size % 2 == 0) {
            (new[midIndex] + new[midIndex + 1]).toDouble() / 2.0
        } else {
            new[midIndex].toDouble()
        }
    }

    fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
        return median(combine(nums1, nums2))
    }

    fun combine(nums1: IntArray, nums2: IntArray): IntArray {
        val m = nums1.size
        val n = nums2.size
        val combined = IntArray(m+n)
        var n1Idx = 0
        var n2Idx = 0
        var cIdx = 0
        while (n1Idx < m || n2Idx < n) {
            if (n1Idx == m) {
                combined[cIdx] = nums2[n2Idx]
                n2Idx++
            } else if (n2Idx == n) {
                combined[cIdx] = nums1[n1Idx]
                n1Idx++
            } else if (nums1[n1Idx] < nums2[n2Idx]) {
                combined[cIdx] = nums1[n1Idx]
                n1Idx++
            } else {
                combined[cIdx] = nums2[n2Idx]
                n2Idx++
            }
            cIdx++
        }
        return combined
    }

    fun median(nums: IntArray): Double {
        val midIndex = (nums.size - 1) / 2

        return if (nums.isEmpty()) {
            -1.0
        } else if (nums.size == 1) {
            nums[0].toDouble()
        } else if (nums.size % 2 == 0) {
            (nums[midIndex] + nums[midIndex + 1]).toDouble() / 2.0
        } else {
            nums[midIndex].toDouble()
        }
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val (sm, lg) = if (nums1.size <= nums2.size) Pair(nums1, nums2) else Pair(nums2, nums1) //find small list and large list
        val m = sm.size // small list size
        val n = lg.size // large list size
        val total = m + n
        val half = total / 2
        var l = 0
        var r = m


        while (l <= r) {
            val midSm = l + (r - l) / 2
            val midLg = half - midSm - 1

            val smLeft = if (midSm < 0) Int.MIN_VALUE else sm[midSm]
            val smRight = if (midSm+1 >= m) Int.MAX_VALUE else sm[midSm+1]

            val lgLeft = if (midLg < 0) Int.MIN_VALUE else lg[midLg]
            val lgRight = if (midLg+1 >= n) Int.MAX_VALUE else lg[midLg+1]

            if (smLeft <= lgRight && lgLeft <= smRight) {
                if ((m+n) % 2 == 0) {
                    return (maxOf(lgLeft, smLeft) + minOf(lgRight, smRight)) / 2.0
                } else {
                    return maxOf(lgLeft, smLeft) * 1.0
                }
            } else if (smRight > lgLeft) {
                r = midSm - 1
            } else {
                l = midSm + 1
            }
        }
        return -1.0
    }
}

fun main() {
    println(MedianOfTwoSortedArrays().findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)))
    println(MedianOfTwoSortedArrays().findMedianSortedArrays(intArrayOf(0, 0, 0, 0, 0), intArrayOf(-1, 0, 0, 0, 0, 0, 1)))
}
