package com.sun.livescore.util

object Util {
    fun getScoreFromString(scores: String?): Array<String> {
        scores?.let {
            val score = it.split(Constant.SCORE_REGEX)
            return arrayOf(score[Constant.FIRST_SCORE_INDEX].trim(), score[Constant.SECOND_SCORE_INDEX].trim())
        }
        return arrayOf()
    }
}
