package com.cmdv.domain.base.functional

sealed class Response<out L, out R> {
    
    data class Left<out L>(val a: L) : Response<L, Nothing>()
    
    data class Right<out R>(val b: R) : Response<Nothing, R>()
    
    val isLeft: Boolean get() = this is Left<L>
    val isRight: Boolean get() = this is Right<R>
    
    fun <L> left(a: L): Left<L> = Left(a)
    fun <R> right(b: R): Right<R> = Right(b)
    
    fun either(fnL: (L) -> Any = {}, fnR: (R) -> Any): Any =
            when (this) {
                is Left -> fnL(a)
                is Right -> fnR(b)
            }
    
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Response<L, R>.flatMap(fn: (R) -> Response<L, T>): Response<L, T> =
        when (this) {
            is Response.Left -> Response.Left(a)
            is Response.Right -> fn(b)
        }

fun <T, L, R> Response<L, R>.mapLeft(fn: (L) -> T): Response<T, R> =
        when (this) {
            is Response.Left -> Response.Left(fn(a))
            is Response.Right -> Response.Right(b)
        }

fun <T, L, R> Response<L, R>.map(fn: (R) -> (T)): Response<L, T> = this.flatMap(fn.c(::right))

fun <L, R> Response<L, R>.value(): R? =
        if (isRight) (this as Response.Right<R>).b else null

