package com.example.greenai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenai.data.remote.RetrofitInstance
import com.example.greenai.data.repositories.AuthRepository
import com.example.greenai.data.repositories.RepositoryImpl
import com.example.greenai.navigation.GreenAIApp
import com.example.greenai.presentation.viewmodel.AuthViewModel
import com.example.greenai.presentation.viewmodel.ChatViewModel
import com.example.greenai.presentation.viewmodel.DiseaseViewModel
import com.example.greenai.presentation.viewmodel.SuggestionViewModel
import com.greenai.ui.theme.GreenAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = RetrofitInstance.api
        val repository = RepositoryImpl(apiService)
        val authRepository = AuthRepository()

        setContent {
            GreenAITheme {
                val authViewModel: AuthViewModel = viewModel {
                    AuthViewModel(repo = authRepository)
                }
                val diseaseViewModel: DiseaseViewModel = viewModel {
                    DiseaseViewModel(repo = repository)
                }
                val suggestionViewModel: SuggestionViewModel = viewModel {
                    SuggestionViewModel(repo = repository)
                }
                val chatbotViewModel: ChatViewModel = viewModel {
                    ChatViewModel()
                }

                GreenAIApp(
                    authViewModel = authViewModel,
                    diseaseViewModel = diseaseViewModel,
                    suggestionViewModel = suggestionViewModel,
                    chatbotViewModel = chatbotViewModel
                )
            }
        }
    }
}