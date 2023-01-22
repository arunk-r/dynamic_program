package com.dynamic.program.binary.search

import java.util.*


/**
 * Divide Chocolate (HARD)
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 *
 *
 * Example 1:
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 *
 *
 * Example 2:
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 *
 *
 * Example 3:
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 *
 * Constraints:
 * 0 <= k < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 *
 */
class DivideChocolate {
    fun maximizeSweetness(sweetness: IntArray, k: Int): Int {
        var left = sweetness[0]
        var right = 0

        for (s in sweetness) {
            left = Math.min(left, s)
            right += s
        }

        right /= (k + 1)

        while (left < right) {
            val mid = (left + right + 1) / 2

            val nPeople = getCnt(sweetness, mid)

            if (nPeople < (k+1)) {
                // not valid (too high)
                right = mid - 1
            } else {
                // valid
                left = mid
            }

        }

        return right
    }

    private fun getCnt(sweetness: IntArray,  mid: Int): Int {
        var currSweetness = 0
        var nPeople = 0
        for (s in sweetness) {
            currSweetness += s

            if (currSweetness >= mid) {
                currSweetness = 0
                nPeople++
            }
        }
        return nPeople
    }
}

fun main() {
    println(DivideChocolate().maximizeSweetness(intArrayOf(1,2,2,1,2,2,1,2,2),2))
}