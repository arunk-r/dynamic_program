package com.dynamic.program.data.structures.sorting

class RadixSort {
    private fun countingSort(nums: IntArray, exp: Int) {
        val output =IntArray(nums.size)
        val pass = IntArray(10)
        for(n in nums) {
            val digit = (n / exp) % 10
            pass[digit]++
        }
        for (i in 1 until 10) {
            pass[i]  += pass[i-1]
        }
        for (i in nums.size - 1 downTo 0) {
            val digit = (nums[i] / exp) % 10
            val key = pass[digit]
            output[key-1] = nums[i]
            pass[digit]--
        }
        for (i in output.indices) {
            nums[i] = output[i]
        }
    }

    fun radixSort(nums: IntArray) {
        var max = Int.MIN_VALUE
        for (n in nums) {
            max = maxOf(n, max)
        }
        var exp = 1
        while (max/exp > 0) {
            countingSort(nums, exp)
            exp *= 10
        }
    }
}
fun main() {
    val r = RadixSort()
    var arr = intArrayOf(7, 3, 5, 8, 2, 6, 9,8,9, 1)
    r.radixSort(arr)
    println(arr.toList())

    arr = intArrayOf(9,9,9,9,9,9,9,9,9,9)
    r.radixSort(arr)
    println(arr.toList())
}
