package com.cmdv.core.base.di

import android.app.Application
import android.content.Context
import com.cmdv.core.base.BaseApplication
import com.cmdv.core.navigator.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule(private val app: BaseApplication) {
    
    @Provides
    @Singleton
    fun provideApplication(): Application = app
    
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = BaseApplication.navigator
    
}