package com.dynamic.program.stacks_queues.hard

import java.util.PriorityQueue

/**
 * 23. Merge k Sorted Lists
Hard

Amazon

Microsoft

Facebook
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
1->4->5,
1->3->4,
2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
 */
class MergeKSortedLists {
    data class ListNode(var `val`: Int, var next: ListNode? = null)
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val q = PriorityQueue<Pair<Int, ListNode?>>{x, y -> x.first - y.first}
        for(l in lists) {
            var node: ListNode? = l
            while(node != null) {
                q.add(Pair(node.`val`, node))
                node = node.next
            }
        }
        if(q.isEmpty()) return null
        val head = q.remove().second
        var node = head
        while(q.isNotEmpty()) {
            node?.next = q.remove().second
            node = node?.next
        }
        node?.next = null

        return head
    }
}
