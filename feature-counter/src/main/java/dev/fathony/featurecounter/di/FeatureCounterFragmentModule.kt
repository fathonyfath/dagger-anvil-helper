package dev.fathony.featurecounter.di

import androidx.lifecycle.createSavedStateHandle
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dev.fathony.featurecounter.FeatureCounterFragment
import dev.fathony.featurecounter.FeatureCounterViewModel
import dev.fathony.viewmodelfactory.TypeSafeViewModelFactory

@Module
@ContributesTo(FeatureCounterFragmentScope::class)
object FeatureCounterFragmentModule {

    @Provides
    fun provideFeatureCounterViewModel(fragment: FeatureCounterFragment): FeatureCounterViewModel {
        return TypeSafeViewModelFactory.withExtras { extras ->
            return@withExtras FeatureCounterViewModel(extras.createSavedStateHandle())
        }
            .asProvider(fragment)
            .get()
    }
}
