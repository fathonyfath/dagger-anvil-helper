package dev.fathony.daggerkspanvilinteraction.di

import dagger.Module
import dagger.Provides
import dev.fathony.daggerkspanvilinteraction.Client
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponent
import javax.inject.Singleton

@Module(
    subcomponents = [
        MainActivityComponent::class
    ]
)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideClient(): Client = Client()
}