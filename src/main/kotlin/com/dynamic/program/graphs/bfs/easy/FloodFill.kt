package com.dynamic.program.graphs.bfs.easy

/**
 * https://leetcode.com/problems/flood-fill/description/
 *
 * 733. Flood Fill
 * Easy
 * 7.3K
 * 723
 * company
 * Amazon
 * company
 * Microsoft
 * company
 * Apple
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * Example 2:
 *
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */
class FloodFill {
    /**
     * BFS
     */
    val dir = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val q = ArrayDeque<Triple<Int, Int, Int>>()
        q.add(Triple(sr, sc, image[sr][sc]))
        image[sr][sc] = color

        while(q.isNotEmpty()) {
            val (r, c, clr) = q.removeFirst()
            dir.forEach{ (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                if (nr in image.indices && nc in image[nr].indices && image[nr][nc] == clr && image[nr][nc] != color) {
                    q.add(Triple(nr, nc, image[nr][nc]))
                    image[nr][nc] = color
                }
            }
        }

        return image
    }
}
