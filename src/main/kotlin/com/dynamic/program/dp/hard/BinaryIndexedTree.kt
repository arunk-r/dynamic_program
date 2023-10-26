package com.dynamic.program.dp.hard

/**
 * https://leetcode.com/discuss/interview-question/4153767/all-you-need-to-know-about-bit
 */
class BinaryIndexedTree {
    var tree: IntArray = IntArray(1)

    fun bit(nums: IntArray) {
       tree = IntArray(nums.size+1)
       for (i in 1 .. nums.size) {
           update(i, nums[i-1])
       }
        for (i in 1 .. nums.size) {
            println("Prefix sum(${i - 1}) = ${query(i)}")
        }
    }

    private fun update(i: Int, num: Int) {
        var idx = i
        while (idx < tree.size) {
            tree[idx] += num
            idx += idx.and(-idx)
        }
    }

    private fun query(i: Int): Int {
        var sum = 0
        var idx = i
        while (idx > 0) {
            sum += tree[idx]
            idx -= idx.and(-idx)
        }
        return sum
    }
}

fun main() {
    println(BinaryIndexedTree().bit(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)))
    println(BinaryIndexedTree().bit(intArrayOf(-2, 5, -1)))
}
