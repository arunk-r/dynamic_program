package com.dynamic.program.heaps

import java.util.*


/**
 * Smallest Range Covering Elements from K Lists
 *
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 * Example 1:
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 * Example 2:
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 *
 * Constraints:
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums\[i].length <= 50
 * -10^5 <= nums\[i]\[j] <= 10^5
 * nums\[i] is sorted in non-decreasing order.
 *
 */
class SmallestRangeCoveringElementsFromKLists {
    fun smallestRange(nums: List<List<Int>>): IntArray {

        var minX = 0
        var minY = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        val next = IntArray(nums.size)
        var flag = true

        val minQueue = PriorityQueue<Int> { i, j -> nums[i][next[i]] - nums[j][next[j]] }
        for (i in nums.indices) {
            minQueue.offer(i)
            max = Math.max(max, nums[i][0])
        }
        var i = 0
        while (i < nums.size && flag) {
            var j = 0
            while (j < nums[i].size && flag) {
                val minI = minQueue.poll()
                if (minY - minX > max - nums[minI][next[minI]]) {
                    minX = nums[minI][next[minI]]
                    minY = max
                }
                next[minI]++
                if (next[minI] == nums[minI].size) {
                    flag = false
                    break
                }
                minQueue.offer(minI)
                max = Math.max(max, nums[minI][next[minI]])
                j++
            }
            i++
        }
        return intArrayOf(minX, minY)
        /*
                if (nums.size == 1) return intArrayOf(nums[0].first(),nums[0].last())
                val heap = PriorityQueue<Pair<IntArray, Int>> { x, y -> x.second - y.second }
                val map = TreeMap<Pair<Int, Int>, Int>{x, y -> if (x.first == y.first) x.second - y.second else x.first - y.first}
                for (i in nums.indices) {
                    map.putIfAbsent(Pair(nums[i][0], i), 0)
                }
                var isValid = true
                while (isValid) {
                    if (map.size > 1) {
                        heap.add(getRangeAndSum(map))
                        isValid = getLowestNextValueFromKList(nums, map)
                    } else {
                        isValid = false
                    }
                }
                return heap.remove().first*/
    }
/*
    private fun getLowestNextValueFromKList(nums: List<List<Int>>, map: TreeMap<Pair<Int, Int>, Int>): Boolean {
        map.forEach { (k, v) ->
            if (v + 1 < nums[k.second].size) {
                map[Pair(nums[k.second][v + 1], k.second)] = v + 1
                map.remove(k)
                return true
            }
        }
        return false
    }

    private fun getRangeAndSum(map: TreeMap<Pair<Int, Int>, Int>): Pair<IntArray, Int> {
        val first = map.firstKey()
        val last = map.lastKey()
        var cnt = 0
        var prev = first
        val it = map.iterator()
        it.next()
        while (it.hasNext()) {
            val tmp = it.next()
            cnt += (tmp.key.first - prev.first)
            prev = tmp.key
        }
        return Pair(intArrayOf(first.first, last.first), cnt)
    }*/
}

fun main() {
    println(SmallestRangeCoveringElementsFromKLists().smallestRange(listOf(listOf(4,10,15,24,26), listOf(0,9,12,20), listOf(5,18,22,30))).toList())
    println(SmallestRangeCoveringElementsFromKLists().smallestRange(listOf(listOf(1,2,3), listOf(1,2,3), listOf(1,2,3))).toList())
    println(SmallestRangeCoveringElementsFromKLists().smallestRange(listOf(listOf(10,10), listOf(11,11))).toList())
    println(SmallestRangeCoveringElementsFromKLists().smallestRange(listOf(listOf(1, 1))).toList())
}