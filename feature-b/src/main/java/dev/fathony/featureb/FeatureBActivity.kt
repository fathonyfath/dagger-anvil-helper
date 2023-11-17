package dev.fathony.featureb

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.fathony.di.DaggerComponent
import dev.fathony.di.DaggerComponentOwner
import dev.fathony.di.applicationComponent
import dev.fathony.featurea.contract.RandomNumberGenerator
import dev.fathony.featureb.di.FeatureBActivityComponentFactory
import javax.inject.Inject


class FeatureBActivity : AppCompatActivity(R.layout.activity_feature_b), DaggerComponentOwner {

    override val component: DaggerComponent<FeatureBActivity> by
    applicationComponent { factory: FeatureBActivityComponentFactory ->
        factory.createFeatureBActivityComponent(this)
    }

    private lateinit var randomNumber: Button
    private lateinit var exitButton: Button

    @Inject
    lateinit var randomNumberGenerator: RandomNumberGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        Log.d("FeatureBActivity", "injected fields: $randomNumberGenerator")

        randomNumber = findViewById(R.id.random_number)
        exitButton = findViewById(R.id.exit)

        randomNumber.setOnClickListener {
            Toast.makeText(this, randomNumberGenerator.provide().toString(), Toast.LENGTH_SHORT)
                .show()
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}