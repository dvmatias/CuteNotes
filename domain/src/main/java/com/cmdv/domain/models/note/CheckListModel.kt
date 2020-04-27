package com.cmdv.domain.models.note

import com.cmdv.domain.models.note.utils.NoteType

data class CheckListModel(
	val noteInfo: NoteInfoModel,
	val type: NoteType = NoteType.TODO_LIST,
	val listItems: List<CheckListItemModel>
) {

	data class CheckListItemModel(
		val text: String,
		var isChecked: Boolean = false
	)

}