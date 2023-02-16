package com.aguadaserra.app.util.validation

interface CPFValidatorInterface {
    fun isCPFValid(cpf: String?): Boolean
}

class CPFValidator : CPFValidatorInterface {

    override fun isCPFValid(cpf: String?): Boolean {

        val cleanCpf = cpf?.replace(".", "")?.replace("-", "") ?: return false

        val zeroInt = '0'.toInt()

        val digits = cleanCpf.map {
            if (it.isDigit()) it.toInt() - zeroInt
            else return false
        }

        if (digits.size != 11)
            return false

        //Verifica se todos os numeros sao iguais
        if (digits.toSet().size == 1)
            return false

        var first9Sum = 0

        first9Sum += digits[0] * 10
        first9Sum += digits[1] * 9
        first9Sum += digits[2] * 8
        first9Sum += digits[3] * 7
        first9Sum += digits[4] * 6
        first9Sum += digits[5] * 5
        first9Sum += digits[6] * 4
        first9Sum += digits[7] * 3
        first9Sum += digits[8] * 2

        val verif1 = (first9Sum * 10) % 11 % 10

        if (verif1 != digits[9]) {
            return false
        }

        first9Sum = digits[0] * 11
        first9Sum += digits[1] * 10
        first9Sum += digits[2] * 9
        first9Sum += digits[3] * 8
        first9Sum += digits[4] * 7
        first9Sum += digits[5] * 6
        first9Sum += digits[6] * 5
        first9Sum += digits[7] * 4
        first9Sum += digits[8] * 3
        first9Sum += digits[9] * 2

        val verif2 = (first9Sum * 10) % 11 % 10

        if (verif2 != digits[10]) {
            return false
        }

        return true
    }

}