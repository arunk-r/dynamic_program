package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/lonely-pixel-i/
 *
 * 531. Lonely Pixel I
 * Medium
 * 414
 * 41
 * company
 * Amazon
 * company
 * Google
 * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
 *
 * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Example 2:
 *
 *
 * Input: picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == picture.length
 * n == picture[i].length
 * 1 <= m, n <= 500
 * picture[i][j] is 'W' or 'B'.
 */
class LonelyPixelI {
    fun findLonelyPixel(picture: Array<CharArray>): Int {
        val rowMap = hashMapOf<Int, Int>()
        val colMap = hashMapOf<Int, Int>()
        var cnt = 0
        for(r in picture.indices) {
            for(c in picture[r].indices) {
                if(picture[r][c] == 'B') {
                    rowMap[r] = rowMap.getOrDefault(r, 0) + 1
                    colMap[c] = colMap.getOrDefault(c, 0) + 1
                }
            }
        }
        for(r in picture.indices) {
            for(c in picture[r].indices) {
                if(picture[r][c] == 'B' && rowMap[r] == 1 && colMap[c] == 1) {
                    cnt++
                }
            }
        }
        return cnt
    }
}
