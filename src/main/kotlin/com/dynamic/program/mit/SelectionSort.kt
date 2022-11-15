package com.dynamic.program.mit

/**
 * Selection sort using recursion
 * O(n^2)
 */

fun findMax(array: MutableList<Int>, index: Int): Int {
    if (index > 0) {
        println("stack -> $index")
        val j = findMax(array, index - 1)
        println("primeMax - index($index) : j($j) - > (${array[index]} < ${array[j]}) $array")
        if (array[index] < array[j]) {
            println("primeMax - return - J -> $j")
            return j
        }
    }
    println("primeMax - return index -> $index")
    return index
}

fun selectionSort(array: MutableList<Int>, index: Int = array.size - 1) {
    if (index > 0) {
        val j = findMax(array, index)
        if (index != j) {
            println("$index -> $j -     ($array)")
            val tmp = array[index]
            array[index] = array[j]
            array[j] = tmp
            println("after swap -> $array")
        }else {
            println("skip $index == $j")
        }
        selectionSort(array, index - 1)
    }
}

fun main() {
    val list = mutableListOf(4, 7, 3, 2, 7, 1)
    selectionSort(list)
    println(list)
}
