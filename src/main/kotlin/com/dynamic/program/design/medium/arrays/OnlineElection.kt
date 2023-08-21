package com.dynamic.program.design.medium.arrays

class OnlineElection(persons: IntArray, times: IntArray) {
    data class Vote(val person: Int, val time: Int)
    val list = mutableListOf<MutableList<Vote>>()
    val map = hashMapOf<Int, Int>()
    init {
        for(i in persons.indices) {
            val p = persons[i]
            val t = times[i]

            val cnt = map.getOrDefault(p, 0) + 1
            map[p] = cnt
            while(list.size <= cnt) {
                list.add(mutableListOf())
            }
            list[cnt].add(Vote(p, t))
        }
    }

    fun q(t: Int): Int {
        val i = findFirstIdx(t)
        val next = findSecondIdx(t, i)
        return list[i][next].person
    }

    private fun findFirstIdx(t :Int): Int {
        var l = 0
        var r = list.size
        while(l < r) {
            val mid = l + (r - l) / 2
            if(list[mid][0].time <= t) {
                l = mid + 1
            } else {
                r= mid
            }
        }
        return l-1
    }

    private fun findSecondIdx(t :Int, i: Int): Int {
        var l = 0
        var r = list[i].size
        while(l < r) {
            val mid = l + (r - l) / 2
            if(list[i][mid].time <= t) {
                l = mid + 1
            } else {
                r= mid
            }
        }
        return maxOf(l-1, 0)
    }
}

/**
 * Your OnlineElection object will be instantiated and called as such:
 * var obj = OnlineElection(persons, times)
 * var param_1 = obj.q(t)
 */
