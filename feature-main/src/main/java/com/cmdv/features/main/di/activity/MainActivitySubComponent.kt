package com.cmdv.features.main.di.activity

import com.cmdv.core.base.di.ViewComponent
import com.cmdv.features.main.ui.activity.MainActivity
import dagger.Subcomponent

@Subcomponent(
	modules = [MainActivityModule::class]
)
interface MainActivitySubComponent : ViewComponent<MainActivity>