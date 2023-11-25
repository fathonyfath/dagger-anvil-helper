package dev.fathony.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

abstract class TypeSafeViewModelFactory<out V : ViewModel> protected constructor(
    override val viewModelClass: Class<out V>,
) : TypeSafeViewModelProviderFactory<V> {

    protected abstract fun create(extras: CreationExtras): V

    override fun newFactory(): ViewModelProvider.Factory = ManagedViewModelFactoryImpl()

    private inner class ManagedViewModelFactoryImpl : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return try {
                modelClass.cast(create(extras))!!
            } catch (throwable: ClassCastException) {
                val message = "$modelClass is not compatible with $viewModelClass."
                throw IllegalArgumentException(message, throwable)
            }
        }
    }

    companion object {
        inline fun <reified V : ViewModel> with(
            crossinline provider: () -> V
        ): TypeSafeViewModelFactory<V> {
            return object : TypeSafeViewModelFactory<V>(V::class.java) {
                override fun create(extras: CreationExtras): V = provider()
            }
        }

        inline fun <reified V : ViewModel> withExtras(
            crossinline provider: (CreationExtras) -> V
        ): TypeSafeViewModelFactory<V> {
            return object : TypeSafeViewModelFactory<V>(V::class.java) {
                override fun create(extras: CreationExtras): V = provider(extras)
            }
        }
    }
}
