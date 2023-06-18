package com.dynamic.program.linked_list.easy

import com.dynamic.program.trees.ListNode

/**
 * https://leetcode.com/problems/middle-of-the-linked-list/description/
 *
 * 876. Middle of the Linked List
 * Easy
 * 9.7K
 * 289
 * company
 * Amazon
 * company
 * Apple
 * company
 * Adobe
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 100].
 * 1 <= Node.val <= 100
 */
class MiddleOfTheLinkedList {
    fun middleNode(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while(fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        return slow
    }

    fun middleNode1(head: ListNode?): ListNode? {
        val data = mutableListOf<ListNode>()
        var n = head
        while(n != null) {
            data.add(n)
            n = n.next
        }
        return data[data.size/2]
    }
}
