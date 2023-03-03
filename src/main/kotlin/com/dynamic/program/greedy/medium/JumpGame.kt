package com.dynamic.program.greedy.medium

/**
 * 55. Jump Game
Medium
Amazon
Microsoft
Bloomberg
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
 */
class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        var farthest = 0
        for (i in 0 until nums.size - 1) {
            if (farthest < i) return false
            farthest = maxOf(farthest, i + nums[i])
        }

        return farthest >= nums.size - 1
    }
}

fun main() {
    println(JumpGame().canJump(intArrayOf(2,3,1,1,4)))
    println(JumpGame().canJump(intArrayOf(3,2,1,0,4)))
}
