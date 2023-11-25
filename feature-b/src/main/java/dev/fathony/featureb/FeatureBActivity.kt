package dev.fathony.featureb

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dev.fathony.di.DaggerComponent
import dev.fathony.di.DaggerComponentOwner
import dev.fathony.di.applicationComponent
import dev.fathony.featurea.contract.RandomNumberGenerator
import dev.fathony.featureb.di.FeatureBActivityComponentFactory
import dev.fathony.featurecounter.api.FeatureCounterFragmentFactory
import javax.inject.Inject


class FeatureBActivity : AppCompatActivity(R.layout.activity_feature_b), DaggerComponentOwner {

    override val component: DaggerComponent<FeatureBActivity> by
    applicationComponent { factory: FeatureBActivityComponentFactory ->
        factory.createFeatureBActivityComponent(this)
    }

    private lateinit var randomNumber: Button
    private lateinit var exitButton: Button
    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var countField: TextView

    @Inject
    lateinit var randomNumberGenerator: RandomNumberGenerator

    @Inject
    lateinit var viewModel: FeatureBViewModel

    @Inject
    lateinit var featureCounterFragmentFactory: FeatureCounterFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        Log.d("FeatureBActivity", "injected fields: $randomNumberGenerator")

        randomNumber = findViewById(R.id.random_number)
        exitButton = findViewById(R.id.exit)
        incrementButton = findViewById(R.id.increment)
        decrementButton = findViewById(R.id.decrement)
        countField = findViewById(R.id.value)

        randomNumber.setOnClickListener {
            Toast.makeText(this, randomNumberGenerator.provide().toString(), Toast.LENGTH_SHORT)
                .show()
        }

        exitButton.setOnClickListener {
            finish()
        }

        incrementButton.setOnClickListener { viewModel.increment() }
        decrementButton.setOnClickListener { viewModel.decrement() }

        viewModel.countValue.observe(this) { value ->
            countField.text = value.toString()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(
                    /* containerViewId = */ R.id.fragment_container_view,
                    /* fragment = */ featureCounterFragmentFactory.createFeatureCounterFragment()
                )
            }
        }
    }
}
