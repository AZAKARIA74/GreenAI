package com.example.greenai.utils


fun validateField(value: String, field: FieldData): String? {
    if (value.isBlank()) return "${field.title} is required"

    return when (val rule = field.rule) {
        is FieldRule.TextOnly -> {
            if ( value.any { it.isDigit() }){
                return "Enter a valid value"
            }
            return null
        }

        is FieldRule.Numeric -> {
            val number = value.toFloatOrNull()
                ?: return "Enter a valid number"
            if (number < rule.min || number > rule.max)
                "Must be between ${rule.min.toInt()} and ${rule.max.toInt()}"
            else null
        }
    }
}