package com.cmdv.domain.base.exception

sealed class Failure {

	open class LocalError(val errorCode: Int = 327) : Failure()

}