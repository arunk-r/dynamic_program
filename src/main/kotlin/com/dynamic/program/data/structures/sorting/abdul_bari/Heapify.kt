package com.dynamic.program.data.structures.sorting.abdul_bari

class Heapify {
    fun heapify(arr: IntArray) {
        maxHeapify(arr)
    }

    private fun maxHeapify(arr: IntArray) {
        val size = arr.size
        for (i in size/2 - 1 downTo 0) {
            heapify(arr, i, size)
        }
    }

    private fun heapify(arr: IntArray, idx: Int, size: Int) {
        val l = 2 * idx + 1
        val r = 2 * idx + 2
        var midI = idx
        if (l < size && arr[midI] < arr[l]) {
            midI = l
        }

        if (r < size && arr[midI] < arr[r]) {
            midI = r
        }

        if (midI != idx) {
            swap(arr, idx, midI)
            heapify(arr, midI, size)
        }
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val t = arr[i]
        arr[i] = arr[j]
        arr[j] = t
    }
}

fun main() {
    var arr = intArrayOf(10,20,15,12,40,25,18)
    Heapify().heapify(arr)
    println(arr.toList())

    arr = intArrayOf(7,4,9,11,1,3,2)
    Heapify().heapify(arr)
    println(arr.toList())
}
