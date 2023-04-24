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
        if (head != null) {
            var prev = head
            var curr = prev.next
            var next = curr?.next
            prev.next = null
            while (next != null) {
                curr?.next = prev
                prev = curr
                curr = next
                next = next.next
            }
            if (curr != null) {
                curr.next = prev
                return curr
            } else {
                return prev
            }
        } else {
            return head
        }
    }
}
