package com.example.carat.Presenter.Writing

import okhttp3.MultipartBody

interface WritingContract {
    interface Presenter {
        fun addImage(image: MultipartBody.Part)
        fun saveContent(content: String)
    }
}