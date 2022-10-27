package com.angiii.word.analysis.application.service.impl

import com.angiii.word.analysis.application.dto.WordFrequencyDto
import com.angiii.word.analysis.application.service.WordAnalysisService
import com.angiii.word.analysis.jieba.JiebaSegmenter
import com.angiii.word.analysis.jieba.WordDictionary
import com.angiii.word.analysis.utils.PunctuationHelper
import com.angiii.word.analysis.utils.StopWordsHelper
import com.angiii.word.analysis.utils.extention.removeBlankWords
import com.angiii.word.analysis.utils.extention.removeContainPunctuationWords
import com.angiii.word.analysis.utils.extention.removeNumberWords
import com.angiii.word.analysis.utils.extention.removeStopWords
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadPoolExecutor

/**
 * 分词服务接口层实现
 *
 * @author wangchang on 2022/10/27
 */
@Service
class WordAnalysisServiceImpl: WordAnalysisService {

    @Autowired
    private lateinit var segmenter: JiebaSegmenter

    @Autowired
    private lateinit var wordAnalysisThreadPool: ThreadPoolExecutor

    @PostConstruct
    fun init() {
        StopWordsHelper.loadStopWords()
        PunctuationHelper.loadStopWords()
        WordDictionary.getInstance().loadUserDict("/sougou.dict")
    }

    override fun segment(sentences: List<String>): List<String> {
        return segmentSentences(sentences).distinct()
    }

    override fun wordFrequency(sentences: List<String>, topCount: Int): List<WordFrequencyDto> {
        return segmentSentences(sentences)
            .groupBy { it }
            .map { WordFrequencyDto(it.key, it.value.size) }
            .sortedByDescending { it.frequency }
            .take(topCount)
            .toList()
    }

    private fun segmentSentences(sentences: List<String>): List<String> {
        return sentences
            .map { wordAnalysisThreadPool.submit<List<String>> { segmentSingleSentence(it) } }
            .map { it.get() }
            .flatten()
    }

    private fun segmentSingleSentence(sentence: String): List<String> {
        return segmenter.sentenceProcess(sentence.removeBlankWords())
            .removeStopWords()
            .removeNumberWords()
            .removeContainPunctuationWords()
    }
}