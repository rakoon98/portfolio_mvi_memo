package com.kun.portfolio.ui.main.contract

sealed interface MainEffect {

    data object Saved : MainEffect
    data class Error(val e: Exception) : MainEffect

}