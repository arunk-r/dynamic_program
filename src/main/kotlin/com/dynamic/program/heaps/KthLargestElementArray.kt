package com.dynamic.program.heaps

import java.util.PriorityQueue

/**
 * Kth Largest Element in an Array
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * You must solve it in O(n) time complexity.
 *
 *
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 *
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums\[i] <= 10^4
 *
 */
class KthLargestElementArray {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        placeK(0, nums.size-1, nums, nums.size - k)
        return nums[nums.size - k]
    }

    private fun placeK(low: Int, high: Int, nums: IntArray, k: Int) {
        if (high <= low) return
        val pivot = findPivot(low, high, nums)
        if (pivot == k) return
        if (pivot < k) {
            placeK(pivot+1, high, nums, k)
        } else {
            placeK(low, pivot-1, nums, k)
        }
    }

    private fun findPivot(low: Int, high: Int, nums: IntArray): Int {
        var i = low
        var j = high + 1

        while (i < j) {
            while (nums[++i] < nums[low]) {
                if (i == high) break
            }

            while (nums[low] < nums[--j]) {
                if (j == low) break
            }

            if (i >= j) break
            swap(i, j, nums)
        }
        swap(low, j, nums)
        return j
    }

    fun swap(i: Int, j: Int, nums: IntArray) {
        val t = nums[i]
        nums[i] = nums[j]
        nums[j] = t
    }

    fun findKthLargest1(nums: IntArray, k: Int): Int {
        val heap = PriorityQueue<Int>()
        nums.forEach{ n ->
            heap.add(n)
            if(heap.size > k) {
                heap.remove()
            }
        }
        return heap.peek()
    }
}

fun main() {
    println(KthLargestElementArray().findKthLargest(intArrayOf(7,2,8,4,8,9,1), 3))
    //1,2,4,5,7,9
}
