package com.cmdv.features.main.di.activity

import android.app.Activity
import android.content.Context
import com.cmdv.features.main.ui.activity.NavRecyclerAdapter
import com.cmdv.features.main.ui.activity.NavRecyclerItemDecoration
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val context: Activity) {

	@Provides
	fun provideContext(): Context = context

	@Provides
	fun provideNavRecyclerAdapter(): NavRecyclerAdapter =
		NavRecyclerAdapter(context, (context as NavRecyclerAdapter.OnNavigationItemClickListener))

	@Provides
	fun provideNavRecyclerItemDecoration(): NavRecyclerItemDecoration =
		NavRecyclerItemDecoration(context)

}