package com.cmdv.domain.models.note

import com.cmdv.domain.models.note.utils.NoteType

data class NoteModel(
	val noteInfo: NoteInfoModel,
	val type: NoteType = NoteType.NOTE,
	val text: String
)