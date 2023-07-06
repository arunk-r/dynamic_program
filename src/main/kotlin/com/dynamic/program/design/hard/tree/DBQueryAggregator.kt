package com.dynamic.program.design.hard.tree

import java.util.TreeSet

class DBQueryAggregator {
    data class AggregatorData(val qId: String, var cnt: Int = 0, var milliseconds: Int = 0)
    data class Output(val qId: String, val cnt: Int, val milliseconds: Int)
    private val map = hashMapOf<String, AggregatorData>()
    private val set = TreeSet<AggregatorData> { x, y ->
        if (x.cnt == y.cnt) {
            if (x.milliseconds == y.milliseconds) {
                x.qId.compareTo(y.qId)
            } else {
                x.milliseconds - y.milliseconds
            }
        } else {
            y.cnt - x.cnt
        }
    }

    fun put(qId: String, milliseconds: Int) {
        var d = map[qId]
        if (d == null) {
            d = AggregatorData(qId)
            map[qId] = d
        } else {
            set.remove(d)
        }
        d.cnt++
        d.milliseconds += milliseconds
        set.add(d)
    }

    fun getTopK(k: Int): List<Output> {
        val result = mutableListOf<Output>()
        var i = 0
        for (d in set) {
            if (i == k) {
                break
            }
            result.add(Output(d.qId, d.cnt, d.milliseconds))
            i++
        }
        return result
    }
}

fun main() {
    val o = DBQueryAggregator()
    o.put("1", 1)
    o.put("1", 1)
    o.put("1", 1)
    o.put("2", 1)
    o.put("2", 1)
    o.put("2", 1)
    o.put("3", 1)
    o.put("3", 1)
    o.put("2", 1)
    o.put("3", 1)
    o.put("3", 1)
    o.put("3", 1)
    println(o.getTopK(3))
}
