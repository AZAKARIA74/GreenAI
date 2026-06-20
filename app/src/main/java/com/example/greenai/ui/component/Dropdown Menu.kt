package com.example.greenai.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greenai.ui.theme.GreenAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    title: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        TextFiled(
            text = selectedOption,
            onTextChange = {},
            title = "soil type",

            modifier = Modifier
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            options.forEach { option ->

                DropdownMenuItem(
                    text = {
                        Text(option)
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SuggestionScreenPreview() {
    val soilTypes = listOf(
        "Clay",
        "Sandy",
        "Loamy",
        "Silty"
    )

    var selectedSoil by remember {
        mutableStateOf("")
    }
    GreenAITheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DropdownField(
                title = "Soil Type",
                options = soilTypes,
                selectedOption = selectedSoil,
                onOptionSelected = {
                    selectedSoil = it
                }
            )
        }
    }
}

