package com.example.openmind.domain.model.category

enum class PostCategories(private val string: String) {
    FEATURE("feature"), BUG("bug");

    fun getStringValue(): String {
        return string
    }
}
