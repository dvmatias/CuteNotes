package com.cmdv.domain.models.note

import com.cmdv.domain.models.note.utils.NoteType

data class RecipeModel(
	val noteInfo: NoteInfoModel,
	val type: NoteType = NoteType.RECIPE
)