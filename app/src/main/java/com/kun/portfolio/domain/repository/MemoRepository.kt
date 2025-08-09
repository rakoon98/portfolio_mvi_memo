package com.kun.portfolio.domain.repository

import com.kun.portfolio.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {

    suspend fun insertMemo(memo: Memo)
    suspend fun getAllMemo() : Flow<List<Memo>>
    suspend fun deleteMemo()
    suspend fun modifyMemo()

}