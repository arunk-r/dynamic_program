package com.dynamic.program.hard.water_area

import kotlin.math.max
import kotlin.math.min

/**
 * You're given an array of non-negative integers where each non-zero integer
 * represents the height of a pillar of width 1. Imagine water being
 * poured over all of the pillars; write a function that returns the surface area
 * of the water trapped between the pillars viewed from the front. Note that
 * spilled water should be ignored.
 *
 *
 * You can refer to the first three minutes of this question's video explanation
 * for a visual example.
 *
 * Sample Input
 * heights = [0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3]
 *
 * Sample Output
 * 48
 *
 * Below is a visual representation of the sample input.
 * The dots and vertical lines represent trapped water and pillars, respectively.
 * Note that there are 48 dots.
 *        |
 *        |
 *  |.....|
 *  |.....|
 *  |.....|
 *  |..|..|
 *  |..|..|
 *  |..|..|.....|
 *  |..|..|.....|
 * _|..|..|..||.|
 */

fun waterArea(heights: List<Int>): Int {
    // Write your code here.
    if(heights.isEmpty()) return 0
    val leftHeight = MutableList(heights.size) {0}
    leftHeight[0] = heights[0]
    for (i in 1 until heights.size) {
        leftHeight[i] = max(heights[i-1], leftHeight[i-1])
    }
    //println(leftHeight)
    val rightHeight = MutableList(heights.size) {0}
    rightHeight[heights.size-1] = 0
    for (i in heights.size - 2 downTo 0) {
        rightHeight[i] = max(heights[i+1], rightHeight[i+1])
    }
    //println(rightHeight)
    var minHeight: Int
    var sum = 0
    for (i in heights.indices) {
        minHeight = min(leftHeight[i], rightHeight[i])
        if (heights[i]< minHeight) {
            sum += minHeight - heights[i]
        }
    }
    //println(waterCnt)
    return sum
}

fun main() {
    println(waterArea(listOf(0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3)))
}