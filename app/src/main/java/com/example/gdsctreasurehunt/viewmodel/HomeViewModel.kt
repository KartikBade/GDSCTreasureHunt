package com.example.gdsctreasurehunt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdsctreasurehunt.model.Hint

class HomeViewModel: ViewModel() {

    private val currentHintNumber: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val hintList = listOf(
        Hint("kartikbade19@gmail.com", listOf("Hint 1 for Kartik", "Hint 2 for Kartik", "Hint 3 for Kartik", "Hint 4 for Kartik"), listOf(1,2,3,4)),
        Hint("dhananjaykuber@gmail.com", listOf("Hint 1 for Kartik", "Hint 2 for Kartik", "Hint 3 for Kartik", "Hint 4 for Kartik"), listOf(10,20,30,40)),
        Hint("sarveshjoshi@gmail.com", listOf("Hint 1 for Kartik", "Hint 2 for Kartik", "Hint 3 for Kartik", "Hint 4 for Kartik"), listOf(100,200,300,400)),
        Hint("nishantjoshi@gmail.com", listOf("Hint 1 for Kartik", "Hint 2 for Kartik", "Hint 3 for Kartik", "Hint 4 for Kartik"), listOf(1000,2000,3000,4000))
    )

    fun getHintList(): List<Hint> {
        return hintList
    }

}