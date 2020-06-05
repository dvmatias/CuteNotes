package com.cmdv.components.input

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.cmdv.components.R

class CustomNoteInputView : AppCompatEditText {

	private var _title: String? = "" // TODO: use a default from R.string...
	private var _inputBackgroundColor: Int? = Color.WHITE // TODO: use a default from R.string...

	constructor(context: Context): super(context) {
		init(null, 0)
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		init(attrs, 0)
	}

	constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
		init(attrs, defStyle)
	}

	private fun init(attrs: AttributeSet?, defStyle: Int) {
		// Load attributes
		val a = context.obtainStyledAttributes(
			attrs, R.styleable.CustomNoteInputView
		)

		a.recycle()
	}
}