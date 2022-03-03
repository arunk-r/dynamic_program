package com.dynamic.program.medium.minimum_characters_for_words

import kotlin.math.max

fun minimumCharactersForWords(words: List<String>): List<Char> {
    // Write your code here.
    val outputCharacters = HashMap<Char, Int>()
    for (word in words) {
        val characterFrequency = characterFrequency(word)
        updateMaximumFrequency(characterFrequency, outputCharacters)
    }

    return generateMaxFrequencyCharacters(outputCharacters)
}

fun characterFrequency(string: String): Map<Char, Int> {
    val characterFrequencyMap = mutableMapOf<Char, Int>()
    for (char in string) {
        if (!characterFrequencyMap.contains(char)) {
            characterFrequencyMap[char] = 0
        }

        characterFrequencyMap[char] = characterFrequencyMap[char]!! + 1
    }
    return characterFrequencyMap
}

fun updateMaximumFrequency(characterFrequency: Map<Char, Int>, outputCharacters: MutableMap<Char, Int>) {
    characterFrequency.forEach { (k, v) ->
        if (outputCharacters.contains(k)) {
            outputCharacters[k] = max(v, outputCharacters[k]!!)
        } else {
            outputCharacters[k] = v
        }
    }
}

fun generateMaxFrequencyCharacters(outputCharacters: MutableMap<Char, Int>): List<Char> {
    val output = mutableListOf<Char>()
    outputCharacters.forEach { (char, frequency) ->
        for (i in 0 until frequency) {
            output.add(char)
        }
    }
    return output
}

fun main() {
    println(minimumCharactersForWords(listOf("this", "that", "did", "deed", "them!", "a")))
}