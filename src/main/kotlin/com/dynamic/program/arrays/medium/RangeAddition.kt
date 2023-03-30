package com.dynamic.program.arrays.medium

/**
 * 370. Range Addition
Medium

company
Citadel
company
Amazon
company
Google
You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.



Example 1:


Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
Output: [-2,0,3,5,3]
Example 2:

Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
Output: [0,-4,2,2,2,4,4,-4,-4,-4]


Constraints:

1 <= length <= 105
0 <= updates.length <= 104
0 <= startIdxi <= endIdxi < length
-1000 <= inci <= 1000
 */
class RangeAddition {
    fun getModifiedArray(length: Int, updates: Array<IntArray>): IntArray {

        val ps = IntArray(length)

        for(update in updates) {
            ps[update[0]] += update[2]
            if (update[1] < length - 1) ps[update[1] + 1] -= update[2]
        }

        var prefix = 0
        for(i in ps.indices) {
            prefix += ps[i]
            ps[i] = prefix
        }

        return ps
    }


    fun getModifiedArray1(length: Int, updates: Array<IntArray>): IntArray {

        val ps = IntArray(length)

        for(update in updates) {
            val lb = update[0]
            val rb = update[1]
            val inc = update[2]
            for(i in lb .. rb) {
                ps[i] = ps[i] + inc
            }
        }

        return ps
    }
}

fun main() {
    println(RangeAddition().getModifiedArray(5, arrayOf(intArrayOf(1,3,2), intArrayOf(2,4,3), intArrayOf(0,2,-2))))
}
