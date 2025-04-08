package com.jin.coinpay.core.util

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String, isLongToast: Boolean = false) {
    val length = if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, msg, length).show()
}