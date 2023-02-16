package com.aguadaserra.app.util.validation

interface PasswordValidatorInterface {
    fun isPasswordValid(password: String?): Boolean
}
