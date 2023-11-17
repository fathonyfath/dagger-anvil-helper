package dev.fathony.featurea.di

import dagger.Binds
import dagger.Module
import dev.fathony.featurea.DefaultRandomNumberGenerator
import dev.fathony.featurea.contract.RandomNumberGenerator
import javax.inject.Singleton

@Module
interface FeatureAModule {

    @Singleton
    @Binds
    fun DefaultRandomNumberGenerator.binds(): RandomNumberGenerator
}
