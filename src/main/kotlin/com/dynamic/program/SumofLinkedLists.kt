package com.dynamic.program

// This is an input class. Do not edit.
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun sumOfLinkedLists(linkedListOne: LinkedList, linkedListTwo: LinkedList): LinkedList {
    // Write your code here.
    var output = LinkedList(Int.MIN_VALUE)
    var dummy = output
    var l1: LinkedList? = linkedListOne
    var l2: LinkedList? = linkedListTwo
    var carry = 0
    while (l1 != null || l2 != null) {
        val cnt = (l1?.value ?: 0) + (l2?.value ?: 0) + carry
        val value = cnt%10
        dummy.next = LinkedList(value)
        dummy = dummy.next!!

        carry = cnt/10
        l1 = l1?.next
        l2 = l2?.next
    }
    if(carry > 0) {
        dummy.next = LinkedList(carry)
    }

    return output.next!!
}

fun main() {
    val l1 = LinkedList(2)
    l1.next = LinkedList(4)
    l1.next!!.next = LinkedList(7)
    l1.next!!.next!!.next = LinkedList(1)
    val l2 = LinkedList(9)
    l2.next = LinkedList(4)
    l2.next!!.next = LinkedList(5)
    println(sumOfLinkedLists(l1, l2))
}