package com.aguadaserra.app.util.validation

interface LoginValidatorInterface {
    fun isEmailValid(email: String?): Boolean
    fun isPasswordValid(password: String?): Boolean
}

class LoginValidator : LoginValidatorInterface {

    override fun isEmailValid(email: String?): Boolean {
        return email?.isNotBlank() == true && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun isPasswordValid(password: String?) = (password?.length ?: 0) > 3
}