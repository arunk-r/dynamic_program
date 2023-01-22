package com.dynamic.program.greedy

import java.util.Arrays

/**
 * Earliest Possible Day of Full Bloom
 */
class EarliestPossibleDayOfFullBloom {
    fun earliestFullBloom(plantTime: IntArray, growTime: IntArray): Int {
        var start = 0
        var end = plantTime[0] + growTime[0] - 1
        for(i in 1 until plantTime.size) {
            start += plantTime[i-1]
            end = start + plantTime[i] + growTime[i]
        }
        return end
    }

    fun earliestFullBloom_1(plantTime: IntArray, growTime: IntArray): Int {

        val n = growTime.size
        val indices: MutableList<Int> = ArrayList()
        for (i in 0 until n) {
            indices.add(i)
        }
        indices.sortWith(kotlin.Comparator{x, y -> growTime[y] - growTime[x]})
        var result = 0
        var i = 0
        var curPlantTime = 0
        while (i < n) {
            val idx = indices[i++]
            val time = curPlantTime + plantTime[idx] + growTime[idx]
            result = Math.max(result, time)
            curPlantTime += plantTime[idx]
        }
        return result
    }
}

fun main() {
    println(EarliestPossibleDayOfFullBloom().earliestFullBloom(intArrayOf(1,4,3), intArrayOf(2,3,1)))
    println(EarliestPossibleDayOfFullBloom().earliestFullBloom_1(intArrayOf(1,2,3,2), intArrayOf(2,1,2,1)))
}