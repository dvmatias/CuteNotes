package com.cmdv.features.main.ui.activity

import com.cmdv.core.base.mvp.BasePresenter
import javax.inject.Inject

class MainActivityPresenter @Inject constructor() :
	BasePresenter<MainActivityContract.View>(), MainActivityContract.Presenter<MainActivityContract.View> {

}