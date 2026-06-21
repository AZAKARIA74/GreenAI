package com.example.greenai.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.example.greenai.ui.theme.GreenAISpacing
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenai.ui.theme.GreenAIShape
import com.greenai.ui.theme.GreenAITheme

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    leadingIcon: Painter? = null,
    icon: Painter? = null,
    trailingIcon: Painter? = null,
    loading: (@Composable () -> Unit)? = null,
    isLoading: Boolean = false,
    caption: String? = null,
    hasBorder: Boolean = false,
    borderSize: Int = 1,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onContainerColor: Color = Color.White,
    enabled: Boolean = true,
    shape: Shape = GreenAIShape.buttonRounded
) {
    Row(
        modifier = modifier
            .height(GreenAISpacing.buttonHeight)
            .clip(shape)

            .background(if (enabled) containerColor else Color.Gray )
            .then(
                if (hasBorder)
                    Modifier.border(borderSize.dp, borderColor, GreenAIShape.circle)
                else Modifier
            )
            .clickable(enabled = enabled && !isLoading) { onClick() }
            .padding(
                vertical = GreenAISpacing.sm,
                horizontal = GreenAISpacing.lg
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        if (isLoading) {
            loading?.invoke() ?: CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp,
                color = onContainerColor
            )

            Spacer(Modifier.width(GreenAISpacing.md))
        }
        if (!isLoading){
        leadingIcon?.let {
            Spacer(Modifier.width(GreenAISpacing.sm))
            Image(
                painter = it,
                contentDescription = null,
                colorFilter = ColorFilter.tint(onContainerColor),
                modifier = Modifier.size(18.dp)
                    .padding(end = 4.dp)
                    .size(24.dp)
            )
        }
        }
        icon?.let {

            Image(
                painter = it,
                contentDescription = null,
                colorFilter = ColorFilter.tint(onContainerColor),
                modifier = Modifier
                    .size(24.dp)
            )
        }



        caption?.let {
            Text(
                text = it,
                color = if (enabled) onContainerColor else Color.DarkGray,

                )
        }


        trailingIcon?.let {
            Spacer(Modifier.width(GreenAISpacing.sm))
            Image(
                painter = it,
                contentDescription = null,
                colorFilter = ColorFilter.tint(onContainerColor),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FilledButtonPreview() {
    GreenAITheme {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // 🔹 Normal Button
        Button(
            onClick = {},
            caption = "Submit",
        )

        Button(
            onClick = {},
            caption = "Submit",
            enabled = false
        )

        // 🔹 With Icon
        Button(
            onClick = {},
            caption = "Upload",
        )

        // 🔹 Loading State
        Button(
            onClick = {},
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            },
            isLoading = true,
            caption = "Loading...",
        )

        // 🔹 With Border
        Button(
            onClick = {},
            caption = "Outlined",
            hasBorder = true,
            borderColor = Color.Gray,
            containerColor = Color.Transparent,
            onContainerColor = Color.Black,
        )

    }
    }
}
