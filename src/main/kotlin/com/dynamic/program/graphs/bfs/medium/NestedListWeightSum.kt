package com.dynamic.program.graphs.bfs.medium

/**
 * 339. Nested List Weight Sum
 * Medium
 * company
 * Facebook
 * company
 * LinkedIn
 * company
 * Amazon
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 * Example 2:
 *
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 * Example 3:
 *
 * Input: nestedList = [0]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 */
class NestedInteger {
    // Constructor initializes an empty nested list.
    constructor()

    // Constructor initializes a single integer.
    constructor(value: Int)

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean {
        return false
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
// Return null if this NestedInteger holds a nested list
    fun getInteger(): Int? {
        return null
    }

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int): Unit {

    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger): Unit {

    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
// Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>? {
        return emptyList()
    }
}

class NestedListWeightSum {
    fun depthSum(nestedList: List<NestedInteger>): Int {
        return calculate(nestedList, 1)
    }

    fun calculate(data: List<NestedInteger>, depth: Int): Int {
        var sum = 0
        for (d in data) {
            if (d.isInteger()) {
                sum += depth * (d.getInteger() ?: 0)
            } else {
                sum += calculate((d.getList() ?: emptyList()), depth + 1)
            }
        }
        return sum
    }
}
