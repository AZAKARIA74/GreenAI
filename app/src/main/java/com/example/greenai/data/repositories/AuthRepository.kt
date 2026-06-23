package com.example.greenai.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signIn(email: String, password: String): FirebaseUser =
        suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if (user != null) continuation.resume(user)
                    else continuation.resumeWithException(IllegalStateException("Sign in failed"))
                }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }

    suspend fun signUp(username: String, email: String, password: String): FirebaseUser =
        suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if (user != null) {
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build()

                        user.updateProfile(profileUpdates)
                            .addOnSuccessListener {
                                if (continuation.isActive) continuation.resume(user)
                            }
                            .addOnFailureListener { e ->
                                if (continuation.isActive) continuation.resumeWithException(e)
                            }
                    } else {
                        if (continuation.isActive) continuation.resumeWithException(IllegalStateException("Sign up failed"))
                    }
                }
                .addOnFailureListener { e ->
                    if (continuation.isActive) continuation.resumeWithException(e)
                }
        }

    fun signOut() = auth.signOut()
}