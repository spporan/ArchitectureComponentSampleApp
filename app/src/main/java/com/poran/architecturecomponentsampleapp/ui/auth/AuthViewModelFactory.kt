package com.poran.architecturecomponentsampleapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val repository: UserRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)) return AuthViewModel(repository) as T
        throw IllegalArgumentException()
    }
}