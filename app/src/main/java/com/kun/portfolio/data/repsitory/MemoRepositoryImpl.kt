package com.kun.portfolio.data.repsitory

import com.kun.portfolio.data.mapper.toDomain
import com.kun.portfolio.data.mapper.toEntity
import com.kun.portfolio.data.source.local.dao.MemoDao
import com.kun.portfolio.domain.model.Memo
import com.kun.portfolio.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoRepository {
    override suspend fun insertMemo(memo: Memo) {
        memoDao.insertMemo(memo.toEntity())
    }

    override suspend fun getAllMemo(): Flow<List<Memo>> =
        memoDao.getAllMemo()
            .map { memoList ->
                memoList.map { memo ->
                    memo.toDomain()
                }
            }


    override suspend fun deleteMemo() {

    }

    override suspend fun modifyMemo() {

    }
}