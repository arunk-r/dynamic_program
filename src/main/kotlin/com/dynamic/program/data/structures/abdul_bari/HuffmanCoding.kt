package com.dynamic.program.data.structures.abdul_bari

import java.util.PriorityQueue

class HuffmanCoding {
    data class HuffmanNode(val c: Char, val cnt: Int, var left: HuffmanNode? = null, var right: HuffmanNode? = null)
    private var root: HuffmanNode? = null
    private val huffmanMappings = hashMapOf<Char, String>()

    private fun getCodes(node: HuffmanNode?, encode: String) {
        if (node == null) return
        else if (node.left == null && node.right == null) {
            huffmanMappings[node.c] = encode
        } else {
            getCodes(node.left, "${encode}0")
            getCodes(node.right, "${encode}1")
        }
    }

    fun encode(url: String): String {
        val pq = PriorityQueue<HuffmanNode> { x, y -> x.cnt - y.cnt }
        val map = hashMapOf<Char, Int>()
        for (c in url) {
            map[c] = map.getOrDefault(c, 0) + 1
        }
        map.forEach { (k, v) ->
            pq.add(HuffmanNode(k, v))
        }
        while (pq.isNotEmpty()) {
            val left = pq.remove()
            var right: HuffmanNode? = null
            if (pq.isNotEmpty()) {
                right = pq.remove()
            }
            val cnt = left.cnt + (right?.cnt ?: 0)
            root = HuffmanNode(' ', cnt, left, right)
            if (pq.isNotEmpty()) {
                pq.add(root)
            }
        }
        getCodes(root, "")
        val buf = StringBuffer()
        for (c in url) {
            buf.append(huffmanMappings[c])
        }
        println(buf)
        return buf.toString()
    }

    fun decode(url: String): String {
        val buf = StringBuffer()
        var i = 0
        while (i < url.length) {
            var node = root
            while (node?.left != null && node.right != null) {
                node = if (url[i++] == '0') {
                    node.left
                } else {
                    node.right
                }
            }
            buf.append(node?.c)
        }
        return buf.toString()
    }
}

fun main() {
    val hc = HuffmanCoding()
    println(hc.decode(hc.encode("https://leetcode.com/problems/design-tinyurl")))
}
