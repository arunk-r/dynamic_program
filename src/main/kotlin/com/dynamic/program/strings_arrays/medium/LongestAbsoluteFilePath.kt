package com.dynamic.program.strings_arrays.medium

class LongestAbsoluteFilePath {
    data class Data(val depth: Int, val s: String, val file: Boolean = false)
    var max = 0
    fun lengthLongestPath(input: String): Int {
        val split = input.split("\n")
        findFile(0, buildMap(split), 0, "")
        return max
    }

    private fun findFile(idx: Int, data: List<Data>, depth: Int, path: String): Int {
        var i = idx
        while(i < data.size) {
            val d = data[i]
            if(d.depth == depth) {
                if(d.file) {
                    max = maxOf(max, (path + d.s).length)
                    i++
                } else {
                    i = findFile(i+1, data, depth+1, path + "/" + d.s)
                }
            } else return i
        }
        return i
    }

    private fun buildMap(files: List<String>): List<Data> {
        val list = mutableListOf<Data>()
        for(file in files) {
            val t = file.split("\t")
            val depth = t.size - 1
            val fileName = t[t.size-1]
            list.add(Data(depth, fileName, fileName.contains(".")))
        }
        return list
    }
}

fun main() {
    println(LongestAbsoluteFilePath().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"))
}
