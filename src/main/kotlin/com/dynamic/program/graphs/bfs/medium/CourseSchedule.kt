package com.dynamic.program.graphs.bfs.medium

/**
 * 207. Course Schedule
Medium

Amazon
Google
Facebook
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
class CourseSchedule {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val visited = hashSetOf<Int>()
        val visiting = hashSetOf<Int>()
        val graph = hashMapOf<Int, MutableList<Int>>()
        for (p in prerequisites) {
            graph.putIfAbsent(p[0], mutableListOf())
            graph[p[0]]!!.add(p[1])
        }

        graph.keys.forEach { k ->
            if (! dfs(k, graph, visited, visiting)) return false
        }

        return true
    }

    fun dfs(c: Int, graph: HashMap<Int, MutableList<Int>>, visited: HashSet<Int>, visiting: HashSet<Int>): Boolean {
        if (visited.contains(c)) return true
        if (visiting.contains(c)) return false
        visiting.add(c)
        if (graph[c] != null) {
            graph[c]!!.forEach { k ->
                if (!dfs(k, graph, visited, visiting)) return false
            }
        }
        visiting.remove(c)
        visited.add(c)
        return true
    }
}

fun main() {
    println(CourseSchedule().canFinish(2, arrayOf(intArrayOf(1, 0))))
    println(CourseSchedule().canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0,1))))
}
