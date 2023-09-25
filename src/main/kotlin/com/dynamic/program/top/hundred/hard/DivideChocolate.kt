package com.dynamic.program.top.hundred.hard

/**
 * https://leetcode.com/problems/divide-chocolate/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 1231. Divide Chocolate
 * Hard
 * 932
 * 53
 * company
 * Google
 * company
 * Amazon
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 *
 * Constraints:
 *
 * 0 <= k < sweetness.length <= 104
 * 1 <= sweetness[i] <= 105
 *
 */
class DivideChocolate {
    fun maximizeSweetness(sweetness: IntArray, k: Int): Int {
        var min = Int.MAX_VALUE
        var max = 0
        for(sweet in sweetness) {
            min = minOf(min, sweet)
            max += sweet
        }
        max /= (k + 1)
        while(min < max) {
            val mid = (min + max + 1) / 2
            val people = findPeople(sweetness, mid)
            if(people < k+1) {
                max = mid - 1
            } else {
                min = mid
            }
        }

        return max
    }

    private fun findPeople(sweetness: IntArray, k: Int): Int {
        var people = 0
        var cnt = 0
        for(sweet in sweetness) {
            cnt += sweet
            if(cnt >= k) {
                cnt = 0
                people++
            }
        }
        return people
    }
}
