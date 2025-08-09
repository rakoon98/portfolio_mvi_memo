package com.kun.portfolio.ui.main.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMemoInsertBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    titleValue: String,
    onTitleValueChange: (String) -> Unit,
    contentValue: String,
    onContentValueChange: (String) -> Unit,
    insertMemo: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        sheetState = sheetState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = titleValue,
                    onValueChange = { text -> onTitleValueChange.invoke(text) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    label = {
                        Text(
                            text = "타이틀 내용",
                            style = TextStyle(
                                color = Color.Black.copy(alpha = .6f)
                            )
                        )
                    },
                    singleLine = true,
                )
                Spacer(Modifier.size(4.dp))
                OutlinedTextField(
                    value = contentValue,
                    onValueChange = { text -> onContentValueChange.invoke(text) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    label = {
                        Text(
                            text = "메모 내용",
                            style = TextStyle(
                                color = Color.Black.copy(alpha = .6f)
                            )
                        )
                    },
                    singleLine = false,
                    maxLines = 5
                )
                Spacer(Modifier.size(10.dp))
                Row {
                    TextButton(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        onClick = { onDismissRequest.invoke() }
                    ) {
                        Text(text = "취소")
                    }
                    Spacer(Modifier.size(10.dp))
                    TextButton(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        onClick = {
                            insertMemo.invoke()
                        }
                    ) {
                        Text(text = "입력")
                    }
                }
            }
        }
    )
}