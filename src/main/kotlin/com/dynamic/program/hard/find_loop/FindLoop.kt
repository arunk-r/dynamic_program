package com.dynamic.program.hard.find_loop

/**
 * Write a function that takes in the head of a Singly Linked List that contains
 * a loop (in other words, the list's tail node points to some node in the list
 * instead of None / null). The function should return
 * the node (the actual node--not just its value) from which the loop originates
 * in constant space.
 *
 * Each LinkedList node has an integer value as well as
 * a next node pointing to the next node in the list.
 *
 * Sample Input
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 // the head node with value 0
 *                            ^         v
 *                            9 <- 8 <- 7
 *
 * Sample Output
 * 4 -> 5 -> 6 // the node with value 4
 * ^         v
 * 9 <- 8 <- 7
 *
 */
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null

}

fun findLoop(head: LinkedList?): LinkedList? {
    // Write your code here.
    val map = mutableMapOf<Int, LinkedList?>()
    var pointer = head
    while (pointer!=null) {
        println(pointer.hashCode())
        if (map.containsKey(pointer.hashCode()) && map[pointer.hashCode()] == pointer) {
            return map[pointer.hashCode()]
        } else {
            map[pointer.hashCode()] = pointer
        }
        pointer = pointer.next
    }
    return null
}

fun findLoopSecondMethod(head: LinkedList?): LinkedList? {
    // Write your code here.
    var first = head
    var second = head?.next?.next
    while (first != second) {
        first = first?.next
        second = second?.next?.next
    }
    first = head
    while (first != second) {
        first = first?.next
        second = second?.next
    }
    return first
}

fun main() {
    val head = LinkedList(0)
    var h = head
    h.next = LinkedList(0)
    h = h.next!!
    h.next = LinkedList(0)
    //h = h.next!!
    //h.next = LinkedList(3)
    h = h.next!!
    h.next = LinkedList(0)
    h = h.next!!
    h.next = LinkedList(0)
    h = h.next!!
    h.next = LinkedList(0)
    h = h.next!!
    h.next = LinkedList(0)
    val loop = h.next
    h = h.next!!
    h.next = LinkedList(0)
    h = h.next!!
    h.next = LinkedList(0)
    h = h.next!!
    h.next = loop

    println(findLoop(head)?.value)

}