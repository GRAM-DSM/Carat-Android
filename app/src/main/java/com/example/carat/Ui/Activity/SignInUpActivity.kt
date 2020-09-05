package com.example.carat.Ui.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.carat.R
import com.example.carat.Ui.Fragment.InitialScreenFragment
import com.example.carat.Ui.Fragment.LoginFragment

class SignInUpActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_up)

        if (intent.getBooleanExtra("isLogout", false)) {
            changeFragment(LoginFragment())
        } else {
            changeFragment(InitialScreenFragment())
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.sign_fragment, fragment)
            .commit()
    }
}