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
import dev.fathony.di.scope.FragmentScope
import dev.fathony.multiscopebinding.MultiScopeFragment
import dev.fathony.multiscopebinding.di.MultiScopeBindingActivityScope
import dev.fathony.multiscopebinding.di.MultiScopeSurfaceLevelFragmentKey
import dev.fathony.multiscopebinding.di.MultiScopeSurfaceLevelFragmentScope

@FragmentScope
@MergeSubcomponent(scope = MultiScopeSurfaceLevelFragmentScope::class)
interface MultiScopeSurfaceLevelFragmentEntryPoint : EntryPoint<MultiScopeFragment> {

    @Subcomponent.Factory
    interface Factory : EntryPoint.Factory<MultiScopeFragment> {
        override fun create(@BindsInstance instance: MultiScopeFragment): EntryPoint<MultiScopeFragment>
    }

    @Module(subcomponents = [MultiScopeSurfaceLevelFragmentEntryPoint::class])
    @ContributesTo(scope = MultiScopeBindingActivityScope::class)
    interface BindingModule {
        @Multibinds
        fun emptyBinding(): Map<Class<*>, EntryPoint.Factory<*>>

        @Binds
        @IntoMap
        @ClassKey(value = MultiScopeSurfaceLevelFragmentKey::class)
        fun bindMultiScopeFragmentEntryPoint(factory: Factory): EntryPoint.Factory<*>
    }
}