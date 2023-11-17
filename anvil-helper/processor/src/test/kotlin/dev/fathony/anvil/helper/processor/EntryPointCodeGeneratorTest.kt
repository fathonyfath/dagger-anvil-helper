package dev.fathony.anvil.helper.processor

import com.squareup.anvil.annotations.ExperimentalAnvilApi
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.junit.Test
import javax.inject.Singleton

@Suppress("UnnecessaryOptInAnnotation")
@OptIn(ExperimentalCompilerApi::class, ExperimentalAnvilApi::class)
class EntryPointCodeGeneratorTest {

    @Test
    fun testEntryPointCodeGenerator() {
        val packageName = "dev.fathony.anvil.helper.sample"
        val className = "SampleComponent"

        val content = createSampleComponentKotlinClass(
            packageName = packageName,
            className = className,
            scope = Singleton::class
        )

        compile(
            content,
            codeGenerators = listOf(EntryPointCodeGenerator())
        ) {
            val generatedPackage = "${packageName}."
            val generatedClassName = className.plus(GeneratedEntryPointSuffix)
            val generatedClass =
                classLoader.loadClass("${generatedPackage}${generatedClassName}")

        }
    }
}