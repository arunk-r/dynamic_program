package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.GTreeNode

/**
 * What is Isomorphism?
 * refer https://en.wikipedia.org/wiki/Graph_isomorphism
 *
 */
class IsomorphicGraph {
    private val alreadyBuildNode = mutableSetOf<Int>()
    fun isomorphic(graph1: Map<Int, List<Int>>, graph2: Map<Int, List<Int>>): Boolean {
        val graph1Roots = findRoot(graph1)
        val graph1Tree = buildTree(graph1Roots[0], graph1)
        val graph1Encode = encodeGraph(graph1Tree)

        alreadyBuildNode.clear()
        val graph2Roots = findRoot(graph2)
        val graph2Tree = buildTree(graph2Roots[0], graph2)
        val graph2Encode = encodeGraph(graph2Tree)

        return graph1Encode.toCharArray().sorted().joinToString("") == graph2Encode.toCharArray().sorted().joinToString("")
    }

    private fun encodeGraph(root: GTreeNode): String {
        if (root.children.size == 0) return "()"
        var encode = ""
        root.children.forEach { node ->
            if (node != null) {
                encode = "$encode${encodeGraph(node)}"
            }
        }
        return "($encode)"
    }

    private fun buildTree(start: Int, graph: Map<Int, List<Int>>): GTreeNode {
        val root = GTreeNode(start, mutableListOf())
        alreadyBuildNode.add(start)
        build(root, graph)
        return root
    }

    private fun build(node: GTreeNode, graph: Map<Int, List<Int>>) {
        graph[node.`val`]?.forEach { neighbor ->
            if (!alreadyBuildNode.contains(neighbor)) {
                val child = GTreeNode(neighbor, mutableListOf())
                alreadyBuildNode.add(neighbor)
                node.children.add(child)
                build(child, graph)
            }
        }
    }

    private fun findRoot(graph: Map<Int, List<Int>>): List<Int> {
        val degree = hashMapOf<Int, Int>()
        var leafNodes = mutableListOf<Int>()
        graph.forEach { (k, v) ->
            degree[k] = v.size
            if (v.size <= 1) {
                leafNodes.add(k)
            }
        }
        while (leafNodes.isNotEmpty()) {
            val neeLeafNodes = mutableListOf<Int>()
            leafNodes.forEach { lf ->
                graph[lf]?.forEach { k ->
                    if (degree.containsKey(k)) {
                        degree[k] = degree[k]!! - 1
                        if (degree[k]!! <= 1) {
                            neeLeafNodes.add(k)
                        }
                    }
                }
                degree.remove(lf)
            }
            leafNodes = if (degree.size > 2) {
                neeLeafNodes
            } else {
                mutableListOf()
            }
        }
        return degree.keys.toList()
    }
}

fun main() {
    val graph1 = hashMapOf<Int, List<Int>>()
    graph1[0] = listOf(1)
    graph1[1] = listOf(2, 4, 0)
    graph1[2] = listOf(1)
    graph1[3] = listOf(5, 4)
    graph1[4] = listOf(3, 1)
    graph1[5] = listOf(3)

    val graph2 = hashMapOf<Int, List<Int>>()
    graph2[0] = listOf(1)
    graph2[1] = listOf(2,0)
    graph2[2] = listOf(4,1)
    graph2[3] = listOf(4)
    graph2[4] = listOf(5, 3, 2)
    graph2[5] = listOf(4)

    println(IsomorphicGraph().isomorphic(graph1, graph2))
}