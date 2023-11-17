package dev.fathony.anvil.helper.processor

import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.api.CodeGenerator
import com.squareup.anvil.compiler.internal.testing.compileAnvil
import com.tschuchort.compiletesting.KotlinCompilation
import org.intellij.lang.annotations.Language
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi

@OptIn(ExperimentalCompilerApi::class, ExperimentalAnvilApi::class)
internal fun compile(
    @Language("kotlin") vararg sources: String,
    previousCompilationResult: KotlinCompilation.Result? = null,
    enableDebuggerAnnotationProcessor: Boolean = false,
    codeGenerators: List<CodeGenerator> = emptyList(),
    allWarningAsErrors: Boolean = false,
    moduleName: String? = null,
    block: KotlinCompilation.Result.() -> Unit = {}
): KotlinCompilation.Result = compileAnvil(
    sources = sources,
    allWarningsAsErrors = allWarningAsErrors,
    previousCompilationResult = previousCompilationResult,
    enableDaggerAnnotationProcessor = enableDebuggerAnnotationProcessor,
    codeGenerators = codeGenerators,
    moduleName = moduleName,
    block = block
)
