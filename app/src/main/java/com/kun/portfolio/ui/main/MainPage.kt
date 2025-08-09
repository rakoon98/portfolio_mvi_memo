package com.kun.portfolio.ui.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kun.portfolio.ui.main.content.MainMemoContent
import com.kun.portfolio.ui.main.content.MainMemoInsertBottomSheet
import com.kun.portfolio.ui.main.contract.MainEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    viewModel: MainViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                MainEffect.Saved ->
                    Toast.makeText(context, "저장 완료!", Toast.LENGTH_SHORT).show()
                is MainEffect.Error ->
                    Toast.makeText(context, effect.e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 바텀 시트 제어
    var showSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if(showSheet) {
        MainMemoInsertBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                showSheet = false
                viewModel.setOnTitle("")
                viewModel.setOnContent("")
            },
            titleValue = viewModel.title,
            onTitleValueChange = { value -> viewModel.setOnTitle(value) },
            contentValue = viewModel.content,
            onContentValueChange = { value -> viewModel.setOnContent(value) },
            insertMemo = {
                viewModel.insertMemo()
                showSheet = false
            }
        )
    }

    // # UI
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Memo") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "추가")
            }
        }
    ) { insets ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(insets)) {
            when {
                state.isLoading && state.items.isEmpty() ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                state.items.isEmpty() ->
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Meme is Empty"
                    )
                else -> {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(state.items, key = { it.id } ) {
                            MainMemoContent(it)
                        }
                    }

                }
            }
        }
    }
}