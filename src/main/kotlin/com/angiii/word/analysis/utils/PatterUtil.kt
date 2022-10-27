package com.angiii.word.analysis.utils

import java.util.regex.Pattern

object PatterUtil {
    /**
     * \r: 匹配一个回车符
     * \n: 换行符匹配;
     * \\s:匹配任何空白字符，包括空格、制表符、换页符等
     */
    val blankPatter: Pattern = Pattern.compile("\r|\n|\\s")

    /**
     * 匹配数字，包括正数、负数、整数、小数
     */
    val numberPatter: Pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*")
}