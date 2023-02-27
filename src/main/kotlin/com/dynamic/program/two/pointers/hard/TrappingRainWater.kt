package com.dynamic.program.two.pointers.hard


/**
 * 42. Trapping Rain Water
Hard
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
class TrappingRainWater {
    fun trap(height: IntArray): Int {
        var sum = 0
        if (height.isEmpty()) return 0
        var left = 0
        var right = height.size -1
        var leftMax = 0
        var rightMax = 0
        while (left < right) {
            if (height[left] > leftMax) leftMax = height[left]
            if (height[right] > rightMax) rightMax = height[right]
            if (leftMax < rightMax) {
                sum += maxOf(0, leftMax-height[left])
                left++
            } else {
                sum += maxOf(0, rightMax-height[right])
                right --
            }
        }
        return sum
    }

}

fun main() {
    println(TrappingRainWater().trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
}
