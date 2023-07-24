package com.dynamic.program.design.medium.stacks

import java.util.Stack

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/description/
 *
 * 341. Flatten Nested List Iterator
 * Medium
 * 4.3K
 * 1.5K
 * company
 * Apple
 * company
 * Yandex
 * company
 * LinkedIn
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 *
 *
 * Constraints:
 *
 * 1 <= nestedList.length <= 500
 * The values of the integers in the nested list is in the range [-106, 106].
 */

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int): Unit

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger): Unit

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger>
}


class FlattenNestedListIterator(nestedList: List<NestedInteger>) {
    val result = mutableListOf<Int>()
    var cnt = 0

    init {
        for (n in nestedList) {
            read(n)
        }
    }

    private fun read(n: NestedInteger) {
        if (n.isInteger()) {
            result.add(n.getInteger())
        } else {
            n.getList().forEach { n1 ->
                read(n1)
            }
        }
    }

    fun next(): Int {
        return result[cnt++]
    }

    fun hasNext(): Boolean {
        return cnt != result.size
    }
}

/**
 * Your FlattenNestedListIterator object will be instantiated and called as such:
 * var obj = FlattenNestedListIterator(nestedList)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */

class NestedIterator(nestedList: List<NestedInteger>) {
    val stk = Stack<NestedInteger>()

    init {
        for (i in nestedList.size - 1 downTo 0) {
            stk.push(nestedList[i])
        }
    }

    fun next(): Int {
        return if (hasNext()) {
            stk.pop().getInteger()
        } else {
            -1
        }
    }

    fun hasNext(): Boolean {
        read()
        return stk.isNotEmpty()
    }

    private fun read() {
        while (stk.isNotEmpty() && !stk.peek().isInteger()) {
            val n = stk.pop().getList()
            for (i in n.size - 1 downTo 0) {
                stk.push(n[i])
            }
        }
    }

}
