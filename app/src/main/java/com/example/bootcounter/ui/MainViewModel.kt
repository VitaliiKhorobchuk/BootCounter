package com.example.bootcounter.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bootcounter.repository.BootCompleteRepository
import com.example.bootcounter.utils.DependencyHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

// TODO: Add dependency injection
class MainViewModel(private val repository: BootCompleteRepository) : ViewModel() {

    private val _stateFlow = MutableStateFlow(listOf<String>())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        getAllRecords()
    }

    private fun getAllRecords() {
        viewModelScope.launch {
            repository.getRecordsList()
                .distinctUntilChanged()
                .collect {
                    _stateFlow.value = it.mapIndexed { index, bootCounterEntity ->
                        "${index + 1} - ${bootCounterEntity.timestamp}"
                    }
                }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                return MainViewModel(DependencyHelper.provideBootCompletedRepository(application)) as T
            }
        }
    }
}