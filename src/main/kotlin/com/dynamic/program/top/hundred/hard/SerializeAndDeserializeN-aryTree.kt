package com.dynamic.program.top.hundred.hard

/**
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 428. Serialize and Deserialize N-ary Tree
 * Hard
 * 1K
 * 56
 * company
 * Uber
 * company
 * Apple
 * company
 * Google
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 *
 *
 *
 *
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
 *
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.
 *
 *
 *
 *
 * For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 *
 * You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Example 2:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */
class `SerializeAndDeserializeN-aryTree` {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    // Encodes a tree to a single string.
    fun serialize(root: Node?): String {
        val buf = StringBuffer()
        if (root != null) {
            buf.append(root.`val`)
            buf.append('#')
            encode(root, buf)
        }
        return buf.toString()
    }

    private fun encode(node: Node?, buf: StringBuffer) {
        if (node != null && node.children.isNotEmpty()) {
            buf.append(node.`val`)
            buf.append('%')
            node.children.forEach { nei ->
                if (nei != null) {
                    buf.append(nei.`val`)
                    buf.append(',')
                }
            }
            buf.deleteCharAt(buf.length - 1)
            buf.append('#')
            node.children.forEach { nei ->
                encode(nei, buf)
            }
        }
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): Node? {
        //println(data)
        val map = HashMap<Int, Node>()
        var root: Node? = null
        val split = data.split('#')
        if (split.isNotEmpty()) {
            if (split[0] != "") {
                root = decode(split[0], map)
                for (i in 1 until split.size) {
                    if (split[i] != "") {
                        decode(split[i], map)
                    }
                }
            }
        }
        return root
    }

    private fun decode(data: String, map: HashMap<Int, Node>): Node {
        val split = data.split('%')
        //println(split.toList())
        val value = split[0].toInt()
        var node = map[value]
        if (node == null) {
            node = Node(value)
            map[value] = node
        } else {
            val lst = mutableListOf<Node>()
            split[1].split(',').forEach { d ->
                val v = d.toInt()
                val n = Node(v)
                lst.add(n)
                map[v] = n
            }
            if (lst.isNotEmpty()) {
                node.children = lst
            }
        }
        return node
    }
}
