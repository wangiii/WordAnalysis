package com.angiii.word.analysis

import com.angiii.word.analysis.application.service.WordAnalysisService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WordAnalysisApplicationTests {

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

}
