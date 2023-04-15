package com.dynamic.program.data.structures.sorting

class MaxHeap {
    fun heapsort(nums: IntArray) {
        buildMaxHeap(nums)
        for (i in nums.size - 1 downTo 1) {
            kotlin.run { val t = nums[i];nums[i] = nums[0];nums[0] = t }
            maxHeapify(nums, 0, i)
        }
    }

    private fun buildMaxHeap(nums: IntArray) {
        val size = nums.size
        for (i in size / 2 - 1 downTo 0) {
            maxHeapify(nums, i, size)
        }
    }

    private fun maxHeapify(nums: IntArray, idx: Int, size: Int) {
        val left = leftNode(idx)
        val right = rightNode(idx)
        var maxI = idx

        if (left < size && nums[left] > nums[maxI]) {
            maxI = left
        }

        if (right < size && nums[right] > nums[maxI]) {
            maxI = right
        }

        if (maxI != idx) {
            kotlin.run { val t = nums[maxI]; nums[maxI] = nums[idx]; nums[idx] = t }
            maxHeapify(nums, maxI, size)
        }
    }

    private fun leftNode(idx: Int): Int = 2 * idx + 1
    private fun rightNode(idx: Int): Int = 2 * idx + 2


    /*fun heapsort(nums: IntArray) {
        buildMaxHeap(nums)
        var size = nums.size
        while (size > 1) {

            run { val tmp = nums[size - 1]; nums[size - 1] = nums[0];nums[0] = tmp }

            size--
            maxHeapify(nums, 0, size)
        }
    }

    private fun buildMaxHeap(nums: IntArray) {
        val size = nums.size
        for (i in size / 2 - 1 downTo 0) {
            maxHeapify(nums, i, size)
        }
    }

    private fun maxHeapify(nums: IntArray, idx: Int, size: Int) {
        val left = leftChild(idx)
        val right = rightChild(idx)
        var maxI = idx

        if (left < size && nums[left] > nums[maxI]) {
            maxI = left
        }

        if (right < size && nums[right] > nums[maxI]) {
            maxI = right
        }

        if (maxI != idx) {
            run { val tmp = nums[maxI]; nums[maxI] = nums[idx];nums[idx] = tmp }
            maxHeapify(nums, maxI, size)
        }
    }

    private fun leftChild(idx: Int): Int = 2 * idx + 1
    private fun rightChild(idx: Int): Int = 2 * idx + 2*/
}

fun main() {
    val arr = intArrayOf(7, 3, 5, 8, 2, 6, 9,8,9, 1)
    MaxHeap().heapsort(arr)
    println(arr.toList())
}
