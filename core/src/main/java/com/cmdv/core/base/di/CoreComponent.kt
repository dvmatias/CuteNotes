package com.cmdv.core.base.di

import android.app.Application
import android.content.Context
import com.cmdv.core.base.di.coroutineDispatcher.CoroutinesModule
import com.cmdv.core.base.di.network.NetworkModule
import com.cmdv.core.base.di.storage.StorageModule
import com.cmdv.core.navigator.Navigator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		CoreModule::class,
		NetworkModule::class,
		StorageModule::class,
		CoroutinesModule::class
	]
)
interface CoreComponent {

	fun application(): Application

	fun context(): Context

	fun navigator(): Navigator

}