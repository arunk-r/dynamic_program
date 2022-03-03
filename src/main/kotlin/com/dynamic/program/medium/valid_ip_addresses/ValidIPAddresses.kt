package com.dynamic.program.medium.valid_ip_addresses

import kotlin.math.min

/**
 *   You're given a string of length 12 or smaller, containing only digits. Write a
 *   function that returns all the possible IP addresses that can be created by
 *   inserting three s in the string.
 *
 *   An IP address is a sequence of four positive integers that are separated by 0s,
 *   where each individual integer is within the range, inclusive.
 *
 *   An IP address isn't valid if any of the individual integers contains leading 0s.
 *   For example, "192.168.0.1"  is a valid IP address, but "192.168.00.1".
 *
 *   Your function should return the IP addresses in string format and in no
 *   particular order. If no valid IP addresses can be created from the string,
 *   your function should return an empty list.
 */
fun validIPAddresses(string: String): List<String> {
    // Write your code here.
    val validIpaddress = mutableListOf<String>()
    for (i in 1 until min(string.length, 4)) {
        val currentIPAddressParts = MutableList<String>(4) {""}

        currentIPAddressParts[0] = string.substring(0, i)
        if (!isValidAddress(currentIPAddressParts[0])) {
            continue
        }

        for (j in i+1 until i + min(string.length - i, 4)) {
            currentIPAddressParts[1] = string.substring(i, j)

            if (!isValidAddress(currentIPAddressParts[1])) {
                continue
            }

            for (k in j+1 until j+ min(string.length - j, 4)) {
                currentIPAddressParts[2] = string.substring(j, k)
                currentIPAddressParts[3] = string.substring(k)

                if (isValidAddress(currentIPAddressParts[2]) && isValidAddress(currentIPAddressParts[3])) {
                    validIpaddress.add(currentIPAddressParts.joinToString("."))
                }
            }
        }
    }
    return validIpaddress
}

fun isValidAddress(string: String): Boolean {
    val stringAsInt = string.toInt()
    if (stringAsInt > 255) return false
    return string.length == stringAsInt.toString().length
}

fun main() {
    println(validIPAddresses("1921680"))
}