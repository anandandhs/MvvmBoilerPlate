package com.tcs.mvvmboilerplate.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    fun provideEventBus(): EventBus = EventBus.getDefault()

    @Provides
    @Singleton
    fun provideGlide(@ApplicationContext context: Context):RequestManager = Glide.with(context)

}