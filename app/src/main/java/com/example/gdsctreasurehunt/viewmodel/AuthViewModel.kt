package com.example.gdsctreasurehunt.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gdsctreasurehunt.model.User

class AuthViewModel: ViewModel() {

    private val userList = listOf(
        User("kartikbade19@gmail.com", "kartik", "Kartik Bade"),
        User("dhananjaykuber@gmail.com", "dhananjay", "Kubrya"),
        User("sarveshjoshi@gmail.com", "sarvesh", "Joshi Bua"),
        User("nishantjoshi@gmail.com", "nishant", "Nishant Zavshi")
    )

    fun getUserData(): List<User> {
        return userList
    }
}