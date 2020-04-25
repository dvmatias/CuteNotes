package com.cmdv.core.base.mvp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cmdv.core.base.di.ViewComponent
import com.cmdv.core.navigator.Navigator
import javax.inject.Inject

val ROOT: Nothing? = null

abstract class BaseActivity<in V : BaseContract.View, P : BaseContract.Presenter<V>, out C : ViewComponent<V>> : AppCompatActivity() {

	@Inject
	protected lateinit var presenter: P

	@Inject
	protected lateinit var navigator: Navigator

	protected abstract fun bindComponent(): C

	protected abstract fun bindLayout(): Int

	protected abstract fun bindViews()

	@Suppress("UNCHECKED_CAST")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		bindComponent().inject(this as V)
		setContentView(layoutInflater.inflate(bindLayout(), ROOT))
		bindViews()

		presenter.attachView(this as V)
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

	protected fun showToast(msg: String) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
	}

}