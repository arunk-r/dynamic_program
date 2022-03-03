package com.dynamic.program.medium.powerset

fun powerset(array: List<Int>): List<List<Int>> {
    // Write your code here.
    val subset = mutableListOf<MutableList<Int>>()
    subset.add(mutableListOf())
    for (el in array) {
        val len = subset.size
        for (idx in 0 until len) {
            val temSubset = subset[idx].toMutableList()
            temSubset.add(el)
            subset.add(temSubset)
        }
    }
    return subset
}

fun main() {
    println(powerset(listOf(1, 2, 3)))
}