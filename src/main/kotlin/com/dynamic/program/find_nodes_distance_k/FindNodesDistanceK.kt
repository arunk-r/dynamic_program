package com.dynamic.program.find_nodes_distance_k

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * You're given the root node of a Binary Tree, a target value of a
 * node that's contained in the tree, and a positive integer k.
 * Write a function that returns the values of all the nodes that are exactly
 * distance k from the node with target value.
 *
 * The distance between two nodes is defined as the number of edges that must be
 * traversed to go from one node to the other. For example, the distance between
 * a node and its immediate left or right child is 1. The same holds
 * in reverse: the distance between a node and its parent is 1. In a
 * tree of three nodes where the root node has a left and right child, the left
 * and right children are distance 2 from each other.
 *
 * Each BinaryTree node has an integer value, a
 * left child node, and a right child node. Children
 * nodes can either be BinaryTree nodes themselves or None/null.
 * Note that all BinaryTree node values will be unique, and your
 * function can return the output values in any order.
 *
 * Sample Input
 *              tree = 1
 *                   /   \
 *                  2     3
 *               /   \     \
 *              4     5     6
 *                        /   \
 *                       7     8
 * target = 3
 * k = 2
 *
 * Sample Output
 * [2, 7, 8] // These values could be ordered differently.
 */
// This is an input class. Do not edit.
open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun findNodesDistanceK(tree: BinaryTree, target: Int, k: Int): List<Int> {
    // Write your code here.
    val allNodes = HashMap<Int, BinaryTree?>()
    populateAllNodes(tree, null, allNodes)

    val targetNode = getTargetNode(target, tree, allNodes)
    return breathFirstSearchForFindNodesDistanceK(targetNode, allNodes, k)
}

fun breathFirstSearchForFindNodesDistanceK(targetNode: BinaryTree?, allNodes: HashMap<Int, BinaryTree?>, k: Int): List<Int> {
    val queue = LinkedList<Pair<BinaryTree, Int>>()
    queue.add(Pair(targetNode!!, 0))
    val seenList = HashSet<Int>()
    seenList.add(targetNode.value)

    while (queue.isNotEmpty()) {
        val (currentNode, distance) = queue.pop()

        if (distance == k) {
            val nodeDistanceK = mutableListOf<Int>()
            for (p in queue) {
                nodeDistanceK.add(p.first.value)
            }
            nodeDistanceK.add(currentNode.value)
            return nodeDistanceK
        }

        val currentNodes = listOf(currentNode.left, currentNode.right, allNodes[currentNode.value])
        for (node in currentNodes) {
            if (node != null && !seenList.contains(node.value)) {
                seenList.add(node.value)
                queue.add(Pair(node, distance+1))
            }
        }
    }

    return emptyList()
}


fun getTargetNode(value: Int, tree: BinaryTree, allNodes: HashMap<Int, BinaryTree?>): BinaryTree? {
    if (tree.value == value) return tree
    val nodeParent = allNodes[value]
    if (nodeParent?.left != null && nodeParent.left!!.value == value) {
        return nodeParent.left!!
    } else if (nodeParent?.right != null && nodeParent.right!!.value == value) {
        return nodeParent.right!!
    }
    return null
}
fun populateAllNodes(tree: BinaryTree?, parent: BinaryTree?, allNodes: HashMap<Int, BinaryTree?>) {
    if (tree != null) {
        allNodes[tree.value] = parent
        populateAllNodes(tree.left, tree, allNodes)
        populateAllNodes(tree.right, tree, allNodes)
    }
}

fun main() {
    val root = BinaryTree(1)
    var left1 = BinaryTree(2)
    val right1 = BinaryTree(3)
    root.left = left1
    root.right = right1

    val left2 = BinaryTree(4)
    val right2 = BinaryTree(5)
    left1 = left2
    left1.right = right2

    val right3 = BinaryTree(6)
    right1.right = right3

    val left4 = BinaryTree(7)
    val right4 = BinaryTree(8)
    right3.left = left4
    right3.right = right4

    println(findNodesDistanceK(root, 3, 2))
}
