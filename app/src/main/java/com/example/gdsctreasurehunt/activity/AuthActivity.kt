package com.example.gdsctreasurehunt.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gdsctreasurehunt.databinding.ActivityAuthBinding
import com.example.gdsctreasurehunt.model.User
import com.example.gdsctreasurehunt.viewmodel.AuthViewModel


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val email = binding.etUsername.text?.trim().toString()
            val password = binding.etPassword.text?.trim().toString()
            val userList = authViewModel.getUserData()
            authenticateUser(email, password, userList)
        }

        binding.btnClearSharedPref.setOnClickListener {
            val hintSharedPreferences = getSharedPreferences("hintData", Context.MODE_PRIVATE)
            hintSharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Data Cleared", Toast.LENGTH_LONG).show()
        }
    }

    private fun authenticateUser(email: String, password: String, userList: List<User>) {
        val currentUser = userList.find {
            email == it.email && password == it.password
        }
        if (currentUser == null) {
            Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_LONG).show()
            return
        }

        val userSharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE)
        val editor = userSharedPref.edit()
        editor.apply {
            putString("username", currentUser.username)
            putString("email", currentUser.email)
            putBoolean("isLoggedIn", true)
            apply()
        }

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (getSharedPreferences("userData", Context.MODE_PRIVATE).getBoolean("isLoggedIn", false)) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}