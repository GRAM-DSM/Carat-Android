package com.example.carat.Presenter.Writing

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class WritingPresenter(val writingView: WritingContract.View) : WritingContract.Presenter,
    BaseCoroutineScope() {

    val repository: Repository = Repository()

    override fun saveContent() {
        CoroutineScope(coroutineContext).launch(handler) {
            repository
        }
    }
}