package com.dynamic.program.hard.four_number_sum

/**
 * Write a function that takes in a non-empty array of distinct integers and an
 * integer representing a target sum. The function should find all quadruplets in
 * the array that sum up to the target sum and return a two-dimensional array of
 * all these quadruplets in no particular order.
 */

fun fourNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    // Write your code here.
    val allPairs = mutableMapOf<Int, MutableList<MutableList<Int>>>()
    val quadruples = mutableListOf<MutableList<Int>>()
    for (i in 1 until array.size - 1) {
        for (j in i + 1 until array.size) {
            val currentSum = array[i] + array[j]
            val difference = targetSum - currentSum
            if (allPairs.contains(difference)) {
                for (pais in allPairs[difference]!!) {
                    val newList = pais.toMutableList()
                    newList.add(array[i])
                    newList.add(array[j])
                    quadruples.add(newList)
                }
            }
        }
        for (k in 0 until i) {
            val currentSum = array[i] + array[k]
            if (!allPairs.contains(currentSum)) {
                allPairs[currentSum] = mutableListOf()
            }
            val newList = allPairs[currentSum]!!.toMutableList()
            newList.add(mutableListOf(array[k], array[i]))
            allPairs[currentSum] = newList
        }
    }
    return quadruples
}

fun main() {
    println(fourNumberSum(mutableListOf(7, 6, 4, -1, 1, 2), 16))
}