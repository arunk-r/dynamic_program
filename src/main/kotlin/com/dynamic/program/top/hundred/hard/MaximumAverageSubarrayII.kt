package com.dynamic.program.top.hundred.hard

/**
 * https://leetcode.com/problems/maximum-average-subarray-ii/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 644. Maximum Average Subarray II
 * Hard
 * 599
 * 68
 * company
 * Google
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is greater than or equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation:
 * - When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average is 12.75
 * - When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.8
 * - When the length is 6, averages are [9.16667] and the maximum average is 9.16667
 * The maximum average is when we choose a subarray of length 4 (i.e., the sub array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
 * Note that we do not consider the subarrays of length < 4.
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= k <= n <= 104
 * -104 <= nums[i] <= 104
 */
class MaximumAverageSubarrayII {
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var lo = nums[0] * 1.0
        var hi = nums[0] * 1.0
        for(num in nums) {
            lo = minOf(lo, num * 1.0)
            hi = maxOf(hi, num * 1.0)
        }
        var prefix = DoubleArray(nums.size + 1)
        while(hi - lo >= 1e-5) {
            val mid = (lo + hi) / 2.0
            for(i in nums.indices) {
                prefix[i+1] = prefix[i] + nums[i] - mid
            }

            var minSum = 0.0
            var maxSum = 0.0
            var valid = false

            for(i in k until prefix.size) {
                maxSum = maxOf(maxSum, prefix[i] - minSum)
                minSum = minOf(minSum, prefix[i - k + 1])
                if(hi > 0) {
                    valid = true
                    break
                }
            }

            if(valid) {
                lo = mid
            } else {
                hi = mid
            }
        }

        return lo
    }

    fun findMaxAverage2(nums: IntArray, k: Int): Double {
        var lo = nums[0] * 1.0
        var hi = nums[0] * 1.0
        for(num in nums) {
            lo = minOf(lo, num * 1.0)
            hi = maxOf(hi, num * 1.0)
        }

        while(hi - lo >= 1e-5) {
            val mid = (lo + hi) / 2.0
            if(check(nums, k, mid)) {
                lo = mid
            } else {
                hi = mid
            }
        }
        return lo
    }

    private fun check(nums: IntArray, k: Int, mid: Double): Boolean {
        var curSum = 0.0
        var prevSum = 0.0
        var minSum = 0.0
        for(i in nums.indices) {
            curSum += (nums[i] - mid)
            if(i >= k-1) {
                if(curSum - minSum >= 0) return true
                prevSum += nums[i - k + 1] - mid
                minSum = minOf(minSum, prevSum)
            }
        }
        return false
    }

    fun findMaxAverage1(nums: IntArray, k: Int): Double {
        var result = Double.MIN_VALUE

        for(i in nums.indices) {
            var sum = 0
            for(j in i until nums.size) {
                sum += nums[j]
                if(j - i + 1 >= k) {
                    result = maxOf(result, (sum * 1.0)/ (j - i + 1))
                }
            }
        }
        return result
    }
}
