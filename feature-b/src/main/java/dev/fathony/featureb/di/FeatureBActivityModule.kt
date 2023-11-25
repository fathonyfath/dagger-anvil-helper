package dev.fathony.featureb.di

import androidx.lifecycle.createSavedStateHandle
import dagger.Module
import dagger.Provides
import dev.fathony.featureb.FeatureBActivity
import dev.fathony.featureb.FeatureBViewModel
import dev.fathony.viewmodelfactory.TypeSafeViewModelFactory

@Module
object FeatureBActivityModule {

    @Provides
    fun provideFeatureBViewModel(
        activity: FeatureBActivity,
    ): FeatureBViewModel {
        return TypeSafeViewModelFactory.withExtras { extras ->
            return@withExtras FeatureBViewModel(extras.createSavedStateHandle())
        }
            .asProvider(activity)
            .get()
    }
}
