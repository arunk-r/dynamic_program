package com.dynamic.program.phone_number_mnemonics

val DIGIT_LETTERS = mapOf(
    "0" to listOf("0"),
    "1" to listOf("1"),
    "2" to listOf("a", "b", "c"),
    "3" to listOf("d", "e", "f"),
    "4" to listOf("g", "h", "i"),
    "5" to listOf("j", "k", "l"),
    "6" to listOf("m", "n", "o"),
    "7" to listOf("p", "q", "r", "s"),
    "8" to listOf("t", "u", "v"),
    "9" to listOf("w", "x", "y", "z")
)

fun phoneNumberMnemonics(phoneNumber: String): List<String> {
    // Write your code here.
    val result: MutableList<String> = mutableListOf()
    val currentMnemonic = Array(phoneNumber.length) {"0"}
    phoneNumberMnemonicsHelper(0, phoneNumber, currentMnemonic, result)
    return result
}

fun phoneNumberMnemonicsHelper(index: Int, phoneNumber: String, currentMnemonic: Array<String>, result: MutableList<String>) {
    if (index == phoneNumber.length) {
        result.add(currentMnemonic.joinToString(""))
    } else {
        for (letter in DIGIT_LETTERS[phoneNumber[index].toString()]!!) {
            currentMnemonic[index] = letter
            phoneNumberMnemonicsHelper(index + 1, phoneNumber, currentMnemonic, result)
        }
    }
}

fun main() {
    println(phoneNumberMnemonics("1905"))
}