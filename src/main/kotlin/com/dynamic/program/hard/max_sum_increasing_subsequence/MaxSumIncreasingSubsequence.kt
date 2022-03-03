package com.dynamic.program.hard.max_sum_increasing_subsequence

/**
 * Write a function that takes in a non-empty array of integers and returns the
 * greatest sum that can be generated from a strictly-increasing subsequence in
 * the array as well as an array of the numbers in that subsequence.
 *
 * A subsequence of an array is a set of numbers that aren't necessarily adjacent
 * in the array but that are in the same order as they appear in the array. For
 * instance, the numbers [1, 3, 4] form a subsequence of the array
 * [1, 2, 3, 4], and so do the numbers [2, 4]. Note
 * that a single number in an array and the array itself are both valid
 * subsequences of the array.
 *
 * You can assume that there will only be one increasing subsequence with the
 * greatest sum.
 *
 * Sample Input
 * array = [10, 70, 20, 30, 50, 11, 30]
 *
 * Sample Output
 * [110, [10, 20, 30, 50]] // The subsequence [10, 20, 30, 50] is strictly increasing and yields the greatest sum: 110.
 */
fun maxSumIncreasingSubsequence(array: List<Int>): Pair<Int, List<Int>> {
    // Write your code here.
    val seqArray = MutableList(array.size) {Int.MIN_VALUE}
    val sums = array.toMutableList()
    var maxSumIdx = 0
    for (i in array.indices) {
        val currentNum = array[i]
        for (j in 0 until i) {
            val otherNum = array[j]
            if (otherNum < currentNum && sums[j] + currentNum >= sums[i]) {
                sums[i] = sums[j] + currentNum
                seqArray[i] = j
            }

            if (sums[i] >= sums[maxSumIdx]) {
                maxSumIdx = i
            }
        }
    }
    return Pair(sums[maxSumIdx], buildSequence(array, seqArray, maxSumIdx))
}

fun buildSequence(array: List<Int>, seqArray: List<Int>, currentIdx: Int): List<Int> {
    val sequence = mutableListOf<Int>()
    var cIdx = currentIdx
    while (cIdx != Int.MIN_VALUE) {
        sequence.add(0, array[cIdx])
        cIdx = seqArray[cIdx]
    }
    return sequence
}

fun main() {
    println(maxSumIncreasingSubsequence(listOf(10, 70, 20, 30, 50, 11, 30)))
}