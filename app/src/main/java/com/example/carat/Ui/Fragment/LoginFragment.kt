package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carat.Presenter.SignInUp.LoginContract
import com.example.carat.Presenter.SignInUp.LoginPresenter
import com.example.carat.R
import com.example.carat.Ui.Activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import java.net.PasswordAuthentication

class LoginFragment : Fragment() ,LoginContract.View{
    private val loginPresenter: LoginContract.Presenter = LoginPresenter(this)

    var email: String? =null
    var password: String? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        login_back_button.setOnClickListener{
            val intent = Intent(context, InitialScreenFragment::class.java)
            startActivity(intent)
        }

        login_sign_button.setOnClickListener {
            val intent = Intent(context, LoginFragment::class.java)
            startActivity(intent)
        }

        login_login_button.setOnClickListener {
            sendDataToServer()

            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }


        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun getData() {
        email = login_email_editText.text?.toString()
        password = login_password_editText.text?.toString()
    }

    private fun sendDataToServer() {
        loginPresenter.sendDataToServer(email.toString(), password.toString())
    }
}