package dev.fathony.daggerkspanvilinteraction.di

import android.app.Application
import dev.fathony.daggerkspanvilinteraction.MyApplication
import dev.fathony.daggerkspanvilinteraction.main.MainActivity
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponent
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponentFactory
import dev.fathony.di.DaggerComponent
import dev.fathony.featureb.FeatureBActivity
import dev.fathony.featureb.di.FeatureBActivityComponent
import dev.fathony.featureb.di.FeatureBActivityComponentFactory

class MyApplicationDaggerComponent(
    private val delegate: DaggerComponent<Application>,
    private val mainActivityComponentFactory: MainActivityComponent.Factory,
    private val featureBActivityComponentFactory: FeatureBActivityComponent.Factory,
) :
    DaggerComponent<MyApplication>,
    MainActivityComponentFactory,
    FeatureBActivityComponentFactory {

    override fun inject(target: MyApplication) = delegate.inject(target)

    override fun createMainActivityComponent(activity: MainActivity): MainActivityComponent =
        mainActivityComponentFactory.create(activity)

    override fun createFeatureBActivityComponent(activity: FeatureBActivity): FeatureBActivityComponent =
        featureBActivityComponentFactory.create(activity)
}
