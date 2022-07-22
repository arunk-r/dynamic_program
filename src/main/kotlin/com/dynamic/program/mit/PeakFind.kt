package com.dynamic.program.mit

fun findPeak(list: MutableList<Int>): Boolean {
    val n = list.size / 2
    return if (n < 1 || list.size == 0) {
        false
    } else if (list.size == 2) {
        list[0] > list[1] || list[1] > list[0]
    } else if (list[n] <= list[n - 1]) {
        findPeak(list.subList(0, n))
    } else if (list[n] < list[n + 1]) {
        findPeak(list.subList(n, list.size))
    } else {
        list[n] > list[n - 1] && list[n] > list[n + 1]
    }
}

fun main() {
    var arg = mutableListOf(1, 3, 2, 2, 1)
    println(findPeak(arg))
    arg = mutableListOf(1, 1, 1, 1, 1, 1, 1)
    println(findPeak(arg))
    arg = mutableListOf(1, 2, 4, 3, 2, 1, 2, 4, 5, 6, 7, 8, 9, 6, 5, 3)
    println(findPeak(arg))
    arg = mutableListOf(9, 8, 7, 6, 5, 4, 3, 2, 1)
    println(findPeak(arg))
    arg = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(findPeak(arg))
    arg = mutableListOf(4, 1, 1, 1, 1, 1, 1, 1, 1, 9)
    println(findPeak(arg))
    arg = mutableListOf(4, 2, 7, 1, 1, 1, 1, 5, 4, 9)
    println(findPeak(arg))
}

fun findPeak2D(array: MutableList<MutableList<Int>>): Boolean {
    return false
}
