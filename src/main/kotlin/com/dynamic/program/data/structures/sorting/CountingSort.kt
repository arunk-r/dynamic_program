package com.dynamic.program.data.structures.sorting

class CountingSort {
    fun countingSortV2(nums: IntArray, k: Int): IntArray {
        val output = IntArray(nums.size)
        val pass = IntArray(k)
        for(n in nums) {
            pass[n]++
        }
        var sum = k
        for(i in k - 1 downTo 0) {
            pass[i] = sum - pass[i]
            sum = pass[i]
        }
        for (i in nums.indices) {
            val key = nums[i]
            output[pass[key]] = key
            pass[key]++
        }
        return output
    }
    fun countingSortV1(nums: IntArray, k: Int): IntArray {
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
    println(CountingSort().countingSortV1(arr, 10).toList())
    println(CountingSort().countingSortV2(arr, 10).toList())

    arr = intArrayOf(9,9,9,9,9,9,9,9,9,9)
    println(CountingSort().countingSortV1(arr, 10).toList())
    println(CountingSort().countingSortV2(arr, 10).toList())
}
