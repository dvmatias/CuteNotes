package com.cmdv.domain.models

data class NavItemModel(
	var isSelected: Boolean,
	val iconRes: Int,
	val labelRes: Int,
	val type: NavItemType
)