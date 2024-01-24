package com.dynamic.program.trees.dp

import com.dynamic.program.trees.TreeNode

class BinaryTreeCameras {
    val mem = HashSet<TreeNode?>()
    var ans = 0
    fun minCameraCover(root: TreeNode?): Int {
        mem.add(null)
        countCameras(root, null)
        return ans
    }

    private fun countCameras(node: TreeNode?, parent: TreeNode?) {
        if(node != null) {
            countCameras(node.left, node)
            countCameras(node.right, node)
            if((parent == null && !mem.contains(node)) ||
                !mem.contains(node.left) ||
                !mem.contains(node.right)) {
                ans++
                mem.add(node)
                mem.add(parent)
                mem.add(node.left)
                mem.add(node.right)
            }
        }
    }
}


fun main() {
    val root = TreeNode(0, TreeNode(0, TreeNode(0), TreeNode(0)))
    println(BinaryTreeCameras().minCameraCover(root))
}
