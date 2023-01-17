package com.dynamic.program.heaps

import java.util.*


/**
 * Total Cost to Hire K Workers
 */
class TotalCostHireKWorkers {
    fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
        val t = TreeMap<Int, MutableList<Int>>()
        var idx = 0
        costs.forEach{ c ->
            t.putIfAbsent(c, mutableListOf())
            t[c]?.add(idx++)
        }
        var sessions = k
        var fc = 0L
        while(sessions > 0 && t.isNotEmpty()) {
            val key = t.firstKey()
            val value = t[key]
            value?.removeAt(0)
            if (value?.isEmpty() == true) {
                t.remove(key)
            }
            fc += key
            sessions--
        }

        return fc
    }
}

fun main() {
    println(TotalCostHireKWorkers().totalCost(intArrayOf(17,12,10,2,7,2,11,20,8), 3, 4))
}