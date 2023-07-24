package com.dynamic.program.data.structures.sorting.abdul_bari

class Heapify {
    fun heapify(arr: IntArray) {
        val size = arr.size
        for (i in size/2 - 1 downTo 0) {
            maxHeapify(arr, i, size)
        }
    }

    fun maxHeapify(arr: IntArray, idx: Int, size: Int) {
        val leftChild = idx * 2 + 1
        val rightChild = idx * 2 + 2
        var maxI = idx
        if (leftChild < size && arr[maxI] < arr[leftChild]) {
            maxI = leftChild
        }

        if (rightChild < size && arr[maxI] < arr[rightChild]) {
            maxI = rightChild
        }
        if (idx != maxI) {
            swap(arr, idx, maxI)
            maxHeapify(arr, maxI, size)
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
