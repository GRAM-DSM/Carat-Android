package com.example.carat.Presenter.TimeLine

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailCaratPresenter(private val view: DetailCaratContract.View): DetailCaratContract.Presenter, BaseCoroutineScope(){

    private val repository: Repository = Repository()

    override fun getDetailCarat(id: String) {
        launch(handler) {
            val result = repository.detailCaring(id)
            view.setDetailCarat(result)
        }
    }
}