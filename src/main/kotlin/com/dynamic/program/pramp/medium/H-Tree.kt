package com.dynamic.program.pramp.medium

/**
 *  |     |
 *  |     |
 *  +-----+
 *  |     |
 *  |     |
 *  depth = 1
 *
 *  |     |     |     |
 *  |     |     |     |
 *  +--+--+     +--+--+
 *  |  |  |     |  |  |
 *  |  |  |     |  |  |
 *     |           |
 *     +-----------+
 *     |           |
 *  |  |  |     |  |  |
 *  |  |  |     |  |  |
 *  +--+--+     +--+--+
 *  |     |     |     |
 *  |     |     |     |
 *
 *  depth = 2
 *
 *  Write a function drawHTree that constructs an H-tree, given its center (x and y coordinates), a starting length, and depth. Assume that the starting line is parallel to the X-axis.
 *
 *  Use the function drawLine provided to implement your algorithm. In a production code, a drawLine function would render a real line between two points. However, this is not a real production environment, so to make things easier, implement drawLine such that it simply prints its arguments (the print format is left to your discretion).
 *
 *  fun drawLine(x0: Double, y0: Double, x1: Double, y1: Double) {
 * 	println("($x0, $y0) ($x1, $y1)")
 *  }
 *
 *  // write your code in this function
 *  fun hTree(x: Double, y: Double, length: Double, depth: Int) {
 *  }
 */
class `H-Tree` {
    private fun drawLine(x0: Double, y0: Double, x1: Double, y1: Double) {
        println("($x0, $y0) ($x1, $y1)")
    }

    // write your code in this function
    fun hTree(x: Double, y: Double, length: Double, depth: Int) {
        val x0 = x - length / 2.0
        val x1 = x + length / 2.0
        val y0 = y - length / 2.0
        val y1 = y + length / 2.0

        drawLine(x0, y0, x0, y1) // left vertical line
        drawLine(x1, y0, x1, y1) // right vertical line
        drawLine(x0, y, x1, y)   // center horizontal line

        if (depth > 1) {
            val l = length / Math.sqrt(2.0) // given in problem definition
            hTree(x0, y0, l, depth - 1) // left top point
            hTree(x0, y1, l, depth - 1) // left bottom point
            hTree(x1, y0, l, depth - 1) // right top pint
            hTree(x1, y1, l, depth - 1) // right bottom point
        }
    }
}

fun main() {
    println(`H-Tree`().hTree(0.0, 0.0, 8.0, 3))
}
