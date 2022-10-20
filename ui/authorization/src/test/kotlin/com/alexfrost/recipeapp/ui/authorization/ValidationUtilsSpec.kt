package com.alexfrost.recipeapp.ui.authorization

import com.alexfrost.recipeapp.core.testing.convertToRowArray
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.blocking.forAll
import io.kotest.matchers.shouldBe

class ValidationUtilsSpec : ShouldSpec({
    context("tryValidateField(X)") {
        should("return Valid if field is non empty") {
            val samples = convertToRowArray(
                listOf("sample$", "/.", "123")
            )

            forAll(*samples) { field ->
                validateField(field) shouldBe FieldValidation.Valid
            }
        }

        should("return Required if field is empty") {
            validateField("") shouldBe FieldValidation.Required
        }
    }

    context("tryValidateEmail(X)") {
        should("return Valid if field satisfies email format") {
            val samples = convertToRowArray(
                listOf("sample@sample.com", "s@s.s", "example._%+@example.ex")
            )

            forAll(*samples) { email ->
                validateEmail(email) shouldBe EmailValidation.Valid
            }
        }

        should("return Invalid if fields doesn't satisfy email format") {
            val samples = convertToRowArray(
                listOf("sample/@sample.com", "s`@s.s", "example.ex.ex")
            )

            forAll(*samples) { email ->
                validateEmail(email) shouldBe EmailValidation.Invalid
            }
        }

        should("return Required if field is empty") {
            validateEmail("") shouldBe EmailValidation.Required
        }
    }

    context("tryCheckIfFieldIsValid(X)") {
        should("return true if `fieldValidation` is set to Valid") {
            val validationResult = FieldValidation.Valid

            validationResult.isValid() shouldBe true
        }

        should("return false if `fieldValidation` is not set to Valid") {
            val samples = convertToRowArray(
                listOf(FieldValidation.None, FieldValidation.Required)
            )

            forAll(*samples) { validation ->
                validation.isValid() shouldBe false
            }
        }
    }

    context("tryCheckIfEmailIsValid(X)") {
        should("return true if `emailValidation` is set to Valid") {
            val validationResult = EmailValidation.Valid

            validationResult.isValid() shouldBe true
        }

        should("return false if `emailValidation` is not set to Valid") {
            val samples = convertToRowArray(
                listOf(EmailValidation.None, EmailValidation.Required)
            )

            forAll(*samples) { validation ->
                validation.isValid() shouldBe false
            }
        }
    }
})
