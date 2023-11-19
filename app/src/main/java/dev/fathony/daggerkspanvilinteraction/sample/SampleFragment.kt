package dev.fathony.daggerkspanvilinteraction.sample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.fathony.anvil.helper.api.DefineEntryPoint
import dev.fathony.anvil.helper.api.DispatchingInjector
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.daggerkspanvilinteraction.Client
import dev.fathony.daggerkspanvilinteraction.databinding.FragmentSampleBinding
import dev.fathony.daggerkspanvilinteraction.di.scopes.SampleActivityScope
import dev.fathony.daggerkspanvilinteraction.di.scopes.SampleFragmentScope
import dev.fathony.di.scope.FragmentScope
import javax.inject.Inject

@FragmentScope
@DefineEntryPoint(SampleFragmentScope::class, SampleActivityScope::class)
class SampleFragment : Fragment() {

    private var _binding: FragmentSampleBinding? = null
    private val binding: FragmentSampleBinding get() = _binding!!

    @Inject
    lateinit var client: Client

    @Inject
    lateinit var dispatchingInjector: DispatchingInjector<SampleFragment>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun Fragment.inject() {
        val firstHasInjector = generateSequence(parentFragment) { it.parentFragment }
            .find { it is HasInjector }
        if (firstHasInjector != null && firstHasInjector is HasInjector) {
            firstHasInjector.injector().inject(this)
            return
        }

        val activity = activity
        if (activity != null && activity is HasInjector) {
            activity.injector().inject(this)
            return
        }

        val application = activity?.application
        if (application != null && application is HasInjector) {
            application.injector().inject(this)
            return
        }
    }
}
