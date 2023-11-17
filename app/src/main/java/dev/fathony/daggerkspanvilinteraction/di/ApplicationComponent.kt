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
    ]
)
abstract class ApplicationComponent :
    DaggerComponent<MyApplication>,
    MainActivityComponentFactory,
    FeatureBActivityComponentFactory {

    abstract fun mainActivityComponentFactory(): MainActivityComponent.Factory

    override fun createMainActivityComponent(activity: MainActivity): MainActivityComponent =
        mainActivityComponentFactory().create(activity)

    abstract fun featureBActivityComponentFactory(): FeatureBActivityComponent.Factory

    override fun createFeatureBActivityComponent(activity: FeatureBActivity): FeatureBActivityComponent =
        featureBActivityComponentFactory().create(activity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
