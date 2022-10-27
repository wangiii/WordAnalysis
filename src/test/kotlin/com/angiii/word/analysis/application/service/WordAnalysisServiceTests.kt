package com.angiii.word.analysis.application.service

import com.alibaba.excel.EasyExcel
import com.alibaba.excel.cache.MapCache
import com.alibaba.excel.read.listener.PageReadListener
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * 分词服务接口层
 * @author wangchang on 2022/10/27
 */
@SpringBootTest
class WordAnalysisServiceTests {

    @Autowired
    private lateinit var wordAnalysisService: WordAnalysisService

    @Test
    fun segment() {
        val segment = wordAnalysisService.segment(listOf("设定有效目标", "对话而非对抗"))
        println(segment.toString())
    }

    @Test
    fun wordFrequency() {
        val wordFrequencyDtoList = wordAnalysisService.wordFrequency(listOf("有效目标", "目标", "设定有效目标", "设定有效目标", "对话而非对抗"), 80)
        println(wordFrequencyDtoList.toString())
    }

    @Test
    fun getWordFrequency() {
        val fileName = "/Users/wangchang/Downloads/Q3期望的改善措施-作业部.xlsx"
        val outFileName = "/Users/wangchang/Downloads/Q3期望的改善措施-作业部词频new.xlsx"
        val sentences = mutableListOf<String>()
        EasyExcel.read(fileName, Data::class.java, PageReadListener<Data> { dataList: List<Data> ->
            for (data in dataList) {
                sentences.add(data.word)
            }
        })
            .readCache(MapCache())
            .sheet()
            .doRead()

        println(wordAnalysisService.wordFrequency(sentences, 80))
        val wordFrequency = wordAnalysisService.wordFrequency(sentences, 80)
        EasyExcel.write(outFileName, OutData::class.java).sheet("词频").doWrite(wordFrequency)
    }

}

class Data() {
    var word: String = ""
}

class OutData() {
    var word: String = ""
    var frequency: Int = 0
}