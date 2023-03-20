package com.dynamic.program.graphs.bfs.medium

class CourseScheduleII {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val inDegree = IntArray(numCourses)
        val lst = mutableListOf<Int>()

        val graph = hashMapOf<Int, MutableList<Int>>()

        for (p in prerequisites) {
            graph.putIfAbsent(p[0], mutableListOf())
            graph[p[0]]?.add(p[1])
            inDegree[p[1]]++
        }
        val q = ArrayDeque<Int>()
        for ((i, v) in inDegree.withIndex()) {
            if (v == 0) {
                q.addLast(i)
            }
        }

        while (q.isNotEmpty()) {
            val parentCourse = q.removeFirst()
            lst.add(parentCourse)
            graph[parentCourse]?.forEach { c ->
                inDegree[c]--
                if (inDegree[c] == 0) {
                    q.addLast(c)
                }
            }
        }
        for ((i, v) in inDegree.withIndex()) {
            if (v != 0) {
                return intArrayOf()
            }
        }
        lst.reverse()
        return lst.toIntArray()
    }
}

fun main() {
    println(CourseScheduleII().findOrder(2, arrayOf(intArrayOf(1, 0))).toList())
    println(CourseScheduleII().findOrder(4, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(3, 1), intArrayOf(3, 2))).toList())
}
