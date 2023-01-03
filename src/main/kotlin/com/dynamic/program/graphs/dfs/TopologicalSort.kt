package com.dynamic.program.graphs.dfs

/**
 * A topological ordering is an ordering of the nodes in a directed graph where for each directed edge from node A to B,
 * node A appears before node B in the ordering.
 *
 * The topological sort algorithm can find a topological ordering in O(V+E) time!
 *
 * Note: Topological ordering are NOT unique.
 */
class TopologicalSort {
    fun topologicalSort(graph: Map<Char, List<Char>>): List<Char> {
        val list = mutableListOf<Char>()
        val visited = mutableSetOf<Char>()
        graph.keys.forEach { k ->
            if (!visited.contains(k)) {
                bfs(k, graph, list, visited)
            }
        }
        list.reverse()
        return list
    }

    private fun bfs(current: Char, graph: Map<Char, List<Char>>, ordering: MutableList<Char>, visited: MutableSet<Char>) {
        if (!visited.contains(current)) {
            visited.add(current)
            if (graph.containsKey(current)) {
                if (graph[current]?.isNotEmpty() == true) {
                    graph[current]?.forEach { neighbor ->
                        if (!visited.contains(neighbor)) {
                            bfs(neighbor, graph, ordering, visited)
                        }
                    }
                }
            }
            ordering.add(current)
        }
    }

    /*    private fun topologicalSort(node: GTreeNode, sortedList: MutableList<Char>) {
            if (node.children.isNotEmpty()) {
                sortedList.add(node.`val`)
                return
            }
            node.children.forEach { child ->
                bfsSort(child, sortedList)
            }
            sortedList.add(node.char)
        }*/
}

fun main() {
    val graph = hashMapOf<Char, List<Char>>()
    graph['A'] = listOf('D')
    graph['B'] = listOf('D')
    graph['C'] = listOf('A', 'B')
    graph['D'] = listOf('H', 'G')
    graph['E'] = listOf('A', 'D', 'F')
    graph['F'] = listOf('K', 'J')
    graph['G'] = listOf('I')
    graph['H'] = listOf('I', 'J')
    graph['I'] = listOf('L')
    graph['J'] = listOf('L', 'M')
    graph['K'] = listOf('J')
    graph['L'] = listOf()
    graph['M'] = listOf()


    println(TopologicalSort().topologicalSort(graph))
}