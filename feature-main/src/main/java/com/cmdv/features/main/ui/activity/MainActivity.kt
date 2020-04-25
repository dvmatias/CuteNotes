package com.cmdv.features.main.ui.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.core.base.mvp.BaseActivity
import com.cmdv.domain.models.NavItemModel
import com.cmdv.domain.models.NavItemType
import com.cmdv.features.R
import com.cmdv.features.main.di.activity.MainActivityModule
import com.cmdv.features.main.di.activity.MainActivitySubComponent
import com.cmdv.features.main.ui.FeatureUiComponent
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class MainActivity :
	BaseActivity<MainActivity, MainActivityPresenter, MainActivitySubComponent>(),
	NavRecyclerAdapter.OnNavigationItemClickListener, MainActivityContract.View {

	// Views.
	private lateinit var drawerLayout: DrawerLayout
	private lateinit var toolbar: Toolbar
	private lateinit var navView: NavigationView
	private lateinit var appBarMain: View
	private lateinit var navRecycler: RecyclerView
	private lateinit var navRecyclerAdapter: NavRecyclerAdapter

	override fun bindComponent(): MainActivitySubComponent =
		FeatureUiComponent.component.plus(MainActivityModule())

	override fun bindLayout(): Int =
		R.layout.activity_main

	override fun bindViews() {
		toolbar = findViewById(R.id.toolbar)
		drawerLayout = findViewById(R.id.drawer_layout)
		navView = findViewById(R.id.nav_view)
		appBarMain = findViewById(R.id.app_bar_main)
		navRecycler = (navView.findViewById(R.id.nav_content) as ViewGroup).findViewById(R.id.nav_recycler)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		this.supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
		supportActionBar?.setDisplayShowCustomEnabled(true)
		supportActionBar?.setCustomView(R.layout.main_toolbar)

		setSupportActionBar(toolbar)
		setupDrawerLayout()
		setupNavRecycler()
		setupStatusBar()
	}

	private fun setupStatusBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
		}
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
		window.statusBarColor = ContextCompat.getColor(this, R.color.colorNavSideMenuBackground)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	private fun setupDrawerLayout() {
		val displayMetrics = DisplayMetrics()
		windowManager.defaultDisplay.getMetrics(displayMetrics)
		val params = navView.layoutParams as DrawerLayout.LayoutParams
		params.width = (displayMetrics.widthPixels * 0.85).toInt()
		navView.layoutParams = params

		val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

//		val drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//			override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//				super.onDrawerSlide(drawerView, slideOffset)
//				val moveFactor: Float = navView.width * slideOffset
//				appBarMain.translationX = moveFactor
//			}
//		}

		drawerLayout.addDrawerListener(drawerToggle)
		drawerToggle.syncState()
		navView.setNavigationItemSelectedListener(null)
	}

	private fun setupNavRecycler() {
		navRecycler.layoutManager = LinearLayoutManager(this)
		this.navRecyclerAdapter = NavRecyclerAdapter(this, this)
		navRecycler.adapter = this.navRecyclerAdapter

		navRecycler.addItemDecoration(NavRecyclerItemDecoration(this))
	}

	override fun onClick(item: NavItemModel, position: Int) {
		this.navRecyclerAdapter.setSelected(position)
		onUserClickOnNavMenuItem(item.type)
		Handler().postDelayed({ drawerLayout.closeDrawer(GravityCompat.START) }, 350)
	}

	/**
	 * [MainActivityContract.View] implementation
	 */
	override fun onUserClickOnNavMenuItem(navItemType: NavItemType) {
		Snackbar.make(drawerLayout, "$navItemType", Snackbar.LENGTH_LONG)
			.show()
	}

}
