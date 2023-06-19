package com.dynamic.program.design.medium.strings

/**
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 *
 * 535. Encode and Decode TinyURL
 * Medium
 * 1.8K
 * 3.5K
 * company
 * Microsoft
 * company
 * Amazon
 * company
 * Bloomberg
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 *
 *
 * Example 1:
 *
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 *
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after decoding it.
 *
 *
 * Constraints:
 *
 * 1 <= url.length <= 104
 * url is guranteed to be a valid URL.
 */
class EncodeAndDecodeTinyURL {
    val char = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    val map = hashMapOf<String, String>()
    // Encodes a URL to a shortened URL.
    fun encode(longUrl: String): String {
        var id:Long = 1
        for(c in longUrl) {
            when (c) {
                in 'a' .. 'z' -> {
                    id *= 62 + (c - 'a')
                }
                in 'A' .. 'Z' -> {
                    id *= 62 + (c - 'A') + 26
                }
                in '0' .. '9' -> {
                    id *= 62 + (c - '0') + 52
                }
            }
        }
        val encode = idToString(id)
        map[encode] = longUrl
        return encode
    }

    private fun idToString(id: Long): String {
        val sb = StringBuffer()
        var num = id
        while(num > 0) {
            sb.append(char[(num%62).toInt()])
            num /= 62
        }
        return sb.toString()
    }

    // Decodes a shortened URL to its original URL.
    fun decode(shortUrl: String): String {
        return map[shortUrl]!!
    }
}
