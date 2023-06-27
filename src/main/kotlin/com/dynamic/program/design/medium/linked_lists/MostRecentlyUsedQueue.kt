package com.dynamic.program.design.medium.linked_lists

/**
 * https://leetcode.com/problems/design-most-recently-used-queue/
 *
 * 1756. Design Most Recently Used Queue
 * Medium
 * 238
 * 18
 * company
 * Bloomberg
 * company
 * Google
 * Design a queue-like data structure that moves the most recently used element to the end of the queue.
 *
 * Implement the MRUQueue class:
 *
 * MRUQueue(int n) constructs the MRUQueue with n elements: [1,2,3,...,n].
 * int fetch(int k) moves the kth element (1-indexed) to the end of the queue and returns it.
 *
 *
 * Example 1:
 *
 * Input:
 * ["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
 * [[8], [3], [5], [2], [8]]
 * Output:
 * [null, 3, 6, 2, 2]
 *
 * Explanation:
 * MRUQueue mRUQueue = new MRUQueue(8); // Initializes the queue to [1,2,3,4,5,6,7,8].
 * mRUQueue.fetch(3); // Moves the 3rd element (3) to the end of the queue to become [1,2,4,5,6,7,8,3] and returns it.
 * mRUQueue.fetch(5); // Moves the 5th element (6) to the end of the queue to become [1,2,4,5,7,8,3,6] and returns it.
 * mRUQueue.fetch(2); // Moves the 2nd element (2) to the end of the queue to become [1,4,5,7,8,3,6,2] and returns it.
 * mRUQueue.fetch(8); // The 8th element (2) is already at the end of the queue so just return it.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 1 <= k <= n
 * At most 2000 calls will be made to fetch.
 *
 *
 * Follow up: Finding an O(n) algorithm per fetch is a bit easy. Can you find an algorithm with a better complexity for each fetch call?
 */
class MostRecentlyUsedQueue(val n: Int) {
    data class Node(val v: Int, var prev: Node? = null, var next: Node? = null)

    private val head = Node(-1)
    private val tail = Node(2001)

    init {
        head.next = tail
        tail.prev = head
        for (i in 1..n) {
            val node = Node(i)
            // attach to end of the queue
            attachToEnd(node)
        }
    }

    fun fetch(k: Int): Int {
        // find node
        val node = findKthPositionNode(k) ?: return -1
        // detach from queue
        detach(node)
        // attach to end of the queue
        attachToEnd(node)
        return node.v
    }

    private fun findKthPositionNode(k: Int): Node? {
        var newK = k
        var cur: Node? = head
        while (newK-- >=1) {
            cur = cur?.next
        }
        return cur
    }

    private fun detach(node: Node) {
        val p = node.prev
        val n = node.next
        p?.next = n
        n?.prev = p
    }

    private fun attachToEnd(node: Node) {
        val p = tail.prev
        p?.next = node
        node.prev = p
        node.next = tail
        tail.prev = node
    }
/*    val data = MutableList(n+1){i -> i}
    fun fetch(k: Int): Int {
        val d = data[k]
        data.removeAt(k)
        data.add(d)
        return d
    }*/
}

fun main() {
    val queue = MostRecentlyUsedQueue(8)
    println(queue.fetch(3))
    println(queue.fetch(5))
    println(queue.fetch(2))
    println(queue.fetch(8))
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * var obj = MRUQueue(n)
 * var param_1 = obj.fetch(k)
 */
