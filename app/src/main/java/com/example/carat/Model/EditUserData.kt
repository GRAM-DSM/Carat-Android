package com.example.carat.Model

import android.net.Uri
import okhttp3.MultipartBody

data class EditUserData(
    var name: String = "",
    var intro: String = "",
    var profileUri: Uri? = null,
    var backUri: Uri? = null,
    var profileToUpload: MultipartBody.Part? = null,
    var backToUpload: MultipartBody.Part? = null
)