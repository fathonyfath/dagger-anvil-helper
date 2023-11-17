package dev.fathony.featureb.di

import dagger.BindsInstance
import dagger.Subcomponent
import dev.fathony.di.DaggerComponent
import dev.fathony.di.scope.ActivityScope
import dev.fathony.featureb.FeatureBActivity

@ActivityScope
@Subcomponent
interface FeatureBActivityComponent : DaggerComponent<FeatureBActivity> {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FeatureBActivity) : FeatureBActivityComponent
    }
}