package com.example.carat.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carat.R
import kotlinx.android.synthetic.main.fragment_initial_screen.*

class InitialScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initial_create_button.setOnClickListener {
            val intent = Intent(context, CreateUserFragment::class.java)
            startActivity(intent)
        }

        initial_login_button.setOnClickListener{
            val intent = Intent(context,LoginFragment::class.java)
            startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_initial_screen, container, false)
    }

}