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
    private val createUserPresenter: CreateUserContract.Presenter by lazy{
        CreateUserPresenter()
    }

    private var name:String? = null
    private var email:String? = null
    private var password:String?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        create_login_button.setOnClickListener {
            val intent = Intent(context, LoginFragment::class.java)
            startActivity(intent)
        }

        create_sign_button.setOnClickListener {
            getData()
            sendDataToServer(name,email,password)

            val intent = Intent(context,LoginFragment::class.java)
            intent.putExtra("createUser_email",email)
            intent.putExtra("createUser_password",password)
            startActivity(intent)
        }

        create_back_button.setOnClickListener {
            val intent = Intent(context,InitialScreenFragment::class.java)
            startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun getData() {
        name = create_name_editText.text?.toString()
        email = create_email_editText.text?.toString()
        password = create_password_editText.text?.toString()
    }

    private fun sendDataToServer(name: String?, email: String?, password: String?) {
        if (name != null && email != null && password != null) {
            createUserPresenter.sendDataToServer(name,email,password)
        }
    }

}