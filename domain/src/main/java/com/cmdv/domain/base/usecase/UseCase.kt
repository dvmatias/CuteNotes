package com.cmdv.domain.base.usecase

import com.cmdv.domain.base.exception.Failure
import com.cmdv.domain.base.functional.Response
import kotlinx.coroutines.*

interface CoroutinesDispatcherProvider {
    fun provideExecutor(): CoroutineDispatcher
    fun provideDispatcher(): CoroutineDispatcher
}

interface UseCaseInvocableContract<out T, in Params> : UseCaseContract {
    val dispatcherProvider: CoroutinesDispatcherProvider
    operator fun invoke(onResult: (Response<Failure, T>) -> Unit, params: Params)
}


abstract class UseCase<out T, in Params> : UseCaseInvocableContract<T, Params> where T : Any {
    
    private var job: Deferred<Response<Failure, T>>? = null
    
    override val dispatcherProvider: CoroutinesDispatcherProvider
        get() =
            object : CoroutinesDispatcherProvider {
                override fun provideExecutor(): CoroutineDispatcher = Dispatchers.Default
                override fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Main
            }
    
    abstract suspend fun run(params: Params): Response<Failure, T>
    
    override operator fun invoke(onResult: (Response<Failure, T>) -> Unit, params: Params) {
        job = GlobalScope.async(dispatcherProvider.provideExecutor()) { run(params) }
        GlobalScope.launch(dispatcherProvider.provideDispatcher()) { onResult.invoke(job!!.await()) }
    }
    
    override fun cancel() {
        job?.cancel()
    }
    
    object None
    
}

abstract class UseCaseCustomDispatcher<out T : Any, in Params>(private val customDispatcherProvider: CoroutinesDispatcherProvider) : UseCase<T, Params>() {
    override val dispatcherProvider: CoroutinesDispatcherProvider
        get() = customDispatcherProvider
}