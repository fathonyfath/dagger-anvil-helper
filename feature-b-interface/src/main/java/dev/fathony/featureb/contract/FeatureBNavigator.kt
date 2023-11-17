package dev.fathony.featureb.contract

import android.content.Context
import android.content.Intent

interface FeatureBNavigator {

    fun createIntent(context: Context): Intent
}