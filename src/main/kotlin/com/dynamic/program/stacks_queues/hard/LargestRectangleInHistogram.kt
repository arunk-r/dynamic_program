package com.dynamic.program.stacks_queues.hard

import java.util.Stack
import kotlin.math.min

/**
 * 84. Largest Rectangle in Histogram
Hard

company
Amazon
company
Adobe
company
Apple
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.



Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4


Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
 */
class LargestRectangleInHistogram {
    fun largestRectangleArea(heights: IntArray): Int {
        var max = Int.MIN_VALUE
        val s = Stack<Int>()
        s.add(-1)
        for (rightLimit in heights.indices) {
            while (s.peek() != -1 && heights[s.peek()] >= heights[rightLimit]) {
                val currentHeight = heights[s.pop()]
                val width = rightLimit - s.peek() - 1 // rightLimit - leftLimit - 1 (stack top element is leftLimit)
                max = maxOf(max, width * currentHeight)
            }
            s.add(rightLimit)
        }
        while (s.peek() != -1) {
            val currentHeight = heights[s.pop()]
            val width = heights.size - s.peek() - 1 // max size - leftLimit - 1 (stack top element is leftLimit)
            max = maxOf(max, width * currentHeight)
        }
        return max
    }
    //brute force
    fun largestRectangleArea1(heights: IntArray): Int {
        var max = Int.MIN_VALUE
        for (i in heights.indices) {
            var minH = heights[i]
            for (j in i+1 until heights.size){
                 minH = minOf(minH, heights[j])
                max = maxOf(max, minH * (j - i + 1))
            }
        }
        return max
    }
}

fun main() {
    println(LargestRectangleInHistogram().largestRectangleArea(intArrayOf(2,1,5,6,2,3)))
}
