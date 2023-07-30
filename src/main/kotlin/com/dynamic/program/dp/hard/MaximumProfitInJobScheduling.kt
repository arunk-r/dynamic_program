package com.dynamic.program.dp.hard

import java.util.PriorityQueue

class MaximumProfitInJobScheduling {
    data class Data(val s: Int, val e: Int, val p: Int)

    private fun findJobs(lst: MutableList<Data>): Int {
        val q = PriorityQueue<Data> { x, y -> x.e - y.e }
        var maxProfit = 0
        for (d in lst) {
            while (q.isNotEmpty() && d.s >= q.peek().e) {
                val qd = q.remove()
                maxProfit = maxOf(maxProfit, qd.p)
            }
            q.add(Data(d.s, d.e, d.p + maxProfit))
        }

        while (q.isNotEmpty()) {
            val qd = q.remove()
            maxProfit = maxOf(maxProfit, qd.p)
        }
        return maxProfit
    }

    fun jobSchedulingPriorityQueue(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val lst = mutableListOf<Data>()
        for (i in startTime.indices) {
            lst.add(Data(startTime[i], endTime[i], profit[i]))
        }
        lst.sortWith(kotlin.Comparator { x, y -> if(x.s == y.s) x.e - y.e else x.s - y.s })

        return findJobs(lst)
    }

    fun jobSchedulingGreedy(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val mem = IntArray(startTime.size) { -1 }
        val lst = mutableListOf<Data>()
        for (i in startTime.indices) {
            lst.add(Data(startTime[i], endTime[i], profit[i]))
        }
        lst.sortWith { x, y -> x.s - y.s }
        return findMaxProfit(0, lst, mem)
    }

    private fun findNextJob(startTime: MutableList<Data>, lastEndingTime: Int): Int {
        var start = 0
        var end = startTime.size - 1
        var nextIndex = startTime.size
        while (start <= end) {
            val mid = (start + end) / 2
            if (startTime[mid].s >= lastEndingTime) {
                nextIndex = mid
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return nextIndex
    }

    private fun findMaxProfit(idx: Int, lst: MutableList<Data>, mem: IntArray): Int {
        if (idx == lst.size) {
            return 0
        }
        if (mem[idx] != -1) return mem[idx]

        val nextIdx = findNextJob(lst, lst[idx].e)
        mem[idx] = maxOf(
            findMaxProfit(idx + 1, lst, mem),
            lst[idx].p + findMaxProfit(nextIdx, lst, mem)
        )
        return mem[idx]
    }
}

fun main() {
    println(
        MaximumProfitInJobScheduling().jobSchedulingGreedy(
            intArrayOf(4, 2, 4, 8, 2),
            intArrayOf(5, 5, 5, 10, 8),
            intArrayOf(1, 2, 8, 10, 4)
        )
    )

    println(
        MaximumProfitInJobScheduling().jobSchedulingPriorityQueue(
            intArrayOf(4, 2, 4, 8, 2),
            intArrayOf(5, 5, 5, 10, 8),
            intArrayOf(1, 2, 8, 10, 4)
        )
    )

    println(
        MaximumProfitInJobScheduling().jobSchedulingGreedy(
            intArrayOf(1,2,3,4,6),
            intArrayOf(3,5,10,6,9),
            intArrayOf(20,20,100,70,60)
        )
    )

    println(
        MaximumProfitInJobScheduling().jobSchedulingPriorityQueue(
            intArrayOf(1,2,3,4,6),
            intArrayOf(3,5,10,6,9),
            intArrayOf(20,20,100,70,60)
        )
    )
}
