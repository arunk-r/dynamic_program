package com.dynamic.program.data.structures.sorting

class MergeSort {
    fun mergesort(nums: IntArray) {
        merge(nums, IntArray(nums.size), 0, nums.size-1)
    }

    fun merge(nums: IntArray, aux: IntArray, lo: Int, hi: Int) {
        if (hi <= lo) return
        val mid = lo + (hi - lo) / 2
        merge(nums, aux, lo, mid)
        merge(nums, aux, mid + 1, hi)
        helper(nums, aux, lo, mid, hi)
    }

    fun helper(nums: IntArray, aux: IntArray, lo: Int, mid: Int, hi: Int) {
        for (k in lo..hi) {
            aux[k] = nums[k]
        }
        var i = lo
        var j = mid + 1
        for (k in lo..hi) {
            if (i > mid) nums[k] = aux[j++]
            else if (j > hi) nums[k] = aux[i++]
            else if (aux[j] < aux[i]) nums[k] = aux[j++]
            else nums[k] = aux[i++]
        }
    }
}

fun main() {
    val arr = intArrayOf(7, 3, 5, 8, 2, 6, 9, 8, 9, 1)
    MergeSort().mergesort(arr)
    println(arr.toList())
}
