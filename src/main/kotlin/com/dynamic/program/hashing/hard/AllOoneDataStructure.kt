package com.dynamic.program.hashing.hard

/**
 * 432. All O`one Data Structure
Hard

company
LinkedIn
company
Amazon
company
VMware
Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.



Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"


Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */

class AllOoneDataStructure {
    data class Node(val v: String, var c: Int = 0, var p: Node?=null, var n: Node? = null)
    val map = hashMapOf<String, Node>()

    val head = Node("")
    val tail = Node("")

    init{
        head.n = tail
        tail.p = head
    }

    fun inc(key: String) {
        map.putIfAbsent(key, Node(key, 0))
        val v = map[key]!!
        v.c++
        addNode(v)
    }

    private fun addNode(node: Node) {
        if (map.size == 1) {
            head.n = node
            node.p = head

            tail.p = node
            node.n = tail
        } else {

            removeNode(node)

            var n1: Node? = head.n
            while(n1 != null && n1.c < node.c) {
                n1 = n1.n
            }
            if (n1 == null) n1 = tail
            val next = n1
            val prev = n1.p!!

            prev.n = node
            node.p = prev

            node.n = next
            next.p = node

        }
    }

    private fun removeNode(node: Node) {
        if (map.isEmpty()) {
            head.n = tail
            tail.p = head
        } else {

            val next = node.n
            val prev = node.p

            prev?.n = next
            next?.p = prev
        }
    }

    fun dec(key: String) {
        val v = map[key]!!
        if (v.c == 1) {
            map.remove(key)
            removeNode(v)
        } else {
            v.c--
            addNode(v)
        }

    }

    fun getMaxKey(): String {
        if (map.isEmpty()) return ""
        return tail.p!!.v
    }

    fun getMinKey(): String {
        if (map.isEmpty()) return ""
        return head.n!!.v
    }

}
