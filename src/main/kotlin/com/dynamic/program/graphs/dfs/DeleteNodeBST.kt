package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.TreeNode
import java.util.TreeMap

/**
 * Delete Node in a BST
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 *
 * Example 3:
 * Input: root = [], key = 0
 * Output: []
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -10^5 <= key <= 10^5
 */
class DeleteNodeBST {
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null ) return null
        if(root.`val` > key) {
            root.left = deleteNode(root.left, key)
        } else if(root.`val` < key) {
            root.right = deleteNode(root.right, key)
        } else {
            if (root.left == null) return root.right
            if (root.right == null) return root.left

            root.`val` = successor(root)
            root.right = deleteNode(root.right, root.`val`)
        }
        val h = hashMapOf<Int, Int>()
        h.keys.toList()[0]
        return root
    }

    fun successor(root: TreeNode): Int {
        var r:TreeNode = root.right!!
        while(r.left != null)
            r.left?.let{r = it}
        return r.`val`
    }
}

fun main() {
    val h = TreeNode(9,
        TreeNode(6, TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(5)), TreeNode(8, TreeNode(7))),
        TreeNode(12, TreeNode(10, null, TreeNode(11)), TreeNode(14, TreeNode(13), TreeNode(16, TreeNode(15)))))
    val h2 = h.copy()

    println(DeleteNodeBST().deleteNode(h, 12))
    //println(DeleteNodeBST().deleteNode(h2, 10))

}