package dev.fathony.featureb.di

import dagger.Binds
import dagger.Module
import dev.fathony.featureb.DefaultFeatureBNavigator
import dev.fathony.featureb.contract.FeatureBNavigator
import javax.inject.Singleton

@Module(
    subcomponents = [FeatureBActivityComponent::class]
)
interface FeatureBModule {

    @Singleton
    @Binds
    fun DefaultFeatureBNavigator.binds(): FeatureBNavigator
}