package com.dynamic.program.boggle_board

/**
 * You're given a two-dimensional array (a matrix) of potentially unequal height
 * and width containing letters; this matrix represents a boggle board. You're
 * also given a list of words.
 *
 * Write a function that returns an array of all the words contained in the
 * boggle board. The final words don't need to be in any particular order.
 *
 * A word is constructed in the boggle board by connecting adjacent
 * (horizontally, vertically, or diagonally) letters, without using any single
 * letter at a given position more than once; while a word can of course have
 * repeated letters, those repeated letters must come from different positions in
 * the boggle board in order for the word to be contained in the board. Note that
 * two or more words are allowed to overlap and use the same letters in the
 * boggle board.
 *
 * Sample Input
 * board = [
 * ["t", "h", "i", "s", "i", "s", "a"],
 * ["s", "i", "m", "p", "l", "e", "x"],
 * ["b", "x", "x", "x", "x", "e", "b"],
 * ["x", "o", "g", "g", "l", "x", "o"],
 * ["x", "x", "x", "D", "T", "r", "a"],
 * ["R", "E", "P", "E", "A", "d", "x"],
 * ["x", "x", "x", "x", "x", "x", "x"],
 * ["N", "O", "T", "R", "E", "-", "P"],
 * ["x", "x", "D", "E", "T", "A", "E"],
 * ],
 * words = [
 * "this", "is", "not", "a", "simple", "boggle",
 * "board", "test", "REPEATED", "NOTRE-PEATED",
 * ]
 *
 * Sample Output
 * ["this", "is", "a", "simple", "boggle", "board", "NOTRE-PEATED"]
 * // The words could be ordered differently.
 *
 */

data class TrieNode(
    val children: MutableMap<Char, TrieNode?> = mutableMapOf(),
    var word: String = ""
)

open class Trie {
    private val endSymbol = '*'
    var root = TrieNode()

    fun add(word: String) {
        var current = this.root
        for (letter in word) {
            if (!current.children.containsKey(letter)) {
                current.children[letter] = TrieNode()
            }
            current = current.children[letter]!!
        }
        current.children[endSymbol] = null
        current.word = word
    }
}

fun getNeighbors(i: Int, j: Int, board: List<List<Char>>): List<Pair<Int, Int>> {
    val neighbors = mutableListOf<Pair<Int, Int>>()
    if (i > 0 && j > 0) {
        neighbors.add(Pair(i - 1, j - 1))
    }

    if (i > 0 && j < board[0].size - 1) {
        neighbors.add(Pair(i - 1, j + 1))
    }

    if (i < board.size - 1 && j < board[0].size - 1) {
        neighbors.add(Pair(i + 1, j + 1))
    }

    if (i < board.size - 1 && j > 0) {
        neighbors.add(Pair(i + 1, j - 1))
    }

    if (i > 0) {
        neighbors.add(Pair(i - 1, j))
    }

    if (i < board.size - 1) {
        neighbors.add(Pair(i + 1, j))
    }

    if (j > 0) {
        neighbors.add(Pair(i, j - 1))
    }

    if (j < board[0].size - 1) {
        neighbors.add(Pair(i, j + 1))
    }

    return neighbors
}

fun explore(
    i: Int,
    j: Int,
    board: List<List<Char>>,
    trieNode: TrieNode,
    visited: List<MutableList<Boolean>>,
    finalWords: MutableMap<String, Boolean>
) {
    if (i >= board.size || j >= board[0].size) return
    if (visited[i][j]) return
    val letter = board[i][j]
    if (!trieNode.children.containsKey(letter)) return
    visited[i][j] = true
    val nextTrieNode = trieNode.children[letter]!!
    if (nextTrieNode.children.containsKey('*')) finalWords[nextTrieNode.word] = true
    val neighbors = getNeighbors(i, j, board)
    //println(neighbors)
    for (neighbor in neighbors) {
        explore(neighbor.first, neighbor.second, board, nextTrieNode, visited, finalWords)
    }
    visited[i][j] = false
}

fun boggleBoard(board: List<List<Char>>, words: List<String>): List<String> {
    val trie = Trie()
    for (word in words) {
        trie.add(word)
    }
    val finalWord = mutableMapOf<String, Boolean>()
    val visited = MutableList(board.size) { MutableList(board[0].size) { false } }
    //println(visited)
    for (i in board.indices) {
        for (j in board[i].indices) {
            explore(i, j, board, trie.root, visited, finalWord)
        }
    }

    return finalWord.filter { it.value }.keys.toList()
}

/**
 * Planning for Hashbase implementation
 */
/*
fun boggleBoard(board: List<List<Char>>, words: List<String>): List<String> {
    // Write your code here.
    val validStrings = mutableListOf<String>()
    val map = buildHashMap(board)
    var matrix: MutableList<MutableList<Boolean>>
    words.forEach { word ->
        matrix = buildEightByEightMatrix()
        if (map.contains(word[0])) {
            for (i in 1 until word.length) {
                if (map.contains(word[i])) {
                    map[word[i]]!!.forEach {

                    }
                } else {
                    break
                }
            }
        }
    }
    return listOf()
}
fun buildEightByEightMatrix(): MutableList<MutableList<Boolean>> {
    return MutableList(8) { MutableList(8) { false } }
}

fun buildHashMap(board: List<List<Char>>): Map<Char, List<Pair<Int, Int>>> {
    val map: HashMap<Char, MutableList<Pair<Int, Int>>> = HashMap()
    for (i in board.indices) {
        for ( j in board[i].indices) {
            if (!map.contains(board[i][j])) {
                map[board[i][j]] = mutableListOf()
            }
            map[board[i][j]]!!.add(Pair(i,j))
        }
    }
    return map
}
*/

fun main() {
    println(
        boggleBoard(
            listOf(
                listOf('t', 'h', 'i', 's', 'i', 's', 'a'),
                listOf('s', 'i', 'm', 'p', 'l', 'e', 'x'),
                listOf('b', 'x', 'x', 'x', 'x', 'e', 'b'),
                listOf('x', 'o', 'g', 'g', 'l', 'x', 'o'),
                listOf('x', 'x', 'x', 'D', 'T', 'r', 'a'),
                listOf('R', 'E', 'P', 'E', 'A', 'd', 'x'),
                listOf('x', 'x', 'x', 'x', 'x', 'x', 'x'),
                listOf('N', 'O', 'T', 'R', 'E', '-', 'P'),
                listOf('x', 'x', 'D', 'E', 'T', 'A', 'E')
            ), listOf("this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED")
        )
    )
}