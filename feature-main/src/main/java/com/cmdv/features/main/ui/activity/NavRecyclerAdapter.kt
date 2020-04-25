package com.cmdv.features.main.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.domain.models.NavItemModel
import com.cmdv.domain.models.NavItemType
import com.cmdv.features.R

class NavRecyclerAdapter(
	private val context: Context,
	private val listener: OnNavigationItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var items: MutableList<NavItemModel> = mutableListOf()

	private var selectedPosition = 0

	init {
		items.add(NavItemModel(true, R.drawable.ic_nav_notes_24dp, R.string.item_nav_notes, NavItemType.NOTES))
		items.add(NavItemModel(false, R.drawable.ic_nav_calendar_24dp, R.string.item_nav_calendar, NavItemType.CALENDAR))
		items.add(NavItemModel(false, R.drawable.ic_nav_archives_24dp, R.string.item_nav_archives, NavItemType.ARCHIVES))
		items.add(NavItemModel(false, R.drawable.ic_nav_deleted_24dp, R.string.item_nav_deleted, NavItemType.DELETED))
		items.add(NavItemModel(false, R.drawable.ic_nav_settings_24dp, R.string.item_nav_settings, NavItemType.SETTINGS))
		items.add(NavItemModel(false, R.drawable.ic_nav_share_24dp, R.string.item_nav_share, NavItemType.SHARE))
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

		fun bind(context: Context, listener: OnNavigationItemClickListener, item: NavItemModel, position: Int) {
			this.listener = listener
			this.context = context

			setupItemUi(item)

			itemView.setOnClickListener { listener.onClick(item, position) }
		}

		private fun setupItemUi(item: NavItemModel) {
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
		fun onClick(item: NavItemModel, position: Int)
	}

}