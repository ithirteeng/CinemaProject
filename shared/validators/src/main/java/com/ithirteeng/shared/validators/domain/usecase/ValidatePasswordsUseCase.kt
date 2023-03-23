package com.ithirteeng.shared.validators.domain.usecase

import com.ithirteeng.shared.validators.common.ValidationResult
import com.ithirteeng.shared.validators.domain.validator.Validator

class ValidatePasswordsUseCase(
    private val validator: Validator
) {
    operator fun invoke(string: String): ValidationResult =
        validator.validate(string)
}