package com.example.carat.Ui.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carat.Repository.Repository

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = if (Repository().getLoginState() == true) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, SignInUpActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}