package dev.fathony.daggerkspanvilinteraction.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.fathony.daggerkspanvilinteraction.Client
import dev.fathony.daggerkspanvilinteraction.sample.SampleActivity
import dev.fathony.daggerkspanvilinteraction.databinding.ActivityMainBinding
import dev.fathony.daggerkspanvilinteraction.main.di.MainActivityComponentFactory
import dev.fathony.di.DaggerComponent
import dev.fathony.di.DaggerComponentOwner
import dev.fathony.di.applicationComponent
import dev.fathony.featurea.contract.RandomNumberGenerator
import dev.fathony.featureb.contract.FeatureBNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(), DaggerComponentOwner {

    override val component: DaggerComponent<MainActivity>
            by applicationComponent { component: MainActivityComponentFactory ->
                component.createMainActivityComponent(this)
            }

    @Inject
    lateinit var client: Client

    @Inject
    lateinit var randomNumberGenerator: RandomNumberGenerator

    @Inject
    lateinit var featureBNavigator: FeatureBNavigator

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "injected fields: $client, $randomNumberGenerator, $featureBNavigator")

        binding.button.setOnClickListener {
            Toast.makeText(this, "random: ${randomNumberGenerator.provide()}", Toast.LENGTH_SHORT).show()
        }

        binding.navigationButton.setOnClickListener {
            startActivity(featureBNavigator.createIntent(this))
        }

        binding.sample.setOnClickListener {
            startActivity(Intent(this, SampleActivity::class.java))
        }
    }
}
