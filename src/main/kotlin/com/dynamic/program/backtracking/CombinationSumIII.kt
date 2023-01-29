package com.dynamic.program.backtracking

/**
 * Combination Sum III
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 *
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 *
 *
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 *
 *
 * Constraints:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
class CombinationSumIII {
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        backtrack(k, 1, k, n, mutableListOf(), result)
        return result
    }

    fun backtrack(level: Int, start: Int, k: Int, target: Int, cur: MutableList<Int>, result: MutableList<MutableList<Int>>) {
        if (level == 0) return
        for(i in start .. Math.min(target, 9)) {
            val nCur = cur.toMutableList()
            if(nCur.size < k) {
                nCur.add(i)
                if(nCur.size == k && nCur.sum() == target) {
                    result.add(nCur)
                } else if (level > 1) {
                    backtrack(level - 1, i+1, k, target, nCur, result)
                }
            }
        }
    }
}