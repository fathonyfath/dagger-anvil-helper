package dev.fathony.daggerapply

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

// Gradle's map {} APIs sometimes are interpreted by Kotlin to be non-null only but legally allow null returns. This
// abuses kotlin contracts to safe cast without a null check.
// https://github.com/gradle/gradle/issues/12388
internal fun <T> sneakyNull(value: T? = null): T {
    markAsNonNullForGradle(value)
    return value
}

// Gradle's map {} APIs sometimes are interpreted by Kotlin to be non-null only but legally allow null returns. This
// abuses kotlin contracts to safe cast without a null check.
@OptIn(ExperimentalContracts::class)
internal fun <T> markAsNonNullForGradle(value: T?) {
    contract {
        returns() implies (value != null)
    }
}

internal fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

internal fun <T> DependencyHandler.implementation(provider: Provider<T?>) =
    addProvider("implementation", provider)

internal fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

internal fun <T> DependencyHandler.kapt(provider: Provider<T?>) =
    addProvider("kapt", provider)

internal inline fun <S, R> Provider<S?>.mapKt(crossinline transformer: (S?) -> R?): Provider<R?> {
    return map { sneakyNull(transformer(it)) }
}

internal inline fun <S, R> Provider<S?>.flatMapKt(crossinline transformer: (S?) -> Provider<R?>): Provider<R?> {
    return flatMap { sneakyNull(transformer(it)) }
}
