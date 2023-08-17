package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/find-smallest-common-element-in-all-rows/
 *
 * 1198. Find Smallest Common Element in All Rows
 * Medium
 * 541
 * 30
 * Walmart Labs
 * company
 * Microsoft
 * Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
 *
 * If there is no common element, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 * Example 2:
 *
 * Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 104
 * mat[i] is sorted in strictly increasing order.
 */
class FindSmallestCommonElementInAllRows {
    fun smallestCommonElement(mat: Array<IntArray>): Int {
        for(n in mat[0]) {
            var cnt = 0
            for(i in 1 until mat.size) {
                if(!found(n, i, mat)) {
                    break
                }
                cnt++
            }
            if(cnt == mat.size - 1) return n
        }
        return -1
    }

    fun found(ele: Int, idx: Int, mat: Array<IntArray>): Boolean {
        var l = 0
        var r = mat[idx].size - 1
        while(l <= r) {
            val mid = l + (r - l) / 2
            if(mat[idx][mid] == ele) return true
            else if (mat[idx][mid] > ele) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return false
    }

    fun smallestCommonElement1(mat: Array<IntArray>): Int {
        val map = hashMapOf<Int, Int>()
        for(m in mat) {
            for(n in m) {
                map[n] = map.getOrDefault(n, 0) + 1
            }
        }
        val n = mat.size
        var minEle = Int.MAX_VALUE
        map.forEach { (k, v) ->
            if(v == n && k < minEle) {
                minEle = k
            }
        }

        return if(minEle == Int.MAX_VALUE) -1 else minEle
    }
}
