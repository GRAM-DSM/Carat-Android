package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.DetailTimeLineData

interface DetailCaratContract {
    interface View{
        fun setDetailCarat(result: DetailTimeLineData)
    }

    interface Presenter{
        fun getDetailCarat(id:String)
    }
}