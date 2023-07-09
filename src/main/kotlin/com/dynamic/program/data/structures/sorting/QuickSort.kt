package com.dynamic.program.data.structures.sorting

class QuickSort {
    fun quickSort(num: IntArray) {
        sort(0, num.size-1, num)
    }

    fun sort(low: Int, high: Int, nums: IntArray) {
        if (high <= low) return
        val pivot = findPivot(low, high, nums)
        sort(low, pivot-1, nums)
        sort(pivot+1, high, nums)
    }

    private fun findPivot(low: Int, high: Int, nums: IntArray): Int {
        var i = low
        var j = high + 1

        while (i < j) {
            while (nums[++i] < nums[low]) {
                if (i == high) break
            }

            while (nums[low] < nums[--j]) {
                if (j == low) break
            }

            if (i >= j) break
            swap(i, j, nums)
        }
        swap(low, j, nums)
        return j
    }

    fun swap(i: Int, j: Int, nums: IntArray) {
        val t = nums[i]
        nums[i] = nums[j]
        nums[j] = t
    }
}

































class QuickSort1 {
    fun quickSort(nums: IntArray) {
        quick(nums, 0, nums.size - 1)
    }

    private fun quick(nums: IntArray, lo: Int, hi: Int) {
        if (hi <= lo) return

        val pivot = partition(nums, lo, hi)
        quick(nums, lo, pivot - 1)
        quick(nums, pivot + 1, hi)
    }

    private fun partition(nums: IntArray, lo: Int, hi: Int): Int {
        var i = lo
        var j = hi + 1

        while (i < j) {
            while (nums[++i] < nums[lo]) {
                if (i == hi) break
            }

            while (nums[lo] < nums[--j]) {
                if (j == lo) break
            }

            if (i >= j) break
            swap(nums, i, j)
        }
        swap(nums, lo, j)
        return j
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val t = nums[i]
        nums[i] = nums[j]
        nums[j] = t
    }

}

fun main() {
    val arr = intArrayOf(7, 3, 5, 8, 2, 6, 9, 8, 9, 1)
    QuickSort().quickSort(arr)
    println(arr.toList())
}
