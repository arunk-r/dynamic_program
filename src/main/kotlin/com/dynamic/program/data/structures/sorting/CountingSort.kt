package com.dynamic.program.data.structures.sorting

class CountingSort {
    fun countingSort(nums: IntArray, k: Int): IntArray {
        val counts = IntArray(k)
        val output = IntArray(nums.size)

        for (i in nums) {
            counts[i]++
        }
        for (i in 1 until k) {
            counts[i] += counts[i-1]
        }
        for (i in nums.size-1 downTo 0) {
            //identify the array value index
            val key = nums[i]
            //get the output array index from counts
            val idx = counts[key]-1
            // add key at idx
            output[idx] = key
            // decrement the value so that next element will be placed in that location idx
            counts[key]--
        }
        return output
    }
}
fun main() {
    var arr = intArrayOf(7, 3, 5, 8, 2, 6, 9,8,9, 1)
    println(CountingSort().countingSort(arr, 10).toList())

    arr = intArrayOf(9,9,9,9,9,9,9,9,9,9)
    println(CountingSort().countingSort(arr, 10).toList())
}
