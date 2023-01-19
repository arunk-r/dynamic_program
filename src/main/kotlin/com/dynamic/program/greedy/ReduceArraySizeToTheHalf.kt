package com.dynamic.program.greedy

import java.util.*

class ReduceArraySizeToTheHalf {
    fun minSetSize(arr: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        arr.forEach{ a ->
            map.putIfAbsent(a, 0)
            map[a] = map[a]!! + 1
        }
        var size = 0
        var cnt = 0

        val q = PriorityQueue<Pair<Int, Int>>{x,y -> y.second - x.second}
        map.forEach{ (k,v) ->
            q.add(Pair(k,v))
        }

        while(q.isNotEmpty()) {
            val cur = q.remove()
            size += cur.second
            cnt++
            if (size >= arr.size / 2) {
                break;
            }
        }
        return cnt
    }
}

fun main() {
    println(ReduceArraySizeToTheHalf().minSetSize(intArrayOf(7,7,7,7,7,7)))
}