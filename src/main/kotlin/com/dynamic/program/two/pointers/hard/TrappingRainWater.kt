package com.dynamic.program.two.pointers.hard

import java.util.Stack
import kotlin.math.max


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
    // 2 pointers
    fun trapTwoPointers(height: IntArray): Int {
        var area = 0
        var l = 1
        var r = height.size-2
        var lMax = height[0]
        var rMax = height[height.size-1]
        while (l < r) {
            if (height[l] > lMax) {
                lMax = height[l]
            }
            if (height[r] > rMax) {
                rMax = height[r]
            }
            if(lMax > rMax) {
                area += maxOf(0, rMax - height[r])
                r--
            } else {
                area += maxOf(0, lMax - height[l])
                l++
            }
        }
        return area
    }
    //Stack
    fun trapStk(height: IntArray): Int {
        var area = 0
        val stk = Stack<Int>()
        var current = 0
        while (current < height.size) {
            while (stk.isNotEmpty() && height[current] > height[stk.peek()]) {
                val idx = stk.pop()
                if (stk.isEmpty()) {
                    break
                }
                val minHgt = minOf(height[current], height[stk.peek()]) - height[idx]
                val distance = current - stk.peek() - 1
                area += maxOf(0, distance * minHgt)

            }
            stk.add(current++)
        }
        return area
    }
    //DP
    fun trapDP(height: IntArray): Int {
        val lHgt = IntArray(height.size)
        val rHgt = IntArray(height.size)
        var l = 0
        var r = height.size - 1
        while (r >= 0) {
            if (l == 0 && r == height.size - 1 ) {
                lHgt[l] = height[l]
                rHgt[r] = height[r]
            } else {
                lHgt[l] = maxOf(height[l], lHgt[l-1])
                rHgt[r] = maxOf(height[r], rHgt[r+1])
            }
            l++
            r--
        }
        var area = 0
        for (i in height.indices) {
            area += minOf(lHgt[i], rHgt[i]) - height[i]
        }
        return area
    }

}

fun main() {
    println(TrappingRainWater().trapDP(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(TrappingRainWater().trapStk(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(TrappingRainWater().trapTwoPointers(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
}
