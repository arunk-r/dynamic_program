package com.dynamic.program.design.medium.orderset

import java.util.TreeMap

class MyCalendar {
    data class Data(val s: Int, val e: Int)
    val map = TreeMap<Int, Int>()
    fun book(start: Int, end: Int): Boolean {
        val prev = map.floorKey(start)
        val next = map.ceilingKey(start)
        if ((prev == null || (map[prev] != null && map[prev]!! <= start)) && (next == null || end <= next)) {
            map[start] = end
            return true
        }
        return false
    }

    private fun canBook(d: Data, lst: MutableList<Data>): Boolean {
        var l = 0
        var r = lst.size - 1
        while (l <= r) {
            val mid = l + (r - l) / 2
            val midVal = lst[mid]
            if (d.s in midVal.s .. midVal.e || d.e in midVal.s .. midVal.e) {
                return false
            } else if (midVal.e < d.s) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return true
    }
}

fun main() {
    val m = MyCalendar()
    /*println(m.book(10,20))
    println(m.book(15,25))
    println(m.book(20,30))*/

    println(m.book(47,50))
    println(m.book(33,41))
    println(m.book(39,45))
    println(m.book(33,42))
    println(m.book(25,32))
    println(m.book(26,35))
    println(m.book(19,25))
    println(m.book(3,8))
    println(m.book(8,13))
    println(m.book(18,27))
}
