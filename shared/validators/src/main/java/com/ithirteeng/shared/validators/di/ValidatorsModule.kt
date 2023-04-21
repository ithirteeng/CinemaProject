package com.ithirteeng.shared.validators.di

import com.ithirteeng.shared.validators.domain.usecase.ValidateEmailUseCase
import com.ithirteeng.shared.validators.domain.usecase.ValidatePasswordsUseCase
import com.ithirteeng.shared.validators.domain.usecase.ValidateTextFieldUseCase
import com.ithirteeng.shared.validators.domain.validator.EmailValidator
import com.ithirteeng.shared.validators.domain.validator.TextFieldValidator
import com.ithirteeng.shared.validators.domain.validator.TwoPasswordsValidator
import com.ithirteeng.shared.validators.domain.validator.Validator
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val EMAIL = "EMAIL"
private const val PASSWORDS = "PASSWORDS"
private const val TEXT_FIELD = "TEXT_FIELD"

val validatorsModule = module {
    single<Validator>(named(EMAIL)) { EmailValidator() }
    single<Validator>(named(PASSWORDS)) { TwoPasswordsValidator() }
    single<Validator>(named(TEXT_FIELD)) { TextFieldValidator() }

    factory { ValidateEmailUseCase(validator = get(named(EMAIL))) }
    factory { ValidatePasswordsUseCase(validator = get(named(PASSWORDS))) }
    factory { ValidateTextFieldUseCase(validator = get(named(TEXT_FIELD))) }
}