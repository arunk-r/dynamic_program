package com.dynamic.program.linked_list

/**
 * Reverse Linked List II
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 */

fun reverseBetween(head: Node?, left: Int, right: Int): Node? {
    val dummy = Node(0, head)
    var before: Node? = dummy
    for (i in 1 until left) {
        before = before?.next
    }
    var prev: Node? = before
    var curr: Node? = before?.next

    for (i in left..right) {
        val next = curr?.next
        curr?.next = prev
        prev = curr
        curr = next
    }

    before?.next?.next = curr
    before?.next = prev
    return dummy.next
}

fun main() {
    val head = Node(1, Node(2, Node(3, Node(4, Node(5)))))
    println(reverseBetween(head, 2, 4))
}
