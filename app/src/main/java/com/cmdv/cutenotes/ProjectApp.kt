package com.cmdv.cutenotes

import com.cmdv.core.base.BaseApplication
import com.cmdv.core.navigator.Navigator
import com.cmdv.cutenotes.navigator.NavigatorImp

class ProjectApp : BaseApplication() {

	override fun onCreate() {
		super.onCreate()
	}

	override fun bindNavigator(): Navigator =
		NavigatorImp()

}