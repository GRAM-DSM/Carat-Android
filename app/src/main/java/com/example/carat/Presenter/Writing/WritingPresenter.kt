package com.example.carat.Presenter.Writing

import com.example.carat.Util.BaseCoroutineScope

class WritingPresenter(var writingView: WritingContract.View) : WritingContract.Presenter,
    BaseCoroutineScope() {
    override fun getProfileImage() {}
    override fun saveContent() {}
}