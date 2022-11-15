package com.dynamic.program.mit

private data class LinkedList(val value: Int, var next: LinkedList? = null)

private fun reverseNode(head: LinkedList, length: Int) {
    println(head.toString())
    var mid: LinkedList? = head

    for (i in 0 until (length - 1) / 2) {
        mid = mid!!.next
    }
    helper(mid, mid, mid!!.next)
    println(head.toString())
}

private fun helper(node: LinkedList?, mid: LinkedList?, previous: LinkedList?) {
    if (node!!.next == null) {
        mid!!.next = node
        return
    }
    helper(node.next, mid, node)
    if (node == mid) {
        previous!!.next = null
    } else {
        node.next!!.next = node
    }
}

fun main() {
    val head = LinkedList(1, LinkedList(2, LinkedList(3, LinkedList(4, LinkedList(5, LinkedList(6, LinkedList(7, LinkedList(8, LinkedList(9, LinkedList(10))))))))))
    reverseNode(head, 10)
    val head1 = LinkedList(1, LinkedList(2, LinkedList(3, LinkedList(4, LinkedList(5, LinkedList(6, LinkedList(7, LinkedList(8, LinkedList(9)))))))))
    reverseNode(head1, 9)
}
