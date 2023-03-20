package com.dynamic.program.arrays.hard


class MergeSort {

    fun mergeSort(arr: MutableList<Int>, s: Int = 0, e: Int = arr.size-1) {
        if ((e - s) > 1) {
            val mid = (s + e + 1) / 2
            mergeSort(arr, s, mid)
            mergeSort(arr, mid, e)
            val leftArray = arr.subList(s, mid).toMutableList()
            val rightArray = arr.subList(mid, e).toMutableList()
            merge(leftArray, rightArray, arr, leftArray.size, rightArray.size, s, e)
        }
    }

    fun merge(l: MutableList<Int>, r: MutableList<Int>, arr: MutableList<Int>, i: Int = l.size, j: Int = r.size, s: Int, e: Int) {
        if (s < e && e > 0) {
            if (j<=0 || i > 0 && l[i-1] > r[j-1] ) {
                arr[e-1] = l[i-1]
                merge(l, r, arr, i-1, j, s, e-1)
            } else {
                arr[e-1] = r[j-1]
                merge(l, r, arr, i, j-1, s, e-1)
            }
        }
    }
}

fun main() {
    val i = mutableListOf(5, 1, 3, 6, 8)
    MergeSort().mergeSort(i)
    println(i)

}
