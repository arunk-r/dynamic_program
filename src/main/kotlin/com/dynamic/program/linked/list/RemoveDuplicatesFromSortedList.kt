package com.dynamic.program.linked.list

/**
 * Remove Duplicates from Sorted List
 *
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 *
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */

fun deleteDuplicates(head: Node?): Node? {
    if (head?.next == null) return head

    var cur: Node? = head;
    var next: Node? = head.next
    while (next != null) {
        if (cur?.value == next.value) {
            cur.next = next.next
            next = cur.next
        } else {
            cur = cur?.next
            next = next.next
        }
    }

    return head
}

fun main() {
    //println(deleteDuplicates(Node(1, Node(1, Node(2)))))
    println(deleteDuplicates(Node(1, Node(1, Node(1)))))
}
