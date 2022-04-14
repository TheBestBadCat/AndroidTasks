package com.stanislavkorneev.korneevapp.calculator

import kotlin.math.pow

class Calculator {

    fun multiply(multiplicand: Double, multiplier: Double): Double =
        multiplicand * multiplier

    fun divide(dividend: Double, divisor: Double): Double {
        if (divisor == 0.0)
            throw IllegalArgumentException("Divisor cannot be 0")
        return dividend / divisor
    }

    fun pow(base: Double, degree: Double): Double =
         base.pow(degree)

    fun multipurpose(firstNumber: Double, secondNumber: Double, action: String): Double =
        when(action) {
            "multiply" -> multiply(firstNumber, secondNumber)
            "divide" -> divide(firstNumber, secondNumber)
            "pow" -> pow(firstNumber, secondNumber)
            else -> throw IllegalArgumentException("action is not supported")
        }

}