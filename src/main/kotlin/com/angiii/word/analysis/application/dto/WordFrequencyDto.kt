package com.angiii.word.analysis.application.dto

import java.io.Serializable

/**
 * 词频DTO
 *
 * @author wangchang on 2022/10/27
 */
data class WordFrequencyDto(
 var word: String = "",
 var frequency: Int = 0
): Serializable