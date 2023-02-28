package com.dynamic.program.linked_list.easy

/**
 * 21. Merge Two Sorted Lists
Easy
company
Amazon
company
Apple
company
Adobe
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
class MergeTwoSortedLists {
    data class ListNode(var `val`: Int, var next: ListNode? = null)
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var node: ListNode? = null
        var root: ListNode? = null

        var left: ListNode? = list1
        var right: ListNode? = list2

        if (left == null) return right
        else if (right == null) return left

        if (left.`val` <= right.`val`) {
            node = left
            root = node
            left = left.next
        } else {
            node = right
            root = node
            right = right.next
        }


        while(left != null && right != null) {
            if (left.`val` <= right.`val`) {
                node?.next = left
                left = left.next
                node = node?.next
            } else {
                node?.next = right
                right = right.next
                node = node?.next
            }
        }

        if(left != null) {
            node?.next = left
        }

        if(right != null) {
            node?.next = right
        }

        return root
    }
}
