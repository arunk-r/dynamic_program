package com.dynamic.program.backtracking

/**
 * Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * Constraints:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 */
class CombinationSumII {
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()
        val result = mutableSetOf<MutableList<Int>>()
        backtrack(0, target, candidates, mutableListOf(), result)
        return result.toList()
    }

    private fun backtrack(idx: Int, target: Int, candidates: IntArray, cur: MutableList<Int>, result: MutableSet<MutableList<Int>>) {
        println("$cur - ${cur.sum()}")
        if (target == 0) {
            result.add(cur.toMutableList())
            return
        }
        if (target < 0 || idx == candidates.size){
            return
        }

        cur.add(candidates[idx])
        backtrack(idx+1, target - candidates[idx], candidates, cur, result)
        cur.removeLast()
        var i = idx
        while (i < candidates.size - 2 && candidates[i] == candidates[i+1]){
            i++
        }
        backtrack(i+1, target, candidates, cur, result)
    }
}

fun main() {
    //println(CombinationSumII().combinationSum2(intArrayOf(10,1,2,7,6,1,5), 8))
    //println(CombinationSumII().combinationSum2(intArrayOf(2,5,2,1,2), 5))
    //println(CombinationSumII().combinationSum2(intArrayOf(1,1), 2))
    println(CombinationSumII().combinationSum2(intArrayOf(4,1,1,4,4,4,4,2,3,5), 10))
}