package dev.fathony.daggerkspanvilinteraction

import android.app.Application
import android.util.Log
import dev.fathony.anvil.helper.api.HasInjector
import dev.fathony.anvil.helper.api.DispatchingInjector
import dev.fathony.daggerkspanvilinteraction.di.ApplicationComponent
import dev.fathony.daggerkspanvilinteraction.di.DaggerApplicationComponent
import dev.fathony.di.DaggerComponent
import dev.fathony.di.DaggerComponentOwner
import javax.inject.Inject

class MyApplication : Application(), DaggerComponentOwner, HasInjector {

    private lateinit var _component: ApplicationComponent

    override val component: DaggerComponent<*> get() = _component.legacyInjector()

    @Inject
    lateinit var client: Client

    @Inject
    lateinit var dispatchingInjector: DispatchingInjector<Any>

    override fun injector(): DispatchingInjector<Any> = dispatchingInjector

    override fun onCreate() {
        super.onCreate()

        _component = DaggerApplicationComponent.factory().create(this)
        _component.inject(this)

        Log.d("Factories", "${this::class.java.simpleName}: ${dispatchingInjector.printFactories()}")

        Log.d("MyApplication", "client: $client")
    }
}
