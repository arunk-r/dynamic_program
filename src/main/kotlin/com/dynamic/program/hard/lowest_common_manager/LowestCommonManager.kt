package com.dynamic.program.hard.lowest_common_manager

/**
 * You're given three inputs, all of which are instances of an
 * OrgChart class that have a directReports property
 * pointing to their direct reports. The first input is the top manager in an
 * organizational chart (i.e., the only instance that isn't anybody else's direct
 * report), and the other two inputs are reports in the organizational chart. The
 * two reports are guaranteed to be distinct.
 *
 * Write a function that returns the lowest common manager to the two reports.
 *
 * Sample Input
 * // From the organizational chart below.
 * topManager = Node A
 * reportOne = Node E
 * reportTwo = Node I
 *                      A
 *                   /     \
 *                  B       C
 *                /   \   /   \
 *               D     E F     G
 *             /   \
 *            H     I
 *
 * Sample Output
 * Node B
 *
 */
class OrgChart(name: Char) {
    val name = name
    val directReports = mutableListOf<OrgChart>()
}

fun getLowestCommonManager(topManager: OrgChart, reportOne: OrgChart, reportTwo: OrgChart): OrgChart? {
    // Write your code here.
    val pair = getLowestCommonManagerLoop(topManager, reportOne, reportTwo)
    return if (pair.first == 2) pair.second else null
}

fun getLowestCommonManagerLoop(topManager: OrgChart, reportOne: OrgChart, reportTwo: OrgChart): Pair<Int, OrgChart?> {
    var length = 0
    var lowestManager: OrgChart? = null
    topManager.directReports.forEach {
        val pair = getLowestCommonManagerLoop(it, reportOne, reportTwo)
        if (pair.second != null) return pair
        length += pair.first
    }
    if (topManager.name == reportOne.name || topManager.name == reportTwo.name)
    {
        length ++
    }
    if (length == 2) {
        lowestManager = topManager
    }
    return Pair(length, lowestManager)
}

fun main() {
    val d = OrgChart('D')
    d.directReports.add(OrgChart('H'))
    d.directReports.add(OrgChart('I'))

    val c = OrgChart('C')
    c.directReports.add(OrgChart('F'))
    c.directReports.add(OrgChart('G'))

    val b = OrgChart('B')
    b.directReports.add(OrgChart('E'))
    b.directReports.add(d)
    val a = OrgChart('A')
    a.directReports.add(b)
    a.directReports.add(c)

    val manager = getLowestCommonManager(a, OrgChart('E'), OrgChart('I'))
    println(manager?.name)
}