package com.dynamic.program.stacks_queues.hard

import java.util.PriorityQueue

/**
 * 239. Sliding Window Maximum
Hard

Amazon

Google

DoorDash
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.



Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */
class SlidingWindowMaximum {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val p = PriorityQueue<Pair<Int, Int>>{x, y  -> if(x.first == y.first) x.second - y.second else y.first - x.first}
        val result = mutableListOf<Int>()
        for (i in 0 until k) {
            p.add(Pair(nums[i], i))
        }
        result.add(p.peek().first)
        p.remove(Pair(nums[0], 0))
        for (i in k until nums.size) {
            p.add(Pair(nums[i], i))

            val prevIdx = i-k+1
            while(p.peek().second <= prevIdx) {
                p.remove()
            }
            result.add(p.peek().first)

        }
        return result.toIntArray()
    }
}

fun main() {
    println(SlidingWindowMaximum().maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 3))
}
