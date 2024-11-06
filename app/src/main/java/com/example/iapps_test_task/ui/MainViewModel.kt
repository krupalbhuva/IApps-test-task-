package com.example.iapps_test_task.ui

import androidx.lifecycle.ViewModel
import com.example.iapps_test_task.model.ItemsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableSharedFlow<MainUiState>(1)
    val uiState = _uiState.asSharedFlow()

    init {
        getFeedData()
    }

    private fun getFeedData() {}
}

sealed class MainUiState {
    data object Loading : MainUiState()

    data class FeedData(val feeds: List<ItemsItem>) : MainUiState()

    data class FeedError(val message: String) : MainUiState()
}