package com.cmdv.core.base.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cmdv.core.base.di.ViewComponent
import javax.inject.Inject

const val ATTACH_TO_ROOT_FALSE: Boolean = false

abstract class BaseFragment<in V : BaseContract.View, P : BaseContract.Presenter<V>, out C : ViewComponent<V>> : Fragment() {
	@Inject
	protected lateinit var presenter: P

	protected abstract fun bindComponent(): C

	protected abstract fun bindLayout(): Int

	protected abstract fun bindViews()

	@Suppress("UNCHECKED_CAST")
	override fun onCreate(savedInstanceState: Bundle?) {
		bindComponent().inject(this as V)
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		bindViews()
		return inflater.inflate(bindLayout(), container, ATTACH_TO_ROOT_FALSE)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		@Suppress("UNCHECKED_CAST")
		presenter.attachView(this as V)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		presenter.create()
	}

	override fun onResume() {
		super.onResume()
		presenter.resume()
	}

	override fun onPause() {
		super.onPause()
		presenter.pause()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.destroy()
	}

}