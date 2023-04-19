package com.dynamic.program.data.structures.sorting

class ThreeWayQuickSort {
    fun threeWayQuickSort(nums: IntArray) {
        sort(nums, 0, nums.size - 1)
    }

    fun sort(nums: IntArray, lo: Int, hi: Int) {
        if (hi <= lo) return
        val low = nums[lo]
        var i = lo
        var lt = lo
        var gt = hi
        while (i <= gt) {
            val cmp = nums[i].compareTo(low)
            if (cmp < 0) swap(nums, lt++, i++)
            else if (cmp > 0) swap(nums, i, gt--)
            else i++
        }
        sort(nums, lo, lt - 1)
        sort(nums, lt + 1, hi)
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val t = nums[i]
        nums[i] = nums[j]
        nums[j] = t
    }
}

fun main() {
    val arr = intArrayOf(7, 3, 5, 8, 2, 6, 9, 8, 9, 1)
    ThreeWayQuickSort().threeWayQuickSort(arr)
    println(arr.toList())
}
