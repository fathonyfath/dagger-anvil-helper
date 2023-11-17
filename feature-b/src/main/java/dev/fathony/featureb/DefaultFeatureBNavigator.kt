package dev.fathony.featureb

import android.content.Context
import android.content.Intent
import dev.fathony.featureb.contract.FeatureBNavigator
import javax.inject.Inject

class DefaultFeatureBNavigator @Inject constructor() : FeatureBNavigator {

    override fun createIntent(context: Context): Intent {
        return Intent(context, FeatureBActivity::class.java)
    }
}