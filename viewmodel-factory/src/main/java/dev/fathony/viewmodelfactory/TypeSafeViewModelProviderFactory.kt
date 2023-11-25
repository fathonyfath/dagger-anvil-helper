package dev.fathony.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

interface TypeSafeViewModelProviderFactory<out V : ViewModel> {

    val viewModelClass: Class<out V>
    fun newFactory(): ViewModelProvider.Factory
    fun asProvider(owner: ViewModelStoreOwner): TypeSafeViewModelProvider<V> =
        TypeSafeViewModelProvider(owner, this)
}
