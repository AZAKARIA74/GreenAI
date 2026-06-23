package com.example.greenai.utils

fun formatDiseaseName(rawName: String): String {

    if (rawName.contains("___")) {
        val parts = rawName.split("___")
        val plant = parts[0].replace("_", " ").trim()
        val disease = parts[1].replace("_", " ").trim()


        if (disease.equals("healthy", ignoreCase = true)) {
            return "$plant (Healthy)"
        }

        val formattedDisease = disease.split(" ").joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }

        return "$plant: $formattedDisease"
    }

    return rawName.replace("_", " ")
}