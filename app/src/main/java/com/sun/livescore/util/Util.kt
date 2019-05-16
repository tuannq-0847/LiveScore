package com.sun.livescore.util

object Util {
    /*function này em viết để có thể tách được tỉ số của độ nhà và đội khách ra ạ. Vì json từ api nó trả về kiểu
    như thế này ạ vd: 0-1*/
    fun getScoreFromString(scores: String?): Array<String> {
        scores?.let {
            for (i in 0 until it.length) {
                if (it[i] in Constants.START_CHAR..Constants.END_CHAR) {
                    for (j in i + 1 until it.length) {
                        if (it[j] in Constants.START_CHAR..Constants.END_CHAR) {
                            return arrayOf(it[i].toString(), it[j].toString())
                        }
                    }
                }
            }
        }
        return arrayOf()
    }

    /**
     * function này em viết để có thể lấy ra thời gian diễn ra trận đấu giờ+phút ạ vì api nó trả về
     * như thế này ạ vd: 12:30:00
     */

    fun cleanUpTimeString(time: String): String {
        var finalTime = ""
        for (i in 0 until time.length - 3) finalTime += time[i]
        return finalTime
    }
}
