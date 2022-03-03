package com.dynamic.program.medium.remove_kth_node_from_end

open class LinkedList1(value: Int) {
    var value = value
    var next: LinkedList1? = null
}

fun removeKthNodeFromEnd(head: LinkedList1, k: Int) {
    // Write your code here.
    var firstPointer: LinkedList1? = head
    var secondPointer: LinkedList1? = head
    var t = 1
    while (t <= k && secondPointer != null) {
        secondPointer = secondPointer.next
        t++
    }
    if(secondPointer == null) {
        head.value = head.next!!.value
        head.next = head.next!!.next
        return
    }
    while (secondPointer!!.next != null) {
        secondPointer = secondPointer.next
        firstPointer = firstPointer!!.next
    }
    firstPointer!!.next = firstPointer.next!!.next
}

fun main() {
    val head = LinkedList1(1)
    head.next = LinkedList1(2)
    head.next!!.next = LinkedList1(3)
    head.next!!.next!!.next = LinkedList1(4)
    head.next!!.next!!.next!!.next = LinkedList1(5)
    head.next!!.next!!.next!!.next!!.next = LinkedList1(6)
    head.next!!.next!!.next!!.next!!.next!!.next = LinkedList1(7)
    head.next!!.next!!.next!!.next!!.next!!.next!!.next = LinkedList1(8)
    head.next!!.next!!.next!!.next!!.next!!.next!!.next!!.next = LinkedList1(9)
    removeKthNodeFromEnd(head, 8)
    println(head)
}