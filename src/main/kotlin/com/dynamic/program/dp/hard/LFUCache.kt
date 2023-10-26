package com.dynamic.program.dp.hard

import java.util.TreeSet

/**
 * https://leetcode.com/problems/lfu-cache/description/
 *
 * 460. LFU Cache
 * Hard
 * 5.4K
 * 319
 * company
 * Amazon
 * company
 * Apple
 * company
 * Google
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * At most 2 * 105 calls will be made to get and put.
 */
class LFUCache(private val capacity: Int) {
    data class Cache(val k: Int, var v: Int, var cnt: Int)
    data class Node(val cache: Cache, var prev: Node? = null, var next: Node? = null)
    val sgTree = TreeSet<Node>{x, y -> if(x.cache.cnt == y.cache.cnt) x.cache.k - y.cache.k else x.cache.cnt - y.cache.cnt}

    val head = Node(Cache(0, 0, 0))
    val tail = Node(Cache(0, 0, 0))

    val map = HashMap<Int, Node>()

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        var v = map[key]
        if (v == null) {
            return -1
        } else {
            v = buildNewObject(v)
            removePrevLinks(v)
            v.cache.cnt++
            addElementToLast(v)
            return v.cache.v
        }
    }

    fun put(key: Int, value: Int) {
        var v = map[key]
        if (v == null) {
            val cache = Cache(key, value, 1)
            v = Node(cache)
            map[key] = v
        } else {
            removePrevLinks(v)
            v = buildNewObject(v)
            v.cache.v = value
            v.cache.cnt++
        }
        addToMap(v)
        addElementToLast(v)
        checkTheCacheThreshold()
    }

    private fun addElementToLast(node: Node) {
        val prev = tail.prev

        prev?.next = node
        node.prev = prev

        node.next = tail
        tail.prev = node
    }

    private fun addToMap(node: Node) {
        map[node.cache.k] = node
        sgTree.add(node)
    }
    private fun removePrevLinks(node: Node) {
        if(node.prev != null && node.next != null) {
            val prev = node.prev
            val next = node.next

            prev?.next = next
            next?.prev = prev
            map.remove(node.cache.k)

        }
    }

    private fun buildNewObject(node: Node): Node = Node(Cache(node.cache.k, node.cache.v, node.cache.cnt))

    private fun checkTheCacheThreshold() {
        if (map.size > capacity) {
            val tmp = head.next
            val next = tmp?.next
            head.next = next
            next?.prev = head
            if (tmp != null) {
                map.remove(tmp.cache.k)
            }
        }
    }
}

fun main() {
    val l = LFUCache(2)
    l.put(1, 1)
    l.put(2, 2)
    println(l.get(1))
    l.put(3, 3)
}
