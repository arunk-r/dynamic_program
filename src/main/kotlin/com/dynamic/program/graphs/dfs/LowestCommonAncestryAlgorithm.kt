package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.GTreeNode

/**
 * A popular algorithm for fining lowest common ancestor.
 * There are many LCA fining algorithms for 2 nodes in a tree.
 * 1. Tarjan's offline
 * 2. Heavy-Light decomposition
 * 3. Binary Lifting
 * etc...
 *
 * Here I am implementing LCA using 'Eulerian tour' + 'Range Minimum Query (RQM)' method.
 *
 * This method can answer LCA queries in O(1) time with O(nlogn) per-processing when using Sparese Tables to do the RMQ's.
 *
 * However, the pre-processing time can be improved to O(n) with the Farach-Colton abd Bender optimization.
 *
 * Few house keeping things
 * 1. Make sure the tree is rooted
 * 2. Ensure that all nodes are uniquely indexed in some way so that we can reference them later between [0 to n-1].
 *
 * To start an Eulerian tour/circuit at the root node, traverse all red edges, and finally return to the root node.
 * As you do this, keep track of which nodes you visit and this will be your Euler tour.
 *
 * Ex: refer image img.png source file
 *
 *
 */
class LowestCommonAncestryAlgorithm {
    private val visited = mutableSetOf<Int>()
    private val nodes = mutableListOf<Int>()
    private val depths = mutableListOf<Int>()
    private val last = mutableMapOf<Int, Int>()
    fun lca(graph: Map<Int, List<Int>>, node1: Int, node2: Int): Int {
        val roots = findRoot(graph)
        val root = buildTree(roots[0], graph)
        dfsLCA(root)
        val leftNode = last[Math.min(node1, node2)]!!
        val rightNode = last[Math.max(node1, node2)]!!
        var min = Int.MAX_VALUE
        depths.subList(leftNode, rightNode)
                .forEach { idx -> min = Math.min(idx, min) }
        return nodes[min]
    }

    private fun dfsLCA(node: GTreeNode?, depth: Int = 0) {
        if (node == null) return
        captureVisit(node, depth)
        node.children.forEach { child ->
            dfsLCA(child, depth + 1)
            captureVisit(node,depth)
        }
    }

    private fun captureVisit(node: GTreeNode, depth: Int) {
        depths.add(depth)
        nodes.add(node.`val`)
        last[node.`val`] = depths.size - 1
    }

    private fun buildTree(start: Int, graph: Map<Int, List<Int>>): GTreeNode {
        val root = GTreeNode(start, mutableListOf())
        build(root, graph)
        return root
    }

    private fun build(node: GTreeNode, graph: Map<Int, List<Int>>) {
        graph[node.`val`]?.forEach { childNodeVal ->
            if (!visited.contains(childNodeVal)) {
                val child = GTreeNode(childNodeVal, mutableListOf())
                visited.add(childNodeVal)
                node.children.add(child)
                build(child, graph)
            }
        }
    }

    private fun findRoot(graph: Map<Int, List<Int>>): List<Int> {
        val degree = hashMapOf<Int, Int>()
        var edges = mutableListOf<Int>()
        graph.forEach { (k, v) ->
            degree[k] = v.size
            if (v.size <= 1) {
                edges.add(k)
            }
        }

        while (edges.isNotEmpty()) {
            val newEdges = mutableListOf<Int>()
            edges.forEach { e ->
                graph[e]?.forEach { neibhor ->
                    if (degree.containsKey(neibhor)) {
                        degree[neibhor] = degree[neibhor]!! - 1
                        if (degree[neibhor]!! <= 1) {
                            newEdges.add(neibhor)
                        }
                    }
                }
                degree.remove(e)
                edges = if (degree.size > 2) newEdges else mutableListOf()
            }
        }
        return degree.keys.toList()
    }
}

fun main() {
    val g1 = hashMapOf<Int, List<Int>>()
    g1[0] = listOf(2, 1)
    g1[1] = listOf(3, 0)
    g1[2] = listOf(5, 4, 0)
    g1[3] = listOf(1)
    g1[4] = listOf(6, 2)
    g1[5] = listOf(2)
    g1[6] = listOf(4)

    println(LowestCommonAncestryAlgorithm().lca(g1, 6, 5))
}