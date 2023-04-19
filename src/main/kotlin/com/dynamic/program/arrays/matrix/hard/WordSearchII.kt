package com.dynamic.program.arrays.matrix.hard

/**
 * 212. Word Search II
Hard

company
Amazon
company
Uber
company
Bloomberg
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
 */
class WordSearchII {
    val lst = mutableListOf<String>()
    val dir = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

    data class TriNode(var word: String? = null, val children: HashMap<Char, TriNode> = hashMapOf())

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val parent = TriNode()
        for (word in words) {
            var node = parent
            for (c in word) {
                if (node.children.containsKey(c)) {
                    node = node.children[c]!!
                } else {
                    val newNode = TriNode()
                    node.children[c] = newNode
                    node = newNode
                }
            }
            node.word = word
        }

        for (r in board.indices) {
            for (c in board[r].indices) {
                val ch = board[r][c]
                val n1 = parent.children[ch]
                if (n1 != null) {
                    backtacking(r, c, board, n1)
                }
            }
        }
        return lst
    }

    private fun backtacking(r: Int, c: Int, board: Array<CharArray>, node: TriNode) {
        val ch = board[r][c]
        if (node.word != null) {
            lst.add(node.word!!)
            node.word = null
        } else {
            board[r][c] = '#'
            dir.forEach { (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                if (isValid(nr, nc, board) && node.children.containsKey(board[nr][nc])) {
                    backtacking(nr, nc, board, node.children[board[nr][nc]]!!)
                }
            }
            board[r][c] = ch
        }
        if(node.children.isEmpty()) {
            node.children.remove(ch)
        }
    }

    fun isValid(r: Int, c: Int, board: Array<CharArray>): Boolean =
        (r in board.indices && c in board[r].indices && board[r][c] != '#')
}

fun main() {
    val board = arrayOf(charArrayOf('a'))
    println(WordSearchII().findWords(board, arrayOf("ab")))
}
