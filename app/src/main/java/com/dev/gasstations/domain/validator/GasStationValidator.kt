package com.dev.gasstations.domain.validator

import java.lang.Exception

object GasStationValidator {

    fun isValidStringParams(address: String, supplier: String, type: String) : Boolean =
            address.isNotBlank() && supplier.isNotBlank() && type.isNotBlank()

    fun isValidNumericParams(count: String, cost: String) : Boolean =
            try { count.toDouble() >= 0 && cost.toDouble() >= 0 }
            catch (e: Exception) { false }

    fun isValidLitersInput(liters: String) : Boolean =
            try {
                liters.toDouble() >= 0
            } catch (e: Exception) { false }

}