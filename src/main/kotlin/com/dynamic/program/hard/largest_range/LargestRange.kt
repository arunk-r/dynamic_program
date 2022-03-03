package com.dynamic.program.hard.largest_range

/**
 * Write a function that takes in an array of integers and returns an array of
 * length 2 representing the largest range of integers contained in that array.
 *
 * The first number in the output array should be the first number in the range,
 * while the second number should be the last number in the range.
 *
 * A range of numbers is defined as a set of numbers that come right after each
 * other in the set of real integers. For instance, the output array
 * [2, 6] represents the range {2, 3, 4, 5, 6}, which
 * is a range of length 5. Note that numbers don't need to be sorted or adjacent
 * in the input array in order to form a range.
 * You can assume that there will only be one largest range.
 * Sample Input = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
 * Sample Output
 * [0, 7]
 */
fun largestRange(array: List<Int>): Pair<Int, Int> {
    // Write your code here.
    var minValue = Int.MAX_VALUE
    var maxValue = Int.MIN_VALUE
    var longestLength = 0
    val hashMap = mutableMapOf<Int, Boolean>()
    for (value in array) {
        hashMap[value] = true
    }
    for (value in array) {
        if (hashMap[value] == true) {
            hashMap[value] = false
            var currentLength = 1
            var left = value - 1
            var right = value + 1
            while (hashMap.contains(left)) {
                hashMap[left] = false
                left --
                currentLength ++
            }

            while (hashMap.contains(right)) {
                hashMap[right] = false
                right ++
                currentLength ++
            }

            if (currentLength > longestLength) {
                longestLength = currentLength
                minValue = left + 1
                maxValue = right - 1
            }
        }
    }
    return Pair(minValue, maxValue)
}

fun main() {
    println(largestRange(listOf(1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6)))
}