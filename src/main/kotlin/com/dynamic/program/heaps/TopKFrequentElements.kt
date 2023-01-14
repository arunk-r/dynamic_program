package com.dynamic.program.heaps

import java.util.PriorityQueue

/**
 * Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 */
class TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val heap = PriorityQueue<Pair<Int, Int>>{o1,o2 -> o1.first - o2.first}
        val map = hashMapOf<Int, Int>()
        nums.forEach{ n ->
            map.putIfAbsent(n,0)
            map[n] = map[n]!!+1
        }

        map.forEach{ (k,v) ->
            heap.add(Pair(v,k))
        }

        while(heap.size > k){
            heap.remove()
        }

        val arr = IntArray(k)
        var idx = 0
        heap.forEach{ p->
            arr[idx++] = p.second
        }
        return arr
    }
}