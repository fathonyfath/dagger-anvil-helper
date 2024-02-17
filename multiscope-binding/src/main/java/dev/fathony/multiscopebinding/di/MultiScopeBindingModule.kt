package dev.fathony.multiscopebinding.di

import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dev.fathony.commonscopes.ApplicationScope
import dev.fathony.multiscopebinding.DefaultMultiScopeBindingActivityIntentFactory
import dev.fathony.multiscopebinding.contract.MultiScopeBindingActivityIntentFactory

@ContributesTo(ApplicationScope::class)
@Module
interface MultiScopeBindingModule {

    @Binds
    fun bindMultiScopeBindingActivityIntentProvider(
        bind: DefaultMultiScopeBindingActivityIntentFactory
    ): MultiScopeBindingActivityIntentFactory
}
