package com.alexfrost.recipeapp.ui.authorization

import androidx.annotation.StringRes
import androidx.core.util.PatternsCompat
import com.alexfrost.core.ui.R as CoreRes

internal enum class FieldValidation(
    @StringRes val descriptionId: Int = CoreRes.string.empty_string
) {
    None,
    Valid,
    Required(CoreRes.string.error_field_required)
}

internal enum class EmailValidation(
    @StringRes val descriptionId: Int = CoreRes.string.empty_string
) {
    None,
    Valid,
    Required(CoreRes.string.error_email_empty),
    Invalid(CoreRes.string.error_email_format)
}

internal fun validateField(field: String): FieldValidation =
    if (field.isEmpty()) FieldValidation.Required else FieldValidation.Valid

internal fun validateEmail(email: String): EmailValidation = when {
    email.isEmpty() -> EmailValidation.Required
    email.isEmailFormat().not() -> EmailValidation.Invalid
    else -> EmailValidation.Valid
}

internal fun FieldValidation.isValid(): Boolean = this == FieldValidation.Valid

internal fun EmailValidation.isValid(): Boolean = this == EmailValidation.Valid

private fun String.isEmailFormat(): Boolean = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
