package com.dynamic.program.heaps

import java.util.*

/**
 * Find K Pairs with Smallest Sums
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 *
 * Example 2:
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 *
 * Example 3:
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 10^5
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1 and nums2 both are sorted in ascending order.
 * 1 <= k <= 10^4
 *
 */
class FindKPairsWithSmallestSums {
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val que = PriorityQueue { a: IntArray, b: IntArray -> a[0] + a[1] - b[0] - b[1] }
        val res = mutableListOf<List<Int>>()

        for (r in nums1.indices) {
            que.add(intArrayOf(nums1[r], nums2[0], 0))
        }
        var idx = 0
        while (idx < k && !que.isEmpty()) {
            idx++
            val cur = que.remove()
            res.add(listOf(cur[0], cur[1]))
            if (cur[2] == nums2.size - 1) continue
            que.offer(intArrayOf(cur[0], nums2[cur[2] + 1], cur[2] + 1))
        }
        return res
    }
}

fun main() {
    println(FindKPairsWithSmallestSums().kSmallestPairs(intArrayOf(1,7,11), intArrayOf(2,4,6), 3))
}