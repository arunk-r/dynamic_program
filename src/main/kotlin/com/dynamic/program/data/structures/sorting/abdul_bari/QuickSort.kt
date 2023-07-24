package com.dynamic.program.data.structures.sorting.abdul_bari

class QuickSort {
    fun quickSort(arr: IntArray, low: Int = 0, high: Int = arr.size - 1) {
        if (low < high) {
            val pivotIdx = findPivotAndSort(arr, low, high)
            quickSort(arr, low, pivotIdx-1)
            quickSort(arr, pivotIdx + 1, high)
        }
    }

    private fun findPivotAndSort(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[low]
        var i = low
        var j = high
        while (i < j) {
            while (i < arr.size && arr[i] <= pivot) {
                i++
            }
            while (j >= 0 && arr[j] > pivot) {
                j--
            }
            if (i < j) {
                swap(arr, i, j)
            }
        }
        if (j != low) {
            swap(arr, low, j)
        }
        return j
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val t = arr[i]
        arr[i] = arr[j]
        arr[j] = t
    }
}

fun main() {
    val arr = intArrayOf(10,5,3,14,8,9,7,9)
    QuickSort().quickSort(arr)
    println(arr.toList())
}
