package com.cmdv.core.base.mvp

interface BaseContract {

	interface View

	interface Presenter<in V : View> {

		fun attachView(view: V)

		fun create() {}

		fun resume() {}

		fun pause() {}

		fun destroy() {}

	}

}