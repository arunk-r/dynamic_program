package com.dynamic.program.medium.permutations

fun getPermutations(array: List<Int>): List<List<Int>> {
    // Write your code here.
    val permutations = mutableListOf<MutableList<Int>>()
    getPermutationsHelper(array, mutableListOf(), permutations)
    return permutations
}

fun getPermutationsHelper(array: List<Int>, currentPermutations: MutableList<Int>, permutations: MutableList<MutableList<Int>>) {
    if (array.isEmpty() && currentPermutations.isNotEmpty()) {
        permutations.add(currentPermutations)
    } else {
        for (i in array) {
            val newArray = array.toMutableList()
            newArray.remove(i)
            val np = currentPermutations.toMutableList()
            np.add(i)
            getPermutationsHelper(newArray, np, permutations)
        }
    }
}

fun main() {
    println(getPermutations(listOf(1, 2, 3)))
}