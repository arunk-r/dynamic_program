package com.dynamic.program.interviewing.io.`2023`.sept.`22`

import com.dynamic.program.trees.TreeNode
import sun.security.ec.point.ProjectivePoint.Mutable

//fix a given BST where two nodes are swapped.
class BSTWhere2NodesAreSwapped {
    private var prev: TreeNode? = null
    private var first: TreeNode? = null
    private var middle: TreeNode? = null
    private var last: TreeNode? = null

    fun correctBstSort(root: TreeNode?) {
        val input = mutableListOf<Int>()
        val idx = intArrayOf(0)
        inorder(root, input, true, idx)     //o(n)
        input.sort()                        //o(nlogn)
        inorder(root, input, false, idx)    //o(n)
    }

    fun correctBstFixingArrayPosition(root: TreeNode?) {
        val input = mutableListOf<Int>()
        val idx = intArrayOf(0)
        inorder(root, input, true, idx)     //o(n)
        input.sort()                        //o(n)
        inorder(root, input, false, idx)    //o(n)
    }

    fun correctBst(root: TreeNode?) {
        if (root == null) return
        else {
            correctBstUtils(root)                           //o(n)
            if (first != null && last != null) {
                val tmp = first?.`val`!!
                first?.`val` = last?.`val`!!
                last?.`val` = tmp
            } else if (first != null && middle != null) {
                val tmp = first?.`val`!!
                first?.`val` = middle?.`val`!!
                middle?.`val` = tmp
            }
        }
    }

    private fun fixingArrayPosition(input: MutableList<Int>) {
        for (i in 0 until input.size - 1) {
            if (input[i] > input[i+1]) {
                var j = i
                while (j >= 0 && input[j] > input[j+1]) {
                    swap(input, j , j + 1)
                    j--
                }
            }
        }
    }

    private fun swap(input: MutableList<Int>, i: Int, j: Int) {
        val tmp = input[i]
        input[i] = input[j]
        input[j] = tmp
    }


    private fun inorder(node: TreeNode?, input: MutableList<Int>, collect: Boolean, idx: IntArray) {
        if (node != null) {
            inorder(node.left, input, collect, idx)
            if (collect) {
                input.add(node.`val`)
            } else {
                node.`val` = input[idx[0]]
                idx[0]++
            }
            inorder(node.right, input, collect, idx)
        }
    }

    private fun correctBstUtils(root: TreeNode?) {
        if (root != null) {
            correctBstUtils(root.left)
            if (prev != null && root.`val` < prev?.`val`!!) {
                if (first == null) {
                    first = prev
                    middle = root
                } else {
                    last = root
                }
            }
            prev = root
            correctBstUtils(root.right)
        }
    }
}

fun main() {
    val node = TreeNode(7, TreeNode(5, TreeNode(4), TreeNode(8)), TreeNode(12))
    val obj = BSTWhere2NodesAreSwapped()
    obj.correctBst(node)
    println(node)
    obj.correctBstSort(node)
    println(node)
    obj.correctBstFixingArrayPosition(node)
    println(node)
}
