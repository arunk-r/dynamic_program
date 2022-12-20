package com.dynamic.program.linked.list

/**
 * Swap Nodes in Pairs
 *
 * Given the head of a linked list, swap every pair of nodes. For example, given a linked list 1 -> 2 -> 3 -> 4 -> 5 -> 6,
 * return a linked list 2 -> 1 -> 4 -> 3 -> 6 -> 5.
 */

fun swapPair(head: Node): Node? {
    if (head.next == null) return head
    var curr: Node? = head
    val dummy: Node? = head.next
    var prev: Node? = null
    while (curr?.next != null) {

        if (prev != null) {
            prev.next = curr.next
        }

        prev = curr

        val nextNode: Node? = curr.next?.next
        curr.next?.next = curr

        curr.next = nextNode
        curr = nextNode
    }
    return dummy
}

fun main() {
    println(swapPair(Node(1, Node(2, Node(3, Node(4, Node(5, Node(6))))))))
}
