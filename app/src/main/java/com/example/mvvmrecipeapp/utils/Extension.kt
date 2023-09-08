package com.example.mvvmrecipeapp.utils

import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.core.text.HtmlCompat


fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun String?.fromHtml(): Spanned {
    val textNonNullable = this?: ""
    return Html.fromHtml(textNonNullable, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
