package com.dynamic.program.graphs.bfs

import java.util.TreeSet

class TimeNeededInformAllEmployees {
    fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        val graph = hashMapOf<Int, HashSet<Int>>()
        for(i in 0 until n) {
            graph[i] = hashSetOf()
        }
        for(i in manager.indices) {
            if (manager[i] != -1) {
                graph[manager[i]]?.add(i)
            }
        }
        return helper(informTime, graph, headID)
    }

    private fun helper(informTime: IntArray, map: Map<Int, HashSet<Int>>, curr: Int): Int {
        if (!map.containsKey(curr)) return 0
        var min = 0
        map[curr]?.forEach {
            min = Math.max(helper(informTime, map, it), min)
        }
        return min + informTime[curr]
    }
}

fun main() {
    println(TimeNeededInformAllEmployees().numOfMinutes(11, 4, intArrayOf(5,9,6,10,-1,8,9,1,9,3,4), intArrayOf(0,213,0,253,686,170,975,0,261,309,337)))
}