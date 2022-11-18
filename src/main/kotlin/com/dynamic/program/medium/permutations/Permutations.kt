package com.dynamic.program.medium.permutations

fun getPermutations(array: List<Int>): List<List<Int>> {
    // Write your code here.
    val permutations = mutableListOf<MutableList<Int>>()
    getPermutationsHelper(array, mutableListOf(), permutations, 1)
    return permutations
}

fun getPermutationsHelper(array: List<Int>, currentPermutations: MutableList<Int>, permutations: MutableList<MutableList<Int>>, r: Int) {
    if (array.isEmpty() && currentPermutations.isNotEmpty()) {
        permutations.add(currentPermutations)
    } else {
        //println("recursion - $r")
        for (i in array) {
            val newArray = array.toMutableList()
            //println("newArray - $newArray - $i")
            newArray.remove(i)
            val np = currentPermutations.toMutableList()
            //np.add(i)
            //println("np - $np")
            //println("permutations - ${permutations}")
            getPermutationsHelper(newArray, np, permutations, r+1)
            //println("loop end--- $r")
        }
    }
}

fun main() {
    println(getPermutations(listOf(1, 2, 3)))
}