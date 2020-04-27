package com.cmdv.features.di.activity

import com.cmdv.core.base.di.ViewComponent
import com.cmdv.features.ui.activity.CreateNoteActivity
import dagger.Subcomponent

@Subcomponent(
	modules = [CreateNoteActivityModule::class]
)
interface CreateNoteActivitySubComponent : ViewComponent<CreateNoteActivity>