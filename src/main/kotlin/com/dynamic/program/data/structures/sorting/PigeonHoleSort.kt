package com.dynamic.program.data.structures.sorting

/**
 * Pigeonhole sorting is a sorting algorithm that is suitable for sorting lists of elements where the number of elements and the number of possible key values are approximately the same.
 * It requires O(n + Range) time where n is number of elements in input array and ‘Range’ is number of possible values in array.
 *
 * The time complexity of Pigeonhole Sort is O(n + range)
 * Auxiliary Space: O(range)
 *
 * Advantages of Pigeonhole sort:
 *
 * It is a non-comparison based sort making it faster in application.
 * It is a stable sorting algorithm.
 * It performs sorting in linear time.
 * Disadvantages of Pigeonhole sort:
 *
 * It is not easy to know the range of the numbers to sort.
 * This number might only work with zero and positive integers.
 */
class PigeonHoleSort {
    fun sort(arr: IntArray) {
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        // find min and max
        for (n in arr) {
            min = minOf(min, n)
            max = maxOf(max, n)
        }
        // identify the range
        val range = max - min + 1
        val pigeon = IntArray(range)
        for (i in arr.indices) {
            pigeon[arr[i] - min] ++
        }
        var idx = 0
        // place the items in pigeonhole to original array
        for (j in pigeon.indices) {
            while (pigeon[j]-- > 0) {
                arr[idx++] = j + min
            }
        }
    }
}

fun main() {
    val input = intArrayOf(8, 3, 2, 7, 4, 6, 2, 8, 9)
    PigeonHoleSort().sort(input)
    println(input.toList())
}
