package com.dynamic.program.hard.topological_sort

/**
 * You're given a list of arbitrary jobs that need to be completed; these jobs
 * are represented by distinct integers. You're also given a list of dependencies. A
 * dependency is represented as a pair of jobs where the first job is a
 * prerequisite of the second one. In other words, the second job depends on the
 * first one; it can only be completed once the first job is completed.
 *
 * Write a function that takes in a list of jobs and a list of dependencies and
 * returns a list containing a valid order in which the given jobs can be
 * completed. If no such order exists, the function should return an empty array.
 *
 * Sample Input
 * jobs = [1, 2, 3, 4]
 * deps = [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]
 *
 * Sample Output
 * [1, 4, 3, 2] or [4, 1, 3, 2]
 */

fun topologicalSort(jobs: List<Int>, deps: List<List<Int>>): List<Int> {
    // Write you code here.
    val graph = createJobsGraph(jobs, deps)
    return getOrderedJobs(graph)
}
fun createJobsGraph(jobs: List<Int>, deps: List<List<Int>>): Graph {
    val graph = Graph(jobs)
    deps.forEach {
        graph.addDependencies(it[1], it[0])
    }
    return graph
}

fun getOrderedJobs(graph: Graph): List<Int> {
    val orderedJobs: MutableList<Int> = mutableListOf()
    val nodes = graph.nodes
    while (nodes.isNotEmpty()) {
        val node = nodes.removeAt(0)
        val cycle = depthFirstSearch(node, orderedJobs)
        if (cycle) return emptyList()
    }
    return orderedJobs
}

fun depthFirstSearch(node:Node, orderedJobs: MutableList<Int>): Boolean {
    if (node.visited) return false
    if (node.visiting) return true
    node.visiting = true
    node.deps.forEach {
        val cycle = depthFirstSearch(it, orderedJobs)
        if (cycle) return true
    }
    node.visited = true
    node.visiting = false
    orderedJobs.add(node.job)
    return false
}
class Graph(jobs: List<Int>) {
    val graph: MutableMap<Int, Node> = mutableMapOf()
    val nodes: MutableList<Node> = mutableListOf()
    fun addDependencies(job: Int, dependent: Int) {
        val jobNode = this.getNode(job)
        val dependentNode = this.getNode(dependent)
        jobNode.deps.add(dependentNode)
    }

    fun getNode(job: Int): Node {
        if (!this.graph.contains(job)) {
            this.addNode(job)
        }
        return this.graph[job]!!
    }

    fun addNode(job:Int) {
        this.graph[job] = Node(job)
        this.graph[job]?.let { this.nodes.add(it) }
    }

    init {
        jobs.forEach { addNode(it) }
    }
}

class Node(val job: Int) {
    val deps: MutableList<Node> = mutableListOf()
    var visited: Boolean = false
    var visiting: Boolean = false
}

fun main() {
    println(
        topologicalSort(
            listOf(1, 2, 3, 4),
            listOf(listOf(1, 2), listOf(1, 3), listOf(3, 2), listOf(4, 2), listOf(4, 3))
        )
    )
}