package com.dynamic.program.strings_arrays.medium

/**
 * https://leetcode.com/problems/spiral-matrix-iii/description/
 *
 * 885. Spiral Matrix III
 * Medium
 * 807
 * 787
 * company
 * Microsoft
 * company
 * Apple
 * company
 * Tesla
 * You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
 *
 * You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.
 *
 * Return an array of coordinates representing the positions of the grid in the order you visited them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: rows = 1, cols = 4, rStart = 0, cStart = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 * Example 2:
 *
 *
 * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 100
 * 0 <= rStart < rows
 * 0 <= cStart < cols
 */
class SpiralMatrixIII {
    fun spiralMatrixIII(rows: Int, cols: Int, rStart: Int, cStart: Int): Array<IntArray> {
        var rowStart = rStart
        var colStart = cStart
        var rowEnd = rStart
        var colEnd = cStart
        var total = 0

        val result = mutableListOf<IntArray>()
        while(true) {
            for(c in colStart .. colEnd) {
                if(c in 0 until cols && rowStart >=0) {
                    result.add(intArrayOf(rowStart, c))
                    total++
                }
            }
            colEnd++
            if (total == rows * cols) break

            for(r in rowStart .. rowEnd) {
                if(colEnd < cols && r in 0 until rows) {
                    result.add(intArrayOf(r, colEnd))
                    total++
                }
            }
            rowEnd++
            if (total == rows * cols) break

            for(c in colEnd downTo  colStart) {
                if(c in 0 until cols && rowEnd < rows) {
                    result.add(intArrayOf(rowEnd, c))
                    total++
                }
            }
            colStart--
            if (total == rows * cols) break

            for(r in rowEnd downTo  rowStart) {
                if(colStart >= 0 && r in 0 until rows) {
                    result.add(intArrayOf(r, colStart))
                    total++
                }
            }
            rowStart--
            if (total == rows * cols) break
        }
        return result.toTypedArray()
    }
}

fun main() {
    println(SpiralMatrixIII().spiralMatrixIII(3,3, 0, 0))
    //println(SpiralMatrixIII().spiralMatrixIII(5,6, 1, 4))
}
