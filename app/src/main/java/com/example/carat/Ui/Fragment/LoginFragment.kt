package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carat.Model.LoginData
import com.example.carat.Presenter.SignInUp.LoginContract
import com.example.carat.Presenter.SignInUp.LoginPresenter
import com.example.carat.R
import com.example.carat.Ui.Activity.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment(), LoginContract.View {
    private val loginPresenter: LoginContract.Presenter = LoginPresenter(this)
    private var loginData = LoginData.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.login_email_editText.setText(loginData.email)
        view.login_password_editText.setText(loginData.password)

        view.login_back_button.setOnClickListener {
            changeFragment(InitialScreenFragment())
        }

        view.login_sign_button.setOnClickListener {
            changeFragment(CreateUserFragment())
        }

        view.login_login_button.setOnClickListener {
            sendDataToServer()
        }

        return view
    }

    private fun changeFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.sign_fragment, fragment)
            .commit()
    }

    private fun getLoginFieldInputData() {
        loginData.email = login_email_editText.text.toString()
        loginData.password = login_password_editText.text.toString()
    }

    private fun sendDataToServer() {
        getLoginFieldInputData()
        if (loginData.email != "" && loginData.password != "") {
            loginPresenter.sendDataToServer()
        }
    }

    override fun moveToMain() {
        val intent = Intent(context, MainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}