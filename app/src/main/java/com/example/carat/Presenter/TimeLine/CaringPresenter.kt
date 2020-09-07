package com.example.carat.Presenter.TimeLine

import com.example.carat.Repository.Repository
import com.example.carat.Util.BaseCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.await

class CaringPresenter(private val view: CaringContract.View, private val id: String) :
    CaringContract.Presenter, BaseCoroutineScope() {
    val repository = Repository()

    override fun getCaringList() {
        launch(handler) {
            val result = repository.getCaringList(id)
            if (result.message != "") {
                view.showToast(result.message)
            } else {
                view.setCaringAdapter(result.followings)
            }
        }
    }

    override fun getCaratList() {
        launch(handler) {
            val result = repository.getCaratList(id)
            if (result.message != "") {
                view.showToast(result.message)
            } else {
                view.setCaratAdapter(result.result)
            }
        }
    }

    override fun sendCaringState(isCaring: Boolean) {
        launch(handler) {
            if(isCaring) {
                repository.cancelReCaring(id).await()
            } else {
                repository.reCaring(id.toInt()).await()
            }
        }
    }

    override fun sendCaratState(isCarat: Boolean) {
        launch(handler) {
            if (isCarat) {
                repository.cancelCarat(id).await()
            } else {
                repository.doCarat(id).await()
            }
        }
    }

    override fun sendFollowingState(email: String) {
        launch(handler) {
            repository.doFollow(email).await()
        }
    }

    override fun sendFollowerState(email: String) {
        launch(handler) {
            repository.cancelFollow(email).await()
        }
    }
}