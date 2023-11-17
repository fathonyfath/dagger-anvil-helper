package dev.fathony.featureb.di

import dev.fathony.di.DaggerSubcomponentFactory
import dev.fathony.featureb.FeatureBActivity

interface FeatureBActivityComponentFactory : DaggerSubcomponentFactory {

    fun createFeatureBActivityComponent(activity: FeatureBActivity) : FeatureBActivityComponent
}