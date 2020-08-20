package com.example.carat.Presenter.Profile

import android.net.Uri
import okhttp3.MultipartBody

interface ChangeProfileContract {
    interface View {
        fun convertToImage(profileUri: Uri?, backUri: Uri?)
    }

    interface Presenter {
        fun doLogOut()
        fun updateProfile(name: String, intro: String)
        fun updateProfileWithImage(profileImage: MultipartBody.Part?, backImage: MultipartBody.Part?)
        fun setProfileImage(uri: Uri)
        fun setBackGroundImage(uri: Uri)
    }
}