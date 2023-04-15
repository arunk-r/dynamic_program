package com.dynamic.program.data.structures.sorting

class MinHeap {
    fun heapsort(nums: IntArray) {
        buildMinHeap(nums)
        val size = nums.size
        for (i in size - 1 downTo 1) {
            kotlin.run { val t = nums[i];nums[i] = nums[0];nums[0] = t }
            minHeapify(nums, 0, i)
        }
    }

    private fun buildMinHeap(nums: IntArray) {
        val size = nums.size
        for (i in size / 2 - 1 downTo 0) {
            minHeapify(nums, i, size)
        }
    }

    private fun minHeapify(nums: IntArray, idx: Int, size: Int) {
        val left = leftNode(idx)
        val right = rightNode(idx)
        var minI = idx

        if (left < size && nums[left] < nums[minI]) {
            minI = left
        }
        if (right < size && nums[right] < nums[minI]) {
            minI = right
        }
        if (minI != idx) {
            kotlin.run { val t = nums[minI];nums[minI] = nums[idx];nums[idx] = t }
            minHeapify(nums, minI, size)
        }

    }

    private fun leftNode(idx: Int): Int = 2 * idx + 1
    private fun rightNode(idx: Int): Int = 2 * idx + 2
}

fun main() {
    val arr = intArrayOf(7, 3, 5, 8, 2, 6, 9,8,9, 1)
    MinHeap().heapsort(arr)
    println(arr.toList())
}
