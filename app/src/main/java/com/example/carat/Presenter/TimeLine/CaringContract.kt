package com.example.carat.Presenter.TimeLine

import com.example.carat.Model.CaratUnitData
import com.example.carat.Model.FollowData

interface CaringContract {
    interface View {
        fun setCaratAdapter(caratData: ArrayList<CaratUnitData>)
        fun setCaringAdapter(caringData: ArrayList<FollowData>)
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