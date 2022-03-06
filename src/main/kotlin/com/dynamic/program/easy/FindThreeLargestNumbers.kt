package com.dynamic.program.easy

/**
 * 
Write a function that takes in an array of at least three integers and,
without sorting the input array, returns a sorted array of the three largest
integers in the input array.


The function should return duplicate integers if necessary; for example, it
should return [10, 10, 12] for an input array of
[10, 5, 9, 10, 12].

Sample Input
array = [141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7]

Sample Output
[18, 141, 541]


 */
fun findThreeLargestNumbers(array: List<Int>): List<Int> {
    // Write your code here.
    var list = mutableListOf<Int>(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)
    for (n in array) {
        helper(list, n)
    }
    return list
}

fun helper(list: MutableList<Int>, num: Int) {
    if (num > list[2]) {
        swapNumber(list, num, 2)
    } else if(num > list[1]) {
        swapNumber(list, num, 1)
    } else if (num > list[0]) {
        swapNumber(list, num, 0)
    }
}

fun swapNumber(list: MutableList<Int>, num: Int, idx: Int) {
    for ( i in 0 until idx + 1) {
        if (idx == i) {
            list[i] = num
        } else {
            list[i] = list[i+1]
        }
    }
}
