package dev.fathony.featurea.di

import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dev.fathony.commonscopes.ApplicationScope
import dev.fathony.featurea.DefaultRandomNumberGenerator
import dev.fathony.featurea.contract.RandomNumberGenerator
import javax.inject.Singleton

@Module
@ContributesTo(ApplicationScope::class)
interface FeatureAModule {

    @Singleton
    @Binds
    fun bindsDefaultRandomNumberGenerator(instance: DefaultRandomNumberGenerator): RandomNumberGenerator
}
