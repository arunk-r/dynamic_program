package com.dynamic.program.trees.others

/**
 * find center of a tree or find a root node(s)
 *
 * Hint: center is always the middle vertex or middle 2 vertices in every longest path of the tree
 * Hint2: Find the number of edges or degree's for each node and pick off each leaf node like layers
 *
 * pass1
 *  (1) 0        (1) 9            (1) 4
 *      |            |                |
 *  (2) 1--------(4) 2 -----------(3) 3
 *                   |                |
 *               (3) 6 - (1) 8    (1) 5
 *                   |
 *               (1) 7
 *
 * pass2
 *               (3) 2 -----------(1) 3
 *                   |
 *               (1) 6
 *
 * pass3
 *               (1) 2
 */
class CenterOFTreeOrFindRootNode {
    fun findRoot(g: Map<Int, List<Int>>): List<Int> {
        val degree = hashMapOf<Int, Int>()
        var leafNode = mutableListOf<Int>()
        g.forEach { (k, v) ->
            degree[k] = v.size
            if (degree[k]!! <= 1) {
                leafNode.add(k)
            }
        }

        while (leafNode.isNotEmpty()) {
            val newLeafNodes = mutableListOf<Int>()
            leafNode.forEach { ln ->
                g[ln]?.forEach { n ->
                    if (degree.containsKey(n)) {
                        degree[n] = degree[n]!! - 1
                        if (degree[n]!! <= 1) {
                            newLeafNodes.add(n)
                        }
                    }
                }
                degree.remove(ln)
            }
            leafNode = if (degree.size > 2) {
                newLeafNodes
            } else {
                mutableListOf()
            }
        }

        return degree.keys.toList()
    }
}

fun main() {
    var g = hashMapOf<Int, List<Int>>()
    g[0] = listOf(1)
    g[1] = listOf(2, 0)
    g[2] = listOf(9, 6, 3, 1)
    g[3] = listOf(5, 4, 2)
    g[4] = listOf(3)
    g[5] = listOf(3)
    g[6] = listOf(8, 7, 2)
    g[7] = listOf(6)
    g[8] = listOf(6)
    g[9] = listOf(2)

    println(CenterOFTreeOrFindRootNode().findRoot(g))

    g = hashMapOf<Int, List<Int>>()
    g[0] = listOf(1)
    g[1] = listOf(4, 3, 0)
    g[2] = listOf(3)
    g[3] = listOf(7, 6, 2, 1)
    g[4] = listOf(8, 5, 1)
    g[5] = listOf(4)
    g[6] = listOf(9, 3)
    g[7] = listOf(3)
    g[8] = listOf(4)
    g[9] = listOf(6)

    println(CenterOFTreeOrFindRootNode().findRoot(g))

    val graph2 = hashMapOf<Int, List<Int>>()
    graph2[0] = listOf(1)
    graph2[1] = listOf(2,0)
    graph2[2] = listOf(4,1)
    graph2[3] = listOf(4)
    graph2[4] = listOf(5, 3, 2)
    graph2[5] = listOf(4)

    println(CenterOFTreeOrFindRootNode().findRoot(graph2))
}