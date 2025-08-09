package com.kun.portfolio.ui.main.contract

import com.kun.portfolio.domain.model.Memo

data class MainUiState(
    val items: List<Memo> = emptyList(),
    var isLoading: Boolean = false
)
