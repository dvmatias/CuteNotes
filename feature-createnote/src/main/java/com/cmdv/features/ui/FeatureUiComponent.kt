package com.cmdv.features.ui

import com.cmdv.core.base.BaseApplication
import com.cmdv.features.di.DaggerFeatureComponent
import com.cmdv.features.di.FeatureComponent
import com.cmdv.features.di.FeatureModule

internal class FeatureUiComponent {

	companion object {
		internal val component: FeatureComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
			createComponent()
		}

		@Suppress("DEPRECATION")
		private fun createComponent(): FeatureComponent =
			DaggerFeatureComponent.builder()
				.coreComponent(BaseApplication.coreComponent)
				.featureModule(FeatureModule())
				.build()

	}

}