package com.cmdv.features.main.ui.activity

import com.cmdv.core.base.mvp.BaseContract
import com.cmdv.domain.models.navitem.NavItemType

interface MainActivityContract {

	interface View : BaseContract.View {

		fun onUserClickFragmentNavMenuItem(navItemType: NavItemType)

		fun onUserClickScreenNavMenuItem(navItemType: NavItemType)

		fun onUserClickOnAddButton()

		fun showAddButton(show: Boolean)

	}

	interface Presenter<V : View> : BaseContract.Presenter<V>

}