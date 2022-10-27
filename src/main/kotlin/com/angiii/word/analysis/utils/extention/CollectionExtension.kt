package com.angiii.word.analysis.utils.extention

import com.angiii.word.analysis.utils.PatterUtil
import com.angiii.word.analysis.utils.PunctuationHelper
import com.angiii.word.analysis.utils.StopWordsHelper

fun Collection<String>.removeStopWords(): List<String> {
    return this.filterNot { StopWordsHelper.stopWordsSet.contains(it) }.toList()
}

fun Collection<String>.removeContainPunctuationWords(): List<String> {
    return this.filterNot { word -> run { PunctuationHelper.punctuationSet.any { word.contains(it) } } }.toList()
}

fun Collection<String>.removeNumberWords(): List<String> {
    return this.filterNot { PatterUtil.numberPatter.matcher(it).matches() }
}