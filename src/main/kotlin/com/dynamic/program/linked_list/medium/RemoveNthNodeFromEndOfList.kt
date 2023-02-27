package com.dynamic.program.linked_list.medium

/**
 * 19. Remove Nth Node From End of List
Medium
company
Apple
company
Amazon
company
Adobe
Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */
class RemoveNthNodeFromEndOfList {
    data class ListNode(val `val`: Int, var next: ListNode? = null)
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        var slow: ListNode? = dummy
        var fast: ListNode? = dummy

        for(i in 1 .. n+1) {
            fast = fast?.next
        }

        while(fast != null) {
            fast = fast.next
            slow = slow?.next
        }
        println(slow?.`val`)
        slow?.next = slow?.next?.next
        return dummy.next
    }
}
