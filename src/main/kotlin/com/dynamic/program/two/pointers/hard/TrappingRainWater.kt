package com.dynamic.program.two.pointers.hard

import java.util.Stack
import java.util.TreeSet
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
    fun trapTwoPointers(h: IntArray): Int {
        var area = 0
        var l = 1
        var r = h.size-2
        var lMax = h[0]
        var rMax = h[h.size-1]
        while (l < r) {
            if(h[l] >= lMax) {
                lMax = h[l]
            }
            if(h[r] >= rMax) {
                rMax = h[r]
            }

            area += if(lMax > rMax) {
                maxOf(0, rMax - h[r--])
            } else {
                maxOf(0, lMax - h[l++])
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
        val pf = IntArray(height.size)
        for(i in height.indices) {
            pf[i] = if(i == 0) {
                height[i]
            } else {
                maxOf(height[i], pf[i-1])
            }
        }
        var result = 0
        var rightMax = 0
        for(i in height.size - 1 downTo 0) {
            rightMax = maxOf(rightMax, height[i])
            result += (minOf(pf[i], rightMax) - height[i])
        }
        return result
    }



    fun compare(x: Pair<Int, Int>, y: Pair<Int, Int>): Int {
        return x.first.compareTo(y.first)
    }

}

fun main() {
    println(TrappingRainWater().trapDP(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(TrappingRainWater().trapStk(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(TrappingRainWater().trapTwoPointers(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
}
