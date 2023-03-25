package com.dynamic.program.linked_list.medium

import java.util.Stack

/**
 * 138. Copy List with Random Pointer
Medium

Amazon
Facebook
Bloomberg
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.



Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]


Constraints:

0 <= n <= 1000
-104 <= Node.val <= 104
Node.random is null or is pointing to some node in the linked list.
 */
class CopyListWithRandomPointer {
    data class Node(val `val`: Int, var next: Node? = null, var random: Node? = null) {
        override fun toString(): String {
            return "Node(`val`=$`val`)"
        }

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }
    fun copyRandomList(node: Node?): Node? {

        if (node == null) return null

        val original = hashSetOf<Node>()
        val mapping = hashMapOf<Node, Node>()
        val stack = Stack<Node>()

        var n: Node? = node
        while(n != null) {
            stack.push(n)
            n = n.next
        }

        var new: Node? = null
        var prev: Node? = null
        while(stack.isNotEmpty()) {

            val n1 = stack.pop()
            new = Node(n1.`val`)

            if(prev != null) {
                new.next = prev
            }

            original.add(n1)
            mapping[n1] = new
            prev = new
        }

        original.forEach { n1 ->
            val cur: Node? = mapping[n1]!!

            n1.random?.let {
                val r = mapping[it]!!
                cur?.random = r
            }
        }

        return new
    }
}

fun main() {
    val o1 = CopyListWithRandomPointer.Node(7, CopyListWithRandomPointer.Node(13, CopyListWithRandomPointer.Node(11, CopyListWithRandomPointer.Node(10, CopyListWithRandomPointer.Node(1)))))
    val o2 = o1.next
    val o3 = o2?.next
    val o4 = o3?.next
    val o5 = o4?.next

    o2?.random = o1
    o3?.random = o5
    o4?.random = o3
    o5?.random = o1

    println(CopyListWithRandomPointer().copyRandomList(o1))
}
