package com.dynamic.program.linked.list

/**
 * Given the head of a linked list with an odd number of nodes head, return the value of the node in the middle.
 * For example, given a linked list that represents 1 -> 2 -> 3 -> 4 -> 5, return 3.
 */

fun middleNode(head: Node): Int? {
    var slow: Node? = head
    var fast: Node? = head
    while (fast != null && fast.next?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }
    return slow?.value
}

fun main() {
    println(middleNode(Node(1, Node(2, Node(3, Node(4, Node(5)))))))
}
