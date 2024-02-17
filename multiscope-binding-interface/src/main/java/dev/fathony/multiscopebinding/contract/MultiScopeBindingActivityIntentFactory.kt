package dev.fathony.multiscopebinding.contract

import android.content.Context
import android.content.Intent

interface MultiScopeBindingActivityIntentFactory {
    
    fun createIntent(context: Context): Intent
}
