package com.example.carat.Ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carat.Presenter.Profile.ChangeProfileContract
import com.example.carat.R

class ChangeProfile : AppCompatActivity(), ChangeProfileContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_profile)
    }
}