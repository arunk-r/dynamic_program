package com.dynamic.program.graphs.bfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * 449. Serialize and Deserialize BST
 * Medium
 * 3.3K
 * 156
 * company
 * Amazon
 * company
 * Microsoft
 * company
 * LinkedIn
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The input tree is guaranteed to be a binary search tree.
 */
class SerializeAndDeserializeBST {
    // Encodes a tree to a single string.
    fun serialize(root: TreeNode?): String {
        val sb = StringBuffer()
        preorder(root, sb)
        if (sb.isNotEmpty()) {
            sb.deleteCharAt(sb.length-1)
        }
        return sb.toString()
    }

    private fun preorder(node: TreeNode?, sb: StringBuffer){
        if (node != null) {
            sb.append(node.`val`)
            sb.append(',')
            preorder(node.left, sb)
            preorder(node.right, sb)
        }
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if (data == "") return null
        val d = data.split(",")
        if (d.isNotEmpty()) {
            val root = TreeNode(d[0].toInt())
            for(i in 1 until d.size) {
                buildTree(d[i].toInt(), root)
            }
            return root
        }
        return null
    }

    private fun buildTree(v: Int, node: TreeNode) {
        if(v < node.`val`) {
            if (node.left == null) {
                node.left = TreeNode(v)
            } else {
                buildTree(v, node.left!!)
            }
        } else {
            if (node.right == null) {
                node.right = TreeNode(v)
            } else {
                buildTree(v, node.right!!)
            }
        }
    }

    // Encodes a tree to a single string.
    fun serialize2(root: TreeNode?): String {
        val sb = StringBuffer()
        postorder(root, sb)
        if (sb.isNotEmpty()) {
            sb.deleteCharAt(sb.length -1)
        }
        return sb.toString()
    }

    private fun postorder(node: TreeNode?, sb: StringBuffer) {
        if (node != null) {
            postorder(node.left, sb)
            postorder(node.right, sb)
            sb.append(node.`val`)
            sb.append(',')
        }
    }

    // Decodes your encoded data to tree.
    fun deserialize2(data: String): TreeNode? {
        //println(data)
        val q = ArrayDeque<Int>()
        val values = data.split(",")
        for(i in values) {
            if (i =="") return null
            q.addLast(i.toInt())
        }
        return tree(q, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun tree(q: ArrayDeque<Int>, left: Int, right: Int): TreeNode? {
        if (q.isEmpty()) return null
        val v = q[q.lastIndex]
        if (v < left || v > right ) {
            return null
        }
        q.removeLast()
        val root = TreeNode(v)
        root.right = tree(q, v, right)
        root.left = tree(q, left, v)
        return root
    }

    // Encodes a tree to a single string.
    fun serialize1(root: TreeNode?): String {
        if (root == null) return ""
        return bfs(root)
    }

    fun bfs(node: TreeNode): String {
        val q = ArrayDeque<TreeNode>()
        q.addLast(node)
        val buf = StringBuffer()
        while(q.isNotEmpty()) {
            for(i in q.size downTo 1) {
                val n = q.removeFirst()
                buf.append(n.`val`)
                if (i != 1) {
                    buf.append(',')
                }
                n.left?.let{q.addLast(it)} ?: '*'
                n.right?.let{q.addLast(it)} ?: '*'
            }
            buf.append('#')
        }
        val result = buf.toString()
        return result.substring(0, result.length-1)
    }

    // Decodes your encoded data to tree.
    fun deserialize1(data: String): TreeNode? {
        if (data == "") return null
        return build(data)
    }

    fun build(s: String): TreeNode? {
        //println(s)
        val levels = s.split("#")
        val root = TreeNode(levels[0].toInt())
        var curRoots = mutableListOf<TreeNode>()
        curRoots.add(root)
        for(i in 1 until levels.size) {
            val values = levels[i].split(",")
            var vIdx = 0
            val temRoots = mutableListOf<TreeNode>()
            for(n in curRoots) {
                if (vIdx < values.size) {
                    val v = values[vIdx++].toInt()
                    if (v < n.`val`) {
                        val lNode = TreeNode(v)
                        n.left = lNode
                        temRoots.add(lNode)
                        if (vIdx < values.size) {
                            val rNode = TreeNode(values[vIdx++].toInt())
                            n.right = rNode
                            temRoots.add(rNode)
                        }
                    } else {
                        val rNode = TreeNode(v)
                        n.right = rNode
                        temRoots.add(rNode)
                    }
                }
            }
            curRoots = temRoots
        }
        return root
    }
}
