package com.dynamic.program.min_heap

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
open class MinHeap(array: MutableList<Int>) {
    val heap = this.buildHeap(array)

    fun buildHeap(array: MutableList<Int>): MutableList<Int> {
        // Write your code here.
        val firstParent = (array.size - 2) / 2
        for (idx in firstParent downTo 0) {
            this.siftDown(idx, array.size - 1, array)
        }
        return array
    }

    fun siftDown(currentIdx: Int, endIdx: Int, heap: MutableList<Int>) {
        // Write your code here.
        var newCurrentIdx = currentIdx
        var childIdx = currentIdx * 2 + 1
        while (childIdx <= endIdx) {
            val childIdxTwo = if (newCurrentIdx * 2 + 2 <= endIdx) newCurrentIdx * 2 + 2 else -1
            val idxSwap: Int = if (childIdxTwo != -1 && heap[childIdxTwo] < heap[childIdx]) {
                childIdxTwo
            } else {
                childIdx
            }

            if (heap[idxSwap] < heap[newCurrentIdx]) {
                swap(heap, newCurrentIdx, idxSwap)
                newCurrentIdx = idxSwap
                childIdx = newCurrentIdx * 2 + 1
            } else {
                return
            }
        }
    }

    fun siftUp(cIdx: Int, heap: MutableList<Int>) {
        // Write your code here.
        var currentIdx = cIdx
        var parentIdx = (currentIdx - 1) / 2
        while (currentIdx > 0 && heap[currentIdx] < heap[parentIdx]) {
            swap(heap, currentIdx, parentIdx)
            currentIdx = parentIdx
            parentIdx = (currentIdx - 1) / 2
        }
    }

    private fun swap(heap: MutableList<Int>, i: Int, j: Int) {
        val swap = heap[i]
        heap[i] = heap[j]
        heap[j] = swap
    }

    fun peek(): Int? {
        // Write your code here.
        return this.heap[0]
    }

    fun remove(): Int? {
        // Write your code here.
        swap(heap, 0, heap.size - 1)
        val lastValue = heap.removeAt(heap.size - 1)
        siftDown(0, heap.size - 1, heap)
        return lastValue
    }

    fun insert(value: Int) {
        // Write your code here.
        this.heap.add(value)
        this.siftUp(heap.size - 1, heap)
    }
}

fun main() {
    println(MinHeap(mutableListOf(48, 12, 24, 7, 8, -5)).heap)
}