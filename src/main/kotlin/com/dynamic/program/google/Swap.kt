package com.dynamic.program.google

/**
 * Where values are 0 to n-1
 * Swap array with a[i] = a[a[i]] with space complexity  =  o(1)
 */

/**
 * What is lesson learn? using modulation we can store old new data (ie is more than one value)
 */

fun swap(list: MutableList<Int>) {
    val n = list.size

    for (i in list.indices) {
        list[i] *= n
    }
    println(list)
    for (i in list.indices) {
        println("list[list[$i]/$n]/$n, list[${list[i]}/$n]/$n, list[${list[i]/n}]/$n, ${list[list[i]/n]}/$n, ${list[list[i]/n]/n}")
        list[i] += list[list[i]/n]/n
    }
    println(list)
    for (i in list.indices) {
        list[i] %=n
    }
    println(list)
}

fun main() {
    val list = mutableListOf(1,6,3,5,4,2,0)
    swap(list)
    println(list)
}
