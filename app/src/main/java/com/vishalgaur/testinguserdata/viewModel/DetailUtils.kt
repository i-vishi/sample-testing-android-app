package com.vishalgaur.testinguserdata.viewModel

import android.util.Patterns
import java.util.regex.Pattern

internal fun isEmailValid(email: String): Boolean {
    val EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return if (email.isEmpty()) {
        false
    } else {
        EMAIL_PATTERN.matcher(email).matches()
    }
}

internal fun isPhoneValid(phone: String): Boolean {
    val PHONE_PATTERN = Pattern.compile("^[6-9]\\d{9}\$")
    return if (phone.isEmpty()) {
        false
    } else {
        PHONE_PATTERN.matcher(phone).matches()
    }
}