package com.example.carat.Presenter.Writing

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class WritingPresenter : WritingContract.Presenter,
    BaseCoroutineScope() {

    private val repository: Repository = Repository()
    private val images: ArrayList<MultipartBody.Part> = arrayListOf()

    override fun addImage(image: MultipartBody.Part) {
        images.add(image)
    }

    override fun saveContent(content: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.createCaring(images, content)
        }
    }
}