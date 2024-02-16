package com.dynamic.program.graphs.uf

class ValidateBinaryTreeNodes {
    fun validateBinaryTreeNodes(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {
        val uf = UnionFind(n)
        for (parent in 0 until n) {
            val left = leftChild[parent]
            val right = rightChild[parent]

            if (left != -1) {
                if (!uf.union(parent, left)) return false
            }
            if (right != -1) {
                if (!uf.union(parent, right)) return false
            }
        }
        return uf.components == 1
    }

    data class UnionFind(val n: Int) {
        private val parents = IntArray(n) { i -> i }
        var components: Int = n

        fun union(parent: Int, child: Int): Boolean {
            val parentIdx = find(parent)
            val childIdx = find(child)
            if (childIdx != child || parentIdx == childIdx) return false
            components--
            parents[childIdx] = parentIdx
            return true
        }

        private fun find(x: Int): Int {
            var c = x
            while (c != parents[c]) {
                parents[c] = parents[parents[c]]
                c = parents[c]
            }
            return c
        }
    }
}
