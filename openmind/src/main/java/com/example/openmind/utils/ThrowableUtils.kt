package com.example.openmind.utils

import java.io.IOException
import java.util.concurrent.TimeoutException

fun Throwable.isNetworkConnectionReason():Boolean =
    (this is IOException) or (this is TimeoutException)