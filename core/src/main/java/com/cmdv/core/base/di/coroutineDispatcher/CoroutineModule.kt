package com.cmdv.core.base.di.coroutineDispatcher

import com.cmdv.domain.base.usecase.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoroutinesModule {

    companion object {
        const val DISPATCHER_BG_UI = "DISPATCHER_BG_UI"
        const val DISPATCHER_BG_BG = "DISPATCHER_BG_BG"
    }
    
    @Provides
    @Singleton
    @Named(DISPATCHER_BG_UI)
    fun provideCoroutinesDispatcherProviderBGtoUI(): CoroutinesDispatcherProvider =
            object : CoroutinesDispatcherProvider {
                override fun provideExecutor(): CoroutineDispatcher = Dispatchers.Default
                override fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Main
            }
    
    @Provides
    @Singleton
    @Named(DISPATCHER_BG_BG)
    fun provideCoroutinesDispatcherProviderBGtoBG(): CoroutinesDispatcherProvider =
            object : CoroutinesDispatcherProvider {
                override fun provideExecutor(): CoroutineDispatcher = Dispatchers.Default
                override fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Default
            }
    
}