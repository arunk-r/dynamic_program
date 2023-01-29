package com.dynamic.program.backtracking

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 *
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
class PermutationsII {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<MutableList<Int>>()
        backtrack(nums.size, nums.asList(), mutableListOf(), result)
        return result.toList()
    }

    fun backtrack(size: Int, nums: List<Int>, cur: MutableList<Int>, result: MutableSet<MutableList<Int>>) {
        if (nums.isEmpty()) {
            if (cur.size == size) {
                result.add(cur.toMutableList())
            }
            return
        }

        for (i in nums.indices) {
            val cVal = nums[i]

            val newNums = nums.toMutableList()
            newNums.removeAt(i)

            val nCur = cur.toMutableList()
            nCur.add(cVal)
            backtrack(size, newNums, nCur, result)
        }
    }
}

fun main() {
    println(PermutationsII().permuteUnique(intArrayOf(1,2,3)))
    println(PermutationsII().permuteUnique(intArrayOf(1,1,2)))
}