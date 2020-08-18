package com.example.carat.Presenter.Profile

import com.example.carat.Util.BaseCoroutineScope

class FollowPresenter(var view: ProfileContract.View) : FollowContract.Presenter,
    BaseCoroutineScope()