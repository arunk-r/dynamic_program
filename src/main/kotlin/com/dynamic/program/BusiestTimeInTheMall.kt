package com.dynamic.program

class BusiestTimeInTheMall {
    fun findBusiestPeriod(data: List<List<Long>>): Long{
        var maxTime:Long = 0L
        var maxCnt = 0L
        val data1 = mutableListOf<MutableList<Long>>()
        data1.add(data[0].toMutableList())

        for (i in 1 until data.size) {
            val d = data[i]
            val d1 = data1[data1.size -1]
            if (d1[0] == d[0]) {
                if (d[2] == 1L) {
                    d1[1] += d[1]
                } else {
                    d1[1] -= d[1]
                }
                if (d1[1] < 0) {
                    d1[1] = 0
                }
            }else {
                data1.add(d.toMutableList())
            }
        }
        var count = 0L
        for (d in data1) {
            if (d[2] == 1L) {
                count += d[1]
            } else {
                count -= d[1]
            }

            if (count > maxCnt) {
                maxCnt = count
                maxTime = d[0]
            }
        }
        return maxTime
    }
}

fun main() {
    println(BusiestTimeInTheMall().findBusiestPeriod(listOf(
        listOf(1487799425,14,1),
        listOf(1487799425,4,0),
        listOf(1487799425,2,0),
        listOf(1487800378,10,1),
        listOf(1487800378,10,1),
        listOf(1487801478,18,0),
        listOf(1487801478,19,1),
        listOf(1487801478,1,0),
        listOf(1487801478,1,1),
        listOf(1487901013,1,0),
        listOf(1487901211,7,1),
        listOf(1487901211,8,0)
    )))
}
