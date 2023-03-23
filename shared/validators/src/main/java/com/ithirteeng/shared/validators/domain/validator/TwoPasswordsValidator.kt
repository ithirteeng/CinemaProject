package com.ithirteeng.shared.validators.domain.validator

import com.ithirteeng.shared.validators.common.ValidationResult

class TwoPasswordsValidator : Validator {
    override fun validate(string: String): ValidationResult {
        val passwords = string.split("\n")
        return if (passwords[0] == passwords[1]) {
            ValidationResult.OK
        } else {
            ValidationResult.TWO_PASSWORDS_ERROR
        }
    }
}