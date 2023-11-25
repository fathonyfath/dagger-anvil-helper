package dev.fathony.featurecounter.di

import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dev.fathony.commonscopes.ApplicationScope
import dev.fathony.featurecounter.FeatureCounterFragmentFactoryImpl
import dev.fathony.featurecounter.api.FeatureCounterFragmentFactory

@Module
@ContributesTo(ApplicationScope::class)
interface FeatureCounterModule {

    @Binds
    fun bindFeatureCounterFragmentFactoryImpl(
        instance: FeatureCounterFragmentFactoryImpl
    ): FeatureCounterFragmentFactory
}
