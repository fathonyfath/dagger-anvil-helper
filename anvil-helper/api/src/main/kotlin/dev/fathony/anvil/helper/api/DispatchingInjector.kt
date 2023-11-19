package dev.fathony.anvil.helper.api

import javax.inject.Inject
import javax.inject.Provider

class DispatchingInjector<T : Any> @Inject constructor(
    private val factories: @JvmSuppressWildcards Map<Class<*>, Provider<EntryPoint.Factory<*>>>
) {

    fun inject(instance: T) {
        val factoryProvider = factories[instance::class.java] ?: return

        @Suppress("UNCHECKED_CAST")
        val factory = factoryProvider.get() as EntryPoint.Factory<T>
        factory.create(instance).inject(instance)
    }

    fun printFactories(): String {
        return factories.toString()
    }
}
