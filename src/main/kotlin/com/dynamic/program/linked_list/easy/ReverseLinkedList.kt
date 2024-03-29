package com.dynamic.program.linked_list.easy

/**
 * 206. Reverse Linked List
Easy
company
Apple
company
Amazon
company
Bloomberg
Given the head of a singly linked list, reverse the list, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:


Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []
 */
class ReverseLinkedList {
    data class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseList(head: ListNode?): ListNode? {
        var p: ListNode? = head
        var c: ListNode? = head?.next
        var n: ListNode? = c?.next
        p?.next = null
        while(c != null) {
            c?.next = p
            p = c
            c = n
            n = n?.next
        }

        return p
    }
}
