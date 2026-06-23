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

    private val _authState = MutableStateFlow<Resource<FirebaseUser>>(Resource.Idle())
    val authState = _authState.asStateFlow()

    val isLoggedIn: Boolean
        get() = repo.currentUser != null

    val currentUserEmail: String?
        get() = repo.currentUser?.email
    val currentUserName: String?
        get() = repo.currentUser?.displayName


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = Resource.Loading()
            try {
                _authState.value = Resource.Success(repo.signIn(email, password))
            } catch (e: Exception) {
                _authState.value = Resource.Error(message = e.message ?: "Login failed")
            }
        }
    }

    fun register(username: String,email: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _authState.value = Resource.Error(message = "Passwords do not match")
            return
        }
        viewModelScope.launch {
            _authState.value = Resource.Loading()
            try {
                _authState.value = Resource.Success(repo.signUp(username,email, password))
            } catch (e: Exception) {
                _authState.value = Resource.Error(message = e.message ?: "Registration failed")
            }
        }
    }

    fun resetState() {
        _authState.value = Resource.Idle()
    }

    fun signOut() {
        repo.signOut()
        _authState.value = Resource.Idle()
    }
}