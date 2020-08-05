package com.example.carat.Ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carat.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    var email: String? =null
    var password: String? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getData()

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    fun getData() {
        email = login_email_editText.text?.toString()
        password = login_password_editText.text?.toString()
    }
}