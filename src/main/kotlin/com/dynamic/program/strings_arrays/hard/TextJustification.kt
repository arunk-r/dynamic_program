package com.dynamic.program.strings_arrays.hard


class TextJustification {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val ans: MutableList<String> = ArrayList()
        var i = 0
        while (i < words.size) {
            val currentLine = getWords(i, words, maxWidth)
            i += currentLine.size
            ans.add(createLine(currentLine, i, words, maxWidth))
        }
        return ans
    }

    private fun getWords(i: Int, words: Array<String>, maxWidth: Int): MutableList<String> {
        var idx = i
        val currentLine: MutableList<String> = ArrayList()
        var currLength = 0
        while (idx < words.size && currLength + words[idx].length <= maxWidth) {
            currentLine.add(words[idx])
            currLength += words[idx].length + 1
            idx++
        }
        return currentLine
    }

    private fun createLine(line: MutableList<String>, i: Int, words: Array<String>, maxWidth: Int): String {
        var baseLength = -1
        for (word in line) {
            baseLength += word.length + 1
        }
         val extraSpaces = maxWidth - baseLength
        if (line.size == 1 || i == words.size) {
            return java.lang.String.join(" ", line) + " ".repeat(extraSpaces)
        }
        val wordCount = line.size - 1
        val spacesPerWord = extraSpaces / wordCount
        val needsExtraSpace = extraSpaces % wordCount
        for (j in 0 until needsExtraSpace) {
            line[j] = line[j] + " "
        }
        for (j in 0 until wordCount) {
            line[j] = line[j] + " ".repeat(spacesPerWord)
        }
        return java.lang.String.join(" ", line)
    }
}

fun main() {
    //println(TextJustification().fullJustify(arrayOf("What","must","be","acknowledgment","shall","be"), 16))
    println(TextJustification().fullJustify(arrayOf("This", "is", "an", "example", "of", "text", "justification."), 16))
}
