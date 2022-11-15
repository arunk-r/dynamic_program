package com.dynamic.program.strings_arrays

/**
 * In a sorted array fina d common elements in both arrays in O(n) time
 */

fun findCommonElements(list1: List<Int>, list2: List<Int>): List<Int> {
    val commonList = mutableListOf<Int>()
    var idx1 = 0
    var idx2 = 0

    while (idx1 < list1.size && idx2 < list2.size) {
        if (list1[idx1] == list2[idx2]) {
            commonList.add(list1[idx1])
            idx1++
            idx2++
        } else if (list1[idx1] > list2[idx2]) {
            idx2++
        } else {
            idx1++
        }
    }
    return commonList
}

fun main() {
    val list1 = listOf(1,2,3,4,5,6,7,8,9)
    val list2 = listOf(2,4,6,8,10,12)
    println(findCommonElements(list1, list2))
}
