package com.kun.portfolio.di

import com.kun.portfolio.data.repsitory.MemoRepositoryImpl
import com.kun.portfolio.data.source.local.dao.MemoDao
import com.kun.portfolio.domain.repository.MemoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMemoRepository(memoDao: MemoDao): MemoRepository = MemoRepositoryImpl(memoDao)

}