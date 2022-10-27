package com.angiii.word.analysis.application.service

import com.angiii.word.analysis.application.dto.WordFrequencyDto

/**
 * 分词服务接口层
 *
 * @author wangchang on 2022/10/27
 */
interface WordAnalysisService {

    /**
     * 对句子进行分词
     * @param sentences 要分词的句子
     * @return 分词结果（将所有句子分词后，去重）
     */
    fun segment(sentences: List<String>): List<String>

    /**
     * 对句子进行词频率分析，由高到底排序
     * @param sentences 要分析的句子
     * @param topCount 取最前面的几个单词
     * @return 词频结果
     */
    fun wordFrequency(sentences: List<String>, topCount: Int): List<WordFrequencyDto>

}