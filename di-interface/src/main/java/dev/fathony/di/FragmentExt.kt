package dev.fathony.di

import android.app.Activity
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty

inline fun <reified F : DaggerSubcomponentFactory, T : Activity> Fragment.applicationComponent(
    noinline create: (F) -> DaggerComponent<T>,
): ReadOnlyProperty<T, DaggerComponent<T>> = createComponentDelegate(
    factoryClass = F::class,
    parentProducer = { requireActivity().application },
    create = create,
)

inline fun <reified F : DaggerSubcomponentFactory, T : Activity> Fragment.activityComponent(
    noinline create: (F) -> DaggerComponent<T>,
): ReadOnlyProperty<T, DaggerComponent<T>> = createComponentDelegate(
    factoryClass = F::class,
    parentProducer = { requireActivity() },
    create = create,
)

inline fun <reified F : DaggerSubcomponentFactory, T : Activity> Fragment.parentFragmentComponent(
    noinline create: (F) -> DaggerComponent<T>,
): ReadOnlyProperty<T, DaggerComponent<T>> = createComponentDelegate(
    factoryClass = F::class,
    parentProducer = { requireParentFragment() },
    create = create,
)
