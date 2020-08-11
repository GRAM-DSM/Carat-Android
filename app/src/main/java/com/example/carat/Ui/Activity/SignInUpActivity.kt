package com.example.carat.Ui.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carat.R
import com.example.carat.Ui.Fragment.InitialScreenFragment

class SignInUpActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_up)

        supportFragmentManager.beginTransaction()
            .replace(R.layout.fragment_initial_screen, InitialScreenFragment())
            .commit()
    }
}