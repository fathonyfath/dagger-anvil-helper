package dev.fathony.anvil.helper.processor

import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.internal.reference.AnnotationReference
import com.squareup.anvil.compiler.internal.reference.AnvilCompilationExceptionClassReference
import com.squareup.anvil.compiler.internal.reference.ClassReference
import com.squareup.anvil.compiler.internal.reference.Visibility
import com.squareup.anvil.compiler.internal.reference.argumentAt

@OptIn(ExperimentalAnvilApi::class)
internal inline fun ClassReference.checkClassIsPublic(message: () -> String) {
    if (visibility() != Visibility.PUBLIC) {
        throw AnvilCompilationExceptionClassReference(
            classReference = this,
            message = message.invoke()
        )
    }
}

@OptIn(ExperimentalAnvilApi::class)
internal fun ClassReference.checkDefineEntryPointRequirement() {
    val daggerScopeCount = annotations.count { it.isDaggerScope() }
    if (daggerScopeCount == 0) {
        throw AnvilCompilationExceptionClassReference(
            message = "Classes annotated with @${DefineEntryPointFqName.shortName()} have to be annotated with a @Scope.",
            classReference = this
        )
    }
    if (daggerScopeCount > 1) {
        throw AnvilCompilationExceptionClassReference(
            message = "Classes annotated with @${DefineEntryPointFqName.shortName()} may not use more " +
                    "than one @Scope.",
            classReference = this
        )
    }

    val annotationCount = annotations.count { it.fqName == DefineEntryPointFqName }
    if (annotationCount > 1) {
        throw AnvilCompilationExceptionClassReference(
            message = "Classes may not use more than one @${DefineEntryPointFqName.shortName()} annotation",
            classReference = this
        )
    }
}

@OptIn(ExperimentalAnvilApi::class)
internal fun AnnotationReference.parentScopeOrNull(): ClassReference? =
    argumentAt("parentScope", 1)?.value()
