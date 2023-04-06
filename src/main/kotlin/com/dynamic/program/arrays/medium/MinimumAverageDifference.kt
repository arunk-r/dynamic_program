package com.dynamic.program.arrays.medium

/**
 * 2256. Minimum Average Difference
Medium\

company
Amazon
company
Facebook
You are given a 0-indexed integer array nums of length n.

The average difference of the index i is the absolute difference between the average of the first i + 1 elements of nums and the average of the last n - i - 1 elements. Both averages should be rounded down to the nearest integer.

Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.

Note:

The absolute difference of two numbers is the absolute value of their difference.
The average of n elements is the sum of the n elements divided (integer division) by n.
The average of 0 elements is considered to be 0.


Example 1:

Input: nums = [2,5,3,9,5,3]
Output: 3
Explanation:
- The average difference of index 0 is: |2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3.
- The average difference of index 1 is: |(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2.
- The average difference of index 2 is: |(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2.
- The average difference of index 3 is: |(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0.
- The average difference of index 4 is: |(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1.
- The average difference of index 5 is: |(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4.
The average difference of index 3 is the minimum average difference so return 3.
Example 2:

Input: nums = [0]
Output: 0
Explanation:
The only index is 0 so return 0.
The average difference of index 0 is: |0 / 1 - 0| = |0 - 0| = 0.


Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
 */
class MinimumAverageDifference {
    fun minimumAverageDifference(nums: IntArray): Int {
        val n = nums.size
        var ans = -1
        var minAvgDiff = Int.MAX_VALUE
        var currPrefixSum: Long = 0

        // Get total sum of array.
        var totalSum: Long = 0
        for (index in 0 until n) {
            totalSum += nums[index].toLong()
        }
        for (index in 0 until n) {
            currPrefixSum += nums[index].toLong()

            // Calculate average of left part of array, index 0 to i.
            var leftPartAverage = currPrefixSum
            leftPartAverage /= (index + 1).toLong()

            // Calculate average of right part of array, index i+1 to n-1.
            var rightPartAverage = totalSum - currPrefixSum
            // If right part have 0 elements, then we don't divide by 0.
            if (index != n - 1) {
                rightPartAverage /= (n - index - 1).toLong()
            }
            val currDifference = Math.abs(leftPartAverage - rightPartAverage).toInt()
            // If current difference of averages is smaller,
            // then current index can be our answer.
            if (currDifference < minAvgDiff) {
                minAvgDiff = currDifference
                ans = index
            }
        }
        return ans
    }
    fun minimumAverageDifference2(nums: IntArray): Int {
        var totalSum = 0
        for(n in nums) {
            totalSum += n
        }

        var idx = 0
        var min = Int.MAX_VALUE
        var curSum = 0
        for(i in nums.indices) {
            curSum += nums[i]
            val right = totalSum - curSum

            val l = curSum / (i+1)
            val r = if(i < nums.size - 1) {
                right / (nums.size - 1 - i)
            } else {
                0
            }
            val diff = Math.abs(l - r)
            if(diff < min) {
                min = diff
                idx = i
            }

        }

        return idx
    }

    fun minimumAverageDifference1(nums: IntArray): Int {
        val ps = IntArray(nums.size+1)

        for(i in nums.indices) {
            ps[i+1] = ps[i]+nums[i]
        }

        var idx = nums.size-1
        val n = ps.size-1
        var min = Math.abs(ps[n]/(n ))
        for(i in 1 until ps.size-1) {
            val left = ps[i]/i
            val right = (ps[n] - ps[i])/(n-i)
            val diff = Math.abs(left - right)
            if(diff < min) {
                min = diff
                idx = i-1
            }

        }

        return idx
    }
}

fun main() {
    println(MinimumAverageDifference().minimumAverageDifference(intArrayOf(2,5,3,9,5,3)))
}
