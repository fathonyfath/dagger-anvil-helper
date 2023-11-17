package dev.fathony.daggerkspanvilinteraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import dev.fathony.anvil.helper.api.EntryPoint
import dev.fathony.daggerkspanvilinteraction.di.scope.MyApplicationScope
import dev.fathony.daggerkspanvilinteraction.di.scope.SampleActivityScope
import dev.fathony.di.scope.ActivityScope

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }
}

@ActivityScope
@MergeSubcomponent(SampleActivityScope::class)
interface SampleActivityEntryPoint : EntryPoint<SampleActivity> {

    @Subcomponent.Factory
    interface Factory : EntryPoint.Factory<SampleActivity> {
        override fun create(@BindsInstance instance: SampleActivity): EntryPoint<SampleActivity>
    }
}

@Module(subcomponents = [SampleActivityEntryPoint::class])
@ContributesTo(MyApplicationScope::class)
interface SampleActivityModule {

    @Multibinds
    fun factories(): Map<Class<*>, EntryPoint.Factory<*>>

    @Binds
    @IntoMap
    @ClassKey(SampleActivity::class)
    fun bindsSampleActivityEntryPointFactory(factory: SampleActivityEntryPoint.Factory): EntryPoint.Factory<*>
}
