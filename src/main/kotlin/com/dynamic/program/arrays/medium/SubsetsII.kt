package com.dynamic.program.arrays.medium

/**
 * 90. Subsets II
Medium

Amazon
Yahoo
Bloomberg
Given an integer array nums that may contain duplicates, return all possible
subsets
(the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
class SubsetsII {
    fun subsetsWithDup1(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<MutableList<Int>>()
        comb(nums, 0, result, mutableListOf())
        return result
    }

    fun comb(nums: IntArray, i: Int, result: MutableList<MutableList<Int>>, cur: MutableList<Int>) {
        result.add(cur.toMutableList())
        for(j in i until nums.size) {
            if (j != i && nums[j] == nums[j-1]) continue
            cur.add(nums[j])
            comb(nums, j+1, result, cur)
            cur.removeAt(cur.size-1)
        }
    }

    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableSetOf<MutableList<Int>>()
        result.add(mutableListOf())
        for(i in nums.indices) {
            combinations(nums, i, result, mutableListOf())
        }

        return result.toMutableList()
    }

    private fun combinations(nums: IntArray, i: Int, result: MutableSet<MutableList<Int>>, cur: MutableList<Int>) {
        for(j in i until nums.size) {
            cur.add(nums[j])
            result.add(cur.toMutableList())
            combinations(nums, j+1, result, cur)
            cur.removeAt(cur.size-1)
        }
    }
}
