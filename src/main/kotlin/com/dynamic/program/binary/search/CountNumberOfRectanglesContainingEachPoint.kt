package com.dynamic.program.binary.search

/**
 * Count Number of Rectangles Containing Each Point
 */
class CountNumberOfRectanglesContainingEachPoint {
    fun countRectangles(rectangles: Array<IntArray>, points: Array<IntArray>): IntArray {
        rectangles.sortWith(kotlin.Comparator { x, y -> if (x[0] == y[0]) x[1] - y[1] else x[0] - y[0] })
        val result = IntArray(points.size) { 0 }
        for (i in points.indices) {
            val idx = twoPointer(rectangles, points[i])
            result[i] = idx
        }

        return result
    }

    private fun twoPointer(rectangles: Array<IntArray>, point: IntArray): Int {
        var left = 0
        var right = rectangles.size - 1
        var cnt = 0
        while (left < right) {
            if (rectangles[left][0] >= point[0] && point[1] <= rectangles[left][1]) {
                cnt++
            }
            if (rectangles[right][0] >= point[0] && point[1] <= rectangles[right][1]) {
                cnt++
            }
            left++
            right--
        }
        if (rectangles.size % 2 != 0) {
            if (rectangles[left][0] >= point[0] && point[1] <= rectangles[left][1]) {
                cnt++
            }
        }
        return cnt
    }
}

fun main() {
    println(
        CountNumberOfRectanglesContainingEachPoint().countRectangles(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(2, 5)
            ), arrayOf(intArrayOf(2, 1), intArrayOf(1, 4))
        ).toList()
    )
    /*println(
        CountNumberOfRectanglesContainingEachPoint().countRectangles(
            arrayOf(
                intArrayOf(7, 1),
                intArrayOf(2, 6),
                intArrayOf(1, 4),
                intArrayOf(5, 2),
                intArrayOf(10, 3),
                intArrayOf(2, 4),
                intArrayOf(5, 9)
            ), arrayOf(
                intArrayOf(10, 3),
                intArrayOf(8, 10),
                intArrayOf(2, 3),
                intArrayOf(5, 4),
                intArrayOf(8, 5),
                intArrayOf(7, 10),
                intArrayOf(6, 6),
                intArrayOf(3, 6)
            )
        ).toList()
    )*/
}