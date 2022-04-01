package com.dynamic.program.facebook.easy

/**
 * Suppose a sorted array A is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array will not contain duplicates.

NOTE 1: Also think about the case when there are duplicates. Does your current solution work? How does the time complexity change?*
PROBLEM APPROACH:

Note: If you know the number of times the array is rotated, then this problem becomes trivial. If the number of rotation is x, then minimum element is A[x].

Lets look at how we can calculate the number of times the array is rotated.

Complete solution in the hints.
 */

fun rotateArray(v: MutableList<Int>, rotate: Int): List<Int> {
    val actualRotate = rotate%v.size
    println(actualRotate)
    if (actualRotate > 0) {
        for (i in 0..actualRotate) {
            v.add(v.removeAt(0))
        }

    }
    return v
}

fun main() {
    println(rotateArray(mutableListOf(0,1,2,3,4,5,6,7), 12))
}

