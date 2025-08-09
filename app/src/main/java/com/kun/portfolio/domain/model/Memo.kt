package com.kun.portfolio.domain.model

data class Memo(
    val id: Int = -1,
    val title: String,
    val content: String,
    val timestamp: Long
)
