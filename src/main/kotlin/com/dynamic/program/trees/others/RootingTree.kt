package com.dynamic.program.trees.others

import com.dynamic.program.trees.GTreeNode

/**
 * Construct a tree using adjacency nodes data.
 */
class RootingTree {
    private val alreadyConstructed = hashSetOf<Int>()
    fun rootTree(g: Map<Int, List<Int>>, startRoot: Int = 0): GTreeNode {
        val root = GTreeNode(startRoot, mutableListOf())
        alreadyConstructed.add(startRoot)
        buildTree(g, root)
        return root
    }

    private fun buildTree(g: Map<Int, List<Int>>, node: GTreeNode) {
        g[node.`val`]?.forEach { neighbor ->
            if (!alreadyConstructed.contains(neighbor)) {
                val child = GTreeNode(neighbor, mutableListOf())
                alreadyConstructed.add(neighbor)
                node.children.add(child)
                buildTree(g, child)
            }
        }
    }
}

fun main() {
    val g = hashMapOf<Int, List<Int>>()
    g[0] = listOf(2, 1, 5)
    g[1] = listOf(0)
    g[2] = listOf(3, 0)
    g[3] = listOf(2)
    g[4] = listOf(5)
    g[5] = listOf(4, 6, 0)
    g[6] = listOf(5)
    println(RootingTree().rootTree(g))

    val graph1 = hashMapOf<Int, List<Int>>()
    graph1[0] = listOf(1)
    graph1[1] = listOf(2, 4, 0)
    graph1[2] = listOf(1)
    graph1[3] = listOf(5, 4)
    graph1[4] = listOf(3, 1)
    graph1[5] = listOf(3)
    println(RootingTree().rootTree(graph1, 4))

    val graph2 = hashMapOf<Int, List<Int>>()
    graph2[0] = listOf(1)
    graph2[1] = listOf(2,0)
    graph2[2] = listOf(4,1)
    graph2[3] = listOf(4)
    graph2[4] = listOf(5, 3, 2)
    graph2[5] = listOf(4)
    println(RootingTree().rootTree(graph2, 2))
}