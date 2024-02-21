package dev.fathony.multiscopebinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dev.fathony.anvil.helper.api.DefineEntryPoint
import dev.fathony.anvil.helper.api.DispatchingInjector
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.commonscopes.ApplicationScope
import dev.fathony.di.scope.ActivityScope
import dev.fathony.multiscopebinding.di.MultiScopeBindingActivityScope
import javax.inject.Inject

@ActivityScope
@DefineEntryPoint(MultiScopeBindingActivityScope::class, ApplicationScope::class)
class MultiScopeBindingActivity : AppCompatActivity(), HasInjector {

    @Inject
    lateinit var injector: DispatchingInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(R.layout.activity_multi_scope_binding)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragment_container, MultiScopeFragment())
                add(R.id.fragment2_container, NestedFragment())
            }
        }
    }

    private fun inject() {
        val injector = application as? HasInjector
        injector?.injector()?.inject(this)
    }

    override fun injector(): DispatchingInjector<Any> = injector
}
