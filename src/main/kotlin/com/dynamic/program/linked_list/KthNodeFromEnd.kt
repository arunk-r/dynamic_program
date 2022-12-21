package com.dynamic.program.linked_list

/**
 * Given the head of a linked list and an integer k, return the kth node from the end.
 *
 * For example, given the linked list that represents 1 -> 2 -> 3 -> 4 -> 5 and k = 2, return the node with value 4, as it is the 2nd node from the end.
 */

fun findNode(head: Node, k: Int): Node? {
    var slow: Node? = head
    var fast: Node? = head
    for (i in 0 until k) {
        fast = fast?.next
    }
    while (fast != null) {
        slow = slow?.next
        fast = fast.next
    }
    return slow
}

fun main() {
    println(findNode(Node(1, Node(2, Node(3, Node(4, Node(5))))), 2) )
}
