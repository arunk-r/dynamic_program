package com.dynamic.program.data.structures.sorting.abdul_bari

class MaxHeap {
    fun  maxHeap(arr: IntArray) {
        val size = arr.size
        for (i in size/2 - 1 downTo 0) {
            heapity(arr, i, size)
        }

        for (i in size-1 downTo 0) {
            swap(arr, 0, i)
            heapity(arr, 0, i)
        }
    }

    private fun heapity(arr: IntArray, idx: Int, size: Int) {
        val left = idx * 2 + 1
        val right = idx * 2 + 2

        var mid = idx
        if (left < size && arr[left] > arr[mid]) {
            mid = left
        }

        if (right < size && arr[right] > arr[mid]) {
            mid = right
        }
        if (idx != mid) {
            swap(arr, idx, mid)
            heapity(arr, mid, size)
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
