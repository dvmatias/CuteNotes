package com.cmdv.features.main.ui

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.features.R
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavRecyclerAdapter.OnNavigationItemClickListener {

	// Views.
	private lateinit var drawerLayout: DrawerLayout
	private lateinit var toolbar: Toolbar
	private lateinit var navView: NavigationView
	private lateinit var appBarMain: View
	private lateinit var navRecycler: RecyclerView
	private lateinit var navRecyclerAdapter: NavRecyclerAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		toolbar = findViewById(R.id.toolbar)
		drawerLayout = findViewById(R.id.drawer_layout)
		navView = findViewById(R.id.nav_view)
		appBarMain = findViewById(R.id.app_bar_main)
		navRecycler = (navView.findViewById(R.id.nav_content) as ViewGroup).findViewById(R.id.nav_recycler)

		this.supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
		supportActionBar?.setDisplayShowCustomEnabled(true)
		supportActionBar?.setCustomView(R.layout.main_toolbar)

		setSupportActionBar(toolbar)
		setupDrawerLayout()
		setupNavigationDrawerRecycler()
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

//		val drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//			override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//				super.onDrawerSlide(drawerView, slideOffset)
//				val moveFactor: Float = navView.width * slideOffset
//				appBarMain.translationX = moveFactor
//			}
//		}
//
//		drawerLayout.addDrawerListener(drawerToggle)
//		drawerToggle.syncState()
//		navView.setNavigationItemSelectedListener(null)
	}

	private fun setupNavigationDrawerRecycler() {
		navRecycler.layoutManager = LinearLayoutManager(this)
		this.navRecyclerAdapter = NavRecyclerAdapter(this, this)
		navRecycler.adapter = this.navRecyclerAdapter
	}

	override fun onClick(item: NavRecyclerAdapter.NavItemModel, position: Int) {
		this.navRecyclerAdapter.setSelected(position)
	}

}
