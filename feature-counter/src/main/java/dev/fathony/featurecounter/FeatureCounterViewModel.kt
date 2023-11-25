package dev.fathony.featurecounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class FeatureCounterViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _counter = savedStateHandle.getLiveData(COUNTER_KEY, 0)
    val counter: LiveData<Int> get() = _counter

    fun increase() {
        val current = _counter.value ?: 0
        _counter.value = current + 1
    }

    fun decrease() {
        val current = _counter.value ?: 0
        _counter.value = current - 1
    }

    companion object {
        private const val COUNTER_KEY = "Counter"
    }
}
