package com.dynamic.program.linked_list

/**
 *  Linked List Cycle
 *
 *  Given the head of a linked list, determine if the linked list has a cycle.
 *  There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 */

fun listCycle(head: Node): Boolean {
    var slow: Node? = head
    var fast: Node? = head
    while (fast?.next?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow == fast){
            return true
        }
    }
    return false
}

fun main() {
    val head = Node(1, Node(2))
    val cycle = Node(3)
    head.next?.next = cycle
    cycle.next = Node(4, Node(5, Node(6, Node(7, cycle))))
    println(listCycle(head))
}
