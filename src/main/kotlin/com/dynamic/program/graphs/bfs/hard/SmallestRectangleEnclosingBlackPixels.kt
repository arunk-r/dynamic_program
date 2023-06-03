package com.dynamic.program.graphs.bfs.hard

import java.util.Arrays

/**
 * 302. Smallest Rectangle Enclosing Black Pixels
 * Hard
 *
 * company
 * Google
 * You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.
 *
 * The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.
 *
 * Given two integers x and y that represents the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * You must write an algorithm with less than O(mn) runtime complexity
 *
 *
 *
 * Example 1:
 *
 *
 * Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
 * Output: 6
 * Example 2:
 *
 * Input: image = [["1"]], x = 0, y = 0
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 100
 * image[i][j] is either '0' or '1'.
 * 0 <= x < m
 * 0 <= y < n
 * image[x][y] == '1'.
 * The black pixels in the image only form one component.
 */
class SmallestRectangleEnclosingBlackPixels {
    val dir = listOf(Pair(1,0), Pair(0,1), Pair(-1,0), Pair(0,-1))
    fun minArea(image: Array<CharArray>, x: Int, y: Int): Int {
        var row_min = Int.MAX_VALUE
        var row_max = Int.MIN_VALUE
        var col_min = Int.MAX_VALUE
        var col_max = Int.MIN_VALUE

        val map = hashMapOf<Int, MutableList<Int>>()
        val keys = map.keys.toIntArray()
        println(keys)
        Arrays.sort(keys)

        val q = ArrayDeque<Pair<Int, Int>>()
        val seen = hashSetOf<Pair<Int, Int>>()
        q.addLast(Pair(x,y))
        seen.add(Pair(x,y))
        while(q.isNotEmpty()) {
            for (i in q.size downTo 1) {
                val (r, c) = q.removeFirst()
                //println("$r, $c")
                row_min = minOf(row_min, r)
                col_min = minOf(col_min, c)

                row_max = maxOf(row_max, r)
                col_max = maxOf(col_max, c)
                dir.forEach { (r1, c1) ->
                    val nr = r + r1
                    val nc = c + c1
                    val p = Pair(nr, nc)
                    if (nr in image.indices && nc in image[nr].indices && image[nr][nc] == '1' && !seen.contains(p)) {
                        q.addLast(p)
                        seen.add(p)
                    }
                }
            }
        }
        return (row_max - row_min + 1) * (col_max - col_min + 1)
    }
}
