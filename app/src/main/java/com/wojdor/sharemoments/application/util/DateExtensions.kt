package com.wojdor.sharemoments.application.util

import java.util.*

val currentDate: String
    get() = Calendar.getInstance().time.toString()
