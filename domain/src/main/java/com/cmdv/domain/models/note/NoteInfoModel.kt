package com.cmdv.domain.models.note

import com.cmdv.domain.models.note.utils.NoteCategory
import com.cmdv.domain.models.note.utils.NotePriority
import java.util.*

abstract class NoteInfoModel(
	val title: String,
	val subtitle: String?,
	val category: NoteCategory,
	val priority: NotePriority,
	val creationDate: Date,
	val updatedDate: Date,
	val deadLineDate: Date?,
	val reminder: Boolean
)