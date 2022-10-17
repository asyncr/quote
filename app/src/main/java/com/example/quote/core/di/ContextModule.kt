package com.example.quote.core.di

import android.content.Context
import com.example.quote.QuotesApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    @Provides
    @Singleton //Solo va a existir una instancia de este objeto
    fun provideContext(application: QuotesApp): Context {
        //El contexto donde se va a ejecutar la aplicación
        return application.applicationContext
    }

}