package dev.fathony.multiscopebinding.di.entrypoint

import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import dev.fathony.anvil.helper.api.EntryPoint
import dev.fathony.di.scope.ChildFragmentScope
import dev.fathony.multiscopebinding.MultiScopeFragment
import dev.fathony.multiscopebinding.di.MultiScopeDeepLevelFragmentKey
import dev.fathony.multiscopebinding.di.MultiScopeDeepLevelFragmentScope
import dev.fathony.multiscopebinding.di.NestedFragmentScope

@ChildFragmentScope
@MergeSubcomponent(MultiScopeDeepLevelFragmentScope::class)
interface MultiScopeDeepLevelFragmentEntryPoint : EntryPoint<MultiScopeFragment> {

    @Subcomponent.Factory
    interface Factory : EntryPoint.Factory<MultiScopeFragment> {
        override fun create(@BindsInstance instance: MultiScopeFragment): EntryPoint<MultiScopeFragment>
    }

    @Module(subcomponents = [MultiScopeDeepLevelFragmentEntryPoint::class])
    @ContributesTo(scope = NestedFragmentScope::class)
    interface BindingModule {
        @Multibinds
        fun emptyBinding(): Map<Class<*>, EntryPoint.Factory<*>>

        @Binds
        @IntoMap
        @ClassKey(value = MultiScopeDeepLevelFragmentKey::class)
        fun bindMultiScopeFragmentEntryPoint(factory: Factory): EntryPoint.Factory<*>
    }
}