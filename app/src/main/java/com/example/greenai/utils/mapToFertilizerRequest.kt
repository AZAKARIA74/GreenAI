package com.example.greenai.utils

import com.example.greenai.domain.model.FertilizerRequest
import kotlin.collections.indexOfFirst

fun mapToFertilizerRequest(
    values: List<String>,
    fields: List<FieldData>
): FertilizerRequest {

    fun getValue(title: String): String {
        val index = fields.indexOfFirst { it.title.equals(title, ignoreCase = true) }
        return values.getOrNull(index) ?: ""
    }

    return FertilizerRequest(
        cropType = getValue("Crop"),
        nitrogen = getValue("Nitrogen").toFloatOrNull() ?: 0f,
        phosphorus = getValue("Phosphorus").toFloatOrNull() ?: 0f,
        potassium = getValue("Potassium").toFloatOrNull() ?: 0f,
        temperature = getValue("Temperature").toFloatOrNull() ?: 0f,
        humidity = getValue("Humidity").toFloatOrNull() ?: 0f,
        pH = getValue("pH").toFloatOrNull() ?: 0f,
        soilType = getValue("Soil Color")
    )
}