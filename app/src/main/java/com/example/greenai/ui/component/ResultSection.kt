package com.example.greenai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenai.R
import com.example.greenai.domain.model.DiseaseResponse
import com.example.greenai.ui.screen.DiseaseScanContent
import com.example.greenai.ui.state.Resource
import com.example.greenai.ui.state.ResultState
import com.greenai.ui.theme.GreenAITheme
import kotlin.text.ifEmpty


@Composable
fun ResultSection(
    result: ResultState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Predicted Disease",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = result.disease.ifEmpty { "—" },
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Confidence",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { result.confidence },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(MaterialTheme.shapes.small),
            color =confidenceColor( result.confidence),
            trackColor =MaterialTheme.colorScheme.surfaceVariant
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "${(result.confidence * 100).toInt()}%",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = Bold),
            color = confidenceColor( result.confidence)
        )
    }
}


@Composable
fun ErrorSection(
    message: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(16.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
            painter = painterResource(R.drawable.alert_error),
            contentDescription = "alert_icon",
            modifier = Modifier.padding(4.dp).size(18.dp),
                tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
        )
            Text(
                text = "Error",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 18.sp
                ),
                color = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
            ) }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text =message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onErrorContainer
        )

        Spacer(modifier = Modifier.height(16.dp))


}
}
@Composable
fun confidenceColor(confidence: Float) : Color {
    return if (confidence > 0.85) {
        MaterialTheme.colorScheme.primary
    }
    else if (confidence >= 0.45 && confidence < 0.85 ){
        MaterialTheme.colorScheme.tertiary
    }
    else {
        MaterialTheme.colorScheme.error
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DiseaseScanScreenPreview() {
    GreenAITheme {
        Column { ResultSection(ResultState("",0.8f))
            ErrorSection("Something Went Wrong!") }

    }
}
