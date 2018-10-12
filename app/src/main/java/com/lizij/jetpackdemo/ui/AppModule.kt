package com.lizij.jetpackdemo.ui

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private var application: Application){

    @Provides
    @Singleton
    fun provideApplication(): Application = application
}