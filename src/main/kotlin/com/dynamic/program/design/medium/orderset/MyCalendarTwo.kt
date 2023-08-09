package com.dynamic.program.design.medium.orderset

import java.util.TreeMap

class MyCalendarTwo {
    val map = TreeMap<Int, Int>()
    fun book(start: Int, end: Int): Boolean {
        map[start] = map.getOrDefault(start, 0) + 1
        map[end] = map.getOrDefault(end, 0) - 1
        map.forEach { (_, v) ->
            if (v >= 3) {
                map[start] = map.getOrDefault(start, 0) - 1
                map[end] = map.getOrDefault(end, 0) + 1
                if (map[start] == 0) {
                    map.remove(start)
                }
                if (map[end] == 0) {
                    map.remove(end)
                }
                return false
            }
        }
        return true
    }
}
