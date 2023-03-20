package com.dynamic.program.trees.dfs.hard

/**
 * 410. Split Array Largest Sum
Hard

Hudson River Trading
Amazon
Google
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.



Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.


Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
 */
class SplitArrayLargestSum {
    fun splitArray(nums: IntArray, k: Int): Int {
        if (nums.isEmpty()) return 0
        var left = 0
        var right = 0
        for (n in nums) {
            left = maxOf(n, left)
            right += n
        }

        var res = 0
        while (left <= right) {
            val mid = left + ((right - left) / 2)
            if (canSplit(mid, nums, k)) {
                res = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return res
    }

    private fun canSplit(size: Int, nums: IntArray, m: Int): Boolean {
        var cnt = 1 // we know that we can make one group
        var curSum = 0
        for (n in nums) {
            curSum += n
            if (curSum > size) {
                curSum = n
                cnt++
            }
        }

        return cnt <= m
    }

    fun splitArray1(nums: IntArray, k: Int): Int {

        val dp = Array(nums.size+1) { IntArray(k+1) { -1 } }

        fun dfs(i: Int, m: Int): Int {
            if (m == 1) {
                var sum = 0
                for (j in i until nums.size) {
                    sum += nums[j]
                }
                return sum
            }
            if (dp[i][m] != -1) return dp[i][m]

            var res = Int.MAX_VALUE
            var curSum = 0
            for (j in i until nums.size - m + 1) {
                curSum = nums[j]
                val v = dfs(j + 1, m - 1)
                dp[j+1][m-1] = v
                val maxSum = maxOf(curSum, v)
                res = minOf(res, maxSum)
                if (curSum > res) break
            }
            dp[i][m] = res
            return res
        }
        return dfs(0, k)
    }
}

fun main() {
    println(SplitArrayLargestSum().splitArray(intArrayOf(7,2,5,10,8), 2))
}
