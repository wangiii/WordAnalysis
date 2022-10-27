package com.angiii.word.analysis.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object StopWordsHelper {
    val stopWordsSet: MutableSet<String> = mutableSetOf()

    fun loadStopWords() {
        val bufferedReader: BufferedReader
        val stream = this.javaClass.getResourceAsStream("/stop_words.txt")
        try {
            bufferedReader = BufferedReader(InputStreamReader(stream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stopWordsSet.add(line!!.trim { it <= ' ' })
            }
            try {
                bufferedReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}