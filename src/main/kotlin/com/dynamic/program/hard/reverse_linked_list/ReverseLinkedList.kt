package com.dynamic.program.hard.reverse_linked_list

/**
 *
 * Write a function that takes in the head of a Singly Linked List, reverses the
 * list in place (i.e., doesn't create a brand new list), and returns its new head.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to the next node in the list or to
 * None / null if it's the tail of the list.
 *
 * You can assume that the input Linked List will always have at least one node; in other
 * words, the head will never be None / null.
 *
 * Sample Input
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 // the head node with value 0
 *
 * Sample Output
 * 5 -> 4 -> 3 -> 2 -> 1 -> 0 // the new head node with value 5
 *
 */
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun reverseLinkedList(head: LinkedList): LinkedList {
    var p1: LinkedList? = null
    var p2: LinkedList?  = head
    var p3: LinkedList?
    while (p2 != null) {
        p3 = p2.next
        p2.next = p1
        p1 = p2
        p2 = p3
    }
    return p1!!
}

fun main() {
    val head = LinkedList(0)
    head.next = LinkedList(1)
    var h1 = head.next
    h1?.next = LinkedList(2)
    h1 = h1?.next
    h1?.next = LinkedList(3)
    h1 = h1?.next
    h1?.next = LinkedList(4)
    h1 = h1?.next
    h1?.next = LinkedList(5)
    h1 = h1?.next
    h1?.next = null

    h1 = head
    while (h1 != null) {
        print("${h1.value} -> ")
        h1 = h1.next
    }
    println()
    h1 = reverseLinkedList(head)
    while (h1 != null) {
        print("${h1.value} -> ")
        h1 = h1.next
    }
}