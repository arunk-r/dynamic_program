package com.dynamic.program.hard.merge_linked_lists

/**
 * Write a function that takes in the heads of two Singly Linked Lists that are
 * in sorted order, respectively. The function should merge the lists in place
 * (i.e., it shouldn't create a brand new list) and return the head of the merged
 * list; the merged list should be in sorted order.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to the next node in the list or to
 * None / null if it's the tail of the list.
 *
 * You can assume that the input linked lists will always have at least one node; in other
 * words, the heads will never be None / null.
 *
 * Sample Input
 * headOne = 2 -> 6 -> 7 -> 8 // the head node with value 2
 * headTwo = 1 -> 3 -> 4 -> 5 -> 9 -> 10 // the head node with value 1
 *
 * Sample Output
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 // the new head node with value 1
 *
 */
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun mergeLinkedLists(headOne: LinkedList, headTwo: LinkedList): LinkedList {
    // Write your code here.
    var p1: LinkedList? = headOne
    var p2: LinkedList? = headTwo
    var pPrev: LinkedList? = null
    while (p1 != null && p2 != null) {
        if (p1.value < p2.value) {
            pPrev = p1
            p1 = p1.next
        } else {
            if (pPrev != null) {
                pPrev.next = p2
            }
            pPrev = p2
            p2 = p2.next
            pPrev.next = p1
        }
    }
    if (p1 == null) {
        pPrev?.next = p2
    }
    return if (headOne.value < headTwo.value) headOne else headTwo
}

fun main() {
    val one = LinkedList(2)
    one.next = LinkedList(6)
    var l = one.next
    l?.next = LinkedList(7)
    l = l?.next
    l?.next = LinkedList(8)

    val two = LinkedList(1)
    two.next = LinkedList(3)
    l = two.next
    l?.next = LinkedList(4)
    l = l?.next
    l?.next = LinkedList(5)
    l = l?.next
    l?.next = LinkedList(9)
    l = l?.next
    l?.next = LinkedList(10)

    l = mergeLinkedLists(one, two)
    while (l != null) {
        print("${l.value} ->")
        l = l.next
    }
}