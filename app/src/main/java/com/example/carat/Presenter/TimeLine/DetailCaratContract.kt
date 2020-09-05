package com.example.carat.Presenter.TimeLine

interface DetailCaratContract {
    interface View{
        fun setDetailCarat()
    }

    interface Presenter{
        fun getDetailCarat(id:String)
    }
}