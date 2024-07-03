package com.example.slovoved.navigation

sealed class Screen(val route: String) {
    data object Search : Screen("search")
    data object Definition : Screen("definition")
    data object Bookmarks : Screen("bookmarks")
}