package com.dynamic.program.min_heap

/**
 * Implement a <span>MinHeap class that supports:
 *
 * Building a Min Heap from an input array of integers.
 *  Inserting integers in the heap.
 *  Removing the heap's minimum / root value.
 *  Peeking at the heap's minimum / root value.
 *
 *  Sifting integers up and down the heap, which is to be used when inserting and removing values.
 *
 * Note that the heap should be represented in the form of an array.
 *
 * If you're unfamiliar with Min Heaps, we recommend watching the
 * Conceptual Overview section of this question's video explanation before
 * starting to code.
 *
 * Sample Usage
 * array = [48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41]
 *
 * // All operations below are performed sequentially.
 * MinHeap(array): - // instantiate a MinHeap (calls the buildHeap method and populates the heap)
 * buildHeap(array): - [-5, 2, 6, 7, 8, 8, 24, 391, 24, 56, 12, 24, 48, 41]
 * insert(76): - [-5, 2, 6, 7, 8, 8, 24, 391, 24, 56, 12, 24, 48, 41, 76]
 * peek(): -5
 * remove(): -5 [2, 7, 6, 24, 8, 8, 24, 391, 76, 56, 12, 24, 48, 41]
 * peek(): 2
 * remove(): 2 [6, 7, 8, 24, 8, 24, 24, 391, 76, 56, 12, 41, 48]
 * peek(): 6
 * insert(87): - [6, 7, 8, 24, 8, 24, 24, 391, 76, 56, 12, 41, 48, 87]


 */
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

    fun isEmpty(): Boolean {
        return heap.isEmpty()
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

    fun peek(): Int {
        // Write your code here.
        return this.heap[0]
    }

    fun remove(): Int {
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