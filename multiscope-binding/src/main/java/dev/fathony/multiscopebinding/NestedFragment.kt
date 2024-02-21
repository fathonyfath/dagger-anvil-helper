package dev.fathony.multiscopebinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dev.fathony.anvil.helper.api.DefineEntryPoint
import dev.fathony.anvil.helper.api.DispatchingInjector
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.di.scope.FragmentScope
import dev.fathony.multiscopebinding.di.MultiScopeBindingActivityScope
import dev.fathony.multiscopebinding.di.NestedFragmentScope
import javax.inject.Inject

@FragmentScope
@DefineEntryPoint(NestedFragmentScope::class, MultiScopeBindingActivityScope::class)
class NestedFragment : Fragment(), HasInjector {

    @Inject
    lateinit var injector: DispatchingInjector<Any>

    override fun injector(): DispatchingInjector<Any> = injector

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nested, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        if (savedInstanceState == null) {
            childFragmentManager.commit { 
                add(R.id.fragment_container, MultiScopeFragment())
            }
        }
    }

    private fun inject() {
        val injector = obtainInjector()
        injector.injector().inject(this)
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
