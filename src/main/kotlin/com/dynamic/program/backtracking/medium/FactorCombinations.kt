package com.dynamic.program.backtracking.medium

/**
 * 254. Factor Combinations
Medium

LinkedIn
TikTok
Uber
Numbers can be regarded as the product of their factors.

For example, 8 = 2 x 2 x 2 = 2 x 4.
Given an integer n, return all possible combinations of its factors. You may return the answer in any order.

Note that the factors should be in the range [2, n - 1].



Example 1:

Input: n = 1
Output: []
Example 2:

Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]
Example 3:

Input: n = 37
Output: []


Constraints:

1 <= n <= 107
 */
class FactorCombinations {
    val result = mutableListOf<MutableList<Int>>()
    fun getFactors(n: Int): List<List<Int>> {
        backtracking(2, n, mutableListOf())
        return result
    }

    fun backtracking(start: Int, n: Int, items: MutableList<Int>) {
        if (n <= 1) {
            if (items.isNotEmpty()) {
                result.add(items.toMutableList())
            }
            return
        }
        for (i in start .. n) {
            if (n % i == 0) {
                items.add(i)
                backtracking(i, n / i, items)
                items.removeAt(items.size - 1)
            }
        }
    }
}

fun main() {
    println(FactorCombinations().getFactors(12))
}
