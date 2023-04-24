package com.dynamic.program.linked_list.medium

import java.util.Stack

/**
 * 143. Reorder List
Medium
company
Amazon
company
Adobe
company
Microsoft
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
 */
class ReorderList {
    data class ListNode(var `val`: Int, var next: ListNode? = null)
    fun reorderList(head: ListNode?): Unit {
        var slow = head
        var fast = head

        while (fast != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        var second = reverseLinkedList(slow)

        var first = head
        while (second != null) {
            val next = first?.next
            val tmp = second

            second = second.next
            first?.next = tmp
            tmp.next = next
            first = next
        }
        first?.next = null
    }

    private fun reverseLinkedList(node: ListNode?): ListNode? {
        var prev: ListNode? = null
        var cur = node
        while (cur != null) {
            val next = cur.next
            cur.next = prev
            prev = cur
            cur = next
        }
        return prev
    }

    fun reorderList1(head: ListNode?): Unit {
        if (head  == null) return
        val s = Stack<ListNode>()

        var cur = head
        while (cur != null) {
            s.push(cur)
            cur = cur.next
        }
        val size = s.size / 2
        var half  = size / 2.0
        val comp = if (size % 2 == 0) 0 else 1
        cur = head
        while (s.isNotEmpty() && half > comp) {
            //keep next track
            val next = cur?.next
            //get last from stack
            val n = s.pop()
            // insert next to cur
            cur?.next = n
            // readjust the pointer
            n?.next = next
            // set cur
            cur = next
            half--
        }

        cur?.next = null
    }
}


fun main() {
    val head = ReorderList.ListNode(1,ReorderList.ListNode(2, ReorderList.ListNode(3, ReorderList.ListNode(4, ReorderList.ListNode(5)))))
    ReorderList().reorderList(head)
    head
}
