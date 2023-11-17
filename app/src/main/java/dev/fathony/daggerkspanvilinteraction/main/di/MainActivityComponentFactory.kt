package dev.fathony.daggerkspanvilinteraction.main.di

import dev.fathony.daggerkspanvilinteraction.main.MainActivity
import dev.fathony.di.DaggerComponent
import dev.fathony.di.DaggerSubcomponentFactory

interface MainActivityComponentFactory : DaggerSubcomponentFactory {

    fun createMainActivityComponent(activity: MainActivity) : MainActivityComponent
}
