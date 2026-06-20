package com.example.greenai.ui.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.greenai.R
import com.example.greenai.ui.theme.GreenAIShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.greenai.presentation.viewmodel.DiseaseViewModel
import com.example.greenai.ui.theme.GreenAISpacing
import com.greenai.ui.theme.GreenAITheme


@Composable
fun UploadImageBox(
    containerColor: Color = MaterialTheme.colorScheme.background,
    onContainerColor : Color = MaterialTheme.colorScheme.onBackground,
    borderColor: Color = MaterialTheme.colorScheme.onBackground,
    imageUri: Uri? = null,
    onImageSelected: (Uri?) -> Unit = {  }
) {



    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImageSelected(uri)
    }

    Column(
        modifier = Modifier
            .padding(GreenAISpacing.cardPadding)
            .fillMaxSize(0.7f)
            .clip(GreenAIShape.card)
            .background(containerColor)
            .dashedBorder(borderColor, GreenAIShape.card)
            .clickable {
                launcher.launch("image/*")
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (imageUri == null) {

            Image(
                painter = painterResource(id = R.drawable.upload_icon),
                contentDescription = "Upload",
                modifier = Modifier.size(80.dp),
                colorFilter = ColorFilter.tint(onContainerColor)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Upload Image",
                color = onContainerColor,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Choose a plant image",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "JPG • JPEG • PNG",
                style = MaterialTheme.typography.bodySmall,
                color = onContainerColor.copy(alpha = 0.7f)
            )

        } else {

            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Selected Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UploadImageBoxPreview() {

    GreenAITheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            UploadImageBox()
        }
    }
}
