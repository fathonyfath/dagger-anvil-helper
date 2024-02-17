package dev.fathony.multiscopebinding

import android.content.Context
import android.content.Intent
import dev.fathony.multiscopebinding.contract.MultiScopeBindingActivityIntentFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMultiScopeBindingActivityIntentFactory @Inject constructor() :
    MultiScopeBindingActivityIntentFactory {

    override fun createIntent(context: Context): Intent {
        return Intent(context, MultiScopeBindingActivity::class.java)
    }
}
