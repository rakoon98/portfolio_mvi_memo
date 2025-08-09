package com.kun.portfolio.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kun.portfolio.data.source.local.dao.MemoDao
import com.kun.portfolio.data.source.local.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}