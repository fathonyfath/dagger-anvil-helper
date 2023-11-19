package dev.fathony.daggerkspanvilinteraction.sample

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.fathony.anvil.helper.api.DefineEntryPoint
import dev.fathony.anvil.helper.api.DispatchingInjector
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.daggerkspanvilinteraction.Client
import dev.fathony.daggerkspanvilinteraction.MyApplication
import dev.fathony.daggerkspanvilinteraction.R
import dev.fathony.daggerkspanvilinteraction.di.scope.MyApplicationScope
import dev.fathony.daggerkspanvilinteraction.di.scope.SampleActivityScope
import dev.fathony.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
@DefineEntryPoint(SampleActivityScope::class, MyApplicationScope::class)
class SampleActivity : AppCompatActivity() {

    @Inject
    lateinit var client: Client

    @Inject
    lateinit var dispatchingInjector: DispatchingInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        inject()
    }

    private fun Activity.inject() {
        val application = application as MyApplication
        val hasInjector = application as HasInjector
        hasInjector.injector().inject(this)
    }
}
