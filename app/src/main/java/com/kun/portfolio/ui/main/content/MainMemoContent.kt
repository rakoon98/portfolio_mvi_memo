package com.kun.portfolio.ui.main.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kun.portfolio.domain.model.Memo
import com.kun.portfolio.util.DateUtil.toStringFormat

@Composable
fun MainMemoContent(memo: Memo) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = memo.title,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black.copy(alpha = .8f)
                    )
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = memo.content,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black,
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            text = memo.timestamp.toStringFormat(),
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            )
        )
    }
}