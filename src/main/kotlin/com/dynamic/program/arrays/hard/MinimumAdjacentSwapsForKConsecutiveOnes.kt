package com.dynamic.program.arrays.hard

/**
 * 1703. Minimum Adjacent Swaps for K Consecutive Ones
Hard

Amazon
Microsoft
Google
You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.

Return the minimum number of moves required so that nums has k consecutive 1's.



Example 1:

Input: nums = [1,0,0,1,0,1], k = 2
Output: 1
Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
Example 2:

Input: nums = [1,0,0,0,0,0,1,1], k = 3
Output: 5
Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
Example 3:

Input: nums = [1,1,0,1], k = 2
Output: 0
Explanation: nums already has 2 consecutive 1's.


Constraints:

1 <= nums.length <= 105
nums[i] is 0 or 1.
1 <= k <= sum(nums)
 */
class MinimumAdjacentSwapsForKConsecutiveOnes {

    fun minMoves(nums: IntArray, k: Int): Int {
        var min = Int.MAX_VALUE
        val ones = mutableListOf<Int>()
        for ((i, v) in nums.withIndex()) {
            if (v == 1) {
                ones.add(i)
            }
        }
        val ps = IntArray(ones.size + 1)

        for (i in ones.indices) {
            ps[i+1] = ps[i] + ones[i]
        }

        for (i in 1 .. ps.size - k) {
            val mid = (i+k) / 2

            val right = ps[i+k-1] - ps[mid]
            val left = ps[mid-1] - ps[i-1]

            var diff = right - left
            if (k % 2 == 0) diff += ps[mid]

            min = minOf(min, diff)
        }
        val r = k -1
        var radios = r * (r+1)/2 * 2
        if (k % 2 == 0) radios += r+1
        return min - radios
    }
}

fun main() {
    println(MinimumAdjacentSwapsForKConsecutiveOnes().minMoves(intArrayOf(1,0,0,1,0,1), 2))
}
