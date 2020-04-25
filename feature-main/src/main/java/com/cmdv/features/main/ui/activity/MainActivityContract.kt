package com.cmdv.features.main.ui.activity

import com.cmdv.core.base.mvp.BaseContract

interface MainActivityContract {

	interface View : BaseContract.View

	interface Presenter<V : View> : BaseContract.Presenter<V>

}