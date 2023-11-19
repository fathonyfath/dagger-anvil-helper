package dev.fathony.daggerkspanvilinteraction.di

import android.app.Application
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dev.fathony.commonscopes.ApplicationScope
import dev.fathony.daggerkspanvilinteraction.MyApplication
import dev.fathony.di.DaggerComponent
import javax.inject.Singleton

@Singleton
@MergeComponent(
    scope = ApplicationScope::class,
    modules = [
        ApplicationModule::class,
        FeatureModule::class,
        MigrationModule::class,
    ]
)
interface ApplicationComponent : DaggerComponent<MyApplication> {

    fun legacyInjector(): MyApplicationDaggerComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
