package com.cmdv.features.main.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cmdv.domain.models.navitem.NavItemModel
import com.cmdv.domain.models.navitem.NavItemType
import com.cmdv.domain.models.note.utils.NoteType
import com.cmdv.features.R

const val SELECTED_INDEX_INIT = -1

class NavRecyclerAdapter(
	private val context: Context,
	private val listener: OnNavigationItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var items: ArrayList<NavItemModel> = arrayListOf(
		NavItemModel(true, R.drawable.ic_nav_notes_24dp, context.getString(R.string.item_nav_notes), NavItemType.NOTES),
		NavItemModel(false, R.drawable.ic_nav_todo_lists_24dp, context.getString(R.string.item_nav_todo_lists_label), NavItemType.TODO_LISTS),
		NavItemModel(false, R.drawable.ic_nav_recipes_24dp, context.getString(R.string.item_nav_recipes_label), NavItemType.RECIPES),
		NavItemModel(false, R.drawable.ic_nav_calendar_24dp, context.getString(R.string.item_nav_calendar_label), NavItemType.CALENDAR),
		NavItemModel(false, R.drawable.ic_nav_archives_24dp, context.getString(R.string.item_nav_archives_label), NavItemType.ARCHIVES),
		NavItemModel(false, R.drawable.ic_nav_deleted_24dp, context.getString(R.string.item_nav_deleted_label), NavItemType.DELETED),
		NavItemModel(false, R.drawable.ic_nav_settings_24dp, context.getString(R.string.item_nav_settings_label), NavItemType.SETTINGS),
		NavItemModel(false, R.drawable.ic_nav_share_24dp, context.getString(R.string.item_nav_share_label), NavItemType.SHARE)
	)

	private var selectedPosition: Int = SELECTED_INDEX_INIT

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
		val itemSelected = items[newSelectedPosition]
		val itemSelectedType = itemSelected.type

		// Click on screen nav menu item
		if (!itemSelected.isSelectable) {
			listener.onNavSelectionScreen(itemSelectedType)
			return
		}
		if (selectedPosition == newSelectedPosition) {
			return
		}

		itemSelected.isSelected = true
		if (SELECTED_INDEX_INIT != selectedPosition) items[selectedPosition].isSelected = false
		notifyDataSetChanged()

		selectedPosition = newSelectedPosition
		listener.onNavSelectionChanged(itemSelected)
	}

	fun getSelectedNoteType(): NoteType? =
		when (items[selectedPosition].type) {
			NavItemType.NOTES -> NoteType.NOTE
			NavItemType.TODO_LISTS -> NoteType.TODO_LIST
			NavItemType.RECIPES -> NoteType.RECIPE
			else -> null
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

			itemView.setOnClickListener {
				listener.onNavItemClick(item, position)
			}
		}

		private fun setupItemUi(item: NavItemModel) {
			ivIcon.setImageResource(item.iconRes)
			tvLabel.text = item.label
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

		fun onNavItemClick(item: NavItemModel, position: Int)

		fun onNavSelectionChanged(itemSelected: NavItemModel)

		fun onNavSelectionScreen(selectedNavItemType: NavItemType)

	}

}