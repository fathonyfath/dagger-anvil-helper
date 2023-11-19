package dev.fathony.daggerkspanvilinteraction.di

import dagger.Module
import dev.fathony.featureb.di.FeatureBModule
import dev.fathony.timeprovider.di.TimeProviderModule

@Module(
    includes = [
        TimeProviderModule::class,
        FeatureBModule::class,
    ]
)
object FeatureModule
