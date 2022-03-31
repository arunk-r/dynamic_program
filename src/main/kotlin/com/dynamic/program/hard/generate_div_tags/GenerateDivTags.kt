package com.dynamic.program.hard.generate_div_tags

/**
 * Write a function that takes in a positive integer
 * numberOfTags and returns a list of all the valid strings that you
 * can generate with that number of matched <div></div> tags.
 *
 * A string is valid and contains matched
 * <div></div> tags if for every opening tag
 * <div>, there's a closing tag </div>
 * that comes after the opening tag and that isn't used as a closing tag for
 * another opening tag. Each output string should contain exactly
 * numberOfTags opening tags and numberOfTags closing
 * tags.
 *
 *
 * For example, given numberOfTags = 2, the valid strings to return
 * would be:
 * ["<div></div><div></div>",
 * "<div><div></div></div>"].
 *
 * Note that the output strings don't need to be in any particular order.
 * Sample Input
 * numberOfTags = 3
 *
 * Sample Output
 *   [
 * "<div><div><div></div></div></div>",
 * "<div><div></div><div></div></div>",
 * "<div><div></div></div><div></div>",
 * "<div></div><div><div></div></div>",
 * "<div></div><div></div><div></div>",
 * ] // The strings could be ordered differently.
 *
 */

fun generateDivTags(numberOfTags: Int): List<String> {
    // Write your code here.
    val resultMatch = mutableListOf<String>()
    recursiveBuild("", numberOfTags, numberOfTags, resultMatch)
    return resultMatch
}

fun recursiveBuild(prefix: String, openTag: Int, closeTag: Int, resultMatch: MutableList<String>) {
    if (openTag > 0) {
        val newPrefix = "$prefix<div>"
        recursiveBuild(newPrefix, openTag-1, closeTag, resultMatch)
    }
    if (openTag < closeTag) {
        val newPrefix = "$prefix</div>"
        recursiveBuild(newPrefix, openTag, closeTag-1, resultMatch)
    }

    if (closeTag == 0) {
        resultMatch.add(prefix)
    }
}

fun main() {
    println(generateDivTags(3))
}