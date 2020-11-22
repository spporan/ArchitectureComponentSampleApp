package com.poran.architecturecomponentsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository

class ProfileViewModel ( userRepository: UserRepository): ViewModel() {
     val user=userRepository.getUser()

}