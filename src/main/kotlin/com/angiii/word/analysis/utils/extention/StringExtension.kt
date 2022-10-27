package com.angiii.word.analysis.utils.extention

import com.angiii.word.analysis.utils.PatterUtil

fun String.removeBlankWords(): String {
    return PatterUtil.blankPatter.matcher(this).replaceAll("")
}
