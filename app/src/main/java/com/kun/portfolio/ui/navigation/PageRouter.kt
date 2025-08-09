package com.kun.portfolio.ui.navigation

sealed class PageRouter(val router: String) {

    data object Main: PageRouter(router = "MainPage")

}