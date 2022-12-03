package com.dynamic.program.strings_arrays

/**
 * Given two sorted integer arrays, return an array that combines both of them and is also sorted.
 * hint - similar to 2 pointer concept. Here both pointers will point beginning of respective array
 *
 * time - O(max(a1.size, a2.size))
 */

fun merge(a1: List<Int>, a2: List<Int>): MutableList<Int> {
    val op = mutableListOf<Int>()
    var i = 0
    var j = 0
    while (i < a1.size && j < a2.size) {
        if (a1[i] < a2[j]) {
            op.add(a1[i++])
        } else {
            op.add(a2[j++])
        }
    }

    while (i < a1.size) {
        op.add(a1[i++])
    }

    while (j < a2.size) {
        op.add(a2[j++])
    }

    return op
}

fun main() {
    println(merge(listOf(1, 3, 5, 7, 9), listOf(2, 4, 6, 8)))
    println(merge(listOf(), listOf(2, 4, 6, 8)))
    println(merge(listOf(1, 3, 5, 7, 9), listOf()))
    println(merge(listOf(), listOf()))
}



