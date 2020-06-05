package com.cmdv.features.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.cmdv.core.EXTRA_NOTE_TYPE_KEY
import com.cmdv.core.base.mvp.BaseActivity
import com.cmdv.domain.models.note.utils.NoteType
import com.cmdv.features.R
import com.cmdv.features.di.activity.CreateNoteActivityModule
import com.cmdv.features.di.activity.CreateNoteActivitySubComponent
import com.cmdv.features.ui.FeatureUiComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_note.*

class CreateNoteActivity :
	BaseActivity<CreateNoteActivity, CreateNoteActivityPresenter, CreateNoteActivitySubComponent>(),
	CreateNoteActivityContract.View {

	// Views.
	private lateinit var toolbar: Toolbar
	private lateinit var createNoteToolbar: View
	private lateinit var ivCancel: AppCompatImageView
	private lateinit var ivSave: AppCompatImageView
	private lateinit var tvToolbarLabel: AppCompatTextView
	private lateinit var contentCreateNote: View
	private lateinit var spinnerPriority: AppCompatSpinner

	private lateinit var noteType: NoteType

	override fun bindComponent(): CreateNoteActivitySubComponent =
		FeatureUiComponent.component.plus(CreateNoteActivityModule())

	override fun bindLayout(): Int =
		R.layout.activity_create_note

	override fun bindViews() {
		toolbar = findViewById(R.id.toolbar)
		createNoteToolbar = toolbar.findViewById(R.id.create_note_toolbar)
		ivCancel = createNoteToolbar.findViewById(R.id.iv_toolbar_cancel)
		ivSave = createNoteToolbar.findViewById(R.id.iv_toolbar_save)
		tvToolbarLabel = createNoteToolbar.findViewById(R.id.tv_toolbar_label)
		contentCreateNote = findViewById(R.id.content_create_note)
		spinnerPriority = contentCreateNote.findViewById(R.id.spinner_priority)

	}

	override fun bindListeners() {
		ivCancel.setOnClickListener { onUserClickCancel() }
		ivSave.setOnClickListener { onUserClickSave() }
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		noteType = intent.extras?.get(EXTRA_NOTE_TYPE_KEY) as (NoteType)

		setSupportActionBar(toolbar)
		setupStatusBar()
		setupToolbar()
		setupSpinnerPriority()
	}

	private fun setupStatusBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
		}
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
		window.statusBarColor = ContextCompat.getColor(this, R.color.colorStatusBar)
	}

	private fun setupToolbar() {
		tvToolbarLabel.text = String.format(
			getString(R.string.placeholder_toolbar_title_create_note_activity),
			when (noteType) {
				NoteType.NOTE -> getString(R.string.note_type_note)
				NoteType.TODO_LIST -> getString(R.string.note_type_todo_list)
				NoteType.RECIPE -> getString(R.string.note_type_recipe)
			}
		)
	}

	private fun setupSpinnerPriority() {
		val priorities = resources.getStringArray(R.array.note_priorities)
		val adapter = ArrayAdapter(this,
			android.R.layout.simple_spinner_item, priorities)
		spinnerPriority.adapter = adapter
	}


	/**
	 * [CreateNoteActivityContract.View] implementation.
	 */

	override fun onUserClickCancel() {
		Snackbar.make(coordinatorLayout, "Cancel note creation", 500).show()
	}

	override fun onUserClickSave() {
		Snackbar.make(coordinatorLayout, "Save Note", 500).show()
	}

}
