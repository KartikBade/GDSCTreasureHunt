package com.example.gdsctreasurehunt.model

data class Hint (
    val email: String,
    val hintList: List<String>,
    val codeList: List<Long>
)