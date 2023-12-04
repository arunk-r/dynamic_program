package com.dynamic.program.arrays.medium

import com.dynamic.program.trees.TreeNode

class TwoCityScheduling {

    data class Costs(val a: Int, val b: Int, val i: Int)
    fun twoCitySchedCost(costs: Array<IntArray>): Int {
        val aList = mutableListOf<Costs>()
        val bList = mutableListOf<Costs>()
        val aSet = HashSet<Int>()
        val bSet = HashSet<Int>()

        for(i in costs.indices) {
            val c = Costs(costs[i][0], costs[i][1], i)
            aList.add(c)
            bList.add(c)
            aSet.add(i)
            bSet.add(i)
        }

        aList.sortBy {it.a}
        bList.sortBy {it.b}

        var i = 0
        var j = 0
        var cnt = costs.size
        var result = 0
        while(cnt > 0) {
            if(aList[i].a <= bList[j].b) {
                result += aList[i].a
                bSet.remove(i)
                i++
            } else {
                result += bList[j].b
                aSet.remove(j)
                j++
            }
            cnt--

            while(!aSet.contains(i)) {
                i++
            }
            while(!bSet.contains(j)) {
                j++
            }
        }
        return result
    }
}

fun main() {
    println(TwoCityScheduling().twoCitySchedCost(arrayOf(intArrayOf(10,20), intArrayOf(30,200), intArrayOf(400,50), intArrayOf(30,20))))
}
