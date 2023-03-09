package com.example.gdsctreasurehunt.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gdsctreasurehunt.R
import com.example.gdsctreasurehunt.databinding.ActivityHomeBinding
import com.example.gdsctreasurehunt.model.Hint
import com.example.gdsctreasurehunt.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var userSharedPref: SharedPreferences
    private lateinit var hintSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        userSharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE)
        hintSharedPreferences = getSharedPreferences("hintData", Context.MODE_PRIVATE)
        hintSharedPreferences.edit().clear().apply()

        binding.tvTitleUsername.text = userSharedPref.getString("username", "Player")

        val currentUserEmail = userSharedPref.getString("email", null)
        val currentHintList = currentUserEmail?.let { getCurrentHintList(it) }
        var currentHintNumber = hintSharedPreferences.getInt("currentHintNumber", 0)
        if (currentHintNumber < 4) {
            binding.tvHint.text = currentHintList?.hintList?.get(currentHintNumber)
        }

        binding.btnSubmitCode.setOnClickListener {
            try {
                val codeEntered = binding.etEnterCode.text.toString().toLong()
                val codeExpected = currentHintList?.codeList?.get(currentHintNumber) ?: return@setOnClickListener

                if (codeEntered == codeExpected) {
                    hintSharedPreferences.edit().putInt("currentHintNumber", currentHintNumber + 1).apply()
                    currentHintNumber = hintSharedPreferences.getInt("currentHintNumber", 0)
                    if (currentHintNumber < 4) {
                        binding.tvHint.text = currentHintList.hintList[currentHintNumber]
                    } else {
                        Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show()
                    }
                    Snackbar.make(findViewById(android.R.id.content), "Correct Code -> $codeExpected", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getColor(R.color.green)).show()
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Invalid Code -> $codeExpected", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getColor(R.color.red)).show()
                }
            } catch (e: java.lang.Exception) {
                Snackbar.make(
                    findViewById(android.R.id.content), "Invalid Code -> ${e.message}", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(R.color.red)).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                userSharedPref.edit().apply {
                    this.putString("username", null)
                    this.putString("email", null)
                    this.putBoolean("isLoggedIn", false)
                    apply()
                }
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCurrentHintList(currentUserEmail: String): Hint {
        val hintList = homeViewModel.getHintList()
        lateinit var currentHintList: Hint
        hintList.find {
            if (it.email == currentUserEmail) {
                currentHintList = it
                return@find true
            } else {
                return@find false
            }
        }
        return currentHintList
    }
}