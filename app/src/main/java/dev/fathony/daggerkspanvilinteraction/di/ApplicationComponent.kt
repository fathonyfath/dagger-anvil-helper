package dev.fathony.daggerkspanvilinteraction.di

import android.app.Application
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dev.fathony.anvil.helper.api.DispatchingInjector
import dev.fathony.daggerkspanvilinteraction.MyApplication
import dev.fathony.daggerkspanvilinteraction.di.scope.MyApplicationScope
import dev.fathony.di.DaggerComponent
import javax.inject.Singleton

@Singleton
@MergeComponent(
    scope = MyApplicationScope::class,
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
