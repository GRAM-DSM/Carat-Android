package com.example.carat.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carat.R
import kotlinx.android.synthetic.main.fragment_initial_screen.*
import kotlinx.android.synthetic.main.fragment_initial_screen.view.*

class InitialScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_initial_screen, container, false)

        view.initial_create_button.setOnClickListener {
            changeFragment(CreateUserFragment())
        }

        view.initial_login_button.setOnClickListener{
            changeFragment(LoginFragment())
        }

        return view
    }

    private fun changeFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.sign_fragment, fragment)
            .commit()
    }
}