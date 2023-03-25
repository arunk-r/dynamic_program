package com.dynamic.program.linked_list.medium

/**
 * 146. LRU Cache
Medium

Amazon
Bloomberg
DoorDash
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 */
class LRUCache(val capacity: Int) {
    private class DLinkedList(val key: Int, var v: Int, var prev: DLinkedList? = null, var next: DLinkedList? = null)

    private var head: DLinkedList = DLinkedList(-1, -1)
    private var tail: DLinkedList = DLinkedList(-1, -1)

    init {
        head.next = tail
        tail.prev = head
    }


    private fun addNode(node: DLinkedList) {
        node.prev = head
        node.next = head.next

        head.next?.prev = node
        head.next = node
    }


    private fun moveToHead(node: DLinkedList) {
        removeNode(node)
        addNode(node)
    }

    private fun removeNode(node: DLinkedList) {
        val prev = node.prev
        val next = node.next

        prev?.next = next
        next?.prev = prev
    }

    private fun popTail(): DLinkedList? {

        val res: DLinkedList? = tail.prev
        res?.let { removeNode(res) }
        return res
    }


    private val cache = hashMapOf<Int, DLinkedList>()

    fun get(key: Int): Int {
        if (cache[key] != null) {
            val node = cache[key]!!
            moveToHead(node)
            return node.v
        } else {
            return -1
        }
    }

    fun put(key: Int, value: Int) {
        val node = cache[key]
        if (node != null) {
            node.v = value
            moveToHead(node)
        } else {
            val n = DLinkedList(key, value)
            cache[key] = n
            addNode(n)

            if (cache.size > capacity) {
                val n1 = popTail()
                cache.remove(n1?.key)
            }
        }
    }
}

fun main() {
    /*val l = LRUCache(2)
    l.put(1,1)
    l.put(2,2)
    l.get(1)
    l.put(3,3)
    l.get(2)*/

    /* val l = LRUCache(1)
     l.put(2,1)
     println( l.get(2))
     l.put(3,2)
     println( l.get(2))
     println( l.get(3))*/

    val l = LRUCache(2)
    l.put(2, 1)
    l.put(3, 2)
    println(l.get(3))
    println(l.get(2))
    l.put(4, 3)
    println(l.get(2))
    println(l.get(3))
    println(l.get(4))
}

//[null,null,1,null,-1,2]
