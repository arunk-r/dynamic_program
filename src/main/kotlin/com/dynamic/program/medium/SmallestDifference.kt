package com.dynamic.program.medium

/**
 * 
Write a function that takes in two non-empty arrays of integers, finds the
pair of numbers (one from each array) whose absolute difference is closest to
zero, and returns an array containing these two numbers, with the number from
the first array in the first position.


Note that the absolute difference of two integers is the distance between
them on the real number line. For example, the absolute difference of -5 and 5
is 10, and the absolute difference of -5 and -4 is 1.


You can assume that there will only be one pair of numbers with the smallest
difference.

Sample Input
arrayOne = [-1, 5, 10, 20, 28, 3]
arrayTwo = [26, 134, 135, 15, 17]

Sample Output
[28, 26]

 */
fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {
    // Write your code here.
    arrayOne.sort()
    arrayTwo.sort()
    var smallEle1 = 0
    var smallEle2 = 0
    var diff = Int.MAX_VALUE
    var arrayOnePointer = 0
    var arrayTwoPointer = 0
    while (arrayOnePointer < arrayOne.size && arrayTwoPointer < arrayTwo.size) {
        val cd = Math.abs(arrayOne[arrayOnePointer] - arrayTwo[arrayTwoPointer])
        if (diff > cd) {
            diff = cd
            smallEle1 = arrayOne[arrayOnePointer]
            smallEle2 = arrayTwo[arrayTwoPointer]
        }
        if (arrayOne[arrayOnePointer] < arrayTwo[arrayTwoPointer]) {
            arrayOnePointer++
        } else if (arrayOne[arrayOnePointer] > arrayTwo[arrayTwoPointer]) {
            arrayTwoPointer++
        } else {
            return listOf(arrayOne[arrayOnePointer], arrayTwo[arrayTwoPointer])
        }

    }

    return listOf(smallEle1, smallEle2)
}