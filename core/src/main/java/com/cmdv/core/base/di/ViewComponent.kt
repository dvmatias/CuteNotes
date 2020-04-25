package com.cmdv.core.base.di

import com.cmdv.core.base.mvp.BaseContract

interface ViewComponent<in V : BaseContract.View> {

	fun inject(view: V)

}