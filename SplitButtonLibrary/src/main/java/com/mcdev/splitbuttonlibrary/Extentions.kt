package com.mcdev.splitbuttonlibrary

import android.icu.text.CaseMap
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

fun String.parseSpannable( color: Int): SpannableString {
    val spanString = SpannableString(this)
    spanString.setSpan(
        ForegroundColorSpan(color),
        0,
        spanString.length,
        0
    )
    return spanString
}