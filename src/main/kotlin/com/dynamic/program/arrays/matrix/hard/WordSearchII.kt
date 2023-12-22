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
    data class Trie(var word: String? = null, val children: HashMap<Char, Trie> = hashMapOf())
    val root = Trie()
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        for(word in words) {
            addToTrie(word, root)
        }
        println(root)
        val result = mutableListOf<String>()
        for(r in board.indices) {
            for(c in board[r].indices) {
                if(root.children.containsKey(board[r][c])) {
                    val word = dfs(r, c, board, root, hashSetOf())
                    if(word != "") {
                        result.add(word)
                    }
                }
            }
        }
        return result
    }

    private fun dfs(r: Int, c: Int, board: Array<CharArray>, root: Trie?, seen: HashSet<Pair<Int, Int>>): String {
        if(root?.word != null) {
            val w = root?.word ?: ""
            root?.word = null
            return w
        }
        else if(root == null) return ""
        else if(seen.contains(Pair(r, c))) return ""
        else if(r < 0 || r >= board.size || c < 0 || c >= board[r].size) return ""
        seen.add(Pair(r, c))
        val next = root.children[board[r][c]]
        if(next != null) {
            var word = dfs(r+1, c, board, next, seen)
            if(word == "") {
                word = dfs(r-1, c, board, next, seen)
            }
            if(word == "") {
                word = dfs(r, c+1, board, next, seen)
            }
            if(word != "") return word
            return dfs(r, c-1, board, next, seen)
        }
        seen.remove(Pair(r, c))
        return ""
    }

    private fun addToTrie(s: String, root: Trie) {
        var node = root
        for(c in s) {
            var next = node.children[c]
            if(next == null) {
                next = Trie()
                node.children[c] = next
            }
            node = next
        }
        node.word = s
    }
    /*data class Trie(val c: Char, var w: String? = null, val map: HashMap<Char, Trie> = hashMapOf())
    val root = Trie(' ')
    val result = mutableListOf<String>()
    val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val m = board.size
        val n = board[0].size

        for (word in words) {
            var node = root
            for (c in word) {
                val n = node.map[c]
                if (n == null) {
                    val nn = Trie(c)
                    node.map[c] = nn
                    node = nn
                } else {
                    node = n
                }
            }
            node.w = word
        }
        for (r in board.indices) {
            for (c in board[r].indices) {
                val node = root.map[board[r][c]]
                if (node != null) {
                    val seen = Array(m) { BooleanArray(n) }
                    find(r, c, node, board, seen)
                }
            }
        }

        return result
    }

    fun find(r: Int, c: Int, node: Trie, board: Array<CharArray>, seen: Array<BooleanArray>) {
        if (!seen[r][c]) {
            seen[r][c] = true
            if (node.w != null) {
                result.add(node.w!!)
                node.w = null
            }
            dir.forEach { (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                if (valid(nr, nc, board, seen)) {
                    val nn = node.map[board[nr][nc]]
                    if (nn != null) {
                        find(nr, nc, nn, board, seen)
                    }
                }
            }
        }
    }

    fun valid(r: Int, c: Int, board: Array<CharArray>, seen: Array<BooleanArray>): Boolean =
        (r in board.indices && c in board[r].indices && !seen[r][c])

     */
}

fun main() {
    val board = arrayOf(
        charArrayOf('o', 'a', 'a', 'n'),
        charArrayOf('e', 't', 'a', 'e'),
        charArrayOf('i', 'h', 'k', 'r'),
        charArrayOf('i', 'f', 'l', 'v')
    )
    println(WordSearchII().findWords(board, arrayOf("oath", "pea", "eat", "rain")))
}
