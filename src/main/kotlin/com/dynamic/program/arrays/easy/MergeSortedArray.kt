package com.dynamic.program.arrays.easy

/**
 * 88. Merge Sorted Array
Easy
company
Google
company
Facebook
company
Bloomberg
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109


Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
class MergeSortedArray {
    /**
     * Intuition is
     *  1. keep 3 pointers.
     *  2. traverse from backwards
     *  3. let 'p' be a replacing pointer
     *  4. let 'p1' be a long array pointer
     *  5. let 'p2' be a small or merging array pointer
     *  6. on loop make sure p is >= 0
     *  7. if p2 reached less than zero means then small array values are already merged hence break the loop
     *
     */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {

        var p = nums1.size - 1
        var p1 = m-1
        var p2 = n-1

        while (p >= 0) {
            if (p2 < 0) return
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--]
            } else {
                nums1[p--] = nums2[p2--]
            }
        }

    }

    fun merge1(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var i = 0
        var j = 0
        val d = IntArray(m+n+1)
        var idx = 0
        while(i < nums1.size && j < nums2.size) {
            if(i < m && nums1[i] <= nums2[j]) {
                d[idx++] = nums1[i++]
            } else {
                d[idx++] = nums2[j++]
            }
        }

        while(i < m) {
            d[idx++] = nums1[i++]
        }

        while(j < nums2.size) {
            d[idx++] = nums2[j++]
        }

        for(i in nums1.indices) {
            nums1[i] = d[i]
        }
    }
}
