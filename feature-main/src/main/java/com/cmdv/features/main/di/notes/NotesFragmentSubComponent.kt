package com.cmdv.features.main.di.notes

import com.cmdv.core.base.di.ViewComponent
import com.cmdv.features.main.ui.notes.NotesFragment
import dagger.Subcomponent

@Subcomponent(
	modules = [NotesFragmentModule::class]
)
interface NotesFragmentSubComponent :ViewComponent<NotesFragment>  {
}