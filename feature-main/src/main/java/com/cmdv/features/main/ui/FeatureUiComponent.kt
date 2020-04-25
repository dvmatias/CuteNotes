package com.cmdv.features.main.ui

import com.cmdv.core.base.BaseApplication
import com.cmdv.features.main.di.FeatureComponent
import com.cmdv.features.main.di.FeatureModule

internal class FeatureUiComponent {

	companion object {
		internal val component: FeatureComponent? by lazy(mode = LazyThreadSafetyMode.NONE) {
			createComponent()
		}

		@Suppress("DEPRECATION")
		private fun createComponent(): FeatureComponent? = null
//			DaggerMainComponent.builder()
//				.baseComponent(BaseApplication.coreComponent)
//				.mainModule(FeatureModule())
//				.build()
	}

}