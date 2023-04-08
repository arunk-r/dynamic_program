package com.dynamic.program.medium

/**
 *
Write a function that takes in a non-empty array of distinct integers and an
integer representing a target sum. The function should find all triplets in
the array that sum up to the target sum and return a two-dimensional array of
all these triplets. The numbers in each triplet should be ordered in ascending
order, and the triplets themselves should be ordered in ascending order with
respect to the numbers they hold.


If no three numbers sum up to the target sum, the function should return an
empty array.

Sample Input
array = [12, 3, 1, 2, -6, 5, -8, 6]
targetSum = 0

Sample Output
[[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]


 */
class ThreeNumberSum {
    fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
        array.sort()
        val result = mutableListOf<List<Int>>()
        for (i in array.indices) {
            var l = i + 1
            var r = array.size - 1
            while (l < r) {
                val ts = array[i] + array[l] + array[r]
                if (ts == targetSum) {
                    result.add(listOf(array[i], array[l], array[r]))
                    r--
                    l++
                } else if (ts < targetSum) {
                    l++
                } else {
                    r--
                }
            }
        }
        return result
    }


    fun threeNumberSum1(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
        // Write your code here
        array.sort()
        val output = mutableListOf<List<Int>>()
        for (cn in 0 until array.size) {
            var l = cn + 1
            var r = array.size - 1
            while (l < r) {
                val cs = array[cn] + array[l] + array[r]
                if (cs == targetSum) {
                    output.add(listOf(array[cn], array[l], array[r]))
                    l++
                    r--
                } else if (cs < targetSum) {
                    l++
                } else {
                    r--
                }
            }
        }
        return output
    }
}

fun main() {
    println(ThreeNumberSum().threeNumberSum(mutableListOf(12, 3, 1, 2, -6, 5, -8, 6), 0))
    println(ThreeNumberSum().threeNumberSum(mutableListOf(30, -40, -20, -10, 40, 0, 10, 5), 0))
}
