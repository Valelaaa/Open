package com.example.openmind.data.viewModel

enum class Categories(private val string:String) {
    FEATURE("feature"), BUG("bug");
    fun getStringValue(): String {
        return string
    }
}
