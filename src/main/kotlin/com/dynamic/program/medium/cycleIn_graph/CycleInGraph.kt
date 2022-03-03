package com.dynamic.program.medium.cycleIn_graph

fun cycleInGraph(edges: List<List<Int>>): Boolean {
    // Write your code here.
    val visited = MutableList<Boolean>(edges.size) {false}
    val currentlyInStack = MutableList<Boolean>(edges.size) {false}

    println(visited)

    for (node in edges.indices) {
        if (visited[node]) {
            continue
        }

        val inCycle = isNodeInCycle(edges, node, visited, currentlyInStack)
        if (inCycle) {
            return true
        }
    }
    return false
}

fun isNodeInCycle(edges: List<List<Int>>, node: Int, visited: MutableList<Boolean>, currentlyInStack: MutableList<Boolean>) : Boolean {
    visited[node] = true
    currentlyInStack[node] = true

    val neighbors = edges[node]

    for (neighbor in neighbors) {
        if (!visited[neighbor]) {
            val inCycle = isNodeInCycle(edges, neighbor, visited, currentlyInStack)
            if (inCycle) {
                return true
            }
        } else if (currentlyInStack[neighbor]) {
            return true
        }
    }
    currentlyInStack[node] = false
    return false
}

fun main() {
    println(cycleInGraph(listOf(listOf(1,3), listOf(2,5,4), listOf(5), listOf(), listOf(2), listOf())))
}