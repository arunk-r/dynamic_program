package com.dynamic.program.nagan


data class Node(val v: Int, var next: Node? = null)

class DeleteANode {
    fun delete(v: Int, node: Node?): Node? {
        var newHead = node
        if (node == null) return node

        while (newHead != null && newHead.v == v) {
            newHead = newHead.next
        }

        if (newHead != null) {

            var p = newHead
            var n: Node? = newHead?.next

            while (n != null) {
                if (n.v == v) {
                    p?.next = n.next
                } else {
                    p = n
                }
                n = n.next
            }
        }
        return newHead
    }
}

fun main() {
    val d = DeleteANode()
    var h: Node? = Node(3, Node(3, Node(2, Node(3, Node(1, Node(4))))))
    h = d.delete(3, h)
    h
}
