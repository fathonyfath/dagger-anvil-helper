package dev.fathony.multiscopebinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import dev.fathony.anvil.helper.api.DefineMultipleEntryPoint
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.di.scope.ChildFragmentScope
import dev.fathony.di.scope.FragmentScope
import dev.fathony.multiscopebinding.di.MultiScopeBindingActivityScope
import dev.fathony.multiscopebinding.di.MultiScopeDeepLevelFragmentKey
import dev.fathony.multiscopebinding.di.MultiScopeDeepLevelFragmentScope
import dev.fathony.multiscopebinding.di.MultiScopeSurfaceLevelFragmentKey
import dev.fathony.multiscopebinding.di.MultiScopeSurfaceLevelFragmentScope
import dev.fathony.multiscopebinding.di.NestedFragmentScope
import dev.fathony.multiscopebinding.message.MessageProvider
import javax.inject.Inject

@DefineMultipleEntryPoint(
    daggerScope = FragmentScope::class,
    key = MultiScopeSurfaceLevelFragmentKey::class,
    scope = MultiScopeSurfaceLevelFragmentScope::class,
    parentScope = MultiScopeBindingActivityScope::class
)
@DefineMultipleEntryPoint(
    daggerScope = ChildFragmentScope::class,
    key = MultiScopeDeepLevelFragmentKey::class,
    scope = MultiScopeDeepLevelFragmentScope::class,
    parentScope = NestedFragmentScope::class
)
class MultiScopeFragment : Fragment() {

    @Inject
    lateinit var messageProvider: MessageProvider

    private lateinit var button: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_multi_scope, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.click_me)
        button.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Message from message provider: ${messageProvider.provideMessage()}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun inject() {
        val injector = obtainInjector().injector()
        val keyToLookup =
            if (injector.factories.containsKey(MultiScopeDeepLevelFragmentKey::class.java)) {
                MultiScopeDeepLevelFragmentKey::class.java
            } else if (injector.factories.containsKey(MultiScopeSurfaceLevelFragmentKey::class.java)) {
                MultiScopeSurfaceLevelFragmentKey::class.java
            } else {
                throw IllegalStateException()
            }
        injector.inject(this, keyToLookup)
    }

    private fun Fragment.obtainInjector(): HasInjector {
        val fragmentParentInjector = generateSequence(parentFragment) { it.parentFragment }
            .find { it is HasInjector } as? HasInjector
        if (fragmentParentInjector != null) {
            return fragmentParentInjector
        }

        if (activity is HasInjector) {
            return activity as HasInjector
        }

        val application = activity?.application
        if (application is HasInjector) {
            return application
        }

        throw IllegalArgumentException()
    }
}
