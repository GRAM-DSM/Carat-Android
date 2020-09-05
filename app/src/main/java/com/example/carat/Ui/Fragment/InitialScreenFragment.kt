package com.example.carat.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carat.R
import kotlinx.android.synthetic.main.fragment_initial_screen.*

class InitialScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initial_create_button.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.sign_fragment, CreateUserFragment())
                .commit()
        }

        initial_login_button.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.sign_fragment, LoginFragment())
                .commit()
        }

        return inflater.inflate(R.layout.fragment_initial_screen, container, false)
    }

}