package dev.fathony.daggerkspanvilinteraction.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.fathony.daggerkspanvilinteraction.MyApplication
import dev.fathony.daggerkspanvilinteraction.main.MainActivity
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponent
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponentFactory
import dev.fathony.di.DaggerComponent
import dev.fathony.featureb.FeatureBActivity
import dev.fathony.featureb.di.FeatureBActivityComponent
import dev.fathony.featureb.di.FeatureBActivityComponentFactory
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        FeatureModule::class,
        MigrationModule::class,
    ]
)
interface ApplicationComponent :
    DaggerComponent<MyApplication> {

    fun legacyInjector(): MyApplicationDaggerComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
