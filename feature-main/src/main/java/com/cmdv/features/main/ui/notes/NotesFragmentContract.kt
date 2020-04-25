package com.cmdv.features.main.ui.notes

import com.cmdv.core.base.mvp.BaseContract

interface NotesFragmentContract {

	interface View : BaseContract.View

	interface Presenter<V : View> : BaseContract.Presenter<V>

}