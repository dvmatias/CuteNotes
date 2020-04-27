package com.cmdv.features.di

import com.cmdv.core.base.di.CoreComponent
import dagger.Component

@Component(
	dependencies = [CoreComponent::class],
	modules = [FeatureModule::class]
)
@FeatureScope
interface FeatureComponent {

}