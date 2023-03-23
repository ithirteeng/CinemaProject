package com.ithirteeng.shared.validators.domain.validator

import com.ithirteeng.shared.validators.common.ValidationResult

interface Validator {
    fun validate(string: String): ValidationResult
}