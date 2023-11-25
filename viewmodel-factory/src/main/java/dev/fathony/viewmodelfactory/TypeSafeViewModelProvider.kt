package dev.fathony.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class TypeSafeViewModelProvider<out V : ViewModel> internal constructor(
    owner: ViewModelStoreOwner,
    private val factory: TypeSafeViewModelProviderFactory<V>,
) {
    private val managedProvider = ViewModelProvider(owner, factory.newFactory())

    fun get(): V = managedProvider[factory.viewModelClass]

    operator fun get(key: String) = managedProvider[key, factory.viewModelClass]
}
