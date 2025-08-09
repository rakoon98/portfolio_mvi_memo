package com.kun.portfolio.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kun.portfolio.domain.model.Memo
import com.kun.portfolio.domain.repository.MemoRepository
import com.kun.portfolio.ui.main.contract.MainEffect
import com.kun.portfolio.ui.main.contract.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<MainEffect>()
    val effect: SharedFlow<MainEffect> = _effect.asSharedFlow()

    var title by mutableStateOf("")
        private set
    var content by mutableStateOf("")
        private set

    init {
        observeMemos()
    }

    fun setOnTitle(title: String) {
        this.title = title
    }
    fun setOnContent(content: String) {
        this.content = content
    }

    fun insertMemo() = viewModelScope.launch {
        if (title.trim().isEmpty()) return@launch
        if (content.trim().isEmpty()) return@launch

        val memo = Memo(
            title = title,
            content = content,
            timestamp = System.currentTimeMillis()
        )

        try {
            memoRepository.insertMemo(memo)
            setOnTitle("")
            setOnContent("")

            _effect.emit(MainEffect.Saved)
        } catch (e: Exception) {
            _effect.emit(MainEffect.Error(e))
        }
    }

    fun observeMemos() = viewModelScope.launch {
        memoRepository.getAllMemo()
            .onStart { _uiState.update { it.copy(isLoading = true) } }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false) }
                _effect.emit(MainEffect.Error(Exception(e)))
            }
            .collect { list ->
                _uiState.update { it.copy(items = list, isLoading = false) }
            }
    }

}