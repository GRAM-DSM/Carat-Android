package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carat.Model.LoginData
import com.example.carat.Presenter.SignInUp.LoginContract
import com.example.carat.Presenter.SignInUp.LoginPresenter
import com.example.carat.R
import com.example.carat.Ui.Activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import java.net.PasswordAuthentication

class LoginFragment : Fragment(), LoginContract.View {
    private val loginPresenter: LoginContract.Presenter = LoginPresenter(this)
    private var loginData = LoginData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        login_back_button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.sign_fragment, InitialScreenFragment())
                .commit()
        }

        login_sign_button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.sign_fragment, CreateUserFragment())
                .commit()
        }

        login_login_button.setOnClickListener {
            sendDataToServer()

            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }


        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun getLoginFieldInputData() {
        loginData.email = login_email_editText.text.toString()
        loginData.password = login_password_editText.text.toString()
    }

    private fun sendDataToServer() {
        getLoginFieldInputData()
        loginPresenter.sendDataToServer(loginData)
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}