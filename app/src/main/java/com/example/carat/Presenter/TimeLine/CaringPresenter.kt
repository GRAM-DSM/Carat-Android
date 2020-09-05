package com.example.carat.Presenter.TimeLine

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CaringPresenter(private val view: CaringContract.View, private val id: String) :
    CaringContract.Presenter, BaseCoroutineScope() {
    val repository = Repository()

    override fun getCaringList() {
        CoroutineScope(coroutineContext).launch(handler) {

        }
    }

    override fun getCaratList() {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = repository.getCaratList(id)
            if (result.message != "") {
                view.showToast(result.message)
            } else {
                view.setCaringAdapter(result.result)
            }
        }
    }

    override fun sendCaringState(isCaring: Boolean) {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = if(isCaring) {
                repository.cancelReCaring(id)
            } else {
                repository.reCaring(id.toInt())
            }
            if (result.message != "") {
                view.showToast(result.message)
            }
        }
    }

    override fun sendCaratState(isCarat: Boolean) {
        CoroutineScope(coroutineContext).launch(handler) {
            val result = if (isCarat) {
                repository.cancelCarat(id)
            } else {
                repository.doCarat(id)
            }

            if (result.message != "") {
                view.showToast(result.message)
            }
        }
    }

    override fun sendFollowingState(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.doFollow(email)
        }
    }

    override fun sendFollowerState(email: String) {
        CoroutineScope(coroutineContext).launch(handler) {
            repository.cancelFollow(email)
        }
    }
}