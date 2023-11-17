package dev.fathony.anvil.helper.api

typealias EntryPointFactories = Map<Class<*>, EntryPoint.Factory<*>>

interface HasInjector {
    fun injectors(): EntryPointFactories
}
