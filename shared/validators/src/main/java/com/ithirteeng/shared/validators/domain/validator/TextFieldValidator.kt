package com.ithirteeng.shared.validators.domain.validator

import com.ithirteeng.shared.validators.common.ValidationResult

class TextFieldValidator : Validator {
    override fun validate(string: String): ValidationResult {
        return if (string.isEmpty()) {
            ValidationResult.EMPTY_ERROR
        } else {
            ValidationResult.OK
        }
    }
}