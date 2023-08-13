package com.dynamic.program.design.medium.union_find


class LongestUploadedPrefix(n: Int) {
    private var root = IntArray(n + 2) { i -> i }
    private val seen = HashSet<Int>()

    init {
        seen.add(0)
    }

    fun upload(video: Int) {
        seen.add(video)
        if (seen.contains(video - 1)) union(video - 1, video)
        if (seen.contains(video + 1)) union(video, video + 1)
    }

    fun longest(): Int {
        return find(0)
    }

    private fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            if (rootX > rootY) {
                root[rootY] = rootX
            } else {
                root[rootX] = rootY
            }
        }
    }

    private fun find(x: Int): Int {
        var c = x
        while (c != root[c]){
            root[c] = root[root[c]]
            c = root[c]
        }
        return c
    }
}

class LongestUploadedPrefix2(val n: Int) {
    private var root = IntArray(n + 2) { i -> i }
    private val seen = HashSet<Int>()
    init {
        seen.add(0)
    }
    fun upload(video: Int) {
        seen.add(video)
        if (seen.contains(video - 1)) union(video - 1, video)
        if (seen.contains(video + 1)) union(video, video + 1)
    }

    fun longest(): Int {
        return find(0)
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            if (rootX > rootY) {
                root[rootY] = rootX
            } else {
                root[rootX] = rootY
            }
        }
    }

    fun find(x: Int): Int {
        if (root[x] == x) {
            return root[x]
        }
        root[x] = find(root[x])
        return root[x]
    }
}

class LongestUploadedPrefix1(val n: Int) {
    var map = IntArray(n + 1) { -1 }
    var idx = 0

    init {
        map[0] = 1
    }

    fun upload(video: Int) {
        map[video] = 1
    }

    fun longest(): Int {
        while (idx < n + 1 && map[idx] != -1) {
            idx++
        }
        idx -= 1
        return idx
    }
}

fun main() {
    val d = LongestUploadedPrefix2(4)
    d.upload(3)
    println(d.longest())
    d.upload(1)
    println(d.longest())
    d.upload(2)
    println(d.longest())
}
