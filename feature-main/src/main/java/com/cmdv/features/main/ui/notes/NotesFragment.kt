package com.cmdv.features.main.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmdv.core.base.mvp.BaseFragment
import com.cmdv.features.R
import com.cmdv.features.main.di.notes.NotesFragmentModule
import com.cmdv.features.main.di.notes.NotesFragmentSubComponent
import com.cmdv.features.main.ui.FeatureUiComponent

class NotesFragment :
	BaseFragment<NotesFragment, NotesFragmentPresenter, NotesFragmentSubComponent>(),
	NotesFragmentContract.View {

	override fun bindComponent(): NotesFragmentSubComponent =
		FeatureUiComponent.component.plus(NotesFragmentModule())

	override fun bindLayout(): Int =
		R.layout.fragment_notes

	override fun bindViews() {

	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? =
		inflater.inflate(R.layout.fragment_notes, container, false)

}
