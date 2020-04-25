package com.cmdv.features.main.ui.activity

import com.cmdv.core.base.mvp.BaseContract
import com.cmdv.domain.models.NavItemType

interface MainActivityContract {

	interface View : BaseContract.View {

		fun onUserClickOnNavMenuItem(navItemType: NavItemType)

	}

	interface Presenter<V : View> : BaseContract.Presenter<V>

}