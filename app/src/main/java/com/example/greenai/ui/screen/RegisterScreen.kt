package com.example.greenai.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.greenai.presentation.viewmodel.AuthViewModel
import com.example.greenai.ui.component.Button
import com.example.greenai.ui.component.ErrorSection
import com.example.greenai.ui.component.TextFiled
import com.example.greenai.ui.state.Resource
import com.example.greenai.ui.theme.GreenAISpacing
import com.google.firebase.auth.FirebaseUser
import com.greenai.ui.theme.GreenAITheme

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    val state by viewModel.authState.collectAsState()

    LaunchedEffect(state) {
        if (state is Resource.Success) {
            onRegisterSuccess()
            viewModel.resetState()
        }
    }

    RegisterContent(
        state = state,
        onRegisterClick = {username, email, password, confirmPassword ->
            viewModel.register(username,email, password, confirmPassword)
        },
        onNavigateToLogin = onNavigateToLogin
    )
}

@Composable
fun RegisterContent(
    state: Resource<FirebaseUser>,
    onRegisterClick: (String,String, String, String) -> Unit = { _,_, _, _ -> },
    onNavigateToLogin: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isLoading = state is Resource.Loading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(GreenAISpacing.lg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CREATE ACCOUNT",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        TextFiled(
            modifier = Modifier.fillMaxWidth(),
            text = username,
            onTextChange = { username = it },
            title = "Username",
            hint = "Zakaria"
        )

        TextFiled(
            modifier = Modifier.fillMaxWidth(),
            text = email,
            onTextChange = { email = it },
            title = "Email",
            hint = "greenai@example.com"
        )

        TextFiled(
            modifier = Modifier.fillMaxWidth(),
            text = password,
            onTextChange = { password = it },
            title = "Password",
            hint = "********",
            isPassword = true
        )

        TextFiled(
            modifier = Modifier.fillMaxWidth(),
            text = confirmPassword,
            onTextChange = { confirmPassword = it },
            title = "Confirm Password",
            hint = "********",
            isPassword = true
        )

        if (state is Resource.Error) {
            ErrorSection(state.message.toString())
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = GreenAISpacing.md),
            caption = "Create account",
            isLoading = isLoading,
            enabled = username.isNotBlank() && email.isNotBlank() && password.isNotBlank() &&
                    confirmPassword.isNotBlank() && !isLoading,
            onClick = { onRegisterClick(username,email, password, confirmPassword) }
        )

        Text(
            text = "Already have an account? Sign in",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = GreenAISpacing.md)
                .clickable(onClick = onNavigateToLogin)
        )
    }
}

@Preview(name = "Light Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegisterScreenPreview() {
    GreenAITheme {
        RegisterContent(state = Resource.Idle())
    }
}