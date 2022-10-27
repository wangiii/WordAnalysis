package com.angiii.word.analysis.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@Configuration
class WordAnalysisThreadPoolConfig {
    val tag = AtomicInteger(0)

    @Bean
    fun wordAnalysisThreadPool(): ThreadPoolExecutor {
        return ThreadPoolExecutor(
            4, 8, 30, TimeUnit.SECONDS, LinkedBlockingQueue()
        ) { r: Runnable ->
            val thread = Thread(r)
            thread.name = "word-analysis-thread-${tag.getAndIncrement()}"
            thread
        }
    }
}