package com.dynamic.program.lists

/**
 * Write a code to remove duplicates from unsorted linked list.
 * Note: How would you solve this problem if a temporary buffer is not allowed?
 *
 * time = O(n^2)
 */
data class Node(val value: Int, var next: Node? = null)

fun removeDuplicates(head: Node) {
    var n: Node? = head
    var i: Node?
    while (n != null) {
        var p = n
        i = n.next
        while (i != null) {
            if (i.value == n.value) {
                p?.next = i.next
                i = p?.next
            } else {
                p = i
                i = i.next
            }
        }
        n = n.next
    }
}

fun main() {
    val head = Node(4, Node(7, Node(8, Node(9, Node(4, Node(7, Node(8, Node(9))))))))
    println(head)
    removeDuplicates(head)
    println(head)

    val head1 = Node(4, Node(4, Node(8, Node(8, Node(4, Node(4, Node(8, Node(8))))))))
    println(head1)
    removeDuplicates(head1)
    println(head1)

    val head2 = Node(4, Node(4, Node(4, Node(4, Node(4, Node(4, Node(4, Node(4))))))))
    println(head2)
    removeDuplicates(head2)
    println(head2)
}
