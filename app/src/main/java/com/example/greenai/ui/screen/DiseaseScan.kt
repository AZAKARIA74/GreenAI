package com.example.greenai.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenai.R
import com.example.greenai.domain.model.DiseaseResponse
import com.example.greenai.presentation.viewmodel.DiseaseViewModel
import com.example.greenai.ui.component.Button
import com.example.greenai.ui.component.ErrorSection
import com.example.greenai.ui.component.MyTopAppBar
import com.example.greenai.ui.component.ResultSection
import com.example.greenai.ui.component.UploadImageBox
import com.example.greenai.ui.state.Resource
import com.example.greenai.ui.state.DiseaseResultState
import com.example.greenai.ui.theme.GreenAIShape
import com.example.greenai.ui.theme.GreenAISpacing
import com.example.greenai.utils.formatDiseaseName
import com.example.greenai.utils.uriToMultipart
import com.greenai.ui.theme.GreenAITheme
import java.io.File

@Composable
fun DiseaseScanScreen(
    viewModel: DiseaseViewModel = viewModel() ,
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    val img = viewModel.imageUri
    val context = LocalContext.current

    DiseaseScanContent(
        state = state,
        onPredictClick = {uri ->
            uri?.let {
                val imgPart = uriToMultipart(context,it)
                viewModel.predictDisease(imgPart)
            }
        },
        imageUri = img,
        onImageSelected = viewModel::updateImageUri,
        onBackClick = onBackClick
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DiseaseScanContent(
    state: Resource<DiseaseResponse>,
    onPredictClick: (uri: Uri?) -> Unit,
    imageUri: Uri? = null,
    onImageSelected: (Uri?) -> Unit,
    onBackClick: () -> Unit = {},

) {

    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar("Disease Scan", painterResource(R.drawable.outline_arrow_back_24), onBackClick = onBackClick)}
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UploadContainer(
                imageUri = imageUri,
                onImageSelected = onImageSelected
            )
            Spacer(modifier = Modifier.height(8.dp))

            when (state) {
                is Resource.Idle->{
                    isLoading = false
                }
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {
                    isLoading = false
                    val diseaseName = formatDiseaseName(
                        state.data?.prediction ?: "Unknown"
                    )
                    ResultSection(
                        result = DiseaseResultState(
                            disease = diseaseName,
                            confidence = state.data?.confidence ?: 0f
                        )
                    )

                }
                is Resource.Error -> {
                    isLoading = false
                    ErrorSection(state.message.toString())
                }

            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    onPredictClick(imageUri)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(GreenAISpacing.md)
                    .clip(GreenAIShape.circle),
                caption = "Scan",
                isLoading = isLoading,
                enabled = imageUri != null && !isLoading
            )
        }
    }
}

@Composable
fun UploadContainer(
    imageUri: Uri?,
    onImageSelected: (Uri?) -> Unit
){
    val context = LocalContext.current

    val tempPhotoUri = remember {
        val file = File(context.cacheDir, "temp_photo_${System.currentTimeMillis()}.jpg")
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            onImageSelected(tempPhotoUri)
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            cameraLauncher.launch(tempPhotoUri)
        }
    }

    Column(
        modifier = Modifier
            .height(360.dp)
            .padding(GreenAISpacing.lg)
            .fillMaxWidth()
            .border(1.dp,MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f), shape = GreenAIShape.card)
            .clip(shape = GreenAIShape.card)
            .background(color = MaterialTheme.colorScheme.background)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {

        UploadImageBox(imageUri = imageUri, onImageSelected = onImageSelected)

        Button(
            onClick = {
                when {
                    ContextCompat.checkSelfPermission(
                        context, Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        cameraLauncher.launch(tempPhotoUri)
                    }
                    else -> {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }

            },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth().padding(GreenAISpacing.md).clip(GreenAIShape.circle),
            caption = "Take a Photo",
            leadingIcon = painterResource(R.drawable.camera_icon)
        )

    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DiseaseScanScreenPreview() {
    GreenAITheme {
        DiseaseScanContent(state = Resource.Error("Something Went Wrong!"),
            onPredictClick = {}, onImageSelected = {})
    }
}