package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.CaratUnitData

interface CaringContract {
    interface View {
        fun setCaringAdapter(caratData: ArrayList<CaratUnitData>)
        fun showToast(message: String)
    }

    interface Presenter {
        fun getCaringList()
        fun getCaratList()
        fun sendCaringState(isCaring: Boolean)
        fun sendCaratState(isCarat: Boolean)
        fun sendFollowingState(email: String)
        fun sendFollowerState(email: String)
    }
}