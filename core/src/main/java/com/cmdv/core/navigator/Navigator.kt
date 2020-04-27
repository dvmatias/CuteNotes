package com.cmdv.core.navigator

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat

interface Navigator {

	fun toCreateNote(activityOrigin: Activity, bundle: Bundle?, options: ActivityOptionsCompat?, finish: Boolean)

}