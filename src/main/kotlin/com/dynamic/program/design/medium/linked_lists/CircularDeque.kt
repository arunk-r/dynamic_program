package com.dynamic.program.design.medium.linked_lists

/**
 * https://leetcode.com/problems/design-circular-deque/description/
 *
 * 641. Design Circular Deque
 * Medium
 * 1K
 * 71
 * company
 * Goldman Sachs
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Implement the MyCircularDeque class:
 *
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 2, true, true, true, 4]
 *
 * Explanation
 * MyCircularDeque myCircularDeque = new MyCircularDeque(3);
 * myCircularDeque.insertLast(1);  // return True
 * myCircularDeque.insertLast(2);  // return True
 * myCircularDeque.insertFront(3); // return True
 * myCircularDeque.insertFront(4); // return False, the queue is full.
 * myCircularDeque.getRear();      // return 2
 * myCircularDeque.isFull();       // return True
 * myCircularDeque.deleteLast();   // return True
 * myCircularDeque.insertFront(4); // return True
 * myCircularDeque.getFront();     // return 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */
class CircularDeque(val k: Int) {
    data class DLNode(val v: Int, var prev:DLNode? = null, var next: DLNode? = null )
    val head = DLNode(0)
    val tail = DLNode(0)
    var cnt = 0
    init {
        head.next = tail
        tail.prev = head
    }
    fun insertFront(value: Int): Boolean {
        if (isFull()) return false
        cnt++
        val n = DLNode(value)
        val tmp:DLNode? = head.next

        head.next = n
        n.prev = head

        n.next = tmp
        tmp?.prev = n
        return true
    }

    fun insertLast(value: Int): Boolean {
        if (isFull()) return false
        cnt++

        val n = DLNode(value)
        val tmp:DLNode? = tail.prev

        tail.prev = n
        n.next = tail

        n.prev = tmp
        tmp?.next = n
        return true
    }

    fun deleteFront(): Boolean {
        if (isEmpty()) return false
        cnt--

        val n:DLNode? = head.next

        val nn:DLNode? = n?.next
        nn?.prev = head
        head.next = nn
        return true
    }

    fun deleteLast(): Boolean {
        if (isEmpty()) return false
        cnt--

        val n:DLNode? = tail.prev

        val nn:DLNode? = n?.prev
        nn?.next = tail
        tail.prev = nn
        return true
    }

    fun getFront(): Int {
        if (isEmpty()) return -1
        return head.next?.v ?: -1
    }

    fun getRear(): Int {
        if (isEmpty()) return -1
        return tail.prev?.v ?: -1
    }

    fun isEmpty(): Boolean {
        return cnt == 0
    }

    fun isFull(): Boolean {
        return cnt == k
    }

}

/**
 * Your CircularDeque object will be instantiated and called as such:
 * var obj = CircularDeque(k)
 * var param_1 = obj.insertFront(value)
 * var param_2 = obj.insertLast(value)
 * var param_3 = obj.deleteFront()
 * var param_4 = obj.deleteLast()
 * var param_5 = obj.getFront()
 * var param_6 = obj.getRear()
 * var param_7 = obj.isEmpty()
 * var param_8 = obj.isFull()
 */
