package com.example.greenai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenai.data.repositories.AuthRepository
import com.example.greenai.ui.state.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: AuthRepository
) : ViewModel() {

    private val _loginState =
        MutableStateFlow<Resource<FirebaseUser>>(Resource.Idle())
    val loginState = _loginState.asStateFlow()

    private val _registerState =
        MutableStateFlow<Resource<FirebaseUser>>(Resource.Idle())
    val registerState = _registerState.asStateFlow()
    val isLoggedIn: Boolean
        get() = repo.currentUser != null

    val currentUserEmail: String?
        get() = repo.currentUser?.email
    val currentUserName: String?
        get() = repo.currentUser?.displayName


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading()
            try {
                _loginState.value = Resource.Success(
                    repo.signIn(email, password)
                )
            } catch (e: Exception) {
                _loginState.value =
                    Resource.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (password != confirmPassword) {
            _registerState.value =
                Resource.Error("Passwords do not match")
            return
        }

        viewModelScope.launch {
            _registerState.value = Resource.Loading()
            try {
                _registerState.value = Resource.Success(
                    repo.signUp(username, email, password)
                )
            } catch (e: Exception) {
                _registerState.value =
                    Resource.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = Resource.Idle()
    }

    fun resetRegisterState() {
        _registerState.value = Resource.Idle()
    }
    fun signOut() {
        repo.signOut()
        resetLoginState()
        resetRegisterState()
    }
}