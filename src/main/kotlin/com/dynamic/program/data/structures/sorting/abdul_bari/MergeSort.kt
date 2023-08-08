package com.dynamic.program.data.structures.sorting.abdul_bari

class MergeSort {
    fun mergeSort(array: IntArray, aux: IntArray = IntArray(array.size), low: Int = 0, high: Int = array.size - 1) {
        if (low < high) {
            val mid = (low + high) / 2
            mergeSort(array, aux, low, mid)
            mergeSort(array, aux, mid + 1, high)
            merge(array, aux, low, mid, high)
        }
    }

    private fun merge(array: IntArray, aux: IntArray, low: Int, mid: Int, high: Int) {
        for (k in low..high) {
            aux[k] = array[k]
        }
        var i = low
        var j = mid + 1
        for (k in low..high) {
            if (i > mid) {
                array[k] = aux[j++]
            } else if (j > high) {
                array[k] = aux[i++]
            } else if (aux[i] > aux[j]) {
                array[k] = aux[j++]
            } else {
                array[k] = aux[i++]
            }
        }
    }
}

fun main() {
    val arr = intArrayOf(10, 5, 3, 14, 8, 9, 7, 9)
    MergeSort().mergeSort(arr)
    println(arr.toList())
}
