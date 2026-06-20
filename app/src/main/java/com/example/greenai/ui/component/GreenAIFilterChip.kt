package com.example.greenai.ui.component

import android.R.attr.label
import android.R.attr.onClick
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.greenai.ui.theme.GreenAIShape

@Composable
fun GreenAIFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = GreenAIShape.circle,
    colors: SelectableChipColors = FilterChipDefaults.filterChipColors(
        containerColor = Color.LightGray.copy(alpha = 0.5f),
        labelColor = Color.Black,
        iconColor = Color.DarkGray,
        disabledContainerColor =  Color.Gray,
        disabledLabelColor = Color.DarkGray,
        selectedContainerColor = MaterialTheme.colorScheme.background,
        selectedLabelColor = MaterialTheme.colorScheme.onBackground,

    ),
    elevation: SelectableChipElevation? = FilterChipDefaults.filterChipElevation(),
    border: BorderStroke? = FilterChipDefaults.filterChipBorder(
        enabled,
        selected,
        selectedBorderColor = MaterialTheme.colorScheme.primary,
        borderColor = Color.Transparent,
        selectedBorderWidth = 2.dp,
        ),
    interactionSource: MutableInteractionSource? = null
){


    FilterChip(
        selected =selected,
        onClick = onClick,
        label = label,
        modifier = modifier.height(40.dp),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        interactionSource = interactionSource
    )

}