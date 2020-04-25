package com.cmdv.domain.models

enum class NavItemType(val isSelectable: Boolean) {
	NOTES(true),
	CALENDAR(true),
	ARCHIVES(true),
	DELETED(true),
	SETTINGS(false),
	SHARE(false),
}
