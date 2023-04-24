package com.dynamic.program.linked_list

/**
 * 141. Linked List Cycle
Easy
 *  Linked List Cycle
 *
 *  Given the head of a linked list, determine if the linked list has a cycle.
 *  There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 *
 *  company
Amazon
company
Apple
company
Adobe
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.



Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
Example 2:


Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
Example 3:


Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.


Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */

class LinkedListCycle {
    fun hasCycle(head: Node?): Boolean {
        if (head != null) {
            var cur = head
            var fpp = head.next?.next
            while(fpp != null) {
                if (cur == fpp) return true
                cur = cur?.next
                fpp = fpp.next?.next
            }
            return false
        }
        return false
    }

    fun listCycle(head: Node): Boolean {
        var slow: Node? = head
        var fast: Node? = head
        while (fast?.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val head = Node(1, Node(2))
    val cycle = Node(3)
    head.next?.next = cycle
    cycle.next = Node(4, Node(5, Node(6, Node(7, cycle))))
    println(LinkedListCycle().listCycle(head))
}
