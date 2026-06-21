package com.example.greenai.ui.component


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenai.ui.theme.GreenAISpacing
import com.greenai.ui.theme.GreenAITheme

@Composable
fun TextFiled(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    focusBorderColor: Color  = MaterialTheme.colorScheme.outline,
    title: String? = null,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    hint: String? = null,
    hintColor: Color = MaterialTheme.colorScheme.onBackground , //MaterialTheme.colorScheme.onSecondaryContainer
    maxLine: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    isError: Boolean = false,
    errorMessage: String? = null,
    shape: Shape = MaterialTheme.shapes.small,
    backgroundColor: Color = MaterialTheme.colorScheme.background
){
    var isFocussed: Boolean by remember { mutableStateOf(false) }
    val borderColor: Color = when {
        isError   -> MaterialTheme.colorScheme.error
        else      -> focusBorderColor
    }
    val borderSize: Dp = if (!isFocussed || isError) 2.dp else 1.dp
    Column (
        modifier = modifier
            .padding(GreenAISpacing.md)
    ){
        title?.let {
            BasicText(
                text = it,
                style = titleStyle.copy(
                    color = if (isError) MaterialTheme.colorScheme.error else titleColor
                ),
                overflow = overflow,
                modifier = modifier.padding(bottom = GreenAISpacing.xxs)
            )


        }

        Row (
            modifier = Modifier
                .fillMaxWidth(1f)
                .heightIn(
                    min = 45.dp,
                    max = 150.dp
                )
                .clip(shape)
                .background(backgroundColor)
                .border(
                    width = borderSize,
                    shape = shape,
                    color = borderColor
                )
                .padding(horizontal = GreenAISpacing.md)
                .onFocusChanged {
                    isFocussed = !isFocussed
                }
                ,


            verticalAlignment = Alignment.CenterVertically,
        ){

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ){
                if(text.isEmpty())
                {
                    hint?.let {
                        BasicText(
                            text = it,
                            style = titleStyle.copy(
                                color = hintColor.copy(alpha = 0.5f)
                            )
                        )
                    }

                }

                BasicTextField(
                    value = text,
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 4.dp),
                    onValueChange = onTextChange,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    textStyle = titleStyle.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    maxLines = maxLine,

                )

            }

        }
        if (isError) {
            BasicText(
                text = errorMessage?:"",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier.padding(
                    top   = GreenAISpacing.xxs,
                    start = GreenAISpacing.xxs
                )
            )
        }
    }

}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TextFieldPreview(){
    var text by remember { mutableStateOf("") }
    GreenAITheme {
        TextFiled(
            text = text,
            onTextChange = {text = it},
            title = "Nitrogen",
            hint = "40hg"
        )
    }

}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TextFieldPreview2() {
    var text by remember { mutableStateOf("") }
    GreenAITheme {
        Column {

            TextFiled(
                text = text,
                onTextChange = { text = it },
                title = "Nitrogen",
                hint = "0 – 100 %"
            )


            TextFiled(
                text = "150",
                onTextChange = {},
                title = "pH",
                hint = "0 – 14",
                isError = true,
                errorMessage = "Value must be between 0 to 14"
            )
        }
    }
}