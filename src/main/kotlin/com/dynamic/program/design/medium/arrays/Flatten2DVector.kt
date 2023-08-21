package com.dynamic.program.design.medium.arrays

/**
 * https://leetcode.com/problems/flatten-2d-vector/description/
 *
 * 251. Flatten 2D Vector
 * Medium
 * 697
 * 390
 * company
 * Airbnb
 * company
 * Coinbase
 * company
 * Twitter
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 *
 * Implement the Vector2D class:
 *
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 *
 * Explanation
 * Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 * vector2D.next();    // return 1
 * vector2D.next();    // return 2
 * vector2D.next();    // return 3
 * vector2D.hasNext(); // return True
 * vector2D.hasNext(); // return True
 * vector2D.next();    // return 4
 * vector2D.hasNext(); // return False
 *
 *
 * Constraints:
 *
 * 0 <= vec.length <= 200
 * 0 <= vec[i].length <= 500
 * -500 <= vec[i][j] <= 500
 * At most 105 calls will be made to next and hasNext.
 *
 *
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
class Flatten2DVector(val vec: Array<IntArray>) {
    private var outer = 0
    private var inner = 0

    fun next(): Int {
        return if (outer < vec.size && inner < vec[outer].size) {
            val v = vec[outer][inner]
            inner++
            if (inner == vec[outer].size) {
                outer++
                inner = 0
                checkValid()
            }
            v
        } else {
            -1
        }
    }

    fun hasNext(): Boolean {
        checkValid()
        return outer < vec.size || (outer == vec.size - 1 && inner < vec[outer].size)
    }

    private fun checkValid() {
        while (outer < vec.size) {
            if (vec[outer].isEmpty()) {
                outer++
            } else {
                break
            }
        }
    }
}
