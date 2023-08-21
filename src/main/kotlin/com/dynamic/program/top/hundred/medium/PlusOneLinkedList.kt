package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/plus-one-linked-list/
 *
 * 369. Plus One Linked List
 * Medium
 * 900
 * 42
 * company
 * Google
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3]
 * Output: [1,2,4]
 * Example 2:
 *
 * Input: head = [0]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * The number represented by the linked list does not contain leading zeros except for the zero itself.
 */
class PlusOneLinkedList {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun plusOne(head: ListNode?): ListNode? {
        var notNine: ListNode? = ListNode(0)
        notNine?.next = head
        val begin = notNine

        var cur = notNine?.next
        while (cur != null) {
            if (cur.`val` != 9) {
                notNine = cur
            }
            cur = cur.next
        }
        notNine?.`val` = notNine!!.`val` + 1
        notNine = notNine.next

        while (notNine != null) {
            notNine.`val` = 0
            notNine = notNine.next
        }

        return if (begin?.`val` == 0) head else begin
    }
}
