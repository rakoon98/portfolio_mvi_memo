package com.kun.portfolio.data.mapper

import com.kun.portfolio.data.source.local.entity.MemoEntity
import com.kun.portfolio.domain.model.Memo

fun MemoEntity.toDomain(): Memo =
    Memo(id = id, title = title, content = content, timestamp = timestamp)

fun Memo.toEntity(): MemoEntity =
    MemoEntity(id = 0, title = title, content = content, timestamp = timestamp)
