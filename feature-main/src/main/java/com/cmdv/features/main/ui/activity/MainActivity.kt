package com.cmdv.features.main.ui.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.core.EXTRA_NOTE_TYPE_KEY
import com.cmdv.core.base.mvp.BaseActivity
import com.cmdv.domain.models.navitem.NavItemModel
import com.cmdv.domain.models.navitem.NavItemType
import com.cmdv.features.R
import com.cmdv.features.main.di.activity.MainActivityModule
import com.cmdv.features.main.di.activity.MainActivitySubComponent
import com.cmdv.features.main.ui.FeatureUiComponent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject


class MainActivity :
	BaseActivity<MainActivity, MainActivityPresenter, MainActivitySubComponent>(),
	NavRecyclerAdapter.OnNavigationItemClickListener, MainActivityContract.View {

	// Views.
	private lateinit var drawerLayout: DrawerLayout
	private lateinit var toolbar: Toolbar
	private lateinit var mainToolbar: ViewGroup
	private lateinit var tvToolbarLabel: AppCompatTextView
	private lateinit var ivToolbarIcon: AppCompatImageView
	private lateinit var navView: NavigationView
	private lateinit var navRecycler: RecyclerView
	private lateinit var appBarMain: ViewGroup
	private lateinit var contentMain: ViewGroup
	private lateinit var fabAddNote: FloatingActionButton

	@Inject
	lateinit var navRecyclerAdapter: NavRecyclerAdapter
	@Inject
	lateinit var navRecyclerItemDecoration: NavRecyclerItemDecoration

	private lateinit var navController: NavController

	override fun bindComponent(): MainActivitySubComponent =
		FeatureUiComponent.component.plus(MainActivityModule(this))

	override fun bindLayout(): Int =
		R.layout.activity_main

	override fun bindViews() {
		toolbar = findViewById(R.id.toolbar)
		mainToolbar = toolbar.findViewById(R.id.main_toolbar)
		tvToolbarLabel = mainToolbar.findViewById(R.id.tv_toolbar_label)
		ivToolbarIcon = mainToolbar.findViewById(R.id.iv_toolbar_icon)
		drawerLayout = findViewById(R.id.drawer_layout)
		navView = findViewById(R.id.nav_view)
		navRecycler = (navView.findViewById(R.id.nav_content) as ViewGroup).findViewById(R.id.nav_recycler)
		appBarMain = findViewById(R.id.app_bar_main)
		contentMain = appBarMain.findViewById(R.id.content_main)
		fabAddNote = contentMain.findViewById(R.id.fab_add_note)
	}

	override fun bindListeners() {
		fabAddNote.setOnClickListener { onUserClickOnAddButton() }
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		navController = Navigation.findNavController(this, R.id.navigation_host_fragment)

		this.supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
		supportActionBar?.setDisplayShowCustomEnabled(true)
		supportActionBar?.setCustomView(R.layout.main_toolbar)

		setSupportActionBar(toolbar)
		setupDrawerLayout()
		setupNavRecycler()
		setupStatusBar()
	}

	override fun onBackPressed() {
		when (isDrawerOpen()) {
			true -> closeDrawer()
			else -> finish()
		}
	}

	private fun isDrawerOpen(): Boolean =
		drawerLayout.isDrawerOpen(GravityCompat.START)

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

		drawerLayout.addDrawerListener(drawerToggle)
		drawerToggle.syncState()
		navView.setNavigationItemSelectedListener(null)
	}

	private fun setupNavRecycler() {
		navRecycler.layoutManager = LinearLayoutManager(this)
		navRecycler.adapter = navRecyclerAdapter

		navRecycler.addItemDecoration(navRecyclerItemDecoration)
		navRecyclerAdapter.setSelected(0)
	}

	private fun setupToolbar(itemSelected: NavItemModel) {
		tvToolbarLabel.text = itemSelected.label
		ivToolbarIcon.setImageDrawable(
			getDrawable(
				when (itemSelected.type) {
					NavItemType.NOTES -> R.drawable.ic_nav_notes_24dp
					NavItemType.TODO_LISTS -> R.drawable.ic_nav_todo_lists_24dp
					NavItemType.RECIPES -> R.drawable.ic_nav_recipes_24dp
					NavItemType.CALENDAR -> R.drawable.ic_nav_calendar_24dp
					NavItemType.ARCHIVES -> R.drawable.ic_nav_archives_24dp
					NavItemType.DELETED -> R.drawable.ic_nav_deleted_24dp
					else -> R.drawable.ic_nav_notes_24dp
				}
			)
		)
	}

	/**
	 * [NavRecyclerAdapter.OnNavigationItemClickListener] implementation
	 */

	override fun onNavItemClick(item: NavItemModel, position: Int) {
		navRecyclerAdapter.setSelected(position)
		Handler().postDelayed({ closeDrawer() }, 200)
	}

	private fun closeDrawer() {
		drawerLayout.closeDrawer(GravityCompat.START)
	}

	override fun onNavSelectionChanged(itemSelected: NavItemModel) {
		onUserClickFragmentNavMenuItem(itemSelected)
	}

	override fun onNavSelectionScreen(selectedNavItemType: NavItemType) {
		onUserClickScreenNavMenuItem(selectedNavItemType)
	}

	/**
	 * [MainActivityContract.View] implementation
	 */

	override fun onUserClickFragmentNavMenuItem(itemSelected: NavItemModel) {
		when (itemSelected.type) {
			NavItemType.NOTES -> navController.navigate(R.id.notesFragment)
			NavItemType.TODO_LISTS -> navController.navigate(R.id.todoListsFragment)
			NavItemType.RECIPES -> navController.navigate(R.id.recipesFragment)
			NavItemType.CALENDAR -> navController.navigate(R.id.calendarFragment)
			NavItemType.ARCHIVES -> navController.navigate(R.id.archivesFragment)
			NavItemType.DELETED -> navController.navigate(R.id.deletedFragment)
			else -> navController.navigate(R.id.notesFragment)
		}
		setupToolbar(itemSelected)
	}

	override fun onUserClickScreenNavMenuItem(navItemType: NavItemType) {
		Toast.makeText(this, "Go to screen $navItemType!", Toast.LENGTH_SHORT).show()
	}

	override fun onUserClickOnAddButton() {
		val bundle = Bundle()
		bundle.putSerializable(EXTRA_NOTE_TYPE_KEY, navRecyclerAdapter.getSelectedNoteType())
		navigator.toCreateNote(this, bundle, null, false)
	}

	override fun showAddButton(show: Boolean) {
		// TODO
	}

}
