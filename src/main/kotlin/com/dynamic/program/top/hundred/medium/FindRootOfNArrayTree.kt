package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/find-root-of-n-ary-tree/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 1506. Find Root of N-Ary Tree
 * Medium
 * 443
 * 179
 * company
 * Bloomberg
 * company
 * Google
 * You are given all the nodes of an N-ary tree as an array of Node objects, where each node has a unique value.
 *
 * Return the root of the N-ary tree.
 *
 * Custom testing:
 *
 * An N-ary tree can be serialized as represented in its level order traversal where each group of children is separated by the null value (see examples).
 *
 *
 *
 * For example, the above tree is serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 *
 * The testing will be done in the following way:
 *
 * The input data should be provided as a serialization of the tree.
 * The driver code will construct the tree from the serialized input data and put each Node object into an array in an arbitrary order.
 * The driver code will pass the array to findRoot, and your function should find and return the root Node object in the array.
 * The driver code will take the returned Node object and serialize it. If the serialized value and the input data are the same, the test passes.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: tree = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Explanation: The tree from the input data is shown above.
 * The driver code creates the tree and gives findRoot the Node objects in an arbitrary order.
 * For example, the passed array could be [Node(5),Node(4),Node(3),Node(6),Node(2),Node(1)] or [Node(2),Node(6),Node(1),Node(3),Node(5),Node(4)].
 * The findRoot function should return the root Node(1), and the driver code will serialize it and compare with the input data.
 * The input data and serialized Node(1) are the same, so the test passes.
 * Example 2:
 *
 *
 *
 * Input: tree = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *
 *
 * Constraints:
 *
 * The total number of nodes is between [1, 5 * 104].
 * Each node has a unique value.
 */
class FindRootOfNArrayTree {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    fun findRoot(tree: List<Node>): Node? {
        if (tree.isEmpty()) return null
        else if (tree.size == 1) return tree[0]
        else {
            val visited = HashSet<Node>()
            val newNodes = HashSet<Node>()
            var q = ArrayDeque<Node>()
            tree.forEach { node ->
                if (!visited.contains(node)) {
                    newNodes.add(node)
                    visited.add(node)

                    q = ArrayDeque<Node>()
                    q.addLast(node)
                    while (q.isNotEmpty()) {
                        val n = q.removeFirst()
                        n.children.forEach { nei ->
                            if (nei != null) {
                                if (newNodes.contains(nei)) {
                                    newNodes.remove(nei)
                                }
                                if (!visited.contains(nei)) {
                                    visited.add(nei)
                                    q.addLast(nei)
                                }
                            }
                        }
                    }
                }
            }
            return newNodes.first()
        }
    }
}

fun main() {
    val o1 = FindRootOfNArrayTree.Node(1)
    val o2 = FindRootOfNArrayTree.Node(2)
    val o3 = FindRootOfNArrayTree.Node(3)
    val o4 = FindRootOfNArrayTree.Node(4)
    val o5 = FindRootOfNArrayTree.Node(5)
    val o6 = FindRootOfNArrayTree.Node(6)
    o1.children = listOf(o3,o2,o4)
    o3.children = listOf(o5, o6)

    println(FindRootOfNArrayTree().findRoot(listOf(o4,o5, o3, o6, o2, o1)))
}
