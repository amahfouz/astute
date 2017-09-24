package com.amahfouz.astute.model

/**
 * Score computation for a level.
 *
 * Score for a level is computed based on:
 *
 * - Grid size (G) and Number of Circles (N).
 * - Number of wrong answers made (W).
 * - Time taken to solve the level (T).
 *
 * Definitions:
 *
 * - Level Difficulty (LD) is computed from G and N.
 * - Perfect level score (P) is computed from LD.
 * - Allowed Time (Ta) is also computed from LD.
 *
 * Rules:
 *
 * - P is obtained if level is solved with W = 0 and T < Ta
 * - For every mistake a deduction (Dw) is made from the max score.
 * - For every second past the allowed time a deduction (Dt) is also made.
 * - Deductions may accumulate to bring the score down to zero.
 *
 * Formulas:
 *
 * - LD = c1 * G + c2 * N
 * - P  = c3 * LD
 * - Ta =
 *         G <= 12 --> 2
 *         G <= 20 --> 3
 *         G > 20  --> 4
 * - Dw = P / max(1, N / 2)
 * - Sd = P - Dw
 * - Dt = Sd / 5
 * - S = Sd - (T - Ta) * Dt
 */
class LevelScore(private val levelSpec: LevelSpec, private val numMistakes: Int, private val timeTaken: Long) {

    companion object {
        fun calcMaxScore(levelSpec: LevelSpec): Int {
            val g = levelSpec.config.dims.cols * levelSpec.config.dims.rows;
            val n = levelSpec.config.numCircles;
            return  3 * g * g + n * n
        }
    }

    fun calc() : Long {
        val g = levelSpec.config.dims.cols * levelSpec.config.dims.rows;
        val n = levelSpec.config.numCircles;
        val p = calcMaxScore(levelSpec)
        val ta = when (g) {
            in 1..12 -> 2000
            in 2..20 -> 3000
            else -> 4000
        }
        val dw = p / n

        val sd = p - dw * numMistakes
        val dt = sd / 5

        val s = sd - maxOf((timeTaken - ta) / 1000, 0) * dt

        return maxOf(s, 0)
    }
}