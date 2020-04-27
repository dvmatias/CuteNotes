package com.cmdv.features.ui.activity

import com.cmdv.core.base.mvp.BasePresenter
import javax.inject.Inject

class CreateNoteActivityPresenter @Inject constructor() :
	BasePresenter<CreateNoteActivityContract.View>(), CreateNoteActivityContract.Presenter<CreateNoteActivityContract.View> {

}