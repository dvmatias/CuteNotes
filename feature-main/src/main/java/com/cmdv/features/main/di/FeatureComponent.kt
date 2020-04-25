package com.cmdv.features.main.di

import com.cmdv.core.base.di.CoreComponent
import com.cmdv.features.main.di.activity.MainActivityModule
import com.cmdv.features.main.di.activity.MainActivitySubComponent
import dagger.Component

@Component(
	dependencies = [CoreComponent::class],
	modules = [FeatureModule::class]
)
@FeatureScope
interface FeatureComponent {

	fun plus(target: MainActivityModule): MainActivitySubComponent

}