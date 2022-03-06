package com.dynamic.program.medium

import kotlin.math.max

/**
 * 
Write a function that takes in a non-empty array of arbitrary intervals,
merges any overlapping intervals, and returns the new intervals in no
particular order.


Each interval interval is an array of two integers, with
interval[0] as the start of the interval and
interval[1] as the end of the interval.


Note that back-to-back intervals aren't considered to be overlapping. For
example, [1, 5] and [6, 7] aren't overlapping;
however, [1, 6] and [6, 7] <i>are</i> indeed
overlapping.


Also note that the start of any particular interval will always be less than
or equal to the end of that interval.

Sample Input
intervals = [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]]

Sample Output
[[1, 2], [3, 8], [9, 10]]
// Merge the intervals [3, 5], [4, 7], and [6, 8].
// The intervals could be ordered differently.


 */

fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {
    // Write your code here.
    var sortList = intervals.toMutableList().sortedWith( Comparator<List<Int>> { f, s -> f[0].compareTo(s[0]) })
    sortList = sortList.map() {it.toMutableList()}
    var currentList = sortList[0].toMutableList()
    val mergedList = mutableListOf<MutableList<Int>>()
    mergedList.add(currentList)
    for (nextInterval in sortList) {
        val (currentStart, currentEnd) = currentList
        val (nextStart, nextEnd) = nextInterval

        if(currentEnd >= nextStart) {
            currentList[1] = max(currentEnd, nextEnd)
        } else {
            currentList = nextInterval
            mergedList.add(currentList)
        }
    }
    return mergedList
}
