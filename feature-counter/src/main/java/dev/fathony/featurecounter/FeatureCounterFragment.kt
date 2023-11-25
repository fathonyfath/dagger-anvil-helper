package dev.fathony.featurecounter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.fathony.anvil.helper.api.DefineEntryPoint
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.commonscopes.ApplicationScope
import dev.fathony.di.scope.FragmentScope
import dev.fathony.featureb.R
import dev.fathony.featurecounter.di.FeatureCounterFragmentScope
import javax.inject.Inject

@FragmentScope
@DefineEntryPoint(FeatureCounterFragmentScope::class, ApplicationScope::class)
class FeatureCounterFragment : Fragment() {

    private lateinit var decrement: Button
    private lateinit var increment: Button
    private lateinit var counter: TextView

    @Inject
    lateinit var viewModel: FeatureCounterViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        obtainInjector().injector().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        decrement = view.findViewById(R.id.decrement)
        increment = view.findViewById(R.id.increment)
        counter = view.findViewById(R.id.counter)

        viewModel.counter.observe(viewLifecycleOwner) { counterValue ->
            counter.text = counterValue.toString()
        }

        decrement.setOnClickListener { viewModel.decrease() }
        increment.setOnClickListener { viewModel.increase() }
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
