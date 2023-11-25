package dev.fathony.featurecounter.api

import androidx.fragment.app.Fragment

interface FeatureCounterFragmentFactory {
    fun createFeatureCounterFragment(): Fragment
}
