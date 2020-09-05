package com.example.carat.Presenter.TimeLine

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailCaratPresenter : DetailCaratContract.Presenter, BaseCoroutineScope(){

    private val repository: Repository = Repository()

    override fun getDetailCarat(id: String) {
        CoroutineScope(coroutineContext).launch {
            val result = repository.detailCaring(id)
            //이 데이터로 set 해줘야하는데 음...
        }
    }

}