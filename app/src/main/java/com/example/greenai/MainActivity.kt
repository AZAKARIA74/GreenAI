package com.example.greenai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenai.data.remote.RetrofitInstance
import com.example.greenai.data.repositories.RepositoryImpl
import com.example.greenai.presentation.viewmodel.DiseaseViewModel
import com.example.greenai.ui.screen.DiseaseScanScreen
//import com.example.greenai.ui.theme.GreenAITheme
import com.greenai.ui.theme.GreenAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = RetrofitInstance.api
        val repository = RepositoryImpl(apiService)

        setContent {
            GreenAITheme {
                    val diseaseViewModel: DiseaseViewModel = viewModel {
                        DiseaseViewModel(repo = repository)
                    }

                    DiseaseScanScreen(
                        viewModel = diseaseViewModel
                    )
            }
        }
    }
}
