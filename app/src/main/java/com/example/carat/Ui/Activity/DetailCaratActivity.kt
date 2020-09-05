package com.example.carat.Ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carat.Presenter.TimeLine.DetailCaratContract
import com.example.carat.Presenter.TimeLine.DetailCaratPresenter
import com.example.carat.R

class DetailCaratActivity : AppCompatActivity(),DetailCaratContract.View {

    private val presenter: DetailCaratContract.Presenter = DetailCaratPresenter()
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_carat)

        if(intent.hasExtra("key")) {
            id = intent.getStringExtra("key")
        }

        getDetailCarat()

    }

    override fun setDetailCarat() {

    }

    private fun getDetailCarat(){
        id?.let { presenter.getDetailCarat(it) }
    }
}