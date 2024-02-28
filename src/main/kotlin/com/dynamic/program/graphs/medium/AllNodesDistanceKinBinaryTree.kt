package com.dynamic.program.graphs.medium

import com.dynamic.program.trees.TreeNode

class AllNodesDistanceKinBinaryTree {
    val parents = HashMap<TreeNode, TreeNode?>()
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        if(root == null || target == null) return listOf()
        // think like graph
        val res = mutableListOf<Int>()
        val q = ArrayDeque<TreeNode>()
        q.addLast(target)
        var dist = 0
        while(q.isNotEmpty() && dist <= k) {
            for(i in q.size downTo 1) {
                val n = q.removeFirst()
                if(dist == k) {
                    res.add(n.`val`)
                }
                n.left?.let{ q.addLast(it)}
                n.right?.let{q.addLast(it)}
                parents[n]?.let{q.addLast(it)}
            }
            dist++
        }
        return res
    }

    private fun buildParent(node: TreeNode?, parent: TreeNode?) {
        if(node == null) return
        parents[node] = parent
        buildParent(node.left, node)
        buildParent(node.right, node)
    }
}
