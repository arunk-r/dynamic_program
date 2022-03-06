package com.dynamic.program.medium

/**
 * 
Write a function that takes in a non-empty array of integers and returns an
array of the same length, where each element in the output array is equal to
the product of every other number in the input array.


In other words, the value at output[i] is equal to the product of
every number in the input array other than input[i].

Note that you're expected to solve this problem without using division.
Sample Input
array = [5, 1, 4, 2]

Sample Output
[8, 40, 10, 20]
// 8 is equal to 1 x 4 x 2
// 40 is equal to 5 x 4 x 2
// 10 is equal to 5 x 1 x 2
// 20 is equal to 5 x 1 x 4


 */

fun arrayOfProducts(array: List<Int>): List<Int> {
    // Write your code here.
    val leftArray = mutableListOf<Int>()
    val rightArray = IntArray(array.size)
    val output = mutableListOf<Int>()
    var p = 1
    for (i in 0 until array.size) {
        leftArray.add(p)
        p *= array[i]
    }
    p = 1
    for (i in array.size-1 downTo 0) {
        rightArray[i] = p
        p *= array[i]
    }
    for(i in 0 until leftArray.size) {
        output.add(leftArray[i] * rightArray[i])
    }
    return output
}
