package com.cmdv.cutenotes.navigator

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.cmdv.core.extensions.navigateTo
import com.cmdv.core.navigator.Navigator
import com.cmdv.features.ui.activity.CreateNoteActivity

class NavigatorImp : Navigator {

	override fun toCreateNote(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean) {
		activityOrigin.navigateTo<CreateNoteActivity>(bundle, options, finish)
	}
}