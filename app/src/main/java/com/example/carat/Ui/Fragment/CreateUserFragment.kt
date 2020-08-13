package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carat.Presenter.SignInUp.CreateUserContract
import com.example.carat.Presenter.SignInUp.CreateUserPresenter
import com.example.carat.R
import kotlinx.android.synthetic.main.fragment_create_user.*

class CreateUserFragment : Fragment() ,CreateUserContract.View{
    var name:String? = null
    var email:String? = null
    var password:String?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        create_sign_button.setOnClickListener {
            getData()
        }

        create_login_button.setOnClickListener{
            goLogin()
        }

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun showInfo() {

    }

    override fun getData() {
        name = create_name_editText.text?.toString()
        email = create_email_editText.text?.toString()
        password = create_password_editText.text?.toString()
    }

    fun goLogin() {
        val intent = Intent(context, LoginFragment::class.java)
        startActivity(intent)
    }

}