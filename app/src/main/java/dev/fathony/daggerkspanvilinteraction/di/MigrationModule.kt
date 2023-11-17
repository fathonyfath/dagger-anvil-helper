package dev.fathony.daggerkspanvilinteraction.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponent
import dev.fathony.di.DaggerComponent
import dev.fathony.featureb.di.FeatureBActivityComponent

@Module
class MigrationModule {

    @Provides
    fun providesMyApplicationDaggerComponent(
        applicationComponent: ApplicationComponent,
        mainActivityComponentFactory: MainActivityComponent.Factory,
        featureBActivityComponentFactory: FeatureBActivityComponent.Factory,
    ): MyApplicationDaggerComponent {
        @Suppress("UNCHECKED_CAST")
        return MyApplicationDaggerComponent(
            applicationComponent as DaggerComponent<Application>,
            mainActivityComponentFactory,
            featureBActivityComponentFactory,
        )
    }
}
