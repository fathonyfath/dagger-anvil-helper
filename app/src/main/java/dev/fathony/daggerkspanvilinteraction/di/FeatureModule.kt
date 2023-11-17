package dev.fathony.daggerkspanvilinteraction.di

import dagger.Module
import dev.fathony.featurea.di.FeatureAModule
import dev.fathony.featureb.di.FeatureBModule
import dev.fathony.timeprovider.di.TimeProviderModule

@Module(
    includes = [
        TimeProviderModule::class,
        FeatureAModule::class,
        FeatureBModule::class,
    ]
)
object FeatureModule
