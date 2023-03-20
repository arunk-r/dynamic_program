package com.dynamic.program.graphs.hard

/**
 * 1136. Parallel Courses
Medium
company
Google
company
Microsoft
company
Amazon
You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.

Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.



Example 1:


Input: n = 3, relations = [[1,3],[2,3]]
Output: 2
Explanation: The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.
Example 2:


Input: n = 3, relations = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: No course can be studied because they are prerequisites of each other.


Constraints:

1 <= n <= 5000
1 <= relations.length <= 5000
relations[i].length == 2
1 <= prevCoursei, nextCoursei <= n
prevCoursei != nextCoursei
All the pairs [prevCoursei, nextCoursei] are unique.
 */
class ParallelCourses {
    fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
        if (n==1) return 0
        val inDegree = IntArray(n+1)
        val graph = hashMapOf<Int, MutableList<Int>>()

        relations.forEach { (f, t) ->
            graph.putIfAbsent(t, mutableListOf())
            graph[t]?.add(f)

            graph.putIfAbsent(f, mutableListOf())
            graph[f]?.add(t)

            inDegree[t]++
        }

        val q = ArrayDeque<Int>()
        for( i in 1 .. n) {
            if (inDegree[i] == 0) {
                q.addLast(i)
            }
        }
        if (q.isEmpty()) return -1
        var level = 0
        var semesters = 0
        while(q.isNotEmpty()) {
            val size = q.size
            level +=size
            semesters++
            for (i in 0 until size) {
                val cur = q.removeFirst()
                graph[cur]?.forEach { c ->
                    inDegree[c]--
                    if (inDegree[c] == 0) {
                        q.addLast(c)
                    }
                }
            }
        }

        if (level != n) return -1
        return semesters
    }
}

fun main() {
    println(ParallelCourses().minimumSemesters(3, arrayOf(intArrayOf(1,3), intArrayOf(2,3))))
}
