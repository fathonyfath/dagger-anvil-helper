package dev.fathony.timeprovider.di

import dagger.Binds
import dagger.Module
import dev.fathony.timeprovider.SystemTimeProvider
import dev.fathony.timeprovider.contract.TimeProvider
import javax.inject.Singleton

@Module
interface TimeProviderModule {

    @Singleton
    @Binds
    fun binds(instance: SystemTimeProvider): TimeProvider
}
