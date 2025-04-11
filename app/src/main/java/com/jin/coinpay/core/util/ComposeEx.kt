package com.jin.coinpay.core.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle

/**
 * Appends text with a link and a specific style to the AnnotatedString.Builder.
 *
 * @param spanStyle The style to apply to the text.
 * @param text The text to append.
 * @param url The URL to link the text to.
 */
fun AnnotatedString.Builder.appendLinkAndStyle(spanStyle: SpanStyle, text: String, url: String) {
    withLink(LinkAnnotation.Url(url)) {
        withStyle(style = spanStyle) {
            append(text)
        }
    }
}