package com.dynamic.program.linked.list

fun reverse(head: Node): Node? {
    var prev: Node? = null
    var curr: Node? = head

    while (curr != null) {
        val nextNode: Node? = curr.next
        curr.next = prev
        prev = curr
        curr = nextNode
    }

    return prev
}

fun main() {
    println(reverse(Node(1, Node(2, Node(3, Node(4, Node(5)))))))
}
