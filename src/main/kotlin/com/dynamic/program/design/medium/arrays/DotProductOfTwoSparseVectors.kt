package com.dynamic.program.design.medium.arrays

/**
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/description/
 *
 * 1570. Dot Product of Two Sparse Vectors
 * Medium
 * 1.1K
 * 139
 * company
 * Facebook
 * company
 * Bloomberg
 * company
 * Amazon
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 *
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 *
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */
class DotProductOfTwoSparseVectors(val nums: IntArray) {
    val map = hashMapOf<Int, Int>()
    init {
        for(i in nums.indices) {
            if(nums[i] != 0) {
                map[i] = nums[i]
            }
        }
    }
    // Return the dotProduct of two sparse vectors
    fun dotProduct(vec: DotProductOfTwoSparseVectors): Int {
        var sum = 0
        for((k, v) in map) {
            val v2 = vec.map[k]
            if (v2 != null) {
                sum += v * v2
            }
        }

        return sum
    }

    // Return the dotProduct of two sparse vectors
    fun dotProduct1(vec: DotProductOfTwoSparseVectors): Int {
        var left = 0
        var right = minOf(nums.size, vec.nums.size)-1
        var sum = 0
        while(left <= right) {
            sum += (nums[left] * vec.nums[left])
            if(left != right){
                sum += (nums[right] * vec.nums[right])
            }
            left++
            right--
        }

        return sum
    }
}
