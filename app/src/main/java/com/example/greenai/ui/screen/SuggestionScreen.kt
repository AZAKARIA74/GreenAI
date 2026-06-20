package com.example.greenai.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenai.R
import com.example.greenai.domain.model.RecommendResponse
import com.example.greenai.ui.component.Button
import com.example.greenai.ui.component.GreenAIFilterChip
import com.example.greenai.ui.component.MyTopAppBar
import com.example.greenai.ui.component.SuggestionResultsSection
import com.example.greenai.ui.component.TextFiled
import com.example.greenai.utils.validateField
import com.greenai.ui.theme.GreenAITheme
import kotlin.collections.lastIndex


enum class SuggestionMode {
    FERTILIZER,
    CROP
}

@Composable
fun SuggestionScreen() {

    var selectedMode by remember {
        mutableStateOf(SuggestionMode.FERTILIZER)
    }

    val suggestionFields = listOf(
        FieldData("Nitrogen", "0 - 200", FieldRule.Numeric(0f, 200f)),
        FieldData("Phosphorus", "0 - 150", FieldRule.Numeric(0f, 150f)),
        FieldData("Potassium", "0 - 300", FieldRule.Numeric(0f, 300f)),
        FieldData("pH", "0 - 14", FieldRule.Numeric(0f, 14f)),
        FieldData("Rainfall", "e.g. 120 mm", FieldRule.Numeric(0f, 5000f)),
        FieldData("Temperature", "-20 to 60 °C", FieldRule.Numeric(-20f, 60f)),
        FieldData("Soil Color", "e.g. Red", FieldRule.TextOnly)
    )

    val compatibilityFields = listOf(
        FieldData("Crop", "e.g. Wheat", FieldRule.TextOnly),
        FieldData("Nitrogen", "0 - 200", FieldRule.Numeric(0f, 200f)),
        FieldData("Phosphorus", "0 - 150", FieldRule.Numeric(0f, 150f)),
        FieldData("Potassium", "0 - 300", FieldRule.Numeric(0f, 300f)),
        FieldData("Temperature", "-20 to 60 °C", FieldRule.Numeric(-20f, 60f)),
        FieldData("Humidity", "0 - 100", FieldRule.Numeric(0f, 100f)),
        FieldData("pH", "0 - 14", FieldRule.Numeric(0f, 14f)),
        FieldData("Soil Color", "e.g. Red", FieldRule.TextOnly)
    )

    val currentFields =
        if (selectedMode == SuggestionMode.CROP)
            suggestionFields
        else
            compatibilityFields

    val values = remember(selectedMode) {
        List(currentFields.size) { "" }.toMutableStateList()
    }

    val errors = remember(selectedMode) {
        List<String?>(currentFields.size) { null }.toMutableStateList()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                stringResource(R.string.suggest_screen),
                painterResource(R.drawable.outline_arrow_back_24)
            )
        }
    ) { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            item(span = { GridItemSpan(2) }) {

                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        GreenAIFilterChip(
                            selected = selectedMode == SuggestionMode.FERTILIZER,
                            onClick = {
                                selectedMode = SuggestionMode.FERTILIZER
                            },
                            label = {
                                Text("Recommend Fertilizer")
                            }
                        )

                        GreenAIFilterChip(
                            selected = selectedMode == SuggestionMode.CROP,
                            onClick = {
                                selectedMode = SuggestionMode.CROP
                            },
                            label = {
                                Text("Recommend Crop")
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text =
                            if (selectedMode == SuggestionMode.FERTILIZER)
                                "Fertilizer Recommendation"
                            else
                                "Crop Recommendation",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text =
                            if (selectedMode == SuggestionMode.FERTILIZER)
                                "Enter soil and crop data"
                            else
                                "Enter soil and climate data",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            items(
                count = currentFields.size,
                key = { index -> "${selectedMode.name}_${currentFields[index].title}" },
                span = { index ->
                    if (currentFields.size %2 != 0 && index == currentFields.lastIndex) {
                        GridItemSpan(2)
                    } else {
                        GridItemSpan(1)
                    }
                }
            ) { index ->

                TextFiled(
                    text = values[index],
                    onTextChange = {
                        values[index] = it
                        errors[index] =
                            validateField(it, currentFields[index])
                    },
                    title = currentFields[index].title,
                    hint = currentFields[index].hint,
                    isError = errors[index] != null,
                    errorMessage = errors[index]
                )
            }

            item(span = { GridItemSpan(2) }) {

                val hasErrors = errors.any { it != null }
                val hasEmptyValues = values.any { it.isBlank() }

                Button(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                    onClick = {},
                    enabled = !hasErrors && !hasEmptyValues,
                    caption = "Recommend"
                )
            }

            item(span = { GridItemSpan(2) }) {

                SuggestionResultsSection(
                    if (selectedMode == SuggestionMode.CROP)
                        RecommendResponse(
                            crop = "Wheat",
                            fertilizer = "Urea"
                        )
                    else
                        RecommendResponse(
                            crop = "",
                            fertilizer = "Urea"
                        )
                )
            }
        }
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SuggestionScreenPreview() {
    GreenAITheme {
        SuggestionScreen()
    }
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




