package com.dynamic.program.medium

/**
 * 
Write a function that takes in an array of integers and returns a boolean
representing whether the array is monotonic.


An array is said to be monotonic if its elements, from left to right, are
entirely non-increasing or entirely non-decreasing.


Non-increasing elements aren't necessarily exclusively decreasing; they simply
don't increase. Similarly, non-decreasing elements aren't necessarily
exclusively increasing; they simply don't decrease.

Note that empty arrays and arrays of one element are monotonic.
Sample Input
array = [-1, -5, -10, -1100, -1100, -1101, -1102, -9001]

Sample Output
true


 */
fun isMonotonic(array: List<Int>): Boolean {
    if (array.size <= 2) return true
    val orderType = if (array[0] <= array[1] && array[1] <= array[2]) "ASC" else "DESC"
    for (a in 1 until (array.size)) {
        if (orderType == "ASC") {
            if(array[a-1] > array[a]) return false
        } else {
            if(array[a-1] < array[a]) return false
        }
    }
    return true
}