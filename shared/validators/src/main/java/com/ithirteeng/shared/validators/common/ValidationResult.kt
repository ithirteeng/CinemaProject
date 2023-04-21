package com.ithirteeng.shared.validators.common

import com.ithirteeng.component.design.R


enum class ValidationResult(val errorStringId: Int) {
    EMPTY_ERROR(R.string.empty_error),
    TWO_PASSWORDS_ERROR(R.string.two_passwords_error),
    EMAIL_ERROR(R.string.email_error),
    OK(R.string.ok);
}