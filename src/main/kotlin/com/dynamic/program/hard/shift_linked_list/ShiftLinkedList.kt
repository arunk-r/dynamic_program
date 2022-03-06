package com.dynamic.program.hard.shift_linked_list

import kotlin.math.abs

/**
 *
Write a function that takes in the head of a Singly Linked List and an integer
k, shifts the list in place (i.e., doesn't create a brand new
list) by k positions, and returns its new head.


Shifting a Linked List means moving its nodes forward or backward and wrapping
them around the list where appropriate. For example, shifting a Linked List
forward by one position would make its tail become the new head of the linked
list.


Whether nodes are moved forward or backward is determined by whether
k is positive or negative.


Each LinkedList node has an integer value as well as
a next node pointing to the next node in the list or to
None / null if it's the tail of the list.


You can assume that the input Linked List will always have at least one node;
in other words, the head will never be None / null.

Sample Input
head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 // the head node with value 0
k = 2

Sample Output
4 -> 5 -> 0 -> 1 -> 2 -> 3 // the new head node with value 4
 */

open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun shiftLinkedList(head: LinkedList, k: Int): LinkedList {
    // Write your code here.
    if (k == 0) return head
    var length: Int = 0
    var endPointer: LinkedList? = null
    var newHead: LinkedList? = head
    var newTail: LinkedList? = null

    var tempHead: LinkedList? = head
    while (tempHead != null) {
        length++
        endPointer = tempHead
        tempHead = tempHead.next
    }

    if (abs(k) % length == 0) return head
    var newTraversalLength: Int = if (k > 0) {
        length - k % length
    } else {
        abs(k) % length
    }

    tempHead = head
    while (newTraversalLength > 0) {
        newTail = tempHead
        newHead = newTail?.next
        tempHead = tempHead?.next
        newTraversalLength--
    }

    newTail?.next = null
    endPointer?.next = head

    return newHead!!
}

fun main() {
    val head = LinkedList(0)
    head.next = LinkedList(1)
    var t = head.next
    t?.next = LinkedList(2)
    t = t?.next
    t?.next = LinkedList(3)
    t = t?.next
    t?.next = LinkedList(4)
    t = t?.next
    t?.next = LinkedList(5)

    t = shiftLinkedList(head, 30)
    while (t != null) {
        print("${t.value} -> ")
        t = t.next
    }
}

