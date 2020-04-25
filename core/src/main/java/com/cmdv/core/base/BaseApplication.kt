package com.cmdv.core.base

import android.app.Application
import com.cmdv.core.base.di.CoreComponent
import com.cmdv.core.base.di.CoreModule
import com.cmdv.core.base.di.DaggerCoreComponent
import com.cmdv.core.navigator.Navigator

abstract class BaseApplication : Application() {

	companion object {
		lateinit var coreComponent: CoreComponent
		internal lateinit var navigator: Navigator
	}

	abstract fun bindNavigator(): Navigator

	override fun onCreate() {
		super.onCreate()
		initInjector()
		initNavigator()
	}

	private fun initInjector() {
		coreComponent = DaggerCoreComponent
			.builder()
			.coreModule(CoreModule(this))
			.build()
	}

	private fun initNavigator() {
		navigator = bindNavigator()
	}

}