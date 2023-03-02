package com.dynamic.program.backtracking.medium

/**
 * 46. Permutations
Medium

Amazon

Microsoft

Google
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
class Permutations {
    fun permute(nums: IntArray): List<List<Int>> {
        var result = mutableListOf<MutableList<Int>>()
        for(i in nums.indices) {
            result = backtrack(nums[i], i+1, result)
        }
        return result
    }

    fun backtrack(n: Int, size: Int, lst: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        if (size == 1) {
            return mutableListOf(mutableListOf(n))
        } else {
            val l = mutableListOf<MutableList<Int>>()
            for (i in 0 until size) {
                for(s in lst) {
                    val l1 = mutableListOf<Int>()
                    var added = false
                    for (j in s.indices) {
                        if (j == i){
                            l1.add(n)
                            l1.add(s[j])
                            added = true
                        } else {
                            l1.add(s[j])
                        }
                    }
                    if (!added) {
                        l1.add(n)
                    }
                    l.add(l1)
                }
            }
            return l
        }
    }
}

fun main() {
    println(Permutations().permute(intArrayOf(1,2,3)))
}
