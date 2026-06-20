package com.example.greenai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenai.data.remote.RetrofitInstance
import com.example.greenai.data.repositories.RepositoryImpl
import com.example.greenai.navigation.GreenAIApp
import com.example.greenai.presentation.viewmodel.DiseaseViewModel
import com.example.greenai.presentation.viewmodel.SuggestionViewModel
import com.example.greenai.ui.screen.DiseaseScanScreen
import com.example.greenai.ui.screen.SuggestionScreen

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
                    val suggestionViewModel: SuggestionViewModel = viewModel {
                         SuggestionViewModel(repo = repository)
                    }

                GreenAIApp(
                    diseaseViewModel = diseaseViewModel,
                    suggestionViewModel = suggestionViewModel
                )
            }
        }
    }
}
