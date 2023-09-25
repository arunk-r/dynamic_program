package com.dynamic.program.interviewing.io.`2023`.sept.`22`

import java.util.PriorityQueue

class SortLinkedList {
    data class Node(val v: Int, var next: Node? = null)
    fun sortLinkedList(head: Node?): Node? {
        if (head == null)
            return null
        else {
            val queue = PriorityQueue<Node>{x, y -> x.v - y.v}
            var node: Node? = head
            while (node != null) {
                queue.add(node)
                node = node.next
            }
            val newHead = queue.remove()
            node = newHead
            while (queue.isNotEmpty()) {
                node?.next = queue.remove()
                node = node?.next
            }
            node?.next = null
            return newHead
        }
    }
}

fun main() {
    val input = SortLinkedList.Node(2, SortLinkedList.Node(3, SortLinkedList.Node(1, SortLinkedList.Node(0))))
    val obj = SortLinkedList()
    val result = obj.sortLinkedList(input)
    println(result)
}
