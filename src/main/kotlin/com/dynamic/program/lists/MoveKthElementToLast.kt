package com.dynamic.program.lists

/**
 * Return Kth to Last
 * Implement an algorithm to find the K th to last element of a singly linked list.
 *
 * What is the catch?
 * handle 1st and last element
 *
 * time O(n)
 */
fun moveKthElementToLast(head: Node, k: Int): Node? {
    if (k>=1 && head.next == null) return head
    var p:Node? = head
    var c:Node? = null
    var n:Node? = head
    var idx = 1
    var newHead:Node? = head
    while (n != null) {
        if (idx == k) {
            if (k == 1) {
                newHead = n.next
                c=n
            } else if (idx == k && n.next == null) {
                c = null
            }
            else {
                p?.next = n.next
                c = n
            }
        }
        idx++
        p=n
        n = n.next
    }
    if (c != null) {
        p?.next = c
        c.next = null
    }
    return newHead
}

fun main() {
    val head = Node(4, Node(5, Node(6, Node(7, Node(8, Node(9))))))
    println(moveKthElementToLast(head, 3))

    val head1 = Node(4, Node(5, ))
    println(moveKthElementToLast(head1, 3))

    val head2 = Node(4)
    println(moveKthElementToLast(head2, 3))

    val head3 = Node(4, Node(5, ))
    println(moveKthElementToLast(head3, 1))

    val head4 = Node(4, Node(5, ))
    println(moveKthElementToLast(head4, 2))

    val head5 = Node(4)
    println(moveKthElementToLast(head5, 1))
}
