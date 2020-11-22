package com.poran.architecturecomponentsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val userRepository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java) ) return ProfileViewModel(userRepository)  as T
        throw IllegalArgumentException()
    }
}