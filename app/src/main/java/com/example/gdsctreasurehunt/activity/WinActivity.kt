package com.example.gdsctreasurehunt.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsctreasurehunt.R
import com.example.gdsctreasurehunt.databinding.ActivityWinBinding
import java.text.SimpleDateFormat
import java.util.*

class WinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWinBinding
    private lateinit var userSharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userSharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE)

        val userName = userSharedPref.getString("username", "Player")
        binding.tvPlayerName.text = "$userName"
        binding.tvTimeCompleted.text = "Completed At: ${ userSharedPref.getString("timeCompleted", null) }"
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        MenuInflater(this).inflate(R.menu.menu_home, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.logout -> {
//                userSharedPref.edit().apply {
//                    this.putString("username", null)
//                    this.putString("email", null)
//                    this.putBoolean("isLoggedIn", false)
//                    apply()
//                }
//                val intent = Intent(this, AuthActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}