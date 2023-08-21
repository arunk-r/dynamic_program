package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 *
 * 708. Insert into a Sorted Circular Linked List
 * Medium
 * 1.1K
 * 710
 * company
 * Facebook
 * company
 * Google
 * company
 * Microsoft
 * Given a Circular Linked List node, which is sorted in non-descending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
 *
 * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the originally given node.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: head = [3,4,1], insertVal = 2
 * Output: [3,4,1,2]
 * Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
 *
 *
 *
 * Example 2:
 *
 * Input: head = [], insertVal = 1
 * Output: [1]
 * Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
 * Example 3:
 *
 * Input: head = [1], insertVal = 0
 * Output: [1,0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -106 <= Node.val, insertVal <= 106
 */
class InsertIntoASortedCircularLinkedList {
    class Node(var `val`: Int) {
        var next: Node? = null
    }

    fun insert(head: Node?, v: Int): Node {
        if (head == null) {
            val n = Node(v)
            n.next = n
            return n
        }
        var cur = head.next
        while (cur != head) {
            val curVal = cur!!.`val`
            val nextVal = cur.next!!.`val`
            if (v in curVal..nextVal) {
                break
            }

            if (nextVal < curVal) {
                if (curVal >= v && nextVal >= v) break
                if (curVal <= v && nextVal <= v) break
            }
            cur = cur.next
        }

        val n = Node(v)
        val next = cur?.next
        cur?.next = n
        n.next = next

        return head
    }

    fun insert1(head: Node?, v: Int): Node {
        if (head == null) {
            val n = Node(v)
            n.next = n
            return n
        }
        var prev = head
        var cur = head.next
        var insert = false
        do {
            if (prev != null && cur != null) {
                if (prev.`val` <= v && v <= cur.`val`) {
                    insert = true
                } else if (prev.`val` > cur.`val`) {
                    if (prev.`val` <= v || v <= cur.`val`) {
                        insert = true
                    }
                }
            }
            if (insert) {
                val n = Node(v)
                n.next = cur
                prev?.next = n
                return head
            }
            prev = cur
            cur = cur?.next
        } while (prev != head)
        val n = Node(v)
        n.next = cur
        prev.next = n
        return head
    }
}
