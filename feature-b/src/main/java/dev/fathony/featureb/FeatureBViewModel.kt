package dev.fathony.featureb

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class FeatureBViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _countValue = savedStateHandle.getLiveData(STORED_VALUE_KEY, 0)
    val countValue: LiveData<Int> get() = _countValue

    fun increment() {
        val currentValue = _countValue.value ?: 0
        _countValue.value = currentValue + 1
    }

    fun decrement() {
        val currentValue = _countValue.value ?: 0
        _countValue.value = currentValue - 1
    }

    companion object {
        private const val STORED_VALUE_KEY = "StoredValue"
    }
}
