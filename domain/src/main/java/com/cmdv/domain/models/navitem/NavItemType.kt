package com.cmdv.domain.models.navitem

enum class NavItemType(val isSelectable: Boolean) {
	NOTES(true),
	TODO_LISTS(true),
	RECIPES(true),
	CALENDAR(true),
	ARCHIVES(true),
	DELETED(true),
	SETTINGS(false),
	SHARE(false),
}
