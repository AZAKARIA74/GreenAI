package com.example.greenai.utils

object SuggestionFields {
    val cropRecommendFields = listOf(
        FieldData("Nitrogen", "0 - 200", FieldRule.Numeric(0f, 200f)),
        FieldData("Phosphorus", "0 - 150", FieldRule.Numeric(0f, 150f)),
        FieldData("Potassium", "0 - 300", FieldRule.Numeric(0f, 300f)),
        FieldData("pH", "0 - 14", FieldRule.Numeric(0f, 14f)),
        FieldData("Rainfall", "e.g. 120 mm", FieldRule.Numeric(0f, 5000f)),
        FieldData("Temperature", "-20 to 60 °C", FieldRule.Numeric(-20f, 60f)),
        FieldData("Soil Color", "e.g. Red", FieldRule.TextOnly)
    )

    val fertilizerRecommendFields = listOf(
        FieldData("Crop", "e.g. Wheat", FieldRule.TextOnly),
        FieldData("Nitrogen", "0 - 200", FieldRule.Numeric(0f, 200f)),
        FieldData("Phosphorus", "0 - 150", FieldRule.Numeric(0f, 150f)),
        FieldData("Potassium", "0 - 300", FieldRule.Numeric(0f, 300f)),
        FieldData("Temperature", "-20 to 60 °C", FieldRule.Numeric(-20f, 60f)),
        FieldData("Humidity", "0 - 100", FieldRule.Numeric(0f, 100f)),
        FieldData("pH", "0 - 14", FieldRule.Numeric(0f, 14f)),
        FieldData("Soil Color", "e.g. Red", FieldRule.TextOnly)
    )
}
sealed class FieldRule {
    data class Numeric(val min: Float, val max: Float) : FieldRule()
    object TextOnly : FieldRule()
}

data class FieldData(
    val title: String,
    val hint: String,
    val rule: FieldRule
)
