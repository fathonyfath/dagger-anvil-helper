package dev.fathony.multiscopebinding.message

import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dev.fathony.multiscopebinding.di.MultiScopeDeepLevelFragmentScope
import dev.fathony.multiscopebinding.di.MultiScopeSurfaceLevelFragmentScope

@Module
@ContributesTo(MultiScopeSurfaceLevelFragmentScope::class)
interface MultiScopeSurfaceLevelFragmentModule {
    @Binds
    fun bindSurfaceLevelMessageProvider(messageProvider: SurfaceLevelMessageProvider): MessageProvider
}

@Module
@ContributesTo(MultiScopeDeepLevelFragmentScope::class)
interface MultiScopeDeepLevelFragmentModule {
    @Binds
    fun bindDeepLevelMessageProvider(messageProvider: DeepLevelMessageProvider): MessageProvider
}