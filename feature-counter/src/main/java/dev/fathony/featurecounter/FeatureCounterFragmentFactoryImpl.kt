package dev.fathony.featurecounter

import androidx.fragment.app.Fragment
import dev.fathony.featurecounter.api.FeatureCounterFragmentFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureCounterFragmentFactoryImpl @Inject constructor() : FeatureCounterFragmentFactory {
    override fun createFeatureCounterFragment(): Fragment {
        return FeatureCounterFragment()
    }
}
