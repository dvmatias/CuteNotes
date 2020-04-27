package com.cmdv.features.di

import com.cmdv.core.base.di.CoreComponent
import com.cmdv.features.di.activity.CreateNoteActivityModule
import com.cmdv.features.di.activity.CreateNoteActivitySubComponent
import com.cmdv.features.ui.activity.CreateNoteActivity
import dagger.Component

@Component(
	dependencies = [CoreComponent::class],
	modules = [FeatureModule::class]
)
@FeatureScope
interface FeatureComponent {

	fun plus(target: CreateNoteActivityModule) : CreateNoteActivitySubComponent

}