package com.dynamic.program.data.structures.sorting

class QuickSortWithK {
    fun quickSort(nums: MutableList<Int>, k: Int, lo: Int, hi: Int) {
        if(hi <= lo) return
        val pivot = sort(nums, lo, hi)
        if(k in lo until pivot) {
            quickSort(nums, k, lo, pivot-1)
        } else if(k in pivot+1 .. hi) {
            quickSort(nums, k, pivot+1, hi)
        }
    }

    private fun sort(nums: MutableList<Int>, lo: Int, hi: Int): Int {
        var i = lo
        var j = hi+1
        while(i < j) {
            while(nums[++i] > nums[lo])
                if(i == hi) break
            while(nums[lo] > nums[--j])
                if(j == lo) break
            if(i >= j) break
            swap(nums, i, j)
        }
        swap(nums, lo, j)
        return j
    }

    private fun swap(nums: MutableList<Int>, lo: Int, hi: Int) {
        val t = nums[lo]
        nums[lo] = nums[hi]
        nums[hi] = t
    }
}

fun main() {
    println(QuickSortWithK().quickSort(mutableListOf(3,2,1), 2, 0, 2))
}
