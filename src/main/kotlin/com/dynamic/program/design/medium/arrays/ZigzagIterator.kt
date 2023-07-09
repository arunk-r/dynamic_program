package com.dynamic.program.design.medium.arrays

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/zigzag-iterator/description/
 *
 * 281. Zigzag Iterator
 * Medium
 * 642
 * 36
 * company
 * Coinbase
 * company
 * Google
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 *
 * Example 1:
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 * Example 2:
 *
 * Input: v1 = [1], v2 = []
 * Output: [1]
 * Example 3:
 *
 * Input: v1 = [], v2 = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 0 <= v1.length, v2.length <= 1000
 * 1 <= v1.length + v2.length <= 2000
 * -231 <= v1[i], v2[i] <= 231 - 1
 *
 *
 * Follow up: What if you are given k vectors? How well can your code be extended to such cases?
 *
 * Clarification for the follow-up question:
 *
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
 *
 * Follow-up Example:
 *
 * Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
 * Output: [1,4,8,2,5,9,3,6,7]
 */
class ZigzagIterator(val v1: IntArray, val v2: IntArray) {
    val q = ArrayDeque<Int>()
    init {
        var v1Cnt = 0
        var v2Cnt = 0
        while(v1Cnt < v1.size || v2Cnt < v2.size) {
            if(v1Cnt <= v2Cnt) {
                if(v1Cnt in v1.indices) {
                    q.addLast(v1[v1Cnt++])
                } else {
                    q.addLast(v2[v2Cnt++])
                }
            } else {
                if(v2Cnt in v2.indices) {
                    q.addLast(v2[v2Cnt++])
                } else {
                    q.addLast(v1[v1Cnt++])
                }
            }
        }
    }

    fun next(): Int {
        return q.removeFirst()
    }

    fun hasNext(): Boolean {
        return q.isNotEmpty()
    }
}

// Your ZigzagIterator object will be instantiated and called as such:
// var i = ZigzagIterator(v1, v2)
// var ret = ArrayList<Int>()
// while(i.hasNext()){
//		ret.add(i.next())
// }

class ZigzagIterator1(val v1: IntArray, val v2: IntArray) {
    private var v1Cnt = 0
    private var v2Cnt = 0

    fun next(): Int {
        return if(v1Cnt <= v2Cnt) {
            if(v1Cnt in v1.indices) {
                v1[v1Cnt++]
            } else {
                v2[v2Cnt++]
            }
        } else {
            if(v2Cnt in v2.indices) {
                v2[v2Cnt++]
            } else {
                v1[v1Cnt++]
            }
        }
    }

    fun hasNext(): Boolean {
        return v1Cnt in v1.indices || v2Cnt in v2.indices
    }
}

// Your ZigzagIterator1 object will be instantiated and called as such:
// var i = ZigzagIterator1(v1, v2)
// var ret = ArrayList<Int>()
// while(i.hasNext()){
//		ret.add(i.next())
// }
