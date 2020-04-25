package com.cmdv.core.base.mvp

import com.cmdv.domain.base.exception.Failure
import com.cmdv.domain.base.usecase.UseCaseContract

abstract class BasePresenter<V : BaseContract.View>(
	private vararg val useCaseList: UseCaseContract
) : BaseContract.Presenter<V> {

	protected var view: V? = null

	override fun attachView(view: V) {
		this.view = view
	}

	override fun destroy() {
		for (useCase in useCaseList) {
			useCase.cancel()
		}
		this.view = null
	}

}