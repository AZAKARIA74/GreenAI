package com.example.greenai.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import com.example.greenai.ui.component.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenai.R
import com.example.greenai.ui.theme.GreenAIShape
import com.example.greenai.ui.theme.GreenAISpacing
import com.greenai.ui.theme.GreenAITheme

enum class HomeTab {
    HOME, SCAN, SUGGEST, CHAT
}

@Composable
fun HomeScreen(
    userName: String = "Farmer",
    onQuickActionClick: (HomeTab) -> Unit = {},
    onSignOutClick: () -> Unit = {}

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeHeader(userName = userName,
            onSignOutClick = onSignOutClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = GreenAISpacing.lg),
            verticalArrangement = Arrangement.spacedBy(GreenAISpacing.lg)
        ) {
            item { QuickActionsRow(onClick = onQuickActionClick) }
            item { TipCard() }
            item { Spacer(Modifier.height(GreenAISpacing.lg)) }
        }
    }
}

@Composable
private fun HomeHeader(
    userName: String,
    onSignOutClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(GreenAISpacing.lg),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back,",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Button(
            caption = "Sign Out",
            containerColor = MaterialTheme.colorScheme.background,
            onContainerColor = MaterialTheme.colorScheme.onBackground,
            hasBorder = true,
            onClick = onSignOutClick
        )

    }
}

@Composable
private fun QuickActionsRow(onClick: (HomeTab) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(GreenAISpacing.sm)
    ) {
        QuickActionCard(
            modifier = Modifier.weight(1f),
            icon = painterResource(R.drawable.camera_icon),
            label = "Disease Scan",
            onClick = { onClick(HomeTab.SCAN) }
        )
        QuickActionCard(
            modifier = Modifier.weight(1f),
            icon = painterResource(R.drawable.leaf),
            label = "Crop Advisor",
            onClick = { onClick(HomeTab.SUGGEST) }
        )
        QuickActionCard(
            modifier = Modifier.weight(1f),
            icon = painterResource(R.drawable.chat_bubble),
            label = "Chatbot",
            onClick = { onClick(HomeTab.CHAT) }
        )
    }
}

@Composable
private fun QuickActionCard(
    modifier: Modifier = Modifier,
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.outline, GreenAIShape.card)
            .clip(GreenAIShape.card)
            .background(MaterialTheme.colorScheme.background)
            .clickable(onClick = onClick)
            .padding(GreenAISpacing.md),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GreenAISpacing.xs)
    ) {
        Box(
            modifier = Modifier
                .size(GreenAISpacing.iconXl)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = icon,
                contentDescription = label,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(GreenAISpacing.iconMd)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TipCard() {

    val tips = listOf(
        "Water your crops early morning to reduce evaporation loss.",
        "Rotate your crops every season to maintain soil health.",
        "Check under the leaves regularly for early signs of pests.",
        "Use organic mulch to retain soil moisture and suppress weeds.",
        "Don't over-fertilize; too much nitrogen can burn plant roots.",
        "Prune dead or diseased branches to encourage healthy new growth."
    )
    val randomTip = remember { tips.random() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, GreenAIShape.card)
            .clip(GreenAIShape.card)
            .background(MaterialTheme.colorScheme.background)
            .padding(GreenAISpacing.lg)
    ) {
        Text(
            text = "Tip of the day",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(GreenAISpacing.xxs))
        Text(
            text = randomTip,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    GreenAITheme {
        HomeScreen()
    }
}