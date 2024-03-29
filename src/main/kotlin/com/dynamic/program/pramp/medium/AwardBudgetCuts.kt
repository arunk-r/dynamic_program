package com.dynamic.program.pramp.medium

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Award Budget Cuts
 * The awards committee of your alma mater (i.e. your college/university) asked for your assistance with a budget allocation problem they’re facing. Originally, the committee planned to give N research grants this year. However, due to spending cutbacks, the budget was reduced to newBudget dollars and now they need to reallocate the grants. The committee made a decision that they’d like to impact as few grant recipients as possible by applying a maximum cap on all grants. Every grant initially planned to be higher than cap will now be exactly cap dollars. Grants less or equal to cap, obviously, won’t be impacted.
 *
 * Given an array grantsArray of the original grants and the reduced budget newBudget, write a function findGrantsCap that finds in the most efficient manner a cap such that the least number of recipients is impacted and that the new budget constraint is met (i.e. sum of the N reallocated grants equals to newBudget).
 *
 * Analyze the time and space complexities of your solution.
 *
 * Example:
 *
 * input:  grantsArray = [2, 100, 50, 120, 1000], newBudget = 190
 *
 * output: 47 # and given this cap the new grants array would be
 *            # [2, 47, 47, 47, 47]. Notice that the sum of the
 *            # new grants is indeed 190
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.double grantsArray
 *
 * 0 ≤ grantsArray.length ≤ 20
 * 0 ≤ grantsArray[i]
 * [input] double newBudget
 *
 * [output] double
 */
class AwardBudgetCuts {
    fun budgetMatch(grants: DoubleArray, newGrant: Double): Double {
        var count: Double = 0.0
        for (grant in grants) {
            count += minOf(grant, newGrant)
        }
        return count
    }
    fun findGrantsCap(grants: DoubleArray, newGrant: Double): Double {
        var min = Double.MAX_VALUE
        var max = Double.MIN_VALUE
        for (grant in grants) {
            min = minOf(grant, min)
            max = maxOf(grant, max)
        }
        while (min <= max) {
            val mid = min + (max  - min) / 2.0
            println(mid)
            val total = budgetMatch(grants, mid)
            if (total == newGrant) return mid
            else if (total > newGrant) {
                max = mid - 0.01
            } else {
                min = mid + 0.01
            }
        }
        return min
    }
    fun findGrantsCap1(grants: DoubleArray, newGrant: Double): Double {
        grants.sort()

        var newNum = newGrant / grants.size * 1.0

        for (i in grants.indices) {
            val grant = grants[i]
            if (grant < newNum) {
                val diff = newNum - grant
                newNum += (diff / (grants.size - i - 1) * 1.0)
            } else {
                break
            }
        }
        return newNum
    }
}


fun main() {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss.SSS")
    var currentDate = sdf.format(Date())
    println(" C DATE is  $currentDate")
    println(AwardBudgetCuts().findGrantsCap(doubleArrayOf(2.0, 100.0, 50.0, 120.0, 1000.0), 190.0))
    currentDate = sdf.format(Date())
    println(" C DATE is  $currentDate")
    println(AwardBudgetCuts().findGrantsCap1(doubleArrayOf(2.0, 100.0, 50.0, 120.0, 1000.0), 190.0))
    currentDate = sdf.format(Date())
    println(" C DATE is  $currentDate")
}
