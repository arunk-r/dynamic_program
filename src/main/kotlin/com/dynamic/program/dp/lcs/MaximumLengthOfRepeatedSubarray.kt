package com.dynamic.program.dp.lcs

/**
 * Maximum Length of Repeated Subarray
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.



Example 1:

Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
Example 2:

Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100
 *
 */
class MaximumLengthOfRepeatedSubarray {
    fun findLength(nums1: IntArray, nums2: IntArray): Int {
        var max = 0
        val dp = Array(nums1.size+1){IntArray(nums2.size+1){-1}}
        for (i in 0..nums1.size) {
            for (j in 0 .. nums2.size) {
                dp[i][j] = if (i ==0 || j == 0) 0
                else if (nums1[i-1] == nums2[j-1]) {
                    dp[i-1][j-1]+1
                } else 0
                max = maxOf(max, dp[i][j])
            }
        }
        return max
    }
}

fun main() {
    println(MaximumLengthOfRepeatedSubarray().findLength(intArrayOf(0,1,1,1,1), intArrayOf(1,0,1,0,1)))
}