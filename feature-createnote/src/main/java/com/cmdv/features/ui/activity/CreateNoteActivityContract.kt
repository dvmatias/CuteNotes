package com.cmdv.features.ui.activity

import com.cmdv.core.base.mvp.BaseContract

interface CreateNoteActivityContract {

	interface View : BaseContract.View {

		fun onUserClickCancel()

		fun onUserClickSave()

	}

	interface Presenter<V : View> : BaseContract.Presenter<V>

}