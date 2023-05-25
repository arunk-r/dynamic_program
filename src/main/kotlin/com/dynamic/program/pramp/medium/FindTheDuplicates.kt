package com.dynamic.program.pramp.medium

/**
 * Find The Duplicates
 * Given two sorted arrays arr1 and arr2 of passport numbers, implement a function findDuplicates that returns an
 * array of all passport numbers that are both in arr1 and arr2. Note that the output array should be sorted in an ascending order.
 *
 * Let N and M be the lengths of arr1 and arr2, respectively. Solve for two cases and analyze the time & space
 * complexities of your solutions: M ≈ N - the array lengths are approximately the same M ≫ N - arr2 is much bigger than arr1.
 *
 * Example:
 *
 * input:  arr1 = [1, 2, 3, 5, 6, 7], arr2 = [3, 6, 7, 8, 20]
 *
 * output: [3, 6, 7] // since only these three values are both in arr1 and arr2
 * Constraints:
 *
 * [time limit] 5000ms
 * [input] array.integer arr1
 * 1 ≤ arr1.length ≤ 100
 * [input] array.integer arr2
 * 1 ≤ arr2.length ≤ 100
 * [output] array.integer
 */
class FindTheDuplicates {
    fun find(arr1: IntArray, arr2: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        var j = 0

        while (i < arr1.size && j < arr2.size) {
            if (arr1[i] == arr2[j]) {
                result.add(arr1[i])
                i++
                j++
            } else if (arr1[i] > arr2[j]) {
                j++
            } else {
                i++
            }
        }

        return result
    }
}

fun main() {
    println(FindTheDuplicates().find(intArrayOf(1, 2, 3, 5, 6, 7), intArrayOf(3, 6, 7, 8, 20)))
}
