package com.example.openmind.utils


fun numberFormatted(count: Int): String {
    return when{
        count >= 1_000_000 -> (count / 1_000_000).toString() + "m"
        count >= 1_000 -> (count / 1_000).toString() + "k"
        else -> count.toString()
    }
}