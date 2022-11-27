package com.dynamic.program.lists

/**
 * write a code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x
 */
fun partition(head: Node, value: Int): Node? {
    var left: Node? = null
    var right: Node? = null
    var n: Node? = head
    var lc: Node? = null
    var rc: Node? = null

    while (n != null) {
        if (n.value < value) {
            if (left == null) {
                left = n
                lc = n
            } else {
                lc?.next = n
                lc = n
            }
        } else {
            if (right == null) {
                right = n
                rc = n
            } else {
                rc?.next = n
                rc = n
            }
        }
        n = n.next
    }
    if (left != null) {
        lc?.next = right
    } else {
        left = right
    }
    rc?.next = null
    return left
}

fun main() {
    val head = Node(3, Node(5, Node(8, Node(5, Node(10, Node(2, Node(1)))))))
    println(partition(head, 5))

    val head1 = Node(3, Node(5, Node(8, Node(5, Node(10, Node(2, Node(1)))))))
    println(partition(head1, 0))

    val head2 = Node(3, Node(5, Node(8, Node(5, Node(10, Node(2, Node(1)))))))
    println(partition(head2, 11))
}
