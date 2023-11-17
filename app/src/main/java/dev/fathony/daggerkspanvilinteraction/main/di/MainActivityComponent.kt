package dev.fathony.daggerkspanvilinteraction.main.di

import dagger.BindsInstance
import dagger.Subcomponent
import dev.fathony.daggerkspanvilinteraction.main.MainActivity
import dev.fathony.di.DaggerComponent
import dev.fathony.di.DaggerSubcomponentFactory
import dev.fathony.di.scope.ActivityScope

@ActivityScope
@Subcomponent
interface MainActivityComponent : DaggerComponent<MainActivity> {

    @Subcomponent.Factory
    interface Factory : DaggerSubcomponentFactory {
        fun create(@BindsInstance target: MainActivity) : MainActivityComponent
    }
}
