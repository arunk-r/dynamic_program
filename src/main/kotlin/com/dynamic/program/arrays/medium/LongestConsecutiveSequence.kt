package com.dynamic.program.arrays.medium

/**
 * 128. Longest Consecutive Sequence
Medium

Google
Amazon
Adobe
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
class LongestConsecutiveSequence {
    // using union find solution
    fun longestConsecutive(nums: IntArray): Int {
        val parent = IntArray(nums.size)
        for(i in parent.indices) {
            parent[i] = i
        }

        val map = hashMapOf<Int, Int>()
        for(i in nums.indices) {
            val n = nums[i]
            if(map[n] == null) {
                map[n] = i
            }
        }

        for(n in nums) {
            if (map[n-1] != null && find(parent, map[n]!!) != find(parent, map[n-1]!!)) {
                union(parent, map[n]!!, map[n-1]!!)
            }
            if (map[n+1] != null && find(parent, map[n]!!) != find(parent, map[n+1]!!)) {
                union(parent, map[n]!!, map[n+1]!!)
            }
        }

        val count = IntArray(nums.size)
        var ans = 0
        map.forEach{ (k,v) ->
            val p = find(parent, v)
            count[p]++
            if (count[p] > ans) {
                ans = count[p]
            }
        }
        return ans
    }

    private fun union(parent: IntArray, v1: Int, v2: Int) {
        val parentV1 = find(parent, v1)
        val parentV2 = find(parent, v2)
        parent[parentV2] = parentV1
    }

    private fun find(parent: IntArray, v: Int): Int {
        var c = v
        while(c != parent[c]) {
            parent[c] = parent[parent[c]]
            c = parent[c]
        }
        return c
    }

    fun longestConsecutive1(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val set = hashSetOf<Int>()
        for(n in nums) { set.add(n)}

        var result = 1
        for(n in nums) {

            if(set.contains(n-1)) continue
            var curMax = 1
            var nn = n
            while(set.contains(++nn)) {
                curMax++
            }
            if(curMax > result) result = curMax
        }

        return result
    }
}

fun main() {
    println(LongestConsecutiveSequence().longestConsecutive(intArrayOf(100,4,200,1,3,2)))
}
