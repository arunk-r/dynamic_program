package com.dynamic.program.data.structures.sorting.abdul_bari

class MaxHeap {
    fun  maxHeap(arr: IntArray) {
        val size = arr.size
        for (i in size/2 -1 downTo 0) {
            heapity(arr, i, size)
        }

        for (i in size-1 downTo 1) {
            swap(arr, 0, i)
            heapity(arr, 0, i)
        }
    }

    private fun heapity(arr: IntArray, idx: Int, size: Int) {
        val left = idx * 2 + 1
        val right = idx * 2 + 2
        var midI = idx

        if (left < size && arr[midI] < arr[left]) {
            midI = left
        }

        if (right < size && arr[midI] < arr[right]) {
            midI = right
        }

        if (midI != idx) {
            swap(arr, idx, midI)
            heapity(arr, midI, size)
        }
    }
    private fun swap(arr: IntArray, i: Int, j: Int) {
        val t = arr[i]
        arr[i] = arr[j]
        arr[j] = t
    }
}
fun main() {
    val arr = intArrayOf(10,13,40,23,78,4,2)
    MaxHeap().maxHeap(arr)
    println(arr.toList())
}
