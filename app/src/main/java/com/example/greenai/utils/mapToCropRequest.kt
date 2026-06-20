package com.example.greenai.utils


import com.example.greenai.domain.model.RecommendRequest
import com.example.greenai.ui.screen.FieldData
import kotlin.collections.indexOfFirst

fun mapToCropRequest(
    values: List<String>,
    fields: List<FieldData>
): RecommendRequest {

    fun getValue(title: String): String {
        val index = fields.indexOfFirst { it.title.equals(title, ignoreCase = true) }
        return values.getOrNull(index) ?: ""
    }

    return RecommendRequest(
        nitrogen = getValue("Nitrogen").toFloatOrNull() ?: 0f,
        phosphorus = getValue("Phosphorus").toFloatOrNull() ?: 0f,
        potassium = getValue("Potassium").toFloatOrNull() ?: 0f,
        pH = getValue("pH").toFloatOrNull() ?: 0f,
        rainfall = getValue("Rainfall").toFloatOrNull() ?: 0f,
        temperature = getValue("Temperature").toFloatOrNull() ?: 0f,
        soilColor = getValue("Soil Color")
    )
}