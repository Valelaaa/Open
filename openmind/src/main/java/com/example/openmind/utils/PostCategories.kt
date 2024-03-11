package com.example.openmind.utils

enum class PostCategories(private val string:String) {
    FEATURE("feature"), BUG("bug");
    fun getStringValue(): String {
        return string
    }
}
