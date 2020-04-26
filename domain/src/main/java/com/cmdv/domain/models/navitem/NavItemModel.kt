package com.cmdv.domain.models.navitem

data class NavItemModel(
	var isSelected: Boolean,
	val iconRes: Int,
	val labelRes: Int,
	val type: NavItemType
) {

	val isSelectable: Boolean
		get() {
		return type.isSelectable
	}

}