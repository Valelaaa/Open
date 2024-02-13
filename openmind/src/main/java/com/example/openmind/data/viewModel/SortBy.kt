package com.example.openmind.data.viewModel

import android.content.Context
import androidx.annotation.StringRes
import com.example.openmind.R

enum class SortBy(@StringRes private val resource: Int) {
    ASC(R.string.ask),
    DESC(R.string.desk);

    fun getText(context: Context): String = context.getString(resource)
}