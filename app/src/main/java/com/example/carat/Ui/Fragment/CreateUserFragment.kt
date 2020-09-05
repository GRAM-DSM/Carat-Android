package com.example.carat.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carat.Model.SignData
import com.example.carat.Presenter.SignInUp.CreateUserContract
import com.example.carat.Presenter.SignInUp.CreateUserPresenter
import com.example.carat.R
import kotlinx.android.synthetic.main.fragment_create_user.*
import kotlinx.android.synthetic.main.fragment_create_user.view.*

class CreateUserFragment : Fragment(), CreateUserContract.View {
    private val createUserPresenter: CreateUserContract.Presenter = CreateUserPresenter(this)
    private var signData = SignData()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_user, container, false)

        view.create_back_button.setOnClickListener {
            changeFragment(InitialScreenFragment())
        }

        view.create_login_button.setOnClickListener {
            changeFragment(LoginFragment())
        }

        view.create_sign_button.setOnClickListener {
            getData()
            sendDataToServer()
        }

        return view
    }

    private fun changeFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.sign_fragment, fragment)
            .commit()
    }

    private fun getData() {
        signData.name = create_name_editText.text.toString()
        signData.email = create_email_editText.text.toString()
        signData.password = create_password_editText.text.toString()
    }

    private fun sendDataToServer() {
        createUserPresenter.sendDataToServer(signData)
    }

    override fun moveToLogin() {
        changeFragment(LoginFragment())
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}