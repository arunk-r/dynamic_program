package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/factor-combinations/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 254. Factor Combinations
 * Medium
 * 1.1K
 * 65
 * company
 * LinkedIn
 * company
 * TikTok
 * company
 * Uber
 * Numbers can be regarded as the product of their factors.
 *
 * For example, 8 = 2 x 2 x 2 = 2 x 4.
 * Given an integer n, return all possible combinations of its factors. You may return the answer in any order.
 *
 * Note that the factors should be in the range [2, n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: []
 * Example 2:
 *
 * Input: n = 12
 * Output: [[2,6],[3,4],[2,2,3]]
 * Example 3:
 *
 * Input: n = 37
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= n <= 107
 */
class FactorCombinations {
    fun getFactors(n: Int): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        backtracking(2, n, mutableListOf(), result)
        if(result.isNotEmpty()) {
            result.removeAt(result.size-1)
        }
        return result
    }

    private fun backtracking(start: Int, n: Int, cur: MutableList<Int>, result: MutableList<MutableList<Int>>) {
        if(n <= 1) {
            result.add(cur.toMutableList())
        } else {
            for(i in start .. n) {
                if(n % i == 0) {
                    cur.add(i)
                    backtracking(i, n/i, cur, result)
                    cur.removeAt(cur.size-1)
                }
            }
        }
    }
}
