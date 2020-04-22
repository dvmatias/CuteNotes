package com.cmdv.features.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.features.R

class NavigationDrawerRecyclerAdapter(
	private val context: Context,
	private val listener: OnNavigationItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var items: MutableList<NavigationItemModel> = mutableListOf()

	private var selectedPosition = 0

	init {
		items.add(NavigationItemModel(true, R.drawable.ic_menu_camera_24dp, R.string.item_nav_notes))
		items.add(NavigationItemModel(false, R.drawable.ic_menu_camera_24dp, R.string.item_nav_calendar))
		items.add(NavigationItemModel(false, R.drawable.ic_menu_camera_24dp, R.string.item_nav_archives))
		items.add(NavigationItemModel(false, R.drawable.ic_menu_camera_24dp, R.string.item_nav_recycler_bin))
		items.add(NavigationItemModel(false, R.drawable.ic_menu_camera_24dp, R.string.item_nav_settings))
		items.add(NavigationItemModel(false, R.drawable.ic_menu_camera_24dp, R.string.item_nav_share))
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_nav_drawer_recycler, parent, false)
		return NavItemHolder(itemView)
	}

	override fun getItemCount(): Int =
		items.size

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as NavItemHolder).bind(context, listener, items[position], position)
	}

	/**
	 *
	 */
	fun setSelected(newSelectedPosition: Int) {
		if (selectedPosition == newSelectedPosition) return

		items[newSelectedPosition].isSelected = true
		items[selectedPosition].isSelected = false
		notifyDataSetChanged()

		selectedPosition = newSelectedPosition
	}

	/********************************************************************************************************************************
	 *
	 */
	class NavItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private lateinit var context: Context
		private lateinit var listener: OnNavigationItemClickListener
		private var clBackground: ConstraintLayout = itemView.findViewById(R.id.cl_background)
		private var vIndicator: View = itemView.findViewById(R.id.v_indicator)
		private var ivIcon: AppCompatImageView = itemView.findViewById(R.id.iv_icon)
		private var tvLabel: AppCompatTextView = itemView.findViewById(R.id.tv_label)

		fun bind(context: Context, listener: OnNavigationItemClickListener, item: NavigationItemModel, position: Int) {
			this.listener = listener
			this.context = context

			setupItemUi(item)

			itemView.setOnClickListener { listener.onClick(item, position) }
		}

		private fun setupItemUi(item: NavigationItemModel) {
			ivIcon.setImageResource(item.iconRes)
			tvLabel.text = context.getString(item.labelRes)
			when (item.isSelected) {
				true -> {
					vIndicator.visibility = View.VISIBLE
					clBackground.isSelected = true
				}
				false -> {
					vIndicator.visibility = View.INVISIBLE
					clBackground.isSelected = false
				}
			}
		}

	}

	interface OnNavigationItemClickListener {
		fun onClick(item: NavigationItemModel, position: Int)
	}

	data class NavigationItemModel(
		var isSelected: Boolean,
		val iconRes: Int,
		val labelRes: Int
	)

}