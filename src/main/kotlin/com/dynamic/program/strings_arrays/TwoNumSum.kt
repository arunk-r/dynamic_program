package com.dynamic.program.strings_arrays

/**
 * Sorted integer find 2 number sum for given target. return -1 if not found the target sum
 * brute force is O(n^2)
 *
 * use 2 pointer approach
 */
fun twoNumSum(a: MutableList<Int>, target: Int): Pair<Int, Int>? {
    var st = 0
    var ed = a.size - 1
    while (st < ed) {
        val sum = a[st] + a[ed]
        if (sum == target) return Pair(a[st], a[ed])
        else if (sum > target) {
            ed--
        } else {
            st++
        }
    }
    return null
}

fun main() {
    println(twoNumSum(mutableListOf(2,4,6,8,9,12,14), 14))
    println(twoNumSum(mutableListOf(2,4,6,8,9,12,14), 15))
    println(twoNumSum(mutableListOf(2,4,6,8,9,12,14), 25))
}
