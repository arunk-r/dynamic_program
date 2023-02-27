package com.dynamic.program.interval


/**
 * Insert Interval
 */
class InsertInterval {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val ans = ArrayList<IntArray>()
        var idx = 0
        while (idx < intervals.size && intervals[idx][0] < newInterval[0]) {
            ans.add(intervals[idx++])
        }
        if (ans.size == 0 || newInterval[0] > ans[ans.size - 1][1]) {
            ans.add(newInterval)
        } else {
            val lastInterval = ans[ans.size - 1]
            lastInterval[1] = maxOf(lastInterval[1], newInterval[1])
        }
        while (idx < intervals.size) {
            val lastInterval = ans[ans.size - 1]
            if (lastInterval[1] >= intervals[idx][0]) {
                lastInterval[1] = maxOf(lastInterval[1], intervals[idx++][1])
            } else ans.add(intervals[idx++])
        }
        return ans.toTypedArray()
    }
}

fun main() {
    println(InsertInterval().insert(arrayOf(intArrayOf(1,3), intArrayOf(6,9)), intArrayOf(2,5)))
}
