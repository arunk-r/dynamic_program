package com.dynamic.program.dp.hard

/**
 *Magical Cows
 * /problems/magicalcows/file/statement/en/img-0001.jpg
 * Image by Graeme Zinck, CC BY-SA
 * Baxter Scott owns The Enlightened Dairy Co., a dairy company with magical cows. Early each morning, he brushes his teeth, strolls outside, and finds that the cows have doubled in number. With double the number of cows, he can produce double the quantity of milk. While he is ecstatic that he has so many cows and so much milk, the Dairy Regulator forces him to keep at most
 *  cows on any given farm, which greatly complicates his business.
 *
 * At The Enlightened Dairy Co., Baxter has access to an unlimited number of farms, each with a maximum capacity of
 *  cows. On each farm, cows reproduce at the same rate: they always double in number when the clock strikes midnight. To stay within the Regulator’s rules, whenever a farm has strictly more than
 *  cows, Baxter selects half of the cows on that farm and moves them to an entirely new, empty farm. More precisely, if there are
 *  cows on a farm, he leaves all
 *  cows on the farm, but if there are
 *  cows on a farm, he leaves
 *
 *  cows on the farm and takes
 *
 *  cows to a new, empty farm. (Here
 *  and
 *  denote the ceiling and floor functions, which round up/down to the nearest integer, respectively.) He does this early every morning, before the Regulator could possibly show up, so that he can avoid paying hefty Moo Fees.
 *
 * The Regulator needs to know how many farms she will be inspecting when she visits The Enlightened Dairy Co. The Regulator inspects every farm that has at least one cow, and does not inspect any farm with zero cows. Given the number of cows on each farm with at least one cow on Day
 * , compute the number of farms that need inspecting on any given day.
 *
 * Input
 * The first line of input contains three space-separated integers,
 * ,
 * , and
 * , where
 *  (
 * ) is the maximum number of cows allowed on a farm,
 *  (
 * ) is the number of farms with at least one cow on Day
 * , and
 *  (
 * ) is the number of different days on which the Regulator visits. The following
 *  lines of input each have a single integer
 *  (
 * ), representing the number of cows on the
 * th of the
 *  farms on Day
 * . The final
 *  lines each have a single integer
 *  (
 * ), representing a day when the Regulator visits. All
 *  are distinct.
 *
 * Output
 * For each
 * , output one line with a single integer: the number of farms the Regulator will inspect on Day
 * .
 *
 * Sample Input 1	Sample Output 1
 * 1 5 5
 * 1
 * 1
 * 1
 * 1
 * 1
 * 0
 * 1
 * 2
 * 3
 * 4
 * output
 * 5
 * 10
 * 20
 * 40
 * 80
 * Sample Input 2	Sample Output 2
 * 2 5 3
 * 1
 * 2
 * 1
 * 2
 * 1
 * 0
 * 1
 * 2
 * Output
 * 5
 * 7
 * 14
 */
class MagicCows {
    fun magicalCow(maxCows: Int, forms: IntArray, m: Int) {
        val maxDays = 50
        val dp = Array(maxDays+1){IntArray(maxCows+1)}
        for(cows in forms) {
            dp[0][cows]++
        }
        for( day in 0 until maxDays) {
            for(cIdx in 1 .. maxCows) {
                if(cIdx <= maxCows/2) {
                    dp[day+1][cIdx * 2] += dp[day][cIdx]
                } else {
                    dp[day+1][cIdx] += (2 * dp[day][cIdx])
                }
            }
        }
        for( day in 0 until m) {
            println(dp[day].sum())
        }
    }
}

fun main() {
    MagicCows().magicalCow(1, intArrayOf(1,1,1,1,1), 5)
    MagicCows().magicalCow(2, intArrayOf(1,2,1,2,1), 3)
}
