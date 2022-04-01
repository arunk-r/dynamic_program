package com.dynamic.program.facebook.medium

import java.util.*


/**
 * There are two sorted arrays A and B of size m and n respectively.

Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).

The overall run time complexity should be O(log (m+n)).

Sample Input

A : [1 4 5]
B : [2 3]

Sample Output

3
NOTE: IF the number of elements in the merged array is even, then the median is the average of n / 2 th and n/2 + 1th element.

For example, if the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5
 */

/**
1) Take the swapping the input arrays ar1[] last element to ar2[] first element.
2) Sort ar1[] and ar2[] respectively.
3) The median will be the last element of ar1[] + the first
element of ar2[] divided by 2. [(ar1[n-1] + ar2[0])/2].
 */

//O(n + nlogn)
fun getMedian(list1: MutableList<Int>, list2: MutableList<Int>): Int {
    var j = 0
    var i: Int = list1.size - 1
    //O(n)
    while (j < list2.size && i > -1 && list1[i] > list2[j]) {
        val temp: Int = list1[i]
        list1[i] = list2[j]
        list2[j] = temp
        i--
        j++
    }

    //O(nlogn)
    list1.sort()
    //O(nlogn)
    list2.sort()

    // n + O(nlogn) + O(nlogn) = n + 2 O(nlogn) = n + O(nlogn)
    return (list1[list1.size - 1] + list2[0]) / 2
}

fun main() {
    println(getMedian(mutableListOf(1,4,5), mutableListOf(2,3)))
    println(getMedian(mutableListOf(1,12,15), mutableListOf(2,13,17,30)))
}