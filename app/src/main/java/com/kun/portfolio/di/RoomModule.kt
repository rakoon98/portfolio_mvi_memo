package com.kun.portfolio.di

import android.content.Context
import androidx.room.Room
import com.kun.portfolio.data.source.local.dao.MemoDao
import com.kun.portfolio.data.source.local.database.MemoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MemoDatabase =
        Room.databaseBuilder(context, MemoDatabase::class.java, "memo_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMemoDao(db: MemoDatabase): MemoDao = db.memoDao()

}