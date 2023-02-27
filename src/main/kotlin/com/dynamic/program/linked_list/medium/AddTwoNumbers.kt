package com.dynamic.program.linked_list.medium

/**
 * 2. Add Two Numbers
Medium
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */
class AddTwoNumbers {
    data class ListNode(val `val`: Int, var next: ListNode? = null)

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val remainder: Int = (l1!!.`val` + l2!!.`val`) % 10
        var nextVal: Int =  (l1.`val` + l2.`val`) / 10

        val l3 = ListNode(remainder)
        var l1Pointer = l1.next
        var l2Pointer = l2.next
        var l3Pointer = l3

        while (l1Pointer != null || l2Pointer != null || nextVal != 0) {
            if (l1Pointer != null) {
                nextVal += l1Pointer.`val`
            }
            if (l2Pointer != null) {
                nextVal += l2Pointer.`val`
            }

            l3Pointer.next = ListNode(nextVal % 10)
            nextVal /= 10

            l3Pointer = l3Pointer.next!!
            if (l1Pointer != null)
                l1Pointer = l1Pointer.next
            if (l2Pointer != null)
                l2Pointer = l2Pointer.next
        }

        return l3
    }
}
