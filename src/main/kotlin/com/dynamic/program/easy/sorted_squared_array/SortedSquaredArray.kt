package com.dynamic.program.easy.sorted_squared_array

/**
 *
Write a function that takes in a non-empty array of integers that are sorted
in ascending order and returns a new array of the same length with the squares
of the original integers also sorted in ascending order.

Sample Input
array = [1, 2, 3, 5, 6, 8, 9]

Sample Output
[1, 4, 9, 25, 36, 64, 81]

 */
fun sortedSquaredArray(array: List<Int>): List<Int> {
    // Write your code here.
    var output = ArrayList<Int>()
    for (i in array) {
        output.add(i * i)
    }
    output.sort()
    return output
}

fun main() {
    println(listOf(1, 2, 3, 5, 6, 8, 9))
}